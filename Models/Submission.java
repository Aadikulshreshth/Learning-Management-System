public class Submission {
    private String submissionId;
    private Student student;
    private Assignment assignment;
    private String content;   // what student submits
    private int marks;
    private String status;    // Submitted / Evaluated
    // Constructor
    public Submission(String submissionId, Student student, Assignment assignment, String content) {
        this.submissionId = submissionId;
        this.student = student;
        this.assignment = assignment;
        this.content = content;
        this.status = "Submitted";
        this.marks = 0;
    }
    // Assign marks
    public void assignMarks(int marks) {
        this.marks = marks;
        this.status = "Evaluated";
    }
    // Getters
    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public int getMarks() {
        return marks;
    }

    public String getStatus() {
        return status;
    }
    // Display
    public void displaySubmission() {
        System.out.println("Submission ID: " + submissionId);
        System.out.println("Student: " + student.getName());
        System.out.println("Assignment: " + assignment.getTitle());
        System.out.println("Marks: " + marks);
        System.out.println("Status: " + status);
    }
}
