package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueManager {
    private IssueRepository repository;

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> findAllOpen() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> findAllClosed() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (!issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByAuthor(Predicate<Issue> predicate) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue))
                result.add(issue);
        }
        return result;
    }

    public List<Issue> filterByLabel(Predicate<HashSet> equalLabel) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (equalLabel.test(issue.getLabel())) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getAssignee().equals(assignee) && issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> getOpenedIssue() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public void openById(int id) {
        repository.openById(id);
    }

    public void closeById(int id) {
        repository.closeById(id);
    }

}
