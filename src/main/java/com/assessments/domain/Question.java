package com.assessments.domain;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
//@Entity
//@Table(name = "question")
@Document(collection = "questions")
public class Question {

    @Id
    private String id;
    private String question;
    private List<Answer> answers;
    private QuestionType type;
    private Integer points;
    private String message;
    //private List<UserAnswer> userAnswers;

}
