package com.split.examples.simpleissuetracker.repository;

import com.split.examples.simpleissuetracker.model.Issue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends MongoRepository<Issue, String> {}
