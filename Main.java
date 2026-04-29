import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        LMSManager manager = new LMSManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LMS MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. Add Assignment");
            System.out.println("5. Submit Assignment");
            System.out.println("6. View Students");
            System.out.println("7. View Courses");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            try {
                switch (choice) {

                    // ---------------- ADD STUDENT ----------------
                    case 1:
                        System.out.print("Enter ID: ");
                        String sid = sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();

                        Student s = new Student(sid, name, email, course);
                        manager.addStudent(s);
                        break;

                    // ---------------- ADD COURSE ----------------
                    case 2:
                        System.out.print("Enter Course ID: ");
                        String cid = sc.nextLine();

                        System.out.print("Enter Course Name: ");
                        String cname = sc.nextLine();

                        Course c = new Course(cid, cname);
                        manager.addCourse(c);
                        break;

                    // ---------------- ENROLL ----------------
                    case 3:
                        System.out.print("Enter Student ID: ");
                        String stuId = sc.nextLine();

                        System.out.print("Enter Course ID: ");
                        String courseId = sc.nextLine();

                        manager.enrollStudent(stuId, courseId);
                        break;

                    // ---------------- ADD ASSIGNMENT ----------------
                    case 4:
                        System.out.print("Enter Course ID: ");
                        String cId = sc.nextLine();

                        System.out.print("Assignment ID: ");
                        String aid = sc.nextLine();

                        System.out.print("Title: ");
                        String title = sc.nextLine();

                        System.out.print("Description: ");
                        String desc = sc.nextLine();

                        System.out.print("Deadline: ");
                        String deadline = sc.nextLine();

                        Assignment a = new Assignment(aid, title, desc, deadline, 100);
                        manager.addAssignment(cId, a);
                        break;

                    // ---------------- SUBMIT ASSIGNMENT ----------------
                    case 5:
                        System.out.print("Enter Student ID: ");
                        String sId = sc.nextLine();

                        System.out.print("Enter Course ID: ");
                        String coId = sc.nextLine();

                        System.out.print("Enter Assignment ID: ");
                        String asId = sc.nextLine();

                        System.out.print("Enter Content: ");
                        String content = sc.nextLine();

                        Course courseObj = manager.findCourse(coId);
                        Assignment found = null;

                        for (Assignment ass : courseObj.getAssignments()) {
                            if (ass.getAssignmentId().equals(asId)) {
                                found = ass;
                                break;
                            }
                        }

                        if (found != null) {
                            manager.submitAssignment(sId, coId, found, content);
                        } else {
                            System.out.println("Assignment not found");
                        }
                        break;

                    // ---------------- VIEW STUDENTS ----------------
                    case 6:
                        System.out.println("\n--- Students ---");
                        manager.displayStudents();
                        break;

                    // ---------------- VIEW COURSES ----------------
                    case 7:
                        System.out.println("\n--- Courses ---");
                        manager.displayCourses();
                        break;

                    // ---------------- EXIT ----------------
                    case 8:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}