package com.assessments.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

//TODO en esta entidad es posible que no tengamos que mezclar datos de mongo con datos de postgresql
// habría que crear otro objeto intermedio que se alimente de ambas BBDD
// el atributo questions tiene que ser otro distinto que tenga solo los datos que vamos a guardar de las
// mismas en la entidad relacional, probablemente un objeto que guarde el id de la pregunta, si el usuario la ha acertado o no, el id del questionario, ...
// si vamos a guardar el assessment en mongodb habrá que generar otra entidad parecida

@Data
@Entity
@Table(name = "assessment")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer maxTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assessment")
    private Set<QuestionRelational> questions = new HashSet<>();
    private Integer numberQuestions;
    private Integer qualification;
    private String userAssessment;
    private AssessmentType type;
    private Integer currentQuestion = 0;
}
