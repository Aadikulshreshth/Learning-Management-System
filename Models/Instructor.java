public class Instructor extends User {
    private String department;
    public Instructor(String userId, String name, String email, String department) {
        super(userId, name, email);
        this.department = department;
    }
  
    public String getDepartment() {
        return department;
    }
  
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void viewDashboard() {
        System.out.println("Instructor Dashboard");
    }
}
