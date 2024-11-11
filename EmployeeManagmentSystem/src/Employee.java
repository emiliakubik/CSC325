import java.lang.Math;
import java.time.LocalDate;
import java.time.Period;
public class Employee {
    //all the employee properties 
    private String employeeID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String position;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String gender;
    private String employmentDay;
    private String employmentMonth;
    private String employmentYear;

    //constructor that initializes all the employee info, but this is for new employees being added because it generates them a new ID
    public Employee(String fullName, String email, String phoneNumber, String position, String street, String city, String state, String zipCode, String birthDay, String birthMonth, String birthYear, String gender, String employmentDay, String employmentMonth, String employmentYear){
        this.employeeID = generateID();
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.gender = gender;
        this.employmentDay = employmentDay;
        this.employmentMonth = employmentMonth;
        this.employmentYear = employmentYear;
    }

    //constructor for an employee that already exists because they already have an ID
    public Employee(String employeeID, String fullName, String email, String phoneNumber, String position, String street, String city, String state, String zipCode, String birthDay, String birthMonth, String birthYear, String gender, String employmentDay, String employmentMonth, String employmentYear){
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.gender = gender;
        this.employmentDay = employmentDay;
        this.employmentMonth = employmentMonth;
        this.employmentYear = employmentYear;
    }

    //getter and setter methods for each property
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

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getZipCode(){
        return zipCode;
    }

    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }

    public String getBirthDay(){
        return birthDay;
    }

    public void setBirthDay(String birthDay){
        this.birthDay = birthDay;
    }

    public String getBirthMonth(){
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth){
        this.birthMonth = birthMonth;
    }

    public String getBirthYear(){
        return birthYear;
    }

    public void setBirthYear(String birthYear){
        this.birthYear = birthYear;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getEmploymentDay(){
        return employmentDay;
    }

    public void setEmploymentDay(String employmentDay){
        this.employmentDay = employmentDay;
    }

    public String getEmploymentMonth(){
        return employmentMonth;
    }

    public void setEmploymentMonth(String employmentMonth){
        this.employmentMonth = employmentMonth;
    }

    public String getEmploymentYear(){
        return employmentYear;
    }

    public void setEmploymentYear(String employmentYear){
        this.employmentYear = employmentYear;
    }

    //this method takes the employment day, month, and year and uses Period class to calculate the length (in years) from then to today to calculate how long the employee has been employed at this company
    public String calculateEmployementLength(){
        int hireDay = Integer.parseInt(employmentDay);
        int hireMonth = Integer.parseInt(employmentMonth);
        int hireYear = Integer.parseInt(employmentYear);

        LocalDate hireDate = LocalDate.of(hireYear, hireMonth, hireDay);
        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(hireDate, currentDate);

        double years = period.getYears() + (period.getMonths() / 12.0) + (period.getDays() / 365.0);
        return String.format("%.2f", years) + " years";
    }   

    //this method generates an employee ID- first character is a letter (A-Z) and the last 3 characters are each numbers (0-9)
    public String generateID(){
        String id = "";
        id += (char)((int)(Math.random() * 26) + 65);
        id += ((int)(Math.random() * 900) + 100);
        return id;
    }
}

