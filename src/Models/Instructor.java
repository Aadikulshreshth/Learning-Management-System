package Models;

public class Instructor extends User {

    private String email;

    public Instructor(String id, String name, String email) {
        super(id, name, "instructor");
        this.email = email;
    }

    public String getId() {
        return super.getUserId();
    }

    public String getName() {
        return super.getName();
    }

    public String getEmail() {
        return email;
    }
}