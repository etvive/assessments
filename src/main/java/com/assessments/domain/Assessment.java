package com.assessments.domain;

import java.util.List;

import lombok.Data;

@Data
public class Assessment {

    private Integer maxTime;
    private List<Question> questions;
    private Integer numberQuestions;
    private Integer qualification;
    private String user;
    private AssessmentType type;
}
