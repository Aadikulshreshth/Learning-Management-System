import java.util.ArrayList;
public class LMSManager {

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Instructor> instructors = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();

    // ---------------- ADD USERS ----------------

    public void addStudent(Student s) {
        students.add(s);
    }

    public void addInstructor(Instructor i) {
        instructors.add(i);
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    // ---------------- LOGIN ----------------

    public User login(String id) {
        for (Student s : students) {
            if (s.getUserId().equals(id)) return s;
        }
        for (Instructor i : instructors) {
            if (i.getUserId().equals(id)) return i;
        }
        return null;
    }

    // ---------------- FIND ----------------

    public Course findCourse(String id) {
        for (Course c : courses) {
            if (c.getCourseId().equals(id)) return c;
        }
        return null;
    }

    // ---------------- ACTIONS ----------------

    public void addAssignment(User user, String courseId, Assignment a) {
        if (!user.getRole().equals("instructor")) {
            System.out.println("Only instructor allowed");
            return;
        }
        Course c = findCourse(courseId);
        if (c != null) {
            c.addAssignment(a);
            System.out.println("Assignment added");
        }
    }

    public void submitAssignment(User user, Assignment a, String content) {
        if (!user.getRole().equals("student")) {
            System.out.println("Only student allowed");
            return;
        }

        Submission s = new Submission((Student) user, a, content);
        a.addSubmission(s);
        System.out.println("Submitted");
    }

    public void grade(User user, Submission s, int marks) {
        if (!user.getRole().equals("instructor")) {
            System.out.println("Only instructor allowed");
            return;
        }
        s.assignMarks(marks);
        System.out.println("Graded");
    }
}