package lms.models;

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
    public String getUserID(){
        return UserID;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    //abstract method
    public abstract void viewDashboard();
}