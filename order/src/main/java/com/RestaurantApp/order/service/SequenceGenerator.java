package com.RestaurantApp.order.service;

import com.RestaurantApp.order.entity.Sequence;
import com.RestaurantApp.order.exception.SequenceGenerationException;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGenerator {

    private static final Logger logger = LoggerFactory.getLogger(SequenceGenerator.class);

    private final MongoOperations mongoOperations;

    @Value("${app.sequence.name:sequence}")
    private String sequenceName;
    
    public SequenceGenerator(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public int generateNextSequence() {
        try {

            Sequence counter = mongoOperations.findAndModify(
                query(where("_id").is(sequenceName)),
                new Update().inc("sequence", 1),
                options().returnNew(true).upsert(true),
                Sequence.class
            );
            
            if(counter == null) {
                logger.error("Sequence document not found for id: {}", sequenceName);
                throw new SequenceGenerationException("Failed to generate sequence for :" + sequenceName);
            }
            int nextValue = counter.getSequence();
            if(nextValue == Integer.MAX_VALUE) {
                logger.error("Sequence overflow detected for {}", sequenceName);
                throw new SequenceGenerationException("Sequence limit exceded");
            }
            logger.info("Generated next sequence value: {} for {}", nextValue, sequenceName);
            return nextValue;

        } catch (MongoException e) {
            logger.error("Error generating next sequence from MongoDB", e);
            throw new SequenceGenerationException("Failed to generate sequence number", e);
        }
    }
}
