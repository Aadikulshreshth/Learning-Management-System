package Models;

import java.util.ArrayList;

public class Assignment {
    private String assignmentId;
    private String title;
    private Course course;
    private String dueDate;
    private String grade;
    private ArrayList<Submission> submissions = new ArrayList<>();

    public Assignment(String assignmentId, String title, Course course) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.course = course;
    }

    public Assignment(String title, String dueDate, String grade) {
        this.title = title;
        this.dueDate = dueDate;
        this.grade = grade;
    }

    public String getAssignmentId() { return assignmentId; }
    public String getTitle() { return title; }
    public Course getCourse() { return course; }
    public String getDueDate() { return dueDate; }
    public String getGrade() { return grade; }

    public void addSubmission(Submission s) {
        submissions.add(s);
    }

    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }
}