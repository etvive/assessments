package com.assessments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.assessments.commands.AssessmentCommand;
import com.assessments.converters.AssessmentCommandToAssessment;
import com.assessments.domain.Assessment;
import com.assessments.repositories.AssessmentRepository;

public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    AssessmentRepository assessmentRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    AssessmentCommandToAssessment converter;

    @Override
    public Assessment createAssessment(AssessmentCommand assessmentCommand) {
        Assessment assessment = converter.convert(assessmentCommand);
        assessment.setQuestions(questionService.getRandomQuestions(assessment.getNumberQuestions()));
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
