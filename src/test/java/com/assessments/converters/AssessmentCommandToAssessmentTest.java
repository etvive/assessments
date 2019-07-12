package com.assessments.converters;

import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import com.assessments.commands.AssessmentCommand;
import com.assessments.domain.Assessment;
import com.assessments.domain.AssessmentType;

public class AssessmentCommandToAssessmentTest {

    AssessmentCommandToAssessment converter;
    AssessmentCommand assessmentCommand;

    @Before
    public void setUp() throws Exception {
        converter = new AssessmentCommandToAssessment();
        assessmentCommand = new AssessmentCommand();

        assessmentCommand.setMaxTime(60);
        assessmentCommand.setNumberQuestions(80);
        assessmentCommand.setType(AssessmentType.SM);
        assessmentCommand.setUser("@etvive");
    }

    @Test
    public void test() {
        Assessment assessment = converter.convert(assessmentCommand);
        assertTrue(Objects.equals(assessment.getMaxTime(), assessmentCommand.getMaxTime()) && 
                Objects.equals(assessment.getNumberQuestions(), assessmentCommand.getNumberQuestions()) &&
                Objects.equals(assessment.getUser(), assessmentCommand.getUser()) &&
                Objects.equals(assessment.getType(), assessmentCommand.getType()));

        assertTrue(Objects.equals(assessment.getCurrentQuestion(), 0));
    }

}
