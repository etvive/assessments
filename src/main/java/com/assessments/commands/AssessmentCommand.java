package com.assessments.commands;

import com.assessments.domain.AssessmentType;

import lombok.Data;

@Data
public class AssessmentCommand {

    private Integer maxTime;
    private Integer numberQuestions;
    private String user;
    private AssessmentType type;
}
