package com.assessments.domain;

import lombok.Data;

@Data
//@Entity
//@Table(name = "user_answer")
public class UserAnswer {

    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long     id;
    // Relation between userAnswer and answer, maybe we can use BBDD relations
    private String answerId;
    // True if the user mark this option
    private boolean userMark;
    // Necesary if use the drag and drop option
    private String value;
    // true if the answer is the correct option
    private boolean correct;

}
