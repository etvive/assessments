package com.assessments.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import com.assessments.domain.Question;

public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public QuestionRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Question> searchRandomQuestion(int number) {
        CustomAggregationOperation customSampleOperation = new CustomAggregationOperation(number);
        TypedAggregation<Question> typedAggr = Aggregation.newAggregation(Question.class, customSampleOperation);

        AggregationResults<Question> aggregationResults = mongoTemplate.aggregate(typedAggr, Question.class);
        return aggregationResults.getMappedResults();
    }

}


