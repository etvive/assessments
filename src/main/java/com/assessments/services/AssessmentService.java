package com.assessments.services;

import java.util.List;

import com.assessments.commands.AssessmentCommand;
import com.assessments.domain.Assessment;

public interface AssessmentService {

    Assessment createAssessment(AssessmentCommand assessmentCommand);

    Assessment getAssessment(Long id);

    List<Assessment> getAssessments();

    List<Assessment> getAssessments(String user);

    Assessment saveAssessment(Assessment assessment);
}
