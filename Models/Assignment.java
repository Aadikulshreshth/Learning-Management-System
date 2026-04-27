public class Assignment {
    private String assignmentID;
    private String title;
    private String description;
    private String deadline;
    private int maxMarks;


    public  Assignment(String assignmentID, String title, String description, String deadline, int maxMarks){
        this.assignmentID=assignmentID;
        this.title=title;
        this.description=description;
        this.deadline=deadline;
        this.maxMarks=maxMarks;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignmentID() {
        return assignmentID;
    }
}
