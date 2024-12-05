import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

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
        searchBarPanel.setBorder(BorderFactory.createLineBorder(new Color(20, 60, 20), 2)); // Set border color
        topSectionPanel.add(searchBarPanel, BorderLayout.WEST);

        // Add Key Indicators on the right
        JPanel keyIndicatorsPanel = createKeyIndicatorsPanel();
        topSectionPanel.add(keyIndicatorsPanel, BorderLayout.CENTER);

        // Add the top section to the dashboard
        dashboardPanel.add(topSectionPanel, BorderLayout.CENTER);

        // Add Graphs Placeholder panels
        JPanel graphsPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // Grid layout with 3 columns

        // Create first Graph Panel 
        JPanel graphPanel1 = createGraphPlaceholderPanel("Map",
                "EmployeeManagmentSystem/src/images/Employee_Map.png", 400, 250);
        graphsPanel.add(graphPanel1);

        // Create second Graph Panel 
        JPanel graphPanel2 = createGraphPlaceholderPanel("Graph",
                "EmployeeManagmentSystem/src/images/EmployeeRate_Graph.png", 500, 300);
        graphsPanel.add(graphPanel2);

        // Create third Graph Panel 
        JPanel graphPanel3 = createGraphPlaceholderPanel("Pie Chart",
                "EmployeeManagmentSystem/src/images/EmployeesGender_Graph.png", 500, 500);
        graphsPanel.add(graphPanel3);

        // Add the graphs panel to the dashboard
        dashboardPanel.add(graphsPanel, BorderLayout.SOUTH);

        // Add the dashboard to the main panel
        mainPanel.add(dashboardPanel, "Dashboard");
        cardLayout.show(mainPanel, "Dashboard");

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createKeyIndicatorsPanel() {
        JPanel keyIndicatorsPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // Set background color
        keyIndicatorsPanel.setBackground(Color.decode("#F2F2F2"));

        // Set the border color and title font size
        Border border = BorderFactory.createLineBorder(new Color(20, 60, 20), 2);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Key Indicators");
        titledBorder.setTitleFont(new Font("Verdana", Font.BOLD, 20));

        keyIndicatorsPanel.setBorder(titledBorder); // Apply the custom border with title font

        List<Employee> employees = employeeManagementSystem.getEmployees();

        // Fetch data
        int numberOfEmployees = employees != null ? employees.size() : 0;
        int numberOfSprintEvaluations = employeeManagementSystem.getTotalSprintEvaluations();

        // Create Labels
        JLabel totalEmployeesLabel = new JLabel("Total Employees: " + numberOfEmployees, JLabel.CENTER);
        JLabel sprintEvaluationsLabel = new JLabel("Total Sprint Evaluations: " + numberOfSprintEvaluations,
                JLabel.CENTER);

        // Set font size for key indicator labels
        totalEmployeesLabel.setFont(new Font("Verdana", Font.BOLD, 22)); 
        sprintEvaluationsLabel.setFont(new Font("Verdana", Font.BOLD, 22)); 

        keyIndicatorsPanel.add(totalEmployeesLabel);
        keyIndicatorsPanel.add(sprintEvaluationsLabel);

        return keyIndicatorsPanel;
    }

    private JPanel createGraphPlaceholderPanel(String title, String imagePath, int panelWidth, int panelHeight) {
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(20, 60, 20), 2), // Set the border color
                title // Title text
        ));

        // Set the preferred size for the graph
        graphPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Load the image
        ImageIcon graphImage = new ImageIcon(imagePath);

        // Create a JLabel to display the image without resizing it
        JLabel graphLabel = new JLabel(graphImage);
        graphPanel.add(graphLabel, BorderLayout.CENTER);

        return graphPanel;
    }

}
