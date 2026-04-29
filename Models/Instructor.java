public class Instructor extends User {

    public Instructor(String userId, String name, String email) {
        super(userId, name, email);
        setRole("instructor");
    }
}