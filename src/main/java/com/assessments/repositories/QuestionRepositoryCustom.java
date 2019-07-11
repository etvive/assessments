package com.assessments.repositories;

import java.util.List;

import com.assessments.domain.Question;

public interface QuestionRepositoryCustom {
    List<Question> searchRandomQuestion(int number);
}
