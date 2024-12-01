import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
public class Employee {
    //all the employee properties 
    private String employeeID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
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
    private List<SprintEvaluation> sprintEvals = new ArrayList<>();
    private List<JobHistory> jobHistories = new ArrayList<>();

    //constructor that initializes all the employee info, but this is for new employees being added because it generates them a new ID
    public Employee(String fullName, String email, String phoneNumber, String role, String street, String city, String state, String zipCode, String birthDay, String birthMonth, String birthYear, String gender, String employmentDay, String employmentMonth, String employmentYear){
        this.employeeID = generateID();
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
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
    public Employee(String employeeID, String fullName, String email, String phoneNumber, String role, String street, String city, String state, String zipCode, String birthDay, String birthMonth, String birthYear, String gender, String employmentDay, String employmentMonth, String employmentYear){
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
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

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
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

    public void addSprintEval(SprintEvaluation eval){
        sprintEvals.add(eval);
    }

    public List<SprintEvaluation> getSprintEval(){
        return sprintEvals;
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
    

    //this method is what will display the employee's information for the search:
    public String toString() {
        return "ID: " + employeeID + 
               ", Name: " + fullName + 
               ", Role: " + role + 
               ", Email: " + email + 
               ", Phone: " + phoneNumber + 
               ", Employed: " + calculateEmployementLength();
    }
    public void addJobHistory(JobHistory jobHistory) {
        jobHistories.add(jobHistory);
    }

    // Get all job histories
    public List<JobHistory> getJobHistories() {
        return jobHistories;
    }

    // Display job history information
    public String displayJobHistories() {
        StringBuilder historyDisplay = new StringBuilder("Job Histories:\n");
        for (JobHistory jobHistory : jobHistories) {
            historyDisplay.append(jobHistory.toString()).append("\n");
        }
        return historyDisplay.toString();
    }
}

