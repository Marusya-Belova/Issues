package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();

    private HashSet<String> label1 = new HashSet<>((Arrays.asList("label1")));
    private HashSet<String> label2 = new HashSet<>((Arrays.asList("label2")));
    private HashSet<String> label3 = new HashSet<>((Arrays.asList("label3")));
    private HashSet<String> label4 = new HashSet<>((Arrays.asList("label4")));
    private HashSet<String> label6 = new HashSet<>((Arrays.asList("label6")));


    private HashSet<String> assignee1 = new HashSet<>((Arrays.asList("assignee1")));
    private HashSet<String> assignee2 = new HashSet<>((Arrays.asList("assignee2")));
    private HashSet<String> assignee3 = new HashSet<>((Arrays.asList("assignee3")));
    private HashSet<String> assignee4 = new HashSet<>((Arrays.asList("assignee4")));
    private HashSet<String> assignee5 = new HashSet<>((Arrays.asList("assignee5")));


    private Issue issue1 = new Issue(1, true, "11.02.2022", "author1", label1, assignee1, 13);
    private Issue issue2 = new Issue(2, false, "21.02.2021", "author2", label2, assignee2, 5);
    private Issue issue3 = new Issue(3, true, "04.12.2020", "author3", label3, assignee3, 7);
    private Issue issue4 = new Issue(4, false, "13.05.2021", "author1", label4, assignee4, 11);
    private Issue issue5 = new Issue(5, true, "07.01.2022", "author4", label2, assignee3, 9);

    @BeforeEach
    public void setUp() {
        repository.save(issue1);
        repository.save(issue2);
        repository.save(issue3);
        repository.save(issue4);
        repository.save(issue5);
    }

    @Test
    void shouldFindAll() {
        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3, issue4, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Issue actual = repository.findById(3);
        Issue expected = issue3;
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenById() {
        repository.openById(1);
        assertTrue(issue1.isOpen());
    }

    @Test
    void shouldCloseById() {
        repository.closeById(5);
        assertFalse(issue5.isOpen());
    }
}