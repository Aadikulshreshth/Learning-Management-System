imimport java.util.ArrayList;

public class Course {

    private String courseId;
    private String courseName;
    private ArrayList<Assignment> assignments = new ArrayList<>();

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }

    public void addAssignment(Assignment a) {
        assignments.add(a);
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
}