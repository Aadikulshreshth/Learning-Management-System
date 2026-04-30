package Models;

public class Submission {
    private String submissionId;
    private Student student;
    private Assignment assignment;
    private String content;
    private int marks;
    private String status; // Submitted / Evaluated

    public Submission(String submissionId, Student student, Assignment assignment, String content) {
        this.submissionId = submissionId;
        this.student = student;
        this.assignment = assignment;
        this.content = content;
        this.status = "Submitted";
        this.marks = 0;
    }

    public void assignMarks(int marks) {
        this.marks = marks;
        this.status = "Evaluated";
    }

    public Student getStudent() { return student; }
    public Assignment getAssignment() { return assignment; }
    public int getMarks() { return marks; }
    public String getStatus() { return status; }
    public String getContent() { return content; }
    public String getSubmissionId() { return submissionId; }
}