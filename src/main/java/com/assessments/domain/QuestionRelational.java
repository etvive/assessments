package com.assessments.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


//TODO revisar entidad
@Data
@Entity
@Table(name = "question")
@Proxy(lazy = false)
public class QuestionRelational {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String idMongo;
    //private List<UserAnswer> userAnswers;
    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

}
