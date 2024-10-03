package com.cs5106.movieMuseum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieMuseumApplication{

	private static final Logger logger = LoggerFactory.getLogger(MovieMuseumApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(MovieMuseumApplication.class, args);
		logger.info("Application started successfully.");
	}

}
