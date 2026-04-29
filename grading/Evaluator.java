package grading;


public interface Evaluator {
    double evaluate(lms.models.Submission submission, lms.models.Attendance attendance);

    double evaluate(Submission submission, Attendance attendance);
}
