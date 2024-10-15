import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private JFrame frame;
    private JPanel mainPanel, buttonPanel;
    private CardLayout cardLayout;

    public GUI() {
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
        cardLayout.show(mainPanel, "Create Employee");
        // Add logic to create employee
    }

    // Show the Edit Employee panel
    private void showEditEmployeePanel() {
        cardLayout.show(mainPanel, "Edit Employee");
        // Add logic to edit employee
    }

    // Show the View Employees panel
    private void showViewEmployeesPanel() {
        cardLayout.show(mainPanel, "View Employees");
        // Add logic to view employees
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