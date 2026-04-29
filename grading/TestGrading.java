package grading;
import Models.*;
public class TestGrading implements Evaluator {

    @Override
    public double evaluate(Submission submission, Attendance attendance) {
        return submission.getMarks();
    }
}
