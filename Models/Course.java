import java.util.ArrayList;

public  class Course {
    private String courseId;
    private String courseName;
    private Instructor instructor;

    private ArrayList<Student> enrolledStudents;
    private ArrayList<Assignment> assignments;

    //Constructor
    public Course(String courseId, String courseName, Instructor instructor){
        this.courseId=courseId;
        this.courseName=courseName;
        this.instructor=instructor;
        this.enrolledStudents= new ArrayList<>();
        this.assignments=new ArrayList<>();
    }

    //Add Students
    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    // Add Assignment
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    // Getters
    public String getCourseName() {
        return courseName;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
}
