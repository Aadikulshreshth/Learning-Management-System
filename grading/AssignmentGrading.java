package grading;

public class AssignmentGrading implements Evaluator {

    @Override
    public double evaluate(lms.Models.Submission submission, lms.Models.Attendance attendance) {
        return submission.getMarks() * 0.8;
    }
}
