package com.assessments.services;

import java.util.List;

import com.assessments.commands.QuestionCommand;
import com.assessments.domain.Question;

public interface QuestionService {

    Question getQuestion(String id);

    List<Question> getQuestions();

    List<Question> getRandomQuestions(int number);

    Question setQuestion(QuestionCommand q);
}
