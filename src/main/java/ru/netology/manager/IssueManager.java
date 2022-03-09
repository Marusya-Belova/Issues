package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository = new IssueRepository();

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public IssueManager() {
    }

    Collection<Issue> issues = repository.findAll();

    public void addIssue(Issue issue) {
        repository.save(issue);
    }

    public Collection<Issue> openIssues() {
        Collection<Issue> result = new LinkedList<>();
        for (Issue issue : issues) {
            if (issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public Collection<Issue> closedIssue() {
        Collection<Issue> result = new LinkedList<>();
        for (Issue issue : issues) {
            if (!issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public Collection<Issue> findBy(Predicate<Issue> filter) {
        Collection<Issue> result = new LinkedList<>();
        for (Issue issue : issues) {
            if (filter.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    public Collection<Issue> filterByAssignee(String text) {
        return findBy(issue -> issue.getAssignee().contains(text));
    }

    public Collection<Issue> filterByLabel(String text) {
        return findBy(issue -> issue.getLabel().contains(text));
    }

    public Collection<Issue> filterByAuthor(String text) {
        return findBy(issue -> issue.getAuthor().contains(text));
    }

    public void toOpenIssue(int id) {
        for (Issue issue : issues) {
            if (!issue.isOpen() && issue.getId() == id) {
                issue.setOpen(true);
            }
        }
    }

    public void toCloseIssue(int id) {
        for (Issue issue : issues) {
            if (!issue.isOpen() && issue.getId() == id) {
                issue.setOpen(false);
            }
        }
    }
}
