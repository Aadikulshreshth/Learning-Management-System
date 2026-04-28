package lms.grading;

import lms.Models.*;

public interface Evaluator {
    double evaluate(lms.models.Submission submission, lms.models.Attendance attendance);

    double evaluate(Submission submission, Attendance attendance);
}
