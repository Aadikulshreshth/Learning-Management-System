package grading;
import Models.*;
public class AttendanceGrading implements Evaluator {

    @Override
    public double evaluate(Submission submission, Attendance attendance) {
        return attendance.getAttendancePercentage();
    }
}
