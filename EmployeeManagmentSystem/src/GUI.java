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
        frame.setSize(800, 600); // Initial resolution when minimized
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Windowed fullscreen

        // Load the logo
        ImageIcon customIcon = new ImageIcon("EmployeeManagmentSystem/src/images/HR_APP_logo.png");

        // Set the logo as the window icon
        frame.setIconImage(customIcon.getImage());

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create button panel for navigation
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9, 1, 40, 30));

        // Set background color
        buttonPanel.setBackground(Color.decode("#F2F2F2"));

        // Add a black border to the right side
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK)); // Top, Left, Bottom, Right

        // Add the logo to the top-left
        ImageIcon logoIcon = new ImageIcon("EmployeeManagmentSystem/src/images/HR_APP_logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        buttonPanel.add(logoLabel); // Add logo to the button panel

        // Resize the image
        Image image = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(image);

        // Update the logo label with the resized icon
        logoLabel.setIcon(logoIcon);

        // Add buttons for each key feature
        addButton("Dashboard", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DashboardPanel dashboardPanel = new DashboardPanel(mainPanel, cardLayout, employeeManagementSystem);
                dashboardPanel.showDashboard();
            }
        });
        addButton("Create Employee", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateEmployeePanel createEmployeePanel = new CreateEmployeePanel(mainPanel, cardLayout,
                        employeeManagementSystem);
                createEmployeePanel.showCreateEmployeePanel();
            }
        });

        addButton("View Employees", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewAndEditEmployee viewAndEditEmployee = new ViewAndEditEmployee(mainPanel, cardLayout,
                        employeeManagementSystem);
                viewAndEditEmployee.showViewEmployeesPanel();
            }
        });

        addButton("Delete Employee", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteEmployeePanel deleteEmployeePanel = new DeleteEmployeePanel(mainPanel, cardLayout,
                        employeeManagementSystem);
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
                SprintEvaluationPanel sprintEvaluationPanel = new SprintEvaluationPanel(mainPanel, cardLayout,
                        employeeManagementSystem);
                sprintEvaluationPanel.showSprintEvalPanel();
            }
        });

        // Add a space panel after the last button (empty JPanel for padding at the
        // bottom)
        JPanel emptyPanel = new JPanel(); // Explicitly create the panel
        emptyPanel.setBackground(Color.decode("#F2F2F2")); // Set the desired background color
        buttonPanel.add(emptyPanel); // Add the empty panel to the button panel

        // Add the Exit button
        addButton("Exit", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the application when the Exit button is clicked
                System.exit(0);
            }
        });

        // Add the panels to the main panel (CardLayout for switching views)
        mainPanel.add(new JPanel(), "Create Employee"); // Placeholder panel
        mainPanel.add(new JPanel(), "View Employees"); // Placeholder panel
        mainPanel.add(new JPanel(), "Delete Employee"); // Placeholder panel
        mainPanel.add(new JPanel(), "Job History"); // Placeholder panel
        DashboardPanel dashboardPanel = new DashboardPanel(mainPanel, cardLayout, employeeManagementSystem);
        dashboardPanel.showDashboard();

        // Layout for the frame
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.WEST); // Buttons on the left
        frame.add(mainPanel, BorderLayout.CENTER); // Dynamic content in the center

        frame.setVisible(true); // Make the window visible
    }

    // Add a button to the button panel
    private void addButton(String title, ActionListener actionListener) {
        JButton button = new JButton(title);
        SetStyle.setButton(button);
        button.addActionListener(actionListener);
        buttonPanel.add(button);
    }
}
