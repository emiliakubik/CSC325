import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

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
        //date of birth and position fields
        JComboBox<String> dayCombo = new JComboBox<>();
        JComboBox<String> monthCombo = new JComboBox<>();
        JComboBox<String> yearCombo = new JComboBox<>();
        JComboBox<String> genderCombo = new JComboBox<>();

        String[] genders = {"Female", "Male"};
        for(String gender : genders){
            genderCombo.addItem(gender);
        }

        for(int i = 0; i <= 31; i++){
            dayCombo.addItem(String.valueOf(i));
        }

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months){
            monthCombo.addItem(month);
        }

        for(int i = 1950; i < 2024; i++){
            yearCombo.addItem(String.valueOf(i));
        }

        //panel for creating an employee and setting the panels layout
        JPanel createEmployeePanel = new JPanel();
        createEmployeePanel.setLayout(new GridLayout(12, 2));

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

        createEmployeePanel.add(new JLabel("Date of Birth"));
        createEmployeePanel.add(dayCombo);
        createEmployeePanel.add(monthCombo);
        createEmployeePanel.add(yearCombo);

        createEmployeePanel.add(new JLabel("Gender: "));
        createEmployeePanel.add(genderCombo);

        createEmployeePanel.add(createButton);

        //add panel to the mainPanel with name "Create Employee"
        mainPanel.add(createEmployeePanel, "Create Employee");

        //show "Create Employee" panel using cardLayout
        cardLayout.show(mainPanel, "Create Employee");

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

            //creating a new employee in Employee class and adding it the the system
            Employee newEmployee = new Employee(name, email, phoneNumber, position, street, city, state, zip, day, month, year, gender);
            employeeManagementSystem.addEmployee(newEmployee);
            JOptionPane.showMessageDialog(mainPanel, name + " added successfully.");
        });
    }
    
    // Show the Edit Employee panel
    private void showEditEmployeePanel(String employeeId) {
        Employee employee = employeeManagementSystem.getEmployeeById(employeeId);

        //components for editing employee info
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

        JComboBox<String> dayCombo = new JComboBox<>();
        for(int i = 1; i <= 31; i++){
            dayCombo.addItem(String.valueOf(i));
        }
        dayCombo.setSelectedItem(employee.getBirthDay().trim());

        JComboBox<String> monthCombo = new JComboBox<>();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for(String month : months){
            monthCombo.addItem(month);
        }
        monthCombo.setSelectedItem(employee.getBirthMonth().trim());

        JComboBox<String> yearCombo = new JComboBox<>();
        for(int i = 1950; i < 2024; i++){
            yearCombo.addItem(String.valueOf(i));
        }
        yearCombo.setSelectedItem(employee.getBirthYear().trim());

        JPanel editEmployeePanel = new JPanel(new GridLayout(12, 2));

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

        JButton saveButton = new JButton("Save Changes");
        editEmployeePanel.add(saveButton);

        mainPanel.add(editEmployeePanel, "Edit Employee");
        cardLayout.show(mainPanel, "Edit Employee");

        //action listener to save changes
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

            employeeManagementSystem.editEmployee(employee);
            JOptionPane.showMessageDialog(mainPanel, "Employee details updated successfully.");

            //Return to the View Employees panel after changes save
            cardLayout.show(mainPanel, "View Employees");
        });
    }

    // Show the View Employees panel
    private void showViewEmployeesPanel() {
        //setting up panel and table for overview of all employees (will only be able to see id, name and position here)
        JPanel viewEmployeesPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"ID", "Name", "Position"};
        List<Employee> employees = employeeManagementSystem.getEmployees();
        Object[][] data = new Object[employees.size()][3];

        for(int i = 0; i < employees.size(); i++){
            data[i][0] = employees.get(i).getEmployeeID();
            data[i][1] = employees.get(i).getFullName();
            data[i][2] = employees.get(i).getPosition();
        }

        JTable employeeTable = new JTable(data, columnNames);
        viewEmployeesPanel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        mainPanel.add(viewEmployeesPanel, "View Employees");
        cardLayout.show(mainPanel, "View Employees");

        //adding mouse listener to allow user to click on each employee to get the rest of their info
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = employeeTable.rowAtPoint(e.getPoint());
                int col = employeeTable.columnAtPoint(e.getPoint());

                //only allows action to happen if name column is clicked, not id nor position
                if (col == 1){
                    String employeeId = (String) employeeTable.getValueAt(row, 0);
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

        //setting up panel for removing a person and the way it's layout
        JPanel removeEmployeePanel = new JPanel();
        removeEmployeePanel.setLayout(new GridLayout(2, 2, 10, 10));

        //adding components to panel
        removeEmployeePanel.add(new JLabel("Employee ID: "));
        removeEmployeePanel.add(employeeIdField);
        removeEmployeePanel.add(new JLabel());
        removeEmployeePanel.add(removeButton);

        //adding this panel to the main panel
        mainPanel.add(removeEmployeePanel, "Remove Employee");

        //showing this panel 
        cardLayout.show(mainPanel, "Remove Employee");

        //action listener to the Remove button
        removeButton.addActionListener(e -> {
            String employeeID = employeeIdField.getText().trim();
            if(employeeID.isEmpty()){
                JOptionPane.showMessageDialog(mainPanel, "Please enter an Employee ID");
                return;
            }

            //finding and removing the employee
            boolean success = employeeManagementSystem.removeEmployee(employeeID);

            if(success){
                JOptionPane.showMessageDialog(mainPanel, "Employee " + employeeID + " removed successfully.");
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Employee " + employeeID + " not found.");
            }

            employeeIdField.setText("");
        });
    }

    // Show the Manage Job History panel
    private void showJobHistoryPanel() {
        // Initialize panel and layout for job history
        cardLayout.show(mainPanel, "Job History");
        JPanel jobHistoryPanel = new JPanel(new GridLayout(13, 2, 10, 10));
    
        // Text fields for job details
        JTextField jobTitleField = new JTextField();
        JTextField companyNameField = new JTextField();
        JTextField startDateField = new JTextField("YYYY-MM-DD");
        JTextField endDateField = new JTextField("YYYY-MM-DD");
        JTextField jobDescriptionField = new JTextField();
        JTextField pastJobTitleField = new JTextField();
        JTextField pastJobDurationField = new JTextField();
        JTextField reasonForLeavingField = new JTextField();
    
        // Dropdowns for department and position
        String[] departments = {"Select Department", "Engineering", "Human Resources", "Finance", "Marketing", "Operations Management", "Production"};
        Map<String, String[]> positionsMap = new HashMap<>();
        positionsMap.put("Engineering", new String[]{"Engineer","Junior Engineer", "Graduate Engineer","Project Engineer","Senior Engineer", "Lead Engineer", "Chief Engineer", "Executive Engineer" });
        positionsMap.put("Human Resources", new String[]{"Human Resources Specialist", "Analyst", "HR Specialist", "Talent Aquisition Specialist", "Human Resources Manager", "Human Resources Executive"});
        positionsMap.put("Finance", new String[]{"Accountant", "Accounting Clerk","Financial Analyst","Administrative Specialist", "Auditor", "Controller", "Chief Financial Officer"});
        positionsMap.put("Marketing", new String[]{"Marketing Assistant", "Marketing Manager", "Communications Manager", "Content Strategist", "SEO Specialist"});
        positionsMap.put("Operations Management", new String[]{" Operations Coordinator", "Operations Analyst", "Operations Supervisor", "Operations Manager", "Project Manager", "Director of Operations"});
        positionsMap.put("Production", new String[]{"Assembly Operator", "Control Room Operator", "Offshore Production Operator", "Onshore Production Operator", "Production Operations Supervisor", "Director of Manufacturing", "VP of Production"});
        JComboBox<String> departmentDropDown = new JComboBox<>(departments);
        JComboBox<String> positionDropDown = new JComboBox<>(new String[]{"Select Position"});
    
        //this populates the position field based on the input of the department field
        departmentDropDown.addActionListener(e -> {
            String selectedDepartment = (String) departmentDropDown.getSelectedItem();
            positionDropDown.removeAllItems();
            positionDropDown.addItem("Select Position");
            if (positionsMap.containsKey(selectedDepartment)) {
                for (String position : positionsMap.get(selectedDepartment)) {
                    positionDropDown.addItem(position);
                }
            }
        });
    
        //makes the past job history panel visible if the checkbox is filled.
        JCheckBox hasPastJobCheckBox = new JCheckBox("Has This Person Previously Been Employed?");
        JPanel pastJobDetailsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        pastJobDetailsPanel.setVisible(false);
        hasPastJobCheckBox.addActionListener(e -> pastJobDetailsPanel.setVisible(hasPastJobCheckBox.isSelected()));
  
        addFields(jobHistoryPanel, new String[]{
            "Job Title", "Company Name", "Start Date", "End Date", "Job Description", "Department", "Position"
        }, new JComponent[]{
            jobTitleField, companyNameField, startDateField, endDateField, jobDescriptionField, departmentDropDown, positionDropDown
        });
    
  
        jobHistoryPanel.add(hasPastJobCheckBox);
        jobHistoryPanel.add(new JLabel());
        pastJobDetailsPanel.add(new JLabel("Past Job Title:"));
        pastJobDetailsPanel.add(pastJobTitleField);
        pastJobDetailsPanel.add(new JLabel("Past Job Duration (Months):"));
        pastJobDetailsPanel.add(pastJobDurationField);
        pastJobDetailsPanel.add(new JLabel("Reason for Leaving:"));
        pastJobDetailsPanel.add(reasonForLeavingField);
        jobHistoryPanel.add(pastJobDetailsPanel);
    
        //creating the submit button
        JButton submitButton = new JButton("Save Job History");
        jobHistoryPanel.add(new JLabel()); 
        jobHistoryPanel.add(submitButton);
    
        // Add job history panel to main panel
        mainPanel.add(jobHistoryPanel, "Job History");
        cardLayout.show(mainPanel, "Job History");
    
        //action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                String jobTitle = jobTitleField.getText();
                String companyName = companyNameField.getText();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String jobDescription = jobDescriptionField.getText();
                String department = (String) departmentDropDown.getSelectedItem();
                String position = (String) positionDropDown.getSelectedItem();
    
                if ("Select Department".equals(department) || "Select Position".equals(position)) {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a valid department and position.");
                    return;
                }
                JobHistory jobHistory;
                if (hasPastJobCheckBox.isSelected()) {
                    String pastJobTitle = pastJobTitleField.getText();
                    String pastJobDuration = pastJobDurationField.getText();
                    String reasonForLeaving = reasonForLeavingField.getText();
                    if (pastJobTitle.isEmpty() || pastJobDuration.isEmpty() || reasonForLeaving.isEmpty()) {
                        JOptionPane.showMessageDialog(mainPanel, "Please fill all past job details.");
                        return;
                    }
                    jobHistory = new JobHistory(jobTitle, companyName, startDate, endDate, jobDescription, department, position,
                            pastJobTitle, pastJobDuration, reasonForLeaving);
                } else {
                    jobHistory = new JobHistory(jobTitle, companyName, startDate, endDate, jobDescription, department, position);
                }
                if (!jobHistory.validateFields()) {
                    JOptionPane.showMessageDialog(mainPanel, "Please fill all required fields.");
                    return;
                }
    
        //saving confirmation message
        JOptionPane.showMessageDialog(mainPanel, "Job History Saved.\n" + jobHistory.toString());
        //throws an exception if there was an error while saving
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainPanel, "Error Saving. " + ex.getMessage());
         }
     });
    }
    
  //this is what adds the fields I created to the panel
    private void addFields(JPanel panel, String[] labels, JComponent[] fields) {
        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i] + ":"));
            panel.add(fields[i]);
        }
    }
}
    