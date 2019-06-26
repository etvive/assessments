package com.assessments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.assessments.commands.QuestionCommand;
import com.assessments.converters.QuestionCommandToQuestion;
import com.assessments.domain.Question;
import com.assessments.repositories.QuestionRepository;

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionCommandToQuestion questionCommandToQuestion;

    @Override
    public Question getQuestion(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question setQuestion(QuestionCommand q) {
        Question question = questionCommandToQuestion.convert(q);
        return questionRepository.save(question);
    }

}
