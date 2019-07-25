package com.assessments.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.assessments.commands.AssessmentCommand;
import com.assessments.converters.AssessmentCommandToAssessment;
import com.assessments.converters.QuestionToQuestionRelational;
import com.assessments.domain.Assessment;
import com.assessments.domain.AssessmentType;
import com.assessments.domain.Question;
import com.assessments.domain.QuestionRelational;
import com.assessments.repositories.AssessmentRepository;

@ActiveProfiles("test")
public class AssessmentServiceImplTest {

    @InjectMocks
    AssessmentServiceImpl service;

    @Mock
    private AssessmentRepository repository;

    @Mock
    AssessmentCommandToAssessment converter;
    
    @Mock
    QuestionToQuestionRelational converterMongoRelational;

    @Mock
    QuestionService questionService;

    private AssessmentCommand getAssessmentCommand() {
        AssessmentCommand assessmentCommand = new AssessmentCommand();
        assessmentCommand.setMaxTime(60);
        assessmentCommand.setNumberQuestions(2);
        assessmentCommand.setType(AssessmentType.SM);
        assessmentCommand.setUser("@etvive");
        return assessmentCommand;
    }

    private List<Assessment> getAssessmentsList() {
        List<Assessment> list = new ArrayList<>();
        list.add(getTestAssessment());
        return list;
    }

    private Assessment getTestAssessment() {
        Assessment assessment = new Assessment();
        assessment.setMaxTime(60);
        assessment.setNumberQuestions(2);
        assessment.setType(AssessmentType.SM);
        assessment.setUserAssessment("@etvive");
        //assessment.setQuestions(getTestQuestions());
        assessment.setQuestions(getTestRelationalQuestions());
        return assessment;
    }
    
    private Assessment getSaveTestAssessment() {
        Assessment assessment = new Assessment();
        assessment.setId(1L);
        assessment.setMaxTime(60);
        assessment.setNumberQuestions(2);
        assessment.setType(AssessmentType.SM);
        assessment.setUserAssessment("@etvive");
        //assessment.setQuestions(getTestQuestions());
        assessment.setQuestions(getTestRelationalQuestions());
        return assessment;
    }

    private List<Question> getTestQuestions() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setId("1001");
        question.setQuestion("What is your name?");
        questions.add(question);
        Question question2 = new Question();
        question2.setId("1002");
        question2.setQuestion("Where do you live?");
        questions.add(question2);

        return questions;
    }
    
    private List<QuestionRelational> getTestRelationalQuestions() {
        List<QuestionRelational> questions = new ArrayList<>();
        QuestionRelational question = new QuestionRelational();
        question.setId("1001");
        questions.add(question);
        QuestionRelational question2 = new QuestionRelational();
        question2.setId("1002");
        questions.add(question2);

        return questions;
    }
    
    private QuestionRelational getRelationalQuestion() {
        QuestionRelational question = new QuestionRelational();
        question.setId("1001");
        return question;
    }
    
        @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAssessment() {
        when(questionService.getRandomQuestions(Mockito.anyInt())).thenReturn(getTestQuestions());
        when(repository.save(Mockito.any())).thenReturn(getTestAssessment());
        when(converter.convert(Mockito.any())).thenReturn(getTestAssessment());
        when(converterMongoRelational.convert(Mockito.any())).thenReturn(getRelationalQuestion());

        Assessment assessment = service.createAssessment(getAssessmentCommand());

        assertTrue(getAssessmentCommand().getNumberQuestions().intValue() == assessment.getQuestions().size());
    }

    @Test
    public void testGetAssessment() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(getTestAssessment()));
        Assessment assessment = service.getAssessment(1L);
        assertTrue(assessment.getUserAssessment().equals("@etvive"));
    }

    @Test
    public void testGetAssessments() {
        when(repository.findAll()).thenReturn(getAssessmentsList());
        List<Assessment> assessments = service.getAssessments();
        assertTrue(assessments.size() == 1);
    }

    @Test
    public void testGetAssessmentsByUser() {
        when(repository.findByUserAssessment(Mockito.anyString())).thenReturn(getAssessmentsList());
        List<Assessment> assessments = service.getAssessments("@etvive");
        assertTrue(assessments.size() == 1);
        assertTrue(assessments.get(0).getUserAssessment().equals("@etvive"));
    }
    
    @Test
    public void testSaveAssessment() {
        Assessment initialAssessment = getSaveTestAssessment();
        initialAssessment.setMaxTime(initialAssessment.getMaxTime()+10);
        when(repository.save(Mockito.any())).thenReturn(initialAssessment);
        Assessment assessment = service.saveAssessment(initialAssessment);

        assertTrue(getSaveTestAssessment().getMaxTime() < assessment.getMaxTime());
    }
}
