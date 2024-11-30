import java.awt.*;
import java.util.List;
import javax.swing.*;

public class DashboardPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;

    public DashboardPanel(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.employeeManagementSystem = employeeManagementSystem;
    }

    // Method to show the dashboard
    public void showDashboard() {
        // Create a new panel for the dashboard
        JPanel dashboardPanel = new JPanel(new BorderLayout());

        // Add the default header to this dashboard panel
        addDefaultHeader(dashboardPanel);

        // Create a panel to display key indicators
        JPanel keyIndicatorsPanel = createKeyIndicatorsPanel();
        dashboardPanel.add(keyIndicatorsPanel, BorderLayout.CENTER);

        // Add the search bar to the dashboard
        SearchBar searchBar = new SearchBar(mainPanel, cardLayout, employeeManagementSystem);
        JPanel searchBarPanel = searchBar.createSearchPanel();
        dashboardPanel.add(searchBarPanel, BorderLayout.SOUTH);

        // Add the dashboard panel to the main panel and show it
        mainPanel.add(dashboardPanel, "Dashboard");
        cardLayout.show(mainPanel, "Dashboard");
    }

    // Utility method to add the default header
    private void addDefaultHeader(JPanel panel) {
        JLabel headerLabel = new JLabel("Welcome to the Employee Management System", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(0, 102, 204));
        headerLabel.setForeground(Color.WHITE);
        panel.add(headerLabel, BorderLayout.NORTH);
    }

    private JPanel createKeyIndicatorsPanel() {
        JPanel keyIndicatorsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        keyIndicatorsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        List<Employee> employees = employeeManagementSystem.getEmployees();
        int numberOfEmployees = employees != null ? employees.size() : 0;
        int numberOfSprintEvaluations = employeeManagementSystem.getTotalSprintEvaluations();


        JLabel totalEmployeesLabel = new JLabel("Total Employees: " + numberOfEmployees, JLabel.CENTER);
        totalEmployeesLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel sprintEvaluationsLabel = new JLabel("Total Sprint Evaluations: " + numberOfSprintEvaluations, JLabel.CENTER);
        sprintEvaluationsLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        keyIndicatorsPanel.add(totalEmployeesLabel);
        keyIndicatorsPanel.add(sprintEvaluationsLabel);

        return keyIndicatorsPanel;
    }
}
