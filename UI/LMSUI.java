import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LMSUI extends Application {

    LMSManager manager = new LMSManager();
    Stage window;

    @Override
    public void start(Stage stage) {

        window = stage;

        // Dummy data (IMPORTANT)
        manager.addStudent(new Student("S1", "Aadi", "mail"));
        manager.addInstructor(new Instructor("I1", "Sir", "mail"));

        showLoginScreen();
    }

    // ---------------- LOGIN SCREEN ----------------

    private void showLoginScreen() {

        TextField userIdField = new TextField();
        userIdField.setPromptText("Enter User ID");

        Label status = new Label();

        Button loginBtn = new Button("Login");

        loginBtn.setOnAction(e -> {
            User user = manager.login(userIdField.getText());

            if (user == null) {
                status.setText("User not found");
                return;
            }

            if (user.getRole().equals("student")) {
                showStudentScreen(user);
            } else {
                showInstructorScreen(user);
            }
        });

        VBox layout = new VBox(10,
                new Label("LMS Login"),
                userIdField,
                loginBtn,
                status
        );

        window.setScene(new Scene(layout, 300, 200));
        window.setTitle("Login");
        window.show();
    }

    // ---------------- STUDENT SCREEN ----------------

    private void showStudentScreen(User user) {

        Label title = new Label("Student Dashboard");

        TextField contentField = new TextField();
        contentField.setPromptText("Assignment content");

        Button submitBtn = new Button("Submit Assignment");

        Label output = new Label();

        submitBtn.setOnAction(e -> {
            Course c = manager.findCourse("C1");
            if (c != null && !c.getAssignments().isEmpty()) {
                Assignment a = c.getAssignments().get(0);
                manager.submitAssignment(user, a, contentField.getText());
                output.setText("Submitted!");
            } else {
                output.setText("No assignment available");
            }
        });

        Button logout = new Button("Logout");
        logout.setOnAction(e -> showLoginScreen());

        VBox layout = new VBox(10, title, contentField, submitBtn, output, logout);

        window.setScene(new Scene(layout, 400, 300));
    }

    // ---------------- INSTRUCTOR SCREEN ----------------

    private void showInstructorScreen(User user) {

        Label title = new Label("Instructor Dashboard");

        TextField courseIdField = new TextField();
        courseIdField.setPromptText("Course ID");

        TextField assignmentIdField = new TextField();
        assignmentIdField.setPromptText("Assignment ID");

        Button addCourseBtn = new Button("Add Course");
        Button addAssignmentBtn = new Button("Add Assignment");

        Label output = new Label();

        addCourseBtn.setOnAction(e -> {
            Course c = new Course(courseIdField.getText(), "Java");
            manager.addCourse(c);
            output.setText("Course Added");
        });

        addAssignmentBtn.setOnAction(e -> {
            Assignment a = new Assignment(assignmentIdField.getText(), "OOP");
            manager.addAssignment(user, courseIdField.getText(), a);
            output.setText("Assignment Added");
        });

        Button logout = new Button("Logout");
        logout.setOnAction(e -> showLoginScreen());

        VBox layout = new VBox(10,
                title,
                courseIdField,
                addCourseBtn,
                assignmentIdField,
                addAssignmentBtn,
                output,
                logout
        );

        window.setScene(new Scene(layout, 400, 300));
    }

    public static void main(String[] args) {
        launch();
    }
}