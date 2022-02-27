package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

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


    private Issue issue1 = new Issue(1, true, "11.02.2022", "author1", label1, "assignee1", 13);
    private Issue issue2 = new Issue(2, false, "21.02.2021", "author2", label2, "assignee2", 5);
    private Issue issue3 = new Issue(3, true, "04.12.2020", "author3", label3, "assignee3", 7);
    private Issue issue4 = new Issue(4, false, "13.05.2021", "author1", label4, "assignee4", 11);
    private Issue issue5 = new Issue(5, true, "07.01.2022", "author4", label2, "assignee3", 9);

    @BeforeEach
    public void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
    }

    @Test
    void shouldFindAllOpen() {
        List<Issue> expected = Arrays.asList(issue1, issue3, issue5);
        List<Issue> actual = manager.findAllOpen();

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllClosed() {
        List<Issue> expected = Arrays.asList(issue2, issue4);
        List<Issue> actual = manager.findAllClosed();

        assertEquals(expected, actual);
    }


    @Test
    void shouldFilterByAuthorIfExists() {
        String author = "author1";
        Predicate<Issue> predicate = issue -> issue.getAuthor().equals(author);
        List<Issue> expected = Arrays.asList(issue1, issue4);
        List<Issue> actual = manager.filterByAuthor(predicate);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthorIfNotExists() {
        String author = "author10";
        Predicate<Issue> predicate = issue -> issue.getAuthor().equals(author);
        List<Issue> expected = Arrays.asList();
        List<Issue> actual = manager.filterByAuthor(predicate);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabelIfExists() {
        Predicate<HashSet> equalLabel = t -> t.equals(label2);
        List<Issue> expected = Arrays.asList(issue2, issue5);
        List<Issue> actual = manager.filterByLabel(equalLabel);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabelIfNotExists() {
        Predicate<HashSet> equalLabel = t -> t.equals(label6);
        List<Issue> expected = Arrays.asList();
        List<Issue> actual = manager.filterByLabel(equalLabel);

        assertEquals(expected, actual);
    }


    @Test
    void shouldById() {
        manager.findById(3);
        assertTrue(issue3.isOpen());
    }

    @Test
    void shouldCloseById() {
        manager.closeById(3);
        assertFalse(issue3.isOpen());
    }
}