package com.assessments.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//TODO revisar la entidad
@Data
@Entity
@Table(name = "answer")
public class AnswerRelational {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // The text associate to the answer
    private String answer;
    // true if the answer is the correct option
    private boolean correct;
    // Necesary if use the drag and drop option
    private String value;

}
