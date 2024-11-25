import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class GUI {

    private JFrame frame;
    private JPanel mainPanel, buttonPanel;
    private CardLayout cardLayout;

    private EmployeeManagementSystem employeeManagementSystem;

    public GUI() {
        employeeManagementSystem = new EmployeeManagementSystem();

        // Initialize the main frame
        frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create button panel for navigation
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        // Add buttons for each key feature
        addButton("Create Employee", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Employee button clicked.");
                showCreateEmployeePanel();
            }
        });

        addButton("View Employees", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("View Employees button clicked.");
                showViewEmployeesPanel();
            }
        });

        addButton("Delete Employee", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeleteEmployeePanel();
            }
        });

        addButton("Job History", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showJobHistoryPanel();
            }
        });

        addButton("Sprint Evaluations", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSprintEvalPanel();
            }
        });

        // Add the panels to the main panel (CardLayout for switching views)
        mainPanel.add(new JPanel(), "Create Employee"); // Placeholder panel
        mainPanel.add(new JPanel(), "View Employees");  // Placeholder panel
        mainPanel.add(new JPanel(), "Delete Employee"); // Placeholder panel
        mainPanel.add(new JPanel(), "Job History"); // Placeholder panel
        
        // Layout for the frame
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.WEST);  // Buttons on the left
        frame.add(mainPanel, BorderLayout.CENTER);  // Dynamic content in the center

        frame.setVisible(true);
    }

    // Add a button to the button panel
    private void addButton(String title, ActionListener actionListener) {
        JButton button = new JButton(title);
        button.addActionListener(actionListener);
        buttonPanel.add(button);
    }

    // Show the Create Employee panel
    private void showCreateEmployeePanel() {
        //components for the "Create Employee" panel
        JButton createButton = new JButton("Create Employee");
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField positionField = new JTextField();
        //address fields 
        JTextField streetField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField stateField = new JTextField();
        JTextField zipField = new JTextField();
        //date of birth and gender fields
        JComboBox<String> dayCombo = new JComboBox<>();
        JComboBox<String> monthCombo = new JComboBox<>();
        JComboBox<String> yearCombo = new JComboBox<>();
        JComboBox<String> genderCombo = new JComboBox<>();
        //date of hire fields
        JComboBox<String> empDayCombo = new JComboBox<>();
        JComboBox<String> empMonthCombo = new JComboBox<>();
        JComboBox<String> empYearCombo = new JComboBox<>();

        //adding a label on the drop down menu to explain to user what to select
        genderCombo.addItem("-gender-");
        //options for gender in drop down menu
        String[] genders = {"Female", "Male"};
        for(String gender : genders){
            genderCombo.addItem(gender);
        }

        //adding a label on the drop down menu to explain to user what to select
        dayCombo.addItem("-day-");
        empDayCombo.addItem("-day-");
        //all the options for day in drop down menu- for both dob and hire date
        for(int i = 1; i <= 31; i++){
            dayCombo.addItem(String.valueOf(i));
            empDayCombo.addItem(String.valueOf(i));
        }

        //adding a label on the drop down menu to explain to user what to select
        monthCombo.addItem("-month-");
        empMonthCombo.addItem("-month-");
        //all the options for month in drop down menu- for both dob and hire date
        for (int i = 1; i <= 12; i++){
            monthCombo.addItem(String.valueOf(i));
            empMonthCombo.addItem(String.valueOf(i));
        }

        //adding a label on the drop down menu to explain to user what to select
        yearCombo.addItem("-year-");
        empYearCombo.addItem("-year-");
        //all the options for year in drop down menu- for both dob and hire date
        for(int i = 1950; i <= 2024; i++){
            yearCombo.addItem(String.valueOf(i));
            empYearCombo.addItem(String.valueOf(i));
        }

        //panel for creating an employee and setting the panels layout
        JPanel createEmployeePanel = new JPanel(new GridLayout(12, 2));

        //adding components to panel
        createEmployeePanel.add(new JLabel("Name: "));
        createEmployeePanel.add(nameField);

        createEmployeePanel.add(new JLabel("Email: "));
        createEmployeePanel.add(emailField);
        
        createEmployeePanel.add(new JLabel("Phone Number: "));
        createEmployeePanel.add(phoneNumberField);

        createEmployeePanel.add(new JLabel("Position: "));
        createEmployeePanel.add(positionField);

        createEmployeePanel.add(new JLabel("Street Address: "));
        createEmployeePanel.add(streetField);

        createEmployeePanel.add(new JLabel("City: "));
        createEmployeePanel.add(cityField);

        createEmployeePanel.add(new JLabel("State: "));
        createEmployeePanel.add(stateField);

        createEmployeePanel.add(new JLabel("Zipcode: "));
        createEmployeePanel.add(zipField);

        createEmployeePanel.add(new JLabel("Gender: "));
        createEmployeePanel.add(genderCombo);

        createEmployeePanel.add(new JLabel("Date of Birth"));
        JPanel dobPanel = new JPanel();
        dobPanel.add(dayCombo);
        dobPanel.add(monthCombo);
        dobPanel.add(yearCombo);
        createEmployeePanel.add(dobPanel);

        createEmployeePanel.add(new JLabel("Date of employment: "));
        JPanel hirePanel = new JPanel();
        hirePanel.add(empDayCombo);
        hirePanel.add(empMonthCombo);
        hirePanel.add(empYearCombo);
        createEmployeePanel.add(hirePanel);

        createEmployeePanel.add(createButton);

        //add panel to the mainPanel with name "Create Employee"
        mainPanel.add(createEmployeePanel, "Create Employee");

        //show "Create Employee" panel using cardLayout
        cardLayout.show(mainPanel, "Create Employee");

        //action listener so that once create button is clicked, input in each box will be taken and put in constructor to create a new object in employee class
        createButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            String position = positionField.getText();
            String street = streetField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip = zipField.getText();
            String day = (String) dayCombo.getSelectedItem();
            String month = (String) monthCombo.getSelectedItem();
            String year = (String) yearCombo.getSelectedItem();
            String gender = (String) genderCombo.getSelectedItem();
            String empDay = (String) empDayCombo.getSelectedItem();
            String empMonth = (String) empMonthCombo.getSelectedItem();
            String empYear = (String) empYearCombo.getSelectedItem();

            //calling constructor to create a new employee in Employee class
            Employee newEmployee = new Employee(name, email, phoneNumber, position, street, city, state, zip, day, month, year, gender, empDay, empMonth, empYear);
            //adding employee to the system
            employeeManagementSystem.addEmployee(newEmployee);
            //success message displayed to user
            JOptionPane.showMessageDialog(mainPanel, name + " added successfully.");
        });
    }
    
    // Show the Edit Employee panel- is private so can only be called from showViewEmployeesPanel, is passed employeeID of whoever's name was clicked
    private void showEditEmployeePanel(String employeeId) {
        //finds that employee by their id and then all their info is derived
        Employee employee = employeeManagementSystem.getEmployeeById(employeeId);

        //collects components for editing employee info
        JTextField nameField = new JTextField(employee.getFullName());
        JTextField emailField = new JTextField(employee.getEmail());
        JTextField phoneNumberField = new JTextField(employee.getPhoneNumber());
        JTextField positionField = new JTextField(employee.getPosition());
        JTextField streetField = new JTextField(employee.getStreet());
        JTextField cityField = new JTextField(employee.getCity());
        JTextField stateField = new JTextField(employee.getState());
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
        editEmployeePanel.add(new JLabel("Name: "));
        editEmployeePanel.add(nameField);

        editEmployeePanel.add(new JLabel("Email: "));
        editEmployeePanel.add(emailField);

        editEmployeePanel.add(new JLabel("Phone Number: "));
        editEmployeePanel.add(phoneNumberField);

        editEmployeePanel.add(new JLabel("Position: "));
        editEmployeePanel.add(positionField);

        editEmployeePanel.add(new JLabel("Street Address: "));
        editEmployeePanel.add(streetField);

        editEmployeePanel.add(new JLabel("City: "));
        editEmployeePanel.add(cityField);

        editEmployeePanel.add(new JLabel("State: "));
        editEmployeePanel.add(stateField);

        editEmployeePanel.add(new JLabel("Zip Code: "));
        editEmployeePanel.add(zipCodeField);

        editEmployeePanel.add(new JLabel("Gender: "));
        editEmployeePanel.add(genderCombo);

        editEmployeePanel.add(new JLabel("Date of Birth: "));
        JPanel dobPanel = new JPanel();
        dobPanel.add(dayCombo);
        dobPanel.add(monthCombo);
        dobPanel.add(yearCombo);
        editEmployeePanel.add(dobPanel);

        editEmployeePanel.add(new JLabel("Date of Employment: "));
        JPanel hirePanel = new JPanel();
        hirePanel.add(empDayCombo);
        hirePanel.add(empMonthCombo);
        hirePanel.add(empYearCombo);
        editEmployeePanel.add(hirePanel);

        JButton saveButton = new JButton("Save Changes");
        editEmployeePanel.add(saveButton);

        //add panel to the mainPanel with name "Edit Employee"
        mainPanel.add(editEmployeePanel, "Edit Employee");
        //show "Edit Employee" panel using cardLayout
        cardLayout.show(mainPanel, "Edit Employee");

        //action listener so that when save button is clicked, all the fields input, whether changed or not, gets taken and saved as a "new" employee and used as an argument to call edit employee method in EmployeeManagementSystem 
        saveButton.addActionListener(e -> {
            employee.setFullName(nameField.getText());
            employee.setEmail(emailField.getText());
            employee.setPhoneNumber(phoneNumberField.getText());
            employee.setPosition(positionField.getText());
            employee.setStreet(streetField.getText());
            employee.setCity(cityField.getText());
            employee.setState(stateField.getText());
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
    private void showViewEmployeesPanel() {
        //setting up panel and table for overview of all employees (will only be able to see id, name, position and years employed here- to view rest of info must call on name)
        JPanel viewEmployeesPanel = new JPanel(new BorderLayout());

        //names of all the fields that will be displayed
        String[] columnNames = {"ID", "Name", "Position", "Years Employed"};
        //retrieving a list of all the currently saved employees in the system
        List<Employee> employees = employeeManagementSystem.getEmployees();
        //using a two-dimensional array whose domain will be the 4 fields and range will be however many employees are in the system
        Object[][] data = new Object[employees.size()][4];

        //for each employee in the system, retrieving the 4 fields of info 
        for(int i = 0; i < employees.size(); i++){
            data[i][0] = employees.get(i).getEmployeeID();
            data[i][1] = employees.get(i).getFullName();
            data[i][2] = employees.get(i).getPosition();
            data[i][3] = employees.get(i).calculateEmployementLength();
        }

        //create and design a table for displaying this info
        JTable employeeTable = new JTable(data, columnNames);
        viewEmployeesPanel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        //create a drop down menu that can allow use to sort by name(alphabetically), position(alphabetically), and years employed(low-high)
        JPanel sortPanel = new JPanel();
        JLabel sortLabel = new JLabel("Sort: ");
        String[] sortOptions = {"by Name", "by Position", "by Years Employed"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
        sortPanel.add(sortLabel);
        sortPanel.add(sortComboBox);
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
                    case "by Position":
                        employees.sort(Comparator.comparing(Employee::getPosition));
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

    // Show the Delete Employee panel
    private void showDeleteEmployeePanel() {
        //implementing attributes of "Remove Employee" button
        JButton removeButton = new JButton("Remove Employee");
        JTextField employeeIdField = new JTextField();

        //setting up panel for removing a person and the way it's laid out
        JPanel removeEmployeePanel = new JPanel();
        removeEmployeePanel.setLayout(new GridLayout(2, 2, 10, 10));

        //adding components to panel- must type in the employees ID to remove them
        removeEmployeePanel.add(new JLabel("Employee ID: "));
        removeEmployeePanel.add(employeeIdField);
        //adding a remove button to finalize the action
        removeEmployeePanel.add(new JLabel());
        removeEmployeePanel.add(removeButton);

        //add panel to the mainPanel with name "Remove Employees"
        mainPanel.add(removeEmployeePanel, "Remove Employee");

        //show "Remove Employee" panel using cardLayout
        cardLayout.show(mainPanel, "Remove Employee");

        //action listener- responds to the remove button being clicked 
        removeButton.addActionListener(e -> {
            String employeeID = employeeIdField.getText().trim();
            //if nothing is input before clicking the button, user receives a message asking for input
            if(employeeID.isEmpty()){
                JOptionPane.showMessageDialog(mainPanel, "Please enter an Employee ID");
                return;
            }

            //calls removeEmployee method in employeeManagementSystem- if successful, returns true
            boolean success = employeeManagementSystem.removeEmployee(employeeID);

            if(success){
                //if successful, user receives confirmation through success message
                JOptionPane.showMessageDialog(mainPanel, "Employee " + employeeID + " removed successfully.");
            } else {
                //if unsuccessful, user receives a message saying the employee was not found, therefore not removed
                JOptionPane.showMessageDialog(mainPanel, "Employee " + employeeID + " not found.");
            }
            //resets the text field to empty for next time use
            employeeIdField.setText("");
        });
    }

    //show the Sprint Evaluation Panel
    private void showSprintEvalPanel(){
        //setting up the panel to hold the content for sprint evaluations
        JPanel chooseEmployeePanel = new JPanel(new BorderLayout());
        //instructs the user on what action to take
        JLabel instructionLabel = new JLabel("Select an employee to create/view Sprint Evaluation.");
        //user first chooses which employee they would like to evaluate
        JComboBox<String> employeeComboBox = new JComboBox<>();
        //then choose whether they want to create a new eval or look at past ones
        JComboBox<String> actionComboBox = new JComboBox<>();

        //inputting all the employees entered into the system as options
        for(Employee employee : employeeManagementSystem.getEmployees()){
            employeeComboBox.addItem((String) employee.getFullName());
        }

        //inputting the two options: create new eval or view past evals
        actionComboBox.addItem("Create New Sprint Evaluation");
        actionComboBox.addItem("View Past Sprint Evaluations");
        //creating a panel to hold both combo boxes and them adding the boxes to the panel
        JPanel centerPanel = new JPanel();
        centerPanel.add(employeeComboBox);
        centerPanel.add(actionComboBox);

        //adding the instructions and the dropdown boxes to the main panel
        chooseEmployeePanel.add(instructionLabel, BorderLayout.NORTH);
        chooseEmployeePanel.add(centerPanel, BorderLayout.CENTER);

        //action listener to react when the choices are made from the drop down menus
        actionComboBox.addActionListener(e -> {
            //gets the selected employee
            Employee selectedEmployee = employeeManagementSystem.getEmployeeByName((String)employeeComboBox.getSelectedItem());
            //gets the selected action
            String selectedAction = (String) actionComboBox.getSelectedItem();
            //creates a new SprintEvaluationPanel instance for the selected employee
            SprintEvaluationPanel sprintEvaluationPanel = new SprintEvaluationPanel(selectedEmployee);

            //check which action was selected (Create or View)
            if(selectedAction.equals("Create New Sprint Evaluation")){
                //if create was selected, calls the create panel from SprintEvaluationPanel class
                mainPanel.add(sprintEvaluationPanel.getCreatePanel(), selectedEmployee.getFullName() + "'s Sprint Evaluation");
                cardLayout.show(mainPanel, selectedEmployee.getFullName() + "'s Sprint Evaluation");
            } else if(selectedAction.equals("View Past Sprint Evaluations")){
                //if view was selected, calls the view panel from SprintEvaluationPanel class
                mainPanel.add(sprintEvaluationPanel.viewSprintEvalPanel(selectedEmployee), selectedEmployee.getFullName() + "'s Sprint Evaluations");
                cardLayout.show(mainPanel, selectedEmployee.getFullName() + "'s Sprint Evaluations");
            }
        });

        //adds chooseEmployeePanel to the main panel and shows the panel
        mainPanel.add(chooseEmployeePanel, "Choose Employee");
        cardLayout.show(mainPanel, "Choose Employee");
    }

    //takes the list of employees in the new sorted order and updates the data to be displayed correctly
    private Object[][] updateEmployeeTable(List<Employee> employees){
        //creates a 2D array to hold the emploeye data
        Object[][] data = new Object[employees.size()][4];
        //loops through each employee in the system
        for (int i = 0; i < employees.size(); i++){
            data[i][0] = employees.get(i).getEmployeeID();
            data[i][1] = employees.get(i).getFullName();
            data[i][2] = employees.get(i).getPosition();
            data[i][3] = employees.get(i).calculateEmployementLength();
        }
        //return the new populated data array
        return data;
    }

    // Show the Manage Job History panel
    private void showJobHistoryPanel() {
        cardLayout.show(mainPanel, "Job History");
        // Add logic to manage job history
    }
}