package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Issue {
    private int id;
    private String title;
    private String author;
    private int date;
    private boolean open;
    private Set<String> label;
    private Set<String> assignee;

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", open=" + open +
                ", label=" + label +
                ", assignee=" + assignee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && date == issue.date && open == issue.open && Objects.equals(title, issue.title) && Objects.equals(author, issue.author) && Objects.equals(label, issue.label) && Objects.equals(assignee, issue.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, date, open, label, assignee);
    }
}