package UI;

import Connection.DatabaseConnection;
import Models.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LMSUI extends Application {
    private Stage window;

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Institute Learning Management System");
        showLoginScreen();
        window.show();
    }

    // ================= 1. LOGIN SCREEN =================
    private void showLoginScreen() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: #f8f9fa;");

        Label title = new Label("INSTITUTE PORTAL");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #800000;");

        TextField uField = new TextField();
        uField.setPromptText("User ID");
        uField.setMaxWidth(350);
        uField.setPrefHeight(45);

        PasswordField pField = new PasswordField();
        pField.setPromptText("Password");
        pField.setMaxWidth(350);
        pField.setPrefHeight(45);

        Button loginBtn = new Button("LOG IN");
        loginBtn.setPrefWidth(350);
        loginBtn.setPrefHeight(45);
        loginBtn.setStyle("-fx-background-color: #800000; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");

        loginBtn.setOnAction(e -> {
            String u = uField.getText();
            String p = pField.getText();

            String query = "SELECT name, role FROM Users WHERE username = ? AND password = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, u);
                pstmt.setString(2, p);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String role = rs.getString("role").toUpperCase();
                    if (role.equals("ADMIN")) {
                        showAdminDashboard();
                    } else if (role.equals("STUDENT")) {
                        showStudentDashboard(new Student(u, name, ""));
                    } else if (role.equals("INSTRUCTOR")) {
                        showInstructorDashboard(new Instructor(u, name, ""));
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid User ID or Password.");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(title, uField, pField, loginBtn);
        applyScene(root);
    }

    // ================= 2. ADMIN DASHBOARD =================
    private void showAdminDashboard() {
        BorderPane layout = new BorderPane();
        layout.setTop(createNavBar("Admin Control Center"));

        VBox content = new VBox(25);
        content.setPadding(new Insets(30));

        Label header = new Label("Institutional Controls (CRUD Operations)");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // --- CREATE / INSERT USER ---
        TextField userField = new TextField();
        userField.setPromptText("Username");
        TextField passField = new TextField();
        passField.setPromptText("Password");
        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        ComboBox<String> roleCombo = new ComboBox<>();
        roleCombo.getItems().addAll("STUDENT", "INSTRUCTOR", "ADMIN");
        roleCombo.setPromptText("Select Role");

        Button addUserBtn = new Button("Add User");
        addUserBtn.setStyle("-fx-background-color: #800000; -fx-text-fill: white;");
        addUserBtn.setOnAction(e -> {
            String q = "INSERT INTO Users (username, password, name, role) VALUES (?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, userField.getText());
                pstmt.setString(2, passField.getText());
                pstmt.setString(3, nameField.getText());
                pstmt.setString(4, roleCombo.getValue());
                pstmt.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "User added successfully!").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        VBox userForm = new VBox(10, new Label("Create/Add User"), userField, passField, nameField, roleCombo, addUserBtn);
        userForm.setPadding(new Insets(15));
        userForm.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");

        // --- ENROLLMENTS / LINK ---
        TextField sId = new TextField();
        sId.setPromptText("Student User ID");
        TextField cId = new TextField();
        cId.setPromptText("Course ID");
        Button enrollBtn = new Button("Enroll Student in Course");
        enrollBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");
        enrollBtn.setOnAction(e -> {
            String q = "INSERT INTO Student_Course (student_id, course_id) VALUES (?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, sId.getText());
                pstmt.setString(2, cId.getText());
                pstmt.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "Student successfully enrolled!").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        VBox enrollForm = new VBox(10, new Label("Course Enrollment"), sId, cId, enrollBtn);
        enrollForm.setPadding(new Insets(15));
        enrollForm.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");

        // --- COURSE MANAGEMENT (CREATE) ---
        TextField courseIdField = new TextField();
        courseIdField.setPromptText("Course ID (e.g., MATH101)");
        TextField courseNameField = new TextField();
        courseNameField.setPromptText("Course Name");
        TextField courseTypeField = new TextField();
        courseTypeField.setPromptText("Course Type (e.g., Core)");

        Button addCourseBtn = new Button("Add New Course");
        addCourseBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
        addCourseBtn.setOnAction(e -> {
            String q = "INSERT INTO Courses (id, course_name, course_type) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, courseIdField.getText());
                pstmt.setString(2, courseNameField.getText());
                pstmt.setString(3, courseTypeField.getText());
                pstmt.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "Course added successfully!").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        VBox courseForm = new VBox(10, new Label("Course Management"), courseIdField, courseNameField, courseTypeField, addCourseBtn);
        courseForm.setPadding(new Insets(15));
        courseForm.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");

        // --- INSTRUCTOR ASSIGNMENT ---
        TextField instructorIdField = new TextField();
        instructorIdField.setPromptText("Instructor Username");
        TextField courseIdAssignField = new TextField();
        courseIdAssignField.setPromptText("Course ID");

        Button assignInstructorBtn = new Button("Assign Instructor to Course");
        assignInstructorBtn.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white;");
        assignInstructorBtn.setOnAction(e -> {
            String q = "INSERT INTO Instructor_Course (instructor_id, course_id) VALUES (?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, instructorIdField.getText());
                pstmt.setString(2, courseIdAssignField.getText());
                pstmt.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "Instructor successfully assigned to course!").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        VBox assignForm = new VBox(10, new Label("Assign Instructor"), instructorIdField, courseIdAssignField, assignInstructorBtn);
        assignForm.setPadding(new Insets(15));
        assignForm.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");

        // --- DELETE OPERATIONS ---
        TextField deleteUserField = new TextField();
        deleteUserField.setPromptText("Username to Delete");
        Button deleteUserBtn = new Button("Delete User");
        deleteUserBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white;");
        deleteUserBtn.setOnAction(e -> {
            String q = "DELETE FROM Users WHERE username = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, deleteUserField.getText());
                int rows = pstmt.executeUpdate();
                if (rows > 0) new Alert(Alert.AlertType.INFORMATION, "User deleted successfully.").show();
                else new Alert(Alert.AlertType.WARNING, "User not found.").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        VBox deleteUserForm = new VBox(10, new Label("Delete User"), deleteUserField, deleteUserBtn);
        deleteUserForm.setPadding(new Insets(15));
        deleteUserForm.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");

        TextField deleteCourseField = new TextField();
        deleteCourseField.setPromptText("Course ID to Delete");
        Button deleteCourseBtn = new Button("Delete Course");
        deleteCourseBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white;");
        deleteCourseBtn.setOnAction(e -> {
            String q = "DELETE FROM Courses WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, deleteCourseField.getText());
                int rows = pstmt.executeUpdate();
                if (rows > 0) new Alert(Alert.AlertType.INFORMATION, "Course deleted successfully.").show();
                else new Alert(Alert.AlertType.WARNING, "Course not found.").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        VBox deleteCourseForm = new VBox(10, new Label("Delete Course"), deleteCourseField, deleteCourseBtn);
        deleteCourseForm.setPadding(new Insets(15));
        deleteCourseForm.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");

        HBox forms = new HBox(15, userForm, enrollForm, courseForm, assignForm, deleteUserForm, deleteCourseForm);
        content.getChildren().addAll(header, new Separator(), forms);
        layout.setCenter(content);
        applyScene(layout);
    }

    // ================= 3. INSTRUCTOR DASHBOARD =================
    private void showInstructorDashboard(Instructor instructor) {
        BorderPane layout = new BorderPane();
        layout.setTop(createNavBar("Faculty Portal: " + instructor.getName()));

        VBox content = new VBox(20);
        content.setPadding(new Insets(30));

        Label lbl = new Label("Your Assigned Courses");
        lbl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        FlowPane flow = new FlowPane(20, 20);
        List<Course> courses = fetchInstructorCourses(instructor.getUserId());
        for (Course c : courses) {
            VBox card = createCourseCard(c);
            card.setOnMouseClicked(e -> showDetailedCourseView(c, instructor));
            flow.getChildren().add(card);
        }

        content.getChildren().addAll(lbl, new Separator(), flow);
        layout.setCenter(content);
        applyScene(layout);
    }

    // ================= 4. STUDENT DASHBOARD =================
    private void showStudentDashboard(Student student) {
        BorderPane layout = new BorderPane();
        layout.setTop(createNavBar("Student: " + student.getName()));

        VBox content = new VBox(20);
        content.setPadding(new Insets(30));

        Label greeting = new Label("Hi, " + student.getName().toUpperCase() + "! 👋");
        greeting.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        FlowPane flow = new FlowPane(20, 20);
        List<Course> courses = fetchEnrolledCourses(student.getUserId());
        for (Course c : courses) {
            VBox card = createCourseCard(c);
            card.setOnMouseClicked(e -> showDetailedCourseView(c, student));
            flow.getChildren().add(card);
        }

        content.getChildren().addAll(greeting, new Label("Enrolled Courses"), new Separator(), flow);
        layout.setCenter(content);
        applyScene(layout);
    }

    // ================= 5. DETAILED COURSE WORKSPACE =================
    private void showDetailedCourseView(Course course, Object user) {
        BorderPane layout = new BorderPane();
        layout.setTop(createNavBar(course.getCourseName()));

        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab attTab = new Tab("Attendance");
        Tab assTab = new Tab("Assignments");

        if (user instanceof Student) {
            attTab.setContent(createStudentAttendanceView(course));
            assTab.setContent(createStudentAssignmentView(course, (Student) user));
        } else {
            attTab.setContent(createInstructorAttendanceView(course));
            assTab.setContent(createInstructorGradingView(course));
        }

        tabs.getTabs().addAll(attTab, assTab);

        Button backBtn = new Button("← Back to Dashboard");
        backBtn.setOnAction(e -> {
            if (user instanceof Student) showStudentDashboard((Student) user);
            else showInstructorDashboard((Instructor) user);
        });

        VBox box = new VBox(10, backBtn, tabs);
        box.setPadding(new Insets(15));
        layout.setCenter(box);
        applyScene(layout);
    }

    // ================= 6. MODULE WORKFLOWS =================
    private VBox createInstructorAttendanceView(Course c) {
        VBox box = new VBox(15);
        box.setPadding(new Insets(20));

        Label title = new Label("Attendance Sheet: " + c.getCourseName());

        TableView<StudentAttendance> table = new TableView<>();
        TableColumn<StudentAttendance, String> nameCol = new TableColumn<>("Student Name");
        TableColumn<StudentAttendance, String> statusCol = new TableColumn<>("Status");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(nameCol, statusCol);
        table.setItems(fetchAttendanceData(c.getId()));

        TextField userField = new TextField();
        userField.setPromptText("Student Username");
        Button markPresentBtn = new Button("Mark Present");
        markPresentBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        Button markAbsentBtn = new Button("Mark Absent");
        markAbsentBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white;");

        markPresentBtn.setOnAction(e -> processAttendance(c.getId(), userField.getText(), "Present", table));
        markAbsentBtn.setOnAction(e -> processAttendance(c.getId(), userField.getText(), "Absent", table));

        HBox controlBox = new HBox(10, userField, markPresentBtn, markAbsentBtn);
        box.getChildren().addAll(title, table, controlBox);
        return box;
    }

    private void processAttendance(String courseId, String studentUsername, String status, TableView<StudentAttendance> table) {
        String q = "INSERT INTO Attendance (course_id, student_id, status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(q)) {
            pstmt.setString(1, courseId);
            pstmt.setString(2, studentUsername);
            pstmt.setString(3, status);
            pstmt.executeUpdate();
            table.setItems(fetchAttendanceData(courseId));
            new Alert(Alert.AlertType.INFORMATION, "Attendance successfully processed.").show();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }
    }

    private VBox createStudentAssignmentView(Course c, Student s) {
        VBox box = new VBox(15);
        box.setPadding(new Insets(20));

        Label title = new Label("Post Assignment for " + c.getCourseName());

        TableView<Assignment> table = new TableView<>();
        TableColumn<Assignment, String> assignCol = new TableColumn<>("Assignment Title");
        TableColumn<Assignment, String> dueCol = new TableColumn<>("Due Date");
        TableColumn<Assignment, String> gradeCol = new TableColumn<>("Assigned Grade");

        assignCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        dueCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        table.getColumns().addAll(assignCol, dueCol, gradeCol);
        table.setItems(fetchAssignments(c.getId()));

        TextField fileField = new TextField();
        fileField.setPromptText("Assignment Submission Link / Text");
        Button submit = new Button("Submit Assignment");
        submit.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        submit.setOnAction(e -> {
            String submissionLink = fileField.getText();
            String q = "INSERT INTO Assignment_Submissions (course_id, student_id, submission_link) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, c.getId());
                pstmt.setString(2, s.getUserId());
                pstmt.setString(3, submissionLink);
                pstmt.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "Submission uploaded.").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        box.getChildren().addAll(title, table, new Label("Upload your files:"), fileField, submit);
        return box;
    }

    private VBox createInstructorGradingView(Course c) {
        VBox box = new VBox(15);
        box.setPadding(new Insets(20));

        Label title = new Label("Grading and Assignments Panel");

        TextField assignTitle = new TextField();
        assignTitle.setPromptText("New Assignment Name");
        TextField dueDate = new TextField();
        dueDate.setPromptText("Due Date (YYYY-MM-DD)");

        Button createAssignBtn = new Button("Create Assignment");
        createAssignBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        createAssignBtn.setOnAction(e -> {
            String q = "INSERT INTO Course_Assignments (course_id, title, due_date) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, c.getId());
                pstmt.setString(2, assignTitle.getText());
                pstmt.setString(3, dueDate.getText());
                pstmt.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "Assignment created.").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade Value");
        TextField studentField = new TextField();
        studentField.setPromptText("Student Username");
        Button gradeBtn = new Button("Post Grade");
        gradeBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        gradeBtn.setOnAction(e -> {
            String q = "INSERT INTO Assignment_Grades (student_id, course_id, grade) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(q)) {
                pstmt.setString(1, studentField.getText());
                pstmt.setString(2, c.getId());
                pstmt.setString(3, gradeField.getText());
                pstmt.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "Grade updated successfully!").show();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        box.getChildren().addAll(
                title,
                new HBox(10, assignTitle, dueDate, createAssignBtn),
                new Separator(),
                new Label("Add Grade to Assignment:"),
                new HBox(10, studentField, gradeField, gradeBtn)
        );

        return box;
    }

    private VBox createStudentAttendanceView(Course c) {
        VBox box = new VBox(15);
        box.setPadding(new Insets(20));

        Label pct = new Label("Course Attendance: 86%");
        pct.setStyle("-fx-font-size: 20px; -fx-text-fill: #27ae60; -fx-font-weight: bold;");
        ProgressBar pb = new ProgressBar(0.86);
        pb.setPrefWidth(300);

        box.getChildren().addAll(new Label("Attendance Overview"), pct, pb);
        return box;
    }

    // ================= 7. HELPERS & DB UTILITIES =================
    private ObservableList<StudentAttendance> fetchAttendanceData(String courseId) {
        ObservableList<StudentAttendance> list = FXCollections.observableArrayList();
        String sql = "SELECT u.name, a.status FROM Users u JOIN Attendance a ON u.username = a.student_id WHERE a.course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new StudentAttendance(rs.getString("name"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ObservableList<Assignment> fetchAssignments(String courseId) {
        ObservableList<Assignment> list = FXCollections.observableArrayList();
        String sql = "SELECT title, due_date, 'Pending' as grade FROM Course_Assignments WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Assignment(
                        rs.getString("title"),
                        rs.getString("due_date"),
                        rs.getString("grade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Course> fetchEnrolledCourses(String username) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT c.id, c.course_name, c.course_type FROM Courses c " +
                "JOIN Student_Course sc ON c.id = sc.course_id " +
                "JOIN Users u ON sc.student_id = u.username WHERE u.username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("id"), rs.getString("course_name"), rs.getString("course_type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Course> fetchInstructorCourses(String username) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT c.id, c.course_name, c.course_type FROM Courses c " +
                "JOIN Instructor_Course ic ON c.id = ic.course_id " +
                "JOIN Users u ON ic.instructor_id = u.username WHERE u.username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getString("id"), rs.getString("course_name"), rs.getString("course_type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private HBox createNavBar(String titleText) {
        HBox nav = new HBox();
        nav.setPadding(new Insets(15, 30, 15, 30));
        nav.setStyle("-fx-background-color: #1a2a3a;");
        nav.setAlignment(Pos.CENTER_LEFT);

        Label brand = new Label(titleText);
        brand.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button logout = new Button("Logout");
        logout.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        logout.setOnAction(e -> showLoginScreen());

        nav.getChildren().addAll(brand, spacer, logout);
        return nav;
    }

    private VBox createCourseCard(Course c) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setPrefSize(250, 120);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");

        Label t = new Label(c.getCourseName());
        t.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label ty = new Label(c.getCourseType());
        ty.setStyle("-fx-text-fill: #7f8c8d;");

        card.getChildren().addAll(t, ty);
        return card;
    }

    private void applyScene(Pane root) {
        Scene scene = new Scene(root, 1250, 650);
        window.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}