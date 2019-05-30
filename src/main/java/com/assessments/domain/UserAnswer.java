package com.assessments.domain;

import lombok.Data;

@Data
public class UserAnswer {

    private String id;
    // Relation between userAnswer and answer, maybe we can use BBDD relations
    private String answerId;
    // True if the user mark this option
    private boolean userMark;
    // Necesary if use the drag and drop option
    private String value;
    // true if the answer is the correct option
    private boolean correct;

}
