import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementSystem{
    private List<Employee> employeeList;

    public EmployeeManagementSystem(){
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void removeEmployee(String employeeID){
        Employee employee = getEmployeeByID(employeeID);
        if(employee != null){
            employeeList.remove(employee);
        }
    }

    public Employee getEmployeeByID(String employeeID){
        for(Employee employee : employeeList){
            if (employee.getEmployeeID().equals(employeeID)){
                return employee;
            }
        }
        return null;
    }

    public void viewEmployeeList(){
        if (employeeList.isEmpty()){
            System.out.println("No employees in the system.");
        } else{
            for(Employee employee : employeeList){
                employee.displayEmployeeInfo();
                System.out.println("-----------------------------------");

            }
        }
    }
}