import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class EmployeeManagementSystem{
    private static final String FILE_PATH = "empManagementSystem.txt";

    public EmployeeManagementSystem(){}

    public void addEmployee(Employee employee){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))){ //"true" allows appending to an already written file, rather than that previous data getting removed
            writer.write(employee.getEmployeeID() + ", " + employee.getFullName() + ", " + employee.getEmail() + ", " + employee.getPhoneNumber() + ", " + employee.getPosition() + ", " + employee.getStreet() + ", " + employee.getCity() + ", " + employee.getState() + ", " + employee.getZipCode() + ", " + employee.getBirthDay() + ", " + employee.getBirthMonth() + ", " + employee.getBirthYear() + ", " + employee.getGender());
            writer.newLine();
        } catch (IOException e){
            System.out.println("Unable to write to file.");
        }
    }

    public void editEmployee(Employee updatedEmp){
        List<Employee> employees = getEmployees();
        String removeID = updatedEmp.getEmployeeID();
        removeEmployee(removeID);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))){
            for (Employee emp : employees){
                if (emp.getEmployeeID().equals(updatedEmp.getEmployeeID())){
                    writer.write(updatedEmp.getEmployeeID() + ", " + updatedEmp.getFullName() + ", " + updatedEmp.getEmail() + ", " + updatedEmp.getPhoneNumber() + ", " + updatedEmp.getPosition() + ", " + updatedEmp.getStreet() + ", " + updatedEmp.getCity() + ", " + updatedEmp.getState() + ", " + updatedEmp.getZipCode() + ", " + updatedEmp.getBirthDay() + ", " + updatedEmp.getBirthMonth() + ", " + updatedEmp.getBirthYear() + ", " + updatedEmp.getGender());
                    writer.newLine();
                } else {
                    writer.newLine();
                }
            }
        }catch (IOException e){
            System.out.println("Unable to write to file.");
        }
    }

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
        }catch(IOException e){
            System.out.println("Unable to read from file.");
        }

        //rewrites the file with only what was in the temporary list
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            for(String employee : employeeList){
                writer.write(employee);
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println("Unable to write to file.");
        }

        //lets the GUI class know to output a success message
        return success;
    }

    public Employee getEmployeeById(String employeeId){
        for (Employee employee : getEmployees()) {
            if (employee.getEmployeeID().equals(employeeId)){
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getEmployees(){
        List<Employee> employeeList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while((line = reader.readLine()) != null){
                String[] details = line.split(",");
                if (details.length == 13){
                    String id = details[0];
                    String name = details[1];
                    String email = details[2];
                    String phone = details[3];
                    String position = details[4];
                    String street = details[5];
                    String city = details[6];
                    String state = details[7];
                    String zipCode = details[8];
                    String birthDay = details[9];
                    String birthMonth = details[10];
                    String birthYear = details[11];
                    String gender = details[12];

                    Employee employee = new Employee(id, name, email, phone, position, street, city, state, zipCode, birthDay, birthMonth, birthYear, gender);
                    employeeList.add(employee);
                }
            }
        } catch(IOException e){
            System.out.println("Unable to read from file.");
        }
        System.out.println("Size: " + employeeList.size());
        return employeeList;
    }
}