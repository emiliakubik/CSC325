import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                CreateEmployeePanel createEmployeePanel = new CreateEmployeePanel(mainPanel, cardLayout, employeeManagementSystem);
                createEmployeePanel.showCreateEmployeePanel();
            }
        });

        addButton("View Employees", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewAndEditEmployee viewAndEditEmployee = new ViewAndEditEmployee(mainPanel, cardLayout, employeeManagementSystem);
                viewAndEditEmployee.showViewEmployeesPanel();
            }
        });

        addButton("Delete Employee", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteEmployeePanel deleteEmployeePanel = new DeleteEmployeePanel(mainPanel, cardLayout, employeeManagementSystem);
                deleteEmployeePanel.showDeleteEmployeePanel();
            }  
        });

        addButton("Job History", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JobHistoryPanel jobHistoryPanel = new JobHistoryPanel(mainPanel, cardLayout, employeeManagementSystem);
                jobHistoryPanel.showJobHistoryPanel();
            }
        });

        addButton("Sprint Evaluations", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SprintEvaluationPanel sprintEvaluationPanel = new SprintEvaluationPanel(mainPanel, cardLayout, employeeManagementSystem);
                sprintEvaluationPanel.showSprintEvalPanel();
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
        SetStyle.setButton(button);
        button.addActionListener(actionListener);
        buttonPanel.add(button);
    }
}
