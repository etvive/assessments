package com.assessments.domain;

import lombok.Data;

@Data
//@Entity
//@Table(name = "answer")
public class Answer {

    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // The text associate to the answer
    private String answer;
    // true if the answer is the correct option
    private boolean correct;
    // Necesary if use the drag and drop option
    private String value;

}
