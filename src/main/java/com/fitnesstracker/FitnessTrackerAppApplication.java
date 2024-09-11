package com.fitnesstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableJpaAuditing
public class FitnessTrackerAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =SpringApplication.run(FitnessTrackerAppApplication.class, args);
	}
	
	
	

}
