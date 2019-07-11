package com.assessments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessments.commands.QuestionCommand;
import com.assessments.domain.Question;
import com.assessments.services.QuestionService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Api(value="contact", description="Manage the contacts")
@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //TODO change the result to redirect to a url and add the model in the Model object

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> questions = questionService.getQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<Void> getHealth(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable(value = "id") String id){
        Question question = questionService.getQuestion(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/random/{number}")
    public ResponseEntity<List<Question>> getRandomQuestions(@PathVariable(value = "number") int number){
        List<Question> questions = questionService.getRandomQuestions(number);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping(path={"", "/"}, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> questionPost(@RequestBody QuestionCommand questionCommand) {
        Question question = questionService.setQuestion(questionCommand);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    //TODO add exceptionHandlers

}
