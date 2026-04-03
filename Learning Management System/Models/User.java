public abstract class User{
    protected String UserID;
    protected String name;
    protected String email;

    public User(String UserID, String name, String emal){
        this.UserID=UserID;
        this.name=name;
        this.email=email;
    }
    //Getters
    public String GetUserID(){
        return UserID;
    }

    public String Getname(){
        return name;
    }

    public String Getemail(){
        return email;
    }

    //abstract method
    public abstract void viewDashboard();
}