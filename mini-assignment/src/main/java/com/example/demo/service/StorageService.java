package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.demo.exception.ApplicationException;
import com.example.demo.models.*;
import com.example.demo.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service

public class StorageService {

	private static Logger logger = LoggerFactory.getLogger(StorageService.class);
	
	 @Autowired
	    private MovieRepository movieRepository;
	 
	 public List<Movie> loadData() {
	        List<Movie> movieList = new ArrayList<>();

	        logger.info("Loading Process Started " + this.getClass().getName());
	        try {
	            Movie movie;
	            logger.info("Reading the CSV file from directory");
	            BufferedReader br = new BufferedReader(new FileReader("C:/Users/ksmitha/Documents/movies.csv"));
	            String line = "";
	            while ((line = br.readLine()) != null)   //returns a Boolean value
	            {
	                logger.info("Splitting the records based on comma");

	                String[] movie_row = line.split(",", -1);// use comma as separator
	                movie = new Movie(movie_row[1], movie_row[3], movie_row[9], movie_row[16], movie_row[20], movie_row[5], movie_row[6], movie_row[7], movie_row[8]);
	                movieList.add(movie);
	            }

	            logger.info("Data Mapped successfully from csv to Model");
	            return movieRepository.saveAll(movieList);
//	            return ResponseEntity.status(HttpStatus.OK).body("Movies table updated in DB");
	        } catch (FileNotFoundException e) {
	            throw new ApplicationException(e.getMessage());
	        } catch (IOException e) {
	            throw new ApplicationException(e.getMessage());
	        } catch (Exception e){
	            throw new ApplicationException(e.getMessage());
	        }
	    }
}
