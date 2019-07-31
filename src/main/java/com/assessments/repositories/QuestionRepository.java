package com.assessments.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assessments.domain.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String>, QuestionRepositoryCustom {

}
