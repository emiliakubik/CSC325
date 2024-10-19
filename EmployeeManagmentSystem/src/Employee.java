import java.lang.Math;
public class Employee {
    private String employeeID;
    private String fullName;
    private String email;
    private String phoneNumber;

    public Employee(String fullName, String email, String phoneNumber){
        this.employeeID = generateID();
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Employee(String employeeID, String fullName, String email, String phoneNumber){
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmployeeID(){
        return employeeID;
    }

    public void setEmployeeID(String employeeID){
        this.employeeID = employeeID;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void displayEmployeeInfo(){
        System.out.println(employeeID + " | " + fullName +  " | " + email + " | " + phoneNumber);
    }

    public String generateID(){
        String id = "";
        id += (char)((int)(Math.random() * 26) + 65);
        id += ((int)(Math.random() * 900) + 100);
        return id;
    }
}

