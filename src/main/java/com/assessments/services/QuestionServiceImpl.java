package com.assessments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessments.commands.QuestionCommand;
import com.assessments.converters.QuestionCommandToQuestion;
import com.assessments.domain.Question;
import com.assessments.repositories.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionCommandToQuestion questionCommandToQuestion;

    @Override
    public Question getQuestion(String id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getRandomQuestions(int number) {
        return questionRepository.searchRandomQuestion(number);
    }

    @Override
    public Question setQuestion(QuestionCommand q) {
        Question question = questionCommandToQuestion.convert(q);
        return questionRepository.save(question);
    }

}
