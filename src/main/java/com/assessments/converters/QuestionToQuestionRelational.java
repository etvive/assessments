package com.assessments.converters;

import java.util.Objects;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.assessments.domain.Question;
import com.assessments.domain.QuestionRelational;

@Component
public class QuestionToQuestionRelational implements Converter<Question, QuestionRelational> {

    @Override
    public QuestionRelational convert(Question source) {
        if (Objects.isNull(source)) {
            return null;
        }

        final QuestionRelational questionRelational = new QuestionRelational();
        questionRelational.setIdMongo(source.getId());

        return questionRelational;
    }

}
