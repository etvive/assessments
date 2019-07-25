package com.assessments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessments.commands.AssessmentCommand;
import com.assessments.domain.Assessment;
import com.assessments.services.AssessmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Api(value="assessment", description="Manage the assessments")
@RestController
@RequestMapping(value = "/assessment")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @ApiOperation(value = "Get all the assessments in the DB",response = List.class)
    @GetMapping("/all")
    public ResponseEntity<List<Assessment>> getAllAssessments(){
        List<Assessment> assessments = assessmentService.getAssessments();
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    @ApiOperation(value = "Return a 200 OK status value")
    @GetMapping("/health")
    public ResponseEntity<Void> getHealth(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Get the assessment by id",response = Assessment.class)
    @GetMapping("/{id}")
    public ResponseEntity<Assessment> getAssessment(@PathVariable(value = "id") Long id){
    	Assessment assessment = assessmentService.getAssessment(id);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Get all the assessments in the DB by user",response = List.class)
    @GetMapping("/user/{user}")
    public ResponseEntity<List<Assessment>> getAllAssessmentsByUser(@PathVariable(value = "user") String user){
        List<Assessment> assessments = assessmentService.getAssessments(user);
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    @ApiOperation(value = "Create an assessment in the DB",response = Assessment.class)
    @PostMapping(path={"", "/"}, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Assessment> createAssessment(@RequestBody AssessmentCommand assessmentCommand) {
    	Assessment assessment = assessmentService.createAssessment(assessmentCommand);
        return new ResponseEntity<>(assessment, HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Update an assessment in the DB",response = Assessment.class)
    @PutMapping(path={"", "/"}, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Assessment> updateAssessment(@RequestBody Assessment assessment) {
    	Assessment assessmentSaved = assessmentService.saveAssessment(assessment);
        return new ResponseEntity<>(assessmentSaved, HttpStatus.CREATED);
    }

    //TODO add exceptionHandlers

}
