public class Student extends User {

    private String courseEnrolled;

    public Student(String userId, String name, String email, String courseEnrolled) {
        super(userId, name, email);
        this.courseEnrolled = courseEnrolled;
        setRole("student");
    }
}