package Models;

public class Student extends User {

    private String email;

    public Student(String id, String name, String email) {
        super(id, name, "student");
        this.email = email;
    }

    public String getId() {
        return super.getUserId();
    }

    public String getName() {
        return super.name;
    }

    public String getEmail() {
        return email;
    }
}