import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

        addButton("Edit Employee", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditEmployeePanel();
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
        mainPanel.add(new JPanel(), "Edit Employee");   // Placeholder panel
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

        //panel for creating an employee and setting the panels layout
        JPanel createEmployeePanel = new JPanel();
        createEmployeePanel.setLayout(new GridLayout(4, 2));

        //adding components to panel
        createEmployeePanel.add(new JLabel("Name: "));
        createEmployeePanel.add(nameField);

        createEmployeePanel.add(new JLabel("Email: "));
        createEmployeePanel.add(emailField);
        
        createEmployeePanel.add(new JLabel("Phone Number: "));
        createEmployeePanel.add(phoneNumberField);
        createEmployeePanel.add(createButton);

        //add panel to the mainPanel with name "Create Employee"
        mainPanel.add(createEmployeePanel, "Create Employee");

        //show "Create Employee" panel using cardLayout
        cardLayout.show(mainPanel, "Create Employee");

        createButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();

            //creating a new employee in Employee class and adding it the the system
            Employee newEmployee = new Employee(name, email, phoneNumber);
            employeeManagementSystem.addEmployee(newEmployee);
            JOptionPane.showMessageDialog(mainPanel, name + " added successfully.");
        });
    }
    
    // Show the Edit Employee panel
    private void showEditEmployeePanel() {
        //GET RID OF THIS DONT NEED.
        cardLayout.show(mainPanel, "Edit Employee");
    }

    // Show the View Employees panel
    private void showViewEmployeesPanel() {
        JPanel viewEmployeesPanel = new JPanel(new BorderLayout());
        viewEmployeesPanel.setName("View Employees");

        List<Employee> employees = employeeManagementSystem.getEmployees();
        String[] columnNames = {"Employee ID", "Full Name", "Email", "Phone Number"};
        Object[][] data = new Object[employees.size()][4];

        for(int i = 0; i < employees.size(); i++){
            Employee emp = employees.get(i);
            data[i][0] = emp.getEmployeeID();
            data[i][1] = emp.getFullName();
            data[i][2] = emp.getEmail();
            data[i][3] = emp.getPhoneNumber();
        }

        JTable employeeTable = new JTable(data, columnNames);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        JPanel editFormPanel = new JPanel(new GridLayout());
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();

        editFormPanel.add(new JLabel("Employee ID: "));
        editFormPanel.add(idField);
        editFormPanel.add(new JLabel("Full Name: "));
        editFormPanel.add(nameField);
        editFormPanel.add(new JLabel("Email: "));
        editFormPanel.add(emailField);
        editFormPanel.add(new JLabel("Phone Number: "));
        editFormPanel.add(phoneField);

        idField.setEditable(false);

        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting() && employeeTable.getSelectedRow() != -1){
                int selectedRow = employeeTable.getSelectedRow();
                Employee selectedEmployee = employees.get(selectedRow);

                idField.setText(selectedEmployee.getEmployeeID());
                nameField.setText(selectedEmployee.getFullName());
                emailField.setText(selectedEmployee.getEmail());
                phoneField.setText(selectedEmployee.getPhoneNumber());
            }
        });

        JButton updateButton = new JButton("Update Employee");
        updateButton.addActionListener(event -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1){
                Employee emp = employees.get(selectedRow);
                emp.setFullName(nameField.getText());
                emp.setEmail(emailField.getText());
                emp.setPhoneNumber(phoneField.getText());

                employeeManagementSystem.editEmployee(emp);
                JOptionPane.showMessageDialog(mainPanel, "Employee updated successfully.");

                showEditEmployeePanel();
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Please select an employee to edit.");
            }
        });

        viewEmployeesPanel.add(scrollPane, BorderLayout.NORTH);
        viewEmployeesPanel.add(editFormPanel, BorderLayout.CENTER);
        viewEmployeesPanel.add(updateButton, BorderLayout.SOUTH);


        //viewEmployeesPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(viewEmployeesPanel, "View Employees");

        viewEmployeesPanel.revalidate();
        viewEmployeesPanel.repaint();

        cardLayout.show(mainPanel, "View Employees");
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
        cardLayout.show(mainPanel, "Job History");
        // Add logic to manage job history
    }
}
