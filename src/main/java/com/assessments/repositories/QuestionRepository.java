package com.assessments.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assessments.domain.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {

}
