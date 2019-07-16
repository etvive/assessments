package com.assessments.commands;

import java.util.List;

import com.assessments.domain.AssessmentType;
import com.assessments.domain.Question;

import lombok.Data;

@Data
public class AssessmentCommand {

    private Integer maxTime;
    private Integer numberQuestions;
    private String user;
    private AssessmentType type;
    private List<Question> questions;
}
