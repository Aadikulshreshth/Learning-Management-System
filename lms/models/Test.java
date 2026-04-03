public class Test {
    public static void main(String[] args) {

        Student s1 = new Student("S1", "Aadi", "aadi@mail.com", "CSE");
        Instructor i1 = new Instructor("I1", "Sir", "sir@mail.com", "CS Dept");

        s1.viewDashboard();
        i1.viewDashboard();
    }
}