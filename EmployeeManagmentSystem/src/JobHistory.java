
public class JobHistory {
    
    private String employeeName;
    private String jobTitle;
    private String companyName;
    private String startDate;
    private String endDate;
    private String jobDescription;
    private String department;
    private String position;
    //fields needed only if employee has a past job
    private String pastJobTitle;
    private String pastJobDuration; //to be entered in months
    private String reasonForLeaving;
    private Employee associatedEmployee;

    //job details constructor
    public JobHistory(String employeeName, String jobTitle, String companyName, String startDate, String endDate, String jobDescription, String department, String position){
        this.employeeName = employeeName;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobDescription = jobDescription;
        this.department = department;
        this.position = position;
    }
    //constructor with job details for employees with past jobs
    public JobHistory(String jobTitle, String companyName, String startDate, String endDate, String jobDescription, String department, String position, String pastJobTitle, String pastJobDuration, String reasonForLeaving){
    //since most of the job information has already been referred, I'll just refer the instances of the past job variables
    this.pastJobDuration = pastJobDuration;
    this.pastJobTitle = pastJobTitle;
    this.reasonForLeaving = reasonForLeaving;
    }

    //creating my getters and setters for each input field
    public String getEmployeeName() {return employeeName;}
    public String getJobTitle() {return jobTitle;}
    public void setJobTitle(String jobTitle){this.jobTitle = jobTitle;}
    public String getCompanyName() {return companyName;}
    public void setCompanyName(String companyName){this.companyName = companyName;}
    public String getStartDate() {return startDate;}
    public void setStartDate(String startDate){this.startDate = startDate;}
    public String getEndDate() {return endDate;}
    public void setEndDate(String endDate){this.endDate = endDate;}
    public String getJobDescription() {return jobDescription;}
    public void setJobDescription(String jobDescription){this.jobDescription = jobDescription;}
    public String getDepartment() {return department;}
    public void setDepartment(String department){this.department = department;}
    public String getPosition() {return position;}
    public void setPosition(String position){this.position = position;}
    public String getPastJobTitle() {return pastJobTitle;}
    public void setPastJobTitle(String pastJobTitle){this.pastJobTitle = pastJobTitle;}
    public String getPastJobDuration() {return pastJobDuration;}
    public void setPastJobDuration(String pastJobDuration){this.pastJobDuration = pastJobDuration;}
    public String getReasonForLeaving() {return reasonForLeaving;}
    public void setReasonForLeaving(String reasonForLeaving){this.reasonForLeaving = reasonForLeaving;}

    //checking to see if any fields are empty
    public boolean validateFields() {
    if (jobTitle == null || jobTitle.isEmpty() ||
        companyName == null || companyName.isEmpty() ||
        startDate == null || startDate.isEmpty() ||
        endDate == null || endDate.isEmpty() ||
        department == null || department.equalsIgnoreCase("Select Department") ||
        position == null || position.equalsIgnoreCase("Select Position")) {
        return false;
    }
    if (pastJobTitle != null && (!validatePastJobFields())) {
        return false;
    }
    return true;
    }
    private boolean validatePastJobFields() {
        return !(pastJobTitle.isEmpty() || pastJobDuration.isEmpty() || reasonForLeaving.isEmpty());
    }

}