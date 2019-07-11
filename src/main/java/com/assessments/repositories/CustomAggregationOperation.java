package com.assessments.repositories;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

import com.mongodb.BasicDBObject;

public class CustomAggregationOperation implements AggregationOperation {

    private int size;
    public CustomAggregationOperation(int size){
        this.size = size;   
    }

    @Override
    public Document toDocument(AggregationOperationContext context) {
        return new Document("$sample", new BasicDBObject("size", size));
    }
}
