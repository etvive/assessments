package com.assessments.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.assessments.commands.AssessmentCommand;
import com.assessments.converters.AssessmentCommandToAssessment;
import com.assessments.converters.QuestionToQuestionRelational;
import com.assessments.domain.Assessment;
import com.assessments.domain.Question;
import com.assessments.domain.QuestionRelational;
import com.assessments.repositories.AssessmentRepository;

public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    AssessmentRepository assessmentRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    AssessmentCommandToAssessment converter;
    
    @Autowired
    QuestionToQuestionRelational converterMongoRelational;

    @Override
    public Assessment createAssessment(AssessmentCommand assessmentCommand) {
        Assessment assessment = converter.convert(assessmentCommand);
        //assessment.setQuestions(questionService.getRandomQuestions(assessment.getNumberQuestions()));
        List<Question> mongoQuestionList = questionService.getRandomQuestions(assessment.getNumberQuestions());
        // we have to obtain the questions from mongo and convert in relational questions
        //mongoQuestionList.stream().forEach(e -> converterMongoRelational.convert(e));
        List<QuestionRelational> listQuestionRelational = new ArrayList<>();
        for (Question question : mongoQuestionList) {
            listQuestionRelational.add(converterMongoRelational.convert(question));
        }
        assessment.setQuestions(listQuestionRelational);
        assessmentRepository.save(assessment);
        return assessment;
    }

    @Override
    public Assessment getAssessment(Long id) {
        return assessmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Assessment> getAssessments() {
        return (List<Assessment>) assessmentRepository.findAll();
    }

    @Override
    public List<Assessment> getAssessments(String user) {
        return assessmentRepository.findByUser(user);
    }

    @Override
    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

}
