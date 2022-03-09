package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class IssueRepository {
    private Collection<Issue> issues = new LinkedList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public void saveAll(Collection<Issue> issues) {
        this.issues.addAll(issues);
    }

    public Collection<Issue> findAll() {
        return this.issues;
    }
}
