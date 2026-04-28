package lms.grading;

public class ExamGrading implements Evaluator {

    @Override
    public double evaluate(Submission submission, Attendance attendance) {
        return submission.getMarks();
    }
}
