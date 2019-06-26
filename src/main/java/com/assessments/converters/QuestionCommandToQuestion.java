package com.assessments.converters;

import java.util.Objects;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.assessments.commands.QuestionCommand;
import com.assessments.domain.Question;

@Component
public class QuestionCommandToQuestion implements Converter<QuestionCommand, Question> {

    @Override
    public Question convert(QuestionCommand source) {
        if (Objects.isNull(source)) {
            return null;
        }

        final Question question = new Question();
        question.setMessage(source.getMessage());
        question.setQuestion(source.getQuestion());
        question.setAnswers(source.getAnswers());
        question.setType(source.getType());

        return question;
    }

}
