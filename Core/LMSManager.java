import java.util.ArrayList;
public class LMSManager {

    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Submission> submissions;

    // Constructor
    public LMSManager() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        submissions = new ArrayList<>();
    }

    // ------------------ STUDENT ------------------

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public Student findStudent(String userId) throws UserNotFoundException {
        for (Student s : students) {
            if (s.getUserId().equals(userId)) {
                return s;
            }
        }
        throw new UserNotFoundException("Student not found");
    }

    // ------------------ COURSE ------------------

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    public Course findCourse(String courseId) throws CourseNotFoundException {
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                return c;
            }
        }
        throw new CourseNotFoundException("Course not found");
    }

    // ------------------ ENROLLMENT ------------------

    public void enrollStudent(String userId, String courseId)
            throws UserNotFoundException, CourseNotFoundException {

        Student student = findStudent(userId);
        Course course = findCourse(courseId);

        course.addStudent(student);
        System.out.println("Student enrolled successfully.");
    }

    // ------------------ ASSIGNMENT ------------------

    public void addAssignment(String courseId, Assignment assignment)
            throws CourseNotFoundException {

        Course course = findCourse(courseId);
        course.addAssignment(assignment);

        System.out.println("Assignment added to course.");
    }

    // ------------------ SUBMISSION ------------------

    public void submitAssignment(String userId, String courseId, Assignment assignment, String content)
            throws UserNotFoundException, CourseNotFoundException {

        Student student = findStudent(userId);
        Course course = findCourse(courseId);

        Submission submission = new Submission("SUB" + (submissions.size() + 1),
                student, assignment, content);

        submissions.add(submission);
        assignment.addSubmission(submission);

        System.out.println("Assignment submitted successfully.");
    }

    // ------------------ EVALUATION ------------------

    public void evaluateSubmission(Submission submission, int marks) {
        submission.assignMarks(marks);
        System.out.println("Submission evaluated.");
    }

    // ------------------ DISPLAY ------------------

    public void displayStudents() {
        for (Student s : students) {
            System.out.println(s.getName());
        }
    }

    public void displayCourses() {
        for (Course c : courses) {
            System.out.println(c.getCourseName());
        }
    }

    public void displaySubmissions() {
        for (Submission s : submissions) {
            s.displaySubmission();
            System.out.println("-------------------");
        }
    }
}