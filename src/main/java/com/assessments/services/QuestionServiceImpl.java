package com.assessments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.assessments.domain.Question;
import com.assessments.repositories.QuestionRepository;

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question getQuestion(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question setQuestion(Question q) {
        return questionRepository.save(q);
    }

}
