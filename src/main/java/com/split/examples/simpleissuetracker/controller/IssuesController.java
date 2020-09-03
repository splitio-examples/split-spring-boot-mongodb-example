package com.split.examples.simpleissuetracker.controller;

import com.split.examples.simpleissuetracker.model.Issue;
import com.split.examples.simpleissuetracker.repository.IssueRepository;
import io.split.client.SplitClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class IssuesController {

    private IssueRepository repository;
    private SplitClient splitClient;

    public IssuesController(IssueRepository repository, SplitClient splitClient) {
        this.repository = repository;
        this.splitClient = splitClient;
    }

    @GetMapping("/issues")
    public String getAllIssues(Model model) {
        String treatment = splitClient.getTreatment("ANONYMOUS_USER", "SORT_ISSUES");

        Iterable<Issue> issues = repository.findAll();
        if (treatment.equals("on")) {
            issues = StreamSupport
                .stream(issues.spliterator(), false)
                .sorted((issue1, issue2) -> Long.compare(issue2.getSeverity(), issue1.getSeverity()))
                .collect(Collectors.toList());
        } else if (treatment.equals("off")) {
            // nothing to do
        } else {
            throw new RuntimeException("Couldn't retrieve treatment from Split.");
        }

        model.addAttribute("issues", issues);
        return "list-issues";
    }
}
