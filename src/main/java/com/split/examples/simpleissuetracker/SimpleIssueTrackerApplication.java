package com.split.examples.simpleissuetracker;

import com.split.examples.simpleissuetracker.repository.IssueRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = IssueRepository.class)
@SpringBootApplication
public class SimpleIssueTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleIssueTrackerApplication.class, args);
	}

}