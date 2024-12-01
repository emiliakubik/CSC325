import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ViewAndEditEmployee {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;

    public ViewAndEditEmployee(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem){
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.employeeManagementSystem = employeeManagementSystem;
    }

    // Show the Edit Employee panel- is private so can only be called from showViewEmployeesPanel, is passed employeeID of whoever's name was clicked
    private void showEditEmployeePanel(String employeeId) {
        //finds that employee by their id and then all their info is derived
        Employee employee = employeeManagementSystem.getEmployeeById(employeeId);

        //collects components for editing employee info
        JTextField nameField = new JTextField(employee.getFullName());
        JTextField emailField = new JTextField(employee.getEmail());
        JTextField phoneNumberField = new JTextField(employee.getPhoneNumber());
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"Admin","Manager","Employee"});
        roleCombo.setSelectedItem(employee.getRole().trim());
        JTextField streetField = new JTextField(employee.getStreet());
        JTextField cityField = new JTextField(employee.getCity());
        JComboBox<String> stateCombo = new JComboBox<>(new String[]{"AL", "AK", "AZ", "AR", "AS", "CA", "CO", "CT", "DE", "DC", "FL","GA","GU","HI"
        ,"ID", "IL","IN","IA", "KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND", "OH","OK","OR","PA",
        "PR","RI","SC","SD","TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"});
        stateCombo.setSelectedItem(employee.getState().trim());
        JTextField zipCodeField = new JTextField(employee.getZipCode());
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female"});
        genderCombo.setSelectedItem(employee.getGender().trim());
        
        //edit option for birth date and hire date- same as in showCreateEmployeePanel
        JComboBox<String> dayCombo = new JComboBox<>();
        JComboBox<String> empDayCombo = new JComboBox<>();
        dayCombo.addItem("-day-");
        empDayCombo.addItem("-day-");
        for(int i = 1; i <= 31; i++){
            dayCombo.addItem(String.valueOf(i));
            empDayCombo.addItem(String.valueOf(i));
        }
        //sets it so that the saved info is automatically selected unless users chooses to change it by selecting something else
        dayCombo.setSelectedItem(employee.getBirthDay().trim());
        empDayCombo.setSelectedItem(employee.getEmploymentDay().trim());

        JComboBox<String> monthCombo = new JComboBox<>();
        JComboBox<String> empMonthCombo = new JComboBox<>();
        monthCombo.addItem("-month-");
        empMonthCombo.addItem("-month-");
        //all the options for month in drop down menu- for both dob and hire date
        for (int i = 1; i <= 12; i++){
            monthCombo.addItem(String.valueOf(i));
            empMonthCombo.addItem(String.valueOf(i));
        }
        //sets it so that the saved info is automatically selected unless users chooses to change it by selecting something else
        monthCombo.setSelectedItem(employee.getBirthMonth().trim());
        empMonthCombo.setSelectedItem(employee.getEmploymentMonth().trim());

        JComboBox<String> yearCombo = new JComboBox<>();
        JComboBox<String> empYearCombo = new JComboBox<>();
        yearCombo.addItem("-year-");
        empYearCombo.addItem("-year-");
        for(int i = 1950; i <= 2024; i++){
            yearCombo.addItem(String.valueOf(i));
            empYearCombo.addItem(String.valueOf(i));
        }
        //sets it so that the saved info is automatically selected unless users chooses to change it by selecting something else
        yearCombo.setSelectedItem(employee.getBirthYear().trim());
        empYearCombo.setSelectedItem(employee.getEmploymentYear().trim());
    
        //panel for editing an employee's info and setting the panels layout
        JPanel editEmployeePanel = new JPanel(new GridLayout(12, 2));

        //adding components to panel
        JLabel nameLabel = new JLabel("Name: ");
        SetStyle.setInstructionText(nameLabel);
        editEmployeePanel.add(nameLabel);
        editEmployeePanel.add(nameField);

        JLabel emailLabel = new JLabel("Email: ");
        SetStyle.setInstructionText(emailLabel);
        editEmployeePanel.add(emailLabel);
        editEmployeePanel.add(emailField);

        JLabel numberLabel = new JLabel("Phone Number: ");
        SetStyle.setInstructionText(numberLabel);
        editEmployeePanel.add(numberLabel);
        editEmployeePanel.add(phoneNumberField);

        JLabel roleLabel = new JLabel("Role: ");
        SetStyle.setInstructionText(roleLabel);
        editEmployeePanel.add(roleLabel);
        editEmployeePanel.add(roleCombo);

        JLabel streetLabel = new JLabel("Street Address: ");
        SetStyle.setInstructionText(streetLabel);
        editEmployeePanel.add(streetLabel);
        editEmployeePanel.add(streetField);

        JLabel cityLabel = new JLabel("City: ");
        SetStyle.setInstructionText(cityLabel);
        editEmployeePanel.add(cityLabel);
        editEmployeePanel.add(cityField);

        JLabel stateLabel = new JLabel("State: ");
        SetStyle.setInstructionText(stateLabel);
        editEmployeePanel.add(stateLabel);
        editEmployeePanel.add(stateCombo);

        JLabel zipLabel = new JLabel("Zip Code: ");
        SetStyle.setInstructionText(zipLabel);
        editEmployeePanel.add(zipLabel);
        editEmployeePanel.add(zipCodeField);

        JLabel genderLabel = new JLabel("Gender: ");
        SetStyle.setInstructionText(genderLabel);
        editEmployeePanel.add(genderLabel);
        editEmployeePanel.add(genderCombo);

        JLabel dobLabel = new JLabel("Date of Birth: ");
        SetStyle.setInstructionText(dobLabel);
        editEmployeePanel.add(dobLabel);
        JPanel dobPanel = new JPanel();
        dobPanel.add(dayCombo);
        dobPanel.add(monthCombo);
        dobPanel.add(yearCombo);
        SetStyle.setBackground(dobPanel);
        editEmployeePanel.add(dobPanel);

        JLabel hireLabel = new JLabel("Date of Employment: ");
        SetStyle.setInstructionText(hireLabel);
        editEmployeePanel.add(hireLabel);
        JPanel hirePanel = new JPanel();
        hirePanel.add(empDayCombo);
        hirePanel.add(empMonthCombo);
        hirePanel.add(empYearCombo);
        SetStyle.setBackground(hirePanel);
        editEmployeePanel.add(hirePanel);

        JButton saveButton = new JButton("Save Changes");
        editEmployeePanel.add(saveButton);
        SetStyle.setBackground(editEmployeePanel);

        //add panel to the mainPanel with name "Edit Employee"
        mainPanel.add(editEmployeePanel, "Edit Employee");
        //show "Edit Employee" panel using cardLayout
        cardLayout.show(mainPanel, "Edit Employee");

        //action listener so that when save button is clicked, all the fields input, whether changed or not, gets taken and saved as a "new" employee and used as an argument to call edit employee method in EmployeeManagementSystem 
        saveButton.addActionListener(e -> {
            employee.setFullName(nameField.getText());
            employee.setEmail(emailField.getText());
            employee.setPhoneNumber(phoneNumberField.getText());
            employee.setRole((String) roleCombo.getSelectedItem());
            employee.setStreet(streetField.getText());
            employee.setCity(cityField.getText());
            employee.setState((String) stateCombo.getSelectedItem());
            employee.setZipCode(zipCodeField.getText());
            employee.setGender((String) genderCombo.getSelectedItem());
            employee.setBirthDay((String) dayCombo.getSelectedItem());
            employee.setBirthMonth((String) monthCombo.getSelectedItem());
            employee.setBirthYear((String) yearCombo.getSelectedItem());
            employee.setEmploymentDay((String) empDayCombo.getSelectedItem());
            employee.setEmploymentMonth((String) empMonthCombo.getSelectedItem());
            employee.setEmploymentYear((String) empYearCombo.getSelectedItem());

            employeeManagementSystem.editEmployee(employee);
            //success message shown to user
            JOptionPane.showMessageDialog(mainPanel, "Employee details updated successfully.");

            //Return to the View Employees panel after changes save
            cardLayout.show(mainPanel, "View Employees");
        });
    }

    // Show the View Employees panel
    public void showViewEmployeesPanel() {
        //setting up panel and table for overview of all employees (will only be able to see id, name, role and years employed here- to view rest of info must call on name)
        JPanel viewEmployeesPanel = new JPanel(new BorderLayout());

        //names of all the fields that will be displayed
        String[] columnNames = {"ID", "Name", "Role", "Years Employed"};
        //retrieving a list of all the currently saved employees in the system
        List<Employee> employees = employeeManagementSystem.getEmployees();
        //using a two-dimensional array whose domain will be the 4 fields and range will be however many employees are in the system
        Object[][] data = new Object[employees.size()][4];

        //for each employee in the system, retrieving the 4 fields of info 
        for(int i = 0; i < employees.size(); i++){
            data[i][0] = employees.get(i).getEmployeeID();
            data[i][1] = employees.get(i).getFullName();
            data[i][2] = employees.get(i).getRole();
            data[i][3] = employees.get(i).calculateEmployementLength();
        }

        //create and design a table for displaying this info
        JTable employeeTable = new JTable(data, columnNames);
        SetStyle.setTable(employeeTable);
        JTableHeader tableHeader = employeeTable.getTableHeader();
        SetStyle.setTableHeaderText(tableHeader);
        viewEmployeesPanel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        //create a drop down menu that can allow use to sort by name(alphabetically), role(alphabetically), and years employed(low-high)
        JPanel sortPanel = new JPanel();
        JLabel sortLabel = new JLabel("Sort: ");
        String[] sortOptions = {"by Name", "by role", "by Years Employed"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
        sortPanel.add(sortLabel);
        sortPanel.add(sortComboBox);
        SetStyle.setBackground(sortPanel);
        SetStyle.setBackground(viewEmployeesPanel);
        viewEmployeesPanel.add(sortPanel, BorderLayout.NORTH);

        //action listener, if any sort button is clicked, will use sort method to execute
        sortComboBox.addActionListener(e -> {
            String selectedOption = (String) sortComboBox.getSelectedItem();
            if(selectedOption != null){
                //using switch-case statement to respond to whatever button user clicks
                switch(selectedOption){
                    case "by Name":
                        employees.sort(Comparator.comparing(Employee::getFullName));
                        break;
                    case "by role":
                        employees.sort(Comparator.comparing(Employee::getRole));
                        break;
                    case "by Years Employed":
                        employees.sort(Comparator.comparing(Employee::calculateEmployementLength));
                        break;
                }
                //calls updateEmployeeTable which is defined lower down in GUI class
                Object[][] sortedData = updateEmployeeTable(employees);
                employeeTable.setModel(new DefaultTableModel(sortedData, columnNames));
            }
        });

                //add panel to the mainPanel with name "View Employees"
        mainPanel.add(viewEmployeesPanel, "View Employees");
        //show "View Employees" panel using cardLayout
        cardLayout.show(mainPanel, "View Employees");

        //adding mouse listener to allow user to click on each employee name to get the rest of their info/edit their info
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = employeeTable.rowAtPoint(e.getPoint());
                int col = employeeTable.columnAtPoint(e.getPoint());

                //only allows action to happen if name column is clicked, not any other column of their info
                if (col == 1){
                    String employeeId = (String) employeeTable.getValueAt(row, 0);
                    //calls private showEditEmployeePanel with the argument being the employee's ID
                    showEditEmployeePanel(employeeId);
                }
            }
        });
    }

    //takes the list of employees in the new sorted order and updates the data to be displayed correctly
    private Object[][] updateEmployeeTable(List<Employee> employees){
        //creates a 2D array to hold the emploeye data
        Object[][] data = new Object[employees.size()][4];
        //loops through each employee in the system
        for (int i = 0; i < employees.size(); i++){
            data[i][0] = employees.get(i).getEmployeeID();
            data[i][1] = employees.get(i).getFullName();
            data[i][2] = employees.get(i).getRole();
            data[i][3] = employees.get(i).calculateEmployementLength();
        }
        //return the new populated data array
        return data;
    }
}
