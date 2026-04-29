package grading;

public class AttendanceGrading implements Evaluator {

    @Override
    public double evaluate(lms.Models.Submission submission, lms.Models.Attendance attendance) {
        return attendance.getAttendancePercentage();
    }
}
