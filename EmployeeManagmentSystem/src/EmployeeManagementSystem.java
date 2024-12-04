import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementSystem{
    //this is the txt file that all employee info will be kept on
    private static final String FILE_PATH = "EmpManagementSystem.txt";

    //this method gets passed an employee from Employee class, takes this info and writes it into the txt file
    public void addEmployee(Employee employee){
        try{
            List<String> fileContent = new ArrayList<>();
            boolean sprintEvalSectionFound = false;
            int sprintEvalIndex = -1;

            try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
                String line;
                int currentIndex = 0;
                while((line = reader.readLine()) != null){
                    fileContent.add(line);
                    if(line.trim().equals("# Sprint Evaluations")){
                        sprintEvalSectionFound = true;
                        sprintEvalIndex = currentIndex;
                    }
                    currentIndex++;
                }
            }

            String employeeData = employee.getEmployeeID() + ", " + employee.getFullName() + ", " + employee.getEmail() + ", " + employee.getPhoneNumber() + ", " + employee.getRole() + ", " + employee.getStreet() + ", " + employee.getCity() + ", " + employee.getState() + ", " + employee.getZipCode() + ", " + employee.getBirthDay() + ", " + employee.getBirthMonth() + ", " + employee.getBirthYear() + ", " + employee.getGender() + ", " + employee.getEmploymentDay() + ", " + employee.getEmploymentMonth() + ", " + employee.getEmploymentYear();
            if(sprintEvalSectionFound && sprintEvalIndex != -1){
                fileContent.add(sprintEvalIndex, employeeData);
            } else {
                fileContent.add(employeeData);
            }
            
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))){ //"true" allows appending to an already written file, rather than that previous data getting removed
                for(String line : fileContent){
                    writer.write(line);
                    writer.newLine();
                }
            } 
        } catch (IOException e){ //exception handling incase for some reason this info cannot be appended to the file
            System.out.println("Unable to write to file.");
        }
    }

    public void saveEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Employee employee : employees) {
                // Write employee info
                writer.write(employee.getEmployeeID() + ", " + employee.getFullName() + ", " + employee.getEmail() + ", " + employee.getPhoneNumber() + ", " + employee.getRole() + ", " + employee.getStreet() + ", " + employee.getCity() + ", " + employee.getState() + ", " + employee.getZipCode() + ", " + employee.getBirthDay() + ", " + employee.getBirthMonth() + ", " + employee.getBirthYear() + ", " + employee.getGender() + ", " + employee.getEmploymentDay() + ", " + employee.getEmploymentMonth() + ", " + employee.getEmploymentYear());
                writer.newLine();

                // Write job histories
                for (JobHistory jobHistory : employee.getJobHistories()) {
                    writer.write("# JobHistory");
                    writer.newLine();
                    writer.write(jobHistory.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to save employees to file.");
        }
    }

    //this method gets passed an evaluation from Sprint Eval class, takes this info and writes it into the txt file
    public void addSprintEval(SprintEvaluation eval){
        try{
            boolean headerExists = false;
            List<String> fileContent = new ArrayList<>();
            
            try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
                String line;
                while((line = reader.readLine()) != null){
                    fileContent.add(line);
                    if(line.trim().equals("# Sprint Evaluations")){
                        headerExists = true;
                    }
                }
            }

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
                for(String line : fileContent){
                    writer.write(line);
                    writer.newLine();
                }

                //this is the way the text file is organized for easier reading later on
                if(!headerExists){
                    writer.write("# Sprint Evaluations");
                    writer.newLine();
                }

                writer.write(eval.getName() + ", " + eval.getTitle() + ", " + eval.getEvalDay() + ", " + eval.getEvalMonth() + ", " + eval.getEvalYear() + ", " + eval.getEvalQuestion1() + ", " + eval.getEvalQuestion2() + ", " + eval.getEvalQuestion3());
            } 
        } catch (IOException e){
            System.out.println("Unable to write to file.");
        }
    }

    //this method gets passed a job history from Job History class, takes this info and writes it into the txt file
    public void addJobHistory(JobHistory jobHistory){
        try{
            boolean headerExists = false;
            List<String> fileContent = new ArrayList<>();

            try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
                String line;
                while((line = reader.readLine()) != null){
                    fileContent.add(line);
                    if(line.trim().equals("# Job History")){
                        headerExists = true;
                    }
                }
            }

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
                for(String line : fileContent){
                    writer.write(line);
                    writer.newLine();
                }

                //this is the way the text file is organized for easier reading later on
                if(!headerExists){
                    writer.write("# Job History");
                    writer.newLine();
                }

                writer.write(jobHistory.getEmployeeName() + ", " + jobHistory.getJobTitle() + ", " + jobHistory.getCompanyName() + ", " + jobHistory.getStartDate() + ", " + jobHistory.getEndDate() + ", " + jobHistory.getJobDescription() + ", " + jobHistory.getDepartment() + ", " + jobHistory.getPosition());
            }
        } catch (IOException e){
            System.out.println("Unable to write to file.");
        }
    }

    //this method gets passed a "new" employee that actually the same employee that already exists but with new updated info, method removes the old employee and all their info and adds this new one
    public void editEmployee(Employee updatedEmp){
        List<Employee> employees = getEmployees();
        String removeID = updatedEmp.getEmployeeID();
        //calls removeEmployee method on this employee with its old info, so that this employee isn't just listed twice now
        removeEmployee(removeID);
        //writes in that employee with its new info into the txt file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))){
            for (Employee emp : employees){
                if (emp.getEmployeeID().equals(updatedEmp.getEmployeeID())){
                    writer.write(updatedEmp.getEmployeeID() + ", " + updatedEmp.getFullName() + ", " + updatedEmp.getEmail() + ", " + updatedEmp.getPhoneNumber() + ", " + updatedEmp.getRole() + ", " + updatedEmp.getStreet() + ", " + updatedEmp.getCity() + ", " + updatedEmp.getState() + ", " + updatedEmp.getZipCode() + ", " + updatedEmp.getBirthDay() + ", " + updatedEmp.getBirthMonth() + ", " + updatedEmp.getBirthYear() + ", " + updatedEmp.getGender() + ", " + updatedEmp.getEmploymentDay() + ", " + updatedEmp.getEmploymentMonth() + ", " + updatedEmp.getEmploymentYear());
                    writer.newLine();
                } else {
                    writer.newLine();
                }
            }
        }catch (IOException e){ //exception handling incase for some reason this info cannot be appended to the file
            System.out.println("Unable to write to file.");
        }
    }

    //this method is passed an employeesID and it finds that employee by its ID and completely removes it from the txt file
    public boolean removeEmployee(String employeeID){
        //create a new temporary list to hold all the employees minus the one being removed
        List<String> employeeList = new ArrayList<>();
        boolean success = false;

        //reading the file line by line, if finds employeeID, skips that list when inputting to the temporary list
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while((line = reader.readLine()) != null){
                if(!line.startsWith(employeeID)){
                    employeeList.add(line);
                } else {
                    success = true;
                }
            }
        }catch(IOException e){ //exception handling incase for some reason info cannot be read from the file
            System.out.println("Unable to read from file.");
        }

        //rewrites the file with only what was in the temporary list
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            for(String employee : employeeList){
                writer.write(employee);
                writer.newLine();
            }
        }catch(IOException e){ //exception handling incase for some reason this info cannot be appended to the file
            System.out.println("Unable to write to file.");
        }

        //lets the GUI class know to output a success message
        return success;
    }

    //this method is passed an employee's ID and searches through the list of all employees in the system until it finds the one with that ID
    public Employee getEmployeeById(String employeeId){
        for (Employee employee : getEmployees()) {
            if (employee.getEmployeeID().equals(employeeId)){
                return employee;
            }
        }
        //if searches whole list and cannot find the employee, returns null
        return null;
    }

    public Employee getEmployeeByName(String fullName){
        for (Employee employee : getEmployees()) {
            if (employee.getFullName().equals(fullName)){
                return employee;
            }
        }
        //if searches whole list and cannot find the employee, returns null
        return null;
    }

    //this method returns a list of every employee that is saved in the system, along with each employees information
    public List<Employee> getEmployees(){
        List<Employee> employeeList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while((line = reader.readLine()) != null){
                String[] details = line.split(",");
                if (details.length == 16){
                    String id = details[0];
                    String name = details[1];
                    String email = details[2];
                    String phone = details[3];
                    String role = details[4];
                    String street = details[5];
                    String city = details[6];
                    String state = details[7];
                    String zipCode = details[8];
                    String birthDay = details[9].trim();
                    String birthMonth = details[10].trim();
                    String birthYear = details[11].trim();
                    String gender = details[12];
                    String employmentDay = details[13].trim();
                    String employmentMonth = details[14].trim();
                    String employmentYear = details[15].trim();

                    //to do this, it just calls the Employee constructor and creates a completely new employee
                    Employee employee = new Employee(id, name, email, phone, role, street, city, state, zipCode, birthDay, birthMonth, birthYear, gender, employmentDay, employmentMonth, employmentYear);
                    employeeList.add(employee);
                }
            }
        } catch(IOException e){ //exception handling incase for some reason info cannot be read from the file
            System.out.println("Unable to read from file. " + FILE_PATH + " " + System.getProperty("user.dir"));
        }
        return employeeList;
    }

    //looks for "# Sprint Evaluations" section in file and reads from there
    public List<String> getSprintEvals(Employee employee){
        List<String> employeeEvals = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            boolean sprintEvalSectionFound = false;
            
            while((line = reader.readLine()) != null){
                line = line.trim();

                if(line.equals("# Sprint Evaluations")){
                    sprintEvalSectionFound = true;
                }

                if (sprintEvalSectionFound){
                    if(line.startsWith(employee.getFullName().trim())){
                        employeeEvals.add(line);
                    }
                }
            }
        } catch(IOException e){
            System.out.println("Unable to read from file.");
        }
        return employeeEvals;
    }

    public int getTotalSprintEvaluations() {
        int sprintEvalCount = 0;
        boolean sprintEvalSectionFound = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
    
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals("# Sprint Evaluations")) {
                    sprintEvalSectionFound = true;
                    continue;
                }
                if (sprintEvalSectionFound && !line.isEmpty()) {
                    sprintEvalCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file.");
        }
        return sprintEvalCount;
    }

    //looks for "# Job History" section in file and reads from there
    public List<String> getJobHistories(Employee employee){
        List<String> employeeJobHistories = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            boolean jobHistorySectionFound = false;

            while((line = reader.readLine()) != null){
                line = line.trim();

                if(line.equals("# Job History")){
                    jobHistorySectionFound = true;
                }

                if(jobHistorySectionFound){
                    if(line.startsWith(employee.getFullName().trim())){
                        employeeJobHistories.add(line);
                    }
                }
            }
        } catch(IOException e){
            System.out.println("Unable to read from file.");
        }
        return employeeJobHistories;
    }
}