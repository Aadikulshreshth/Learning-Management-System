package grading;
import Models.*;
public class AssignmentGrading implements Evaluator {

    @Override
    public double evaluate(Submission submission, Attendance attendance) {
        return submission.getMarks() * 0.8;
    }
}
