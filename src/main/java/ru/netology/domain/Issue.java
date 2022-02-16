package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue {
    private int id;
    private boolean open;
    private String date;
    private String author;
    private HashSet<String> label;
    private String assignee;
    private int countComments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && open == issue.open && date == issue.date && countComments == issue.countComments && Objects.equals(author, issue.author) && Objects.equals(label, issue.label) && Objects.equals(assignee, issue.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, open, date, author, label, assignee, countComments);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", open=" + open +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", label=" + label +
                ", assignee='" + assignee + '\'' +
                ", countComments=" + countComments +
                '}';
    }
}


