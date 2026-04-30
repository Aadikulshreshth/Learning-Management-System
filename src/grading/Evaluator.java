package grading;
import Models.*;

public interface Evaluator {
    double evaluate(Submission submission, Attendance attendance);
}
