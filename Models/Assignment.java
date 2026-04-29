import java.util.ArrayList;

public class Assignment {

    private String assignmentId;
    private String title;
    private ArrayList<Submission> submissions = new ArrayList<>();

    public Assignment(String assignmentId, String title) {
        this.assignmentId = assignmentId;
        this.title = title;
    }

    public String getAssignmentId() { return assignmentId; }
    public String getTitle() { return title; }

    public void addSubmission(Submission s) {
        submissions.add(s);
    }

    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }
}