package com.assessments.commands;

import java.util.List;

import com.assessments.domain.Answer;
import com.assessments.domain.QuestionType;
import com.assessments.domain.UserAnswer;

import lombok.Data;

@Data
public class QuestionCommand {

    private String question;
    private List<Answer> answers;
    private QuestionType type;
    private String message;
    private List<UserAnswer> userAnswers;

}
