package com.example.demo.repository;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.demo.models.Movie;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MovieRepository {
	
	private static Logger logger = LoggerFactory.getLogger(MovieRepository.class);

    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    
    public Movie save(Movie movie) {
        logger.info("save movie " + this.getClass().getName());
        dynamoDBMapper.save(movie);
        return movie;
    }

    public List<Movie> saveAll(List<Movie> movieList) {
        logger.info("Uploading movie from CSV to Database " + this.getClass().getName());
        dynamoDBMapper.batchSave(movieList);
        return movieList;
    }


}
