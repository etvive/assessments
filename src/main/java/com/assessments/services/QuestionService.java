package com.assessments.services;

import java.util.List;

import com.assessments.domain.Question;

public interface QuestionService {

    Question getQuestion(Long id);

    List<Question> getQuestions();

    Question setQuestion(Question q);
}
