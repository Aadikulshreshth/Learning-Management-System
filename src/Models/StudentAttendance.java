package Models;

public class StudentAttendance {
    private String studentName;
    private String status;

    public StudentAttendance(String studentName, String status) {
        this.studentName = studentName;
        this.status = status;
    }

    public String getStudentName() { return studentName; }
    public String getStatus() { return status; }
}
