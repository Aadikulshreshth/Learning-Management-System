package Models;

import java.util.ArrayList;

public class Course {

    private String courseId;
    private String courseName;
    private String courseType;

    private Instructor instructor;
    private ArrayList<Student> students = new ArrayList<>();

    public Course(String id, String name, String type) {
        this.courseId = id;
        this.courseName = name;
        this.courseType = type;
    }

    // ADD THIS METHOD TO FIX THE COMPILATION ERROR
    public String getId() {
        return courseId;
    }

    public String getCourseName() { return courseName; }
    public String getCourseType() { return courseType; }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return this.courseName;
    }
}