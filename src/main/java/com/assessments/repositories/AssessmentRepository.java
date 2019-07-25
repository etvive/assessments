package com.assessments.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assessments.domain.Assessment;

public interface AssessmentRepository extends CrudRepository<Assessment, Long> {

    List<Assessment> findByUserAssessment(String userAssessment);
}
