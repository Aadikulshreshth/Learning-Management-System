public class Attendance {
    private Student student;
    private Course course;
    private int totalClasses;
    private int attendedClasses;
    // Constructor
    public Attendance(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.totalClasses = 0;
        this.attendedClasses = 0;
    }
    // Mark attendance
    public void markPresent() {
        totalClasses++;
        attendedClasses++;
    }

    public void markAbsent() {
        totalClasses++;
    }
    // Calculate percentage
    public double getAttendancePercentage() {
        if (totalClasses == 0) return 0;
        return (attendedClasses * 100.0) / totalClasses;
    }
    // Display
    public void displayAttendance() {
        System.out.println("Student: " + student.getName());
        System.out.println("Course: " + course.getCourseName());
        System.out.println("Attendance: " + getAttendancePercentage() + "%");
    }
}
