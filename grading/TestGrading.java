package lms.grading;

import lms.grading.Evaluator;

public class TestGrading implements Evaluator {

    @Override
    public double evaluate(lms.models.Submission submission, lms.models.Attendance attendance) {
        return submission.getMarks();
    }
}
