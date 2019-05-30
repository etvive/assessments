package com.assessments.domain;

import java.util.List;

import lombok.Data;

@Data
public class Question {

    private String id;
    private String question;
    private List<Answer> answers;
    private QuestionType type;
    private Integer points;
    private String message;
    private List<UserAnswer> userAnswers;

}
