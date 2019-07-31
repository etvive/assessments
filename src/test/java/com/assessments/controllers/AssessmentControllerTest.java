package com.assessments.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.assessments.commands.AssessmentCommand;
import com.assessments.commands.QuestionCommand;
import com.assessments.converters.AssessmentCommandToAssessment;
import com.assessments.converters.QuestionCommandToQuestion;
import com.assessments.domain.Answer;
import com.assessments.domain.Assessment;
import com.assessments.domain.AssessmentType;
import com.assessments.domain.Question;
import com.assessments.domain.QuestionRelational;
import com.assessments.domain.QuestionType;
import com.assessments.repositories.AssessmentRepository;
import com.assessments.repositories.QuestionRepository;
import com.assessments.services.AssessmentServiceImpl;
import com.assessments.services.QuestionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AssessmentControllerTest {

    @Mock
    AssessmentServiceImpl service;

    @Mock
    Model model;

    @InjectMocks
    AssessmentController controller;

    @Mock
    private AssessmentRepository repository;

    @Mock
    private AssessmentCommandToAssessment assessmentCommandToAssessment;

    MockMvc mockMvc;

    private AssessmentCommand getInsertTestAssessmentCommand() {
    	AssessmentCommand assessment = new AssessmentCommand();
    	
    	assessment.setMaxTime(20);
    	assessment.setNumberQuestions(80);
    	assessment.setQuestions(getQuestions());
    	assessment.setType(AssessmentType.SM);
    	assessment.setUser("etvive");
    	
    	return assessment;
    }
    
    private Assessment getInsertTestAssessment() {
    	Assessment assessment = new Assessment();
    	
    	assessment.setMaxTime(20);
    	assessment.setNumberQuestions(80);
    	assessment.setType(AssessmentType.SM);
    	assessment.setCurrentQuestion(1);
    	assessment.setId(1000L);
    	assessment.setQualification(100);
    	assessment.setUserAssessment("etvive");
    	assessment.setQuestions(getRelationalQuestions());
    	
    	return assessment;
    }

    private Set<QuestionRelational> getRelationalQuestions() {
		Set<QuestionRelational> questions = new HashSet<>();
    	
		QuestionRelational question = new QuestionRelational();
        question.setAssessment(new Assessment());
        question.setId("100");
        question.setIdMongo("1001");

        questions.add(question);
        return questions;
	}
    
	private List<Question> getQuestions() {
		List<Question> questions = new ArrayList<>();
    	
    	Question question = new Question();
        question.setQuestion("It is mandatory that the product increment be released to production at the end of each Sprint.");
        question.setType(QuestionType.SIMPLE);
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
        
        questions.add(question);
        return questions;
	}

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllAssessments() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/assessment/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new ArrayList<Assessment>()))
                ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetHealth() throws Exception {

        mockMvc.perform(get("/assessment/health"))
        .andExpect(status().isOk());
    }

    @Test
    public void testGetAssessmentById() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/assessment/" + 1000L)
                .contentType(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    
    @Test
    public void testGetAssessmentByUser() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/assessment/user/" + "etvive")
                .contentType(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testCreateAssessment() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(post("/assessment/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(getInsertTestAssessmentCommand()))
                ).andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
    
    @Test
    public void testSaveAssessment() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(put("/assessment/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(getInsertTestAssessment()))
                ).andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private String toJson(Object caseValue) {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = Obj.writeValueAsString(caseValue);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr; 
    }


}
