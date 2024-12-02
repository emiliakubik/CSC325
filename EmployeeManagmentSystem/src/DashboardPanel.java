import java.awt.*;
import javax.swing.*;
import java.util.List;

public class DashboardPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;

    public DashboardPanel(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.employeeManagementSystem = employeeManagementSystem;
    }

    public void showDashboard() {
        // Create the main dashboard panel with BorderLayout
        JPanel dashboardPanel = new JPanel(new BorderLayout());

        // Add header
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Welcome to WorkWatch", JLabel.CENTER);
        headerLabel.setFont(new Font("Verdana", Font.PLAIN, 30)); // Set font size and style
        headerLabel.setForeground(new Color(245, 245, 220));
        headerPanel.setBackground(new Color(20, 60, 20)); // Set header background color
        headerPanel.add(headerLabel);

        // Add header to the top section
        dashboardPanel.add(headerPanel, BorderLayout.NORTH);

        // Top section: Search Bar + Key Indicators
        JPanel topSectionPanel = new JPanel(new BorderLayout());

        // Add Search Bar on the left
        SearchBar searchBar = new SearchBar(mainPanel, cardLayout, employeeManagementSystem);
        JPanel searchBarPanel = searchBar.createSearchPanel();

        // Add a border around the search bar panel
        searchBarPanel.setBorder(BorderFactory.createLineBorder(new Color(20, 60, 20), 2)); // Set border color and wdith
        topSectionPanel.add(searchBarPanel, BorderLayout.WEST);

        // Add Key Indicators on the right
        JPanel keyIndicatorsPanel = createKeyIndicatorsPanel();
        topSectionPanel.add(keyIndicatorsPanel, BorderLayout.CENTER);

        // Add the top section to the dashboard
        dashboardPanel.add(topSectionPanel, BorderLayout.CENTER);

        // Add Graphs Placeholder panels
        JPanel graphsPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Create first Graph Panel
        JPanel graphPanel1 = createGraphPlaceholderPanel("Performance Graph 1");
        graphsPanel.add(graphPanel1);

        // Create second Graph Panel
        JPanel graphPanel2 = createGraphPlaceholderPanel("Performance Graph 2");
        graphsPanel.add(graphPanel2);

        dashboardPanel.add(graphsPanel, BorderLayout.SOUTH);

        // Add the dashboard to the main panel
        mainPanel.add(dashboardPanel, "Dashboard");
        cardLayout.show(mainPanel, "Dashboard");

        // Ensure layout is updated
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createKeyIndicatorsPanel() {
        JPanel keyIndicatorsPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // Set background color
        keyIndicatorsPanel.setBackground(Color.decode("#F2F2F2"));

        // Set the border color
        keyIndicatorsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(20, 60, 20), 2),
                "Key Indicators" // Title text
        ));

        List<Employee> employees = employeeManagementSystem.getEmployees();

        // Fetch data
        int numberOfEmployees = employees != null ? employees.size() : 0;
        int numberOfSprintEvaluations = employeeManagementSystem.getTotalSprintEvaluations();

        // Create Labels
        JLabel totalEmployeesLabel = new JLabel("Total Employees: " + numberOfEmployees, JLabel.CENTER);
        JLabel sprintEvaluationsLabel = new JLabel("Total Sprint Evaluations: " + numberOfSprintEvaluations,
                JLabel.CENTER);

        keyIndicatorsPanel.add(totalEmployeesLabel);
        keyIndicatorsPanel.add(sprintEvaluationsLabel);

        return keyIndicatorsPanel;
    }

    private JPanel createGraphPlaceholderPanel(String title) {
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(20, 60, 20), 2), // Set the border color
                "Performance Graph" // Title text
        ));

        JLabel placeholder = new JLabel("Graph will appear here", JLabel.CENTER);
        placeholder.setOpaque(true);
        placeholder.setBackground(Color.decode("#F2F2F2"));
        placeholder.setForeground(Color.DARK_GRAY);
        graphPanel.add(placeholder, BorderLayout.CENTER);

        // Set a preferred size
        graphPanel.setPreferredSize(new Dimension(400, 300));

        return graphPanel;
    }
}
