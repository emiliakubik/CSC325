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
        cardLayout.show(mainPanel, "Edit Employee");
        // Add logic to edit employee
    }

    // Show the View Employees panel
    private void showViewEmployeesPanel() {
        JPanel viewEmployeesPanel = new JPanel();
        viewEmployeesPanel.setLayout(new BorderLayout());

        List<Employee> employees = employeeManagementSystem.getEmployees();

        String columnNames[] = {"Employee ID", "Full Name", "Email", "Phone Number"};
        Object data[][] = new Object[employees.size()][4];

        for(int i = 0; i < employees.size(); i++){
            Employee emp = employees.get(i);
            data[i][0] = emp.getEmployeeID();
            data[i][1] = emp.getFullName();
            data[i][2] = emp.getEmail();
            data[i][3] = emp.getPhoneNumber();
        }

        JTable employeeTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        viewEmployeesPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(viewEmployeesPanel, "View Employees");

        cardLayout.show(mainPanel, "View Employees");
    }

    // Show the Delete Employee panel
    private void showDeleteEmployeePanel() {
        cardLayout.show(mainPanel, "Delete Employee");
        // Add logic to delete employee 
    }

    // Show the Manage Job History panel
    private void showJobHistoryPanel() {
        cardLayout.show(mainPanel, "Job History");
        // Add logic to manage job history
    }
}

/*JPanel viewEmployeesPanel = new JPanel();
viewEmployeesPanel.setLayout(new BorderLayout());
mainPanel.add(viewEmployeesPanel, "View Employees");

cardLayout.show(mainPanel, "View Employees");
List<Employee> employees = employeeManagementSystem.getEmployees();

String columnNames[] = {"employee ID", "Full name", "Email", "Phone number"};
Object data[][] = new Object[employees.size()][4];

for(int i = 0; i < employees.size(); i++){
    Employee emp = employees.get(i);
    data[i][0] = emp.getEmployeeID();
    data[i][1] = emp.getFullName();
    data[i][2] = emp.getEmail();
    data[i][3] = emp.getPhoneNumber();
}

JTable employeeTable = new JTable(data, columnNames);
JScrollPane scrollPane = new JScrollPane(employeeTable);
viewEmployeesPanel.add(scrollPane, BorderLayout.CENTER);

mainPanel.add(viewEmployeesPanel, "View Employees");

cardLayout.show(mainPanel, "View Employees");*/