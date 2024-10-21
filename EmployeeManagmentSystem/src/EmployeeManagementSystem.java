import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class EmployeeManagementSystem{
    private static final String FILE_PATH = "empManagementSystem.txt";

    public EmployeeManagementSystem(){}

    public void addEmployee(Employee employee){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            writer.write(employee.getEmployeeID() + ", " + employee.getFullName() + ", " + employee.getEmail() + ", " + employee.getPhoneNumber());
            writer.newLine();
        } catch (IOException e){
            System.out.println("Unable to write to file.");
        }
    }

    public void removeEmployee(String employeeID){
        List<String> employeeList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while((line = reader.readLine()) != null){
                if(!line.startsWith(employeeID)){
                    employeeList.add(line);
                }
            }
        }catch(IOException e){
            System.out.println("Unable to read from file.");
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            for(String employee : employeeList){
                writer.write(employee);
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println("Unable to write to file.");
        }
    }

    public List<Employee> getEmployees(){
        List<Employee> employeeList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while((line = reader.readLine()) != null){
                String[] details = line.split(",");
                if (details.length == 4){
                    String id = details[0];
                    String name = details[1];
                    String email = details[2];
                    String phone = details[3];

                    Employee employee = new Employee(name, email, phone);
                    employeeList.add(employee);
                }
            }
        } catch(IOException e){
            System.out.println("Unable to read from file.");
        }
        return employeeList;  // Added this to get rid of the error
    }

    // public List<Employee> getEmployees(){
    //     return employeeList;
    // }

    // public void viewEmployeeList(){ //may not need this method?
    //     if (employeeList.isEmpty()){
    //         System.out.println("No employees in the system.");
    //     } else{
    //         for(Employee employee : employeeList){
    //             employee.displayEmployeeInfo();
    //             System.out.println("-----------------------------------");

    //         }
    //     }
    // }
}