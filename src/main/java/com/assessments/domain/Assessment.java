package com.assessments.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "assessment")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer maxTime;
    private List<Question> questions;
    private Integer numberQuestions;
    private Integer qualification;
    private String user;
    private AssessmentType type;
    private Integer currentQuestion = 0;
}
