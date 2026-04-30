package Core;

import Models.*;
import java.util.*;

public class LMSManager {

    private List<Student> students = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private ArrayList<Attendance> attendanceList = new ArrayList<>();
    private Map<Student, List<Course>> studentCourses = new HashMap<>();
    private Map<Course, Instructor> courseInstructor = new HashMap<>();
    private Map<String, Attendance> attendanceMap = new HashMap<>();
    private Map<Course, List<Assignment>> assignments = new HashMap<>();

    // ================= LOGIN =================
    public User login(String id) {
        for (Student s : students) {
            if (s.getUserId().equals(id)) return s;
        }
        for (Instructor i : instructors) {
            if (i.getUserId().equals(id)) return i;
        }
        return null;
    }

    // ================= ADD =================
    public void addStudent(Student s) {
        students.add(s);
    }

    public void addInstructor(Instructor i) {
        instructors.add(i);
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    // ================= LINK =================
    public void assignStudentToCourse(Student s, Course c) {
        studentCourses.putIfAbsent(s, new ArrayList<>());
        studentCourses.get(s).add(c);

        c.addStudent(s);

        // create attendance record
        attendanceMap.put(s.getUserId() + "_" + c.getCourseName(),
                new Attendance(s, c));
    }

    public void assignInstructorToCourse(Instructor i, Course c) {
        courseInstructor.put(c, i);
        c.setInstructor(i);
    }

    // ================= GET =================
    public List<Course> getCoursesOfStudent(Student s) {

        List<Course> result = new ArrayList<>();

        for (Course c : courses) {
            if (c.getStudents().contains(s)) {
                result.add(c);
            }
        }

        return result;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Course> getCoursesOfInstructor(Instructor i) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (courseInstructor.get(c) == i) {
                result.add(c);
            }
        }
        return result;
    }

    public Attendance getAttendance(Student student, Course course) {

        for (Attendance a : attendanceList) {
            if (a.getStudent().equals(student) && a.getCourse().equals(course)) {
                return a;
            }
        }

        // CREATE if not exists
        Attendance newAtt = new Attendance(student, course);
        attendanceList.add(newAtt);

        return newAtt;
    }

    // ================= ASSIGNMENTS =================
    public void addAssignment(Course c, Assignment a) {
        assignments.putIfAbsent(c, new ArrayList<>());
        assignments.get(c).add(a);
    }

    public List<Assignment> getAssignments(Course c) {
        return assignments.getOrDefault(c, new ArrayList<>());
    }
}