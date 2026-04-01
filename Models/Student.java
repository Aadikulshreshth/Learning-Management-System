public class Student extends User{
  private String courseEnrolled;
  public Student(String userId, String name, String email, String courseEnrolled){
    super(userId, name, email);
    this.courseEnrolled=courseEnrolled;
  }

  public String getCourseEnrolled(){
    return courseEnrolled;
  }

  public void setCourseEnrolled(String courseEnrolled){
    this.courseEnrolled=courseEnrolled;
  }

  @override
  public void viewDashboard(){
    System.out.println("Student Dashboard");
  }
}
