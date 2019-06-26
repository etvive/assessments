package com.assessments.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.assessments.commands.QuestionCommand;
import com.assessments.converters.QuestionCommandToQuestion;
import com.assessments.domain.Answer;
import com.assessments.domain.Question;
import com.assessments.domain.QuestionType;
import com.assessments.repositories.QuestionRepository;
import com.assessments.services.QuestionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionControllerTest {

    @Mock
    QuestionServiceImpl service;

    @Mock
    Model model;

    @InjectMocks
    QuestionController controller;

    @Mock
    private QuestionRepository repository;

    @Mock
    private QuestionCommandToQuestion questionCommandToQuestion;

    MockMvc mockMvc;

    private QuestionCommand getInsertTestQuestionCommand() {
        QuestionCommand question = new QuestionCommand();
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
        return question;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllQuestions() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/question/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new ArrayList<Question>()))
                ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetHealth() throws Exception {

        mockMvc.perform(get("/question/health"))
        .andExpect(status().isOk());
    }

    @Test
    public void testGetQuestionById() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/question/" + 1000L)
                .contentType(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testPostQuestion() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(post("/question/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(getInsertTestQuestionCommand()))
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
