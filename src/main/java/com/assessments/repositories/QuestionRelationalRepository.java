package com.assessments.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assessments.domain.Assessment;
import com.assessments.domain.QuestionRelational;

@Repository
public interface QuestionRelationalRepository extends CrudRepository<QuestionRelational, Long> {

	List<QuestionRelational> findByAssessment(Assessment assessment);
}
