package com.assessments.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


//TODO revisar entidad
@Data
@Entity
@Table(name = "question")
public class QuestionRelational {

    @Id
    private String id;
    private String idMongo;
    //private List<UserAnswer> userAnswers;
    @ManyToOne
    @JoinColumn(name = "assessment_id")
    // another option
    //@JoinTable(name = "user_locations", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Assessment assessment;

}
