package lms.grading;
import lms.Models.*;

public class ExamGrading implements Evaluator {

    @Override
    public double evaluate(lms.Models.Submission submission, lms.Models.Attendance attendance) {
        return submission.getMarks();
    }
}
