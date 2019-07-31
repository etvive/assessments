package com.assessments.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessments.commands.AssessmentCommand;
import com.assessments.converters.AssessmentCommandToAssessment;
import com.assessments.converters.QuestionToQuestionRelational;
import com.assessments.domain.Assessment;
import com.assessments.domain.Question;
import com.assessments.domain.QuestionRelational;
import com.assessments.repositories.AssessmentRepository;
import com.assessments.repositories.QuestionRelationalRepository;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    AssessmentRepository assessmentRepository;
    
    @Autowired
    QuestionRelationalRepository questionRelationalRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    AssessmentCommandToAssessment converter;
    
    @Autowired
    QuestionToQuestionRelational converterMongoRelational;

    @Override
    public Assessment createAssessment(AssessmentCommand assessmentCommand) {
        Assessment assessment = converter.convert(assessmentCommand);
        assessmentRepository.save(assessment);
        List<Question> mongoQuestionList = questionService.getRandomQuestions(assessment.getNumberQuestions());
        // we have to obtain the questions from mongo and convert in relational questions
        for (Question question : mongoQuestionList) {
        	QuestionRelational questionRelational = converterMongoRelational.convert(question);
        	questionRelational.setAssessment(assessment);
            questionRelationalRepository.save(questionRelational);
        }
        
        //TODO add the questions to the assessment
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
        return assessmentRepository.findByUserAssessment(user);
    }

    @Override
    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

}
