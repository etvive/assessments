package com.assessments.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.assessments.domain.Question;

@Repository
public interface QuestionRepositoryCustom {
    List<Question> searchRandomQuestion(int number);
}
