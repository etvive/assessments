package com.assessments.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.assessments.domain.Answer;
import com.assessments.domain.Question;
import com.assessments.domain.QuestionType;
import com.assessments.repositories.QuestionRepository;

@ActiveProfiles("test")
public class QuestionServiceImplTest {

    @InjectMocks
    QuestionServiceImpl service;

    @Mock
    private QuestionRepository repository;

    private Question getInsertTestQuestion() {
        Question question = new Question();
        question.setQuestion("It is mandatory that the product increment be released to production at the end of each Sprint.");
        question.setType(QuestionType.SIMPLE);
        question.setPoints(1);
        question.setMessage("The product increment should be usable and releasable at the end of every Sprint, but it does not have to be released.");

        List<Answer> listAnswer = new ArrayList<>();
        Answer answer1 = new Answer();
        answer1.setId(10001L);
        answer1.setCorrect(false);
        answer1.setValue("True");
        listAnswer.add(answer1);

        Answer answer2 = new Answer();
        answer2.setId(10002L);
        answer2.setCorrect(true);
        answer2.setValue("False");
        listAnswer.add(answer2);

        question.setAnswers(listAnswer);
        return question;
    }

    private List<Question> getQuestions(){
        List<Question> list = new ArrayList<>();
        list.add(getTestQuestion());
        return list;
    }

    private Question getTestQuestion() {
        Question question = getInsertTestQuestion();
        question.setId(1001L);

        return question;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetQuestion() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(getTestQuestion()));

        Question question = service.getQuestion(1001L);

        assertTrue(question.getId().equals(1001L));
        assertTrue(question.getType().equals(QuestionType.SIMPLE));
    }

    @Test
    public void testGetQuestions() {
        when(repository.findAll()).thenReturn(getQuestions());

        List<Question> questionList = service.getQuestions();

        assertTrue(questionList.size()== 1);
    }

    @Test
    public void testSetQuestion(){
        when(repository.save(Mockito.any())).thenReturn(getTestQuestion());
        Question question = service.setQuestion(getInsertTestQuestion());

        assertTrue(Objects.nonNull(question));
        assertTrue(question.getId().equals(1001L));
    }

}
