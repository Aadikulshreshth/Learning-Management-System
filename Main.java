public class Main {
    public static void main(String[] args) {

        LMSManager manager = new LMSManager();

        // Create users
        Student s = new Student("S1", "Aadi", "mail");
        Instructor i = new Instructor("I1", "Sir", "mail");

        manager.addStudent(s);
        manager.addInstructor(i);

        // Create course
        Course c = new Course("C1", "Java");
        manager.addCourse(c);

        // Login as instructor
        User user = manager.login("I1");

        // Add assignment
        Assignment a = new Assignment("A1", "OOP");
        manager.addAssignment(user, "C1", a);

        // Login as student
        user = manager.login("S1");

        // Submit
        manager.submitAssignment(user, a, "My answer");

        // Grade
        user = manager.login("I1");
        Submission sub = a.getSubmissions().get(0);
        manager.grade(user, sub, 90);
    }
}