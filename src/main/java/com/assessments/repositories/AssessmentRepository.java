package com.assessments.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assessments.domain.Assessment;

@Repository
public interface AssessmentRepository extends CrudRepository<Assessment, Long> {

    List<Assessment> findByUserAssessment(String userAssessment);
}
