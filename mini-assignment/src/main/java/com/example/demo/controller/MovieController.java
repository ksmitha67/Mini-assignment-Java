package com.example.demo.controller;

import com.example.demo.exception.ApplicationException;
import com.example.demo.models.Movie;
import com.example.demo.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;



@RestController
@RequestMapping("/movie")

public class MovieController {
	private static Logger logger = LoggerFactory.getLogger(StorageService.class);
	
	private HttpHeaders setTimeHeader(Instant startTime) {
        long time = Duration.between(startTime, Instant.now()).toMillis();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-TIME-TO-EXECUTE", String.valueOf(time));
        return responseHeaders;
    }
	
	@Autowired
	
    private StorageService movieService;
	
	@GetMapping("/upload")
    public ResponseEntity<List<Movie>> load() {
        logger.info("Uploading movie from CSV to Database " + this.getClass().getName());
        Instant start = Instant.now();
        return new ResponseEntity<>(movieService.loadData(),setTimeHeader(start), HttpStatus.OK);
    }

}
