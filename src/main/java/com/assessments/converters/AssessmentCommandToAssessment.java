package com.assessments.converters;

import java.util.Objects;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.assessments.commands.AssessmentCommand;
import com.assessments.domain.Assessment;

@Component
public class AssessmentCommandToAssessment implements Converter<AssessmentCommand, Assessment> {

    @Override
    public Assessment convert(AssessmentCommand source) {
        if (Objects.isNull(source)) {
            return null;
        }

        final Assessment assessment = new Assessment();
        assessment.setMaxTime(source.getMaxTime());
        assessment.setNumberQuestions(source.getNumberQuestions());
        assessment.setType(source.getType());
        assessment.setUser(source.getUser());

        return assessment;
    }

}
