import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.*;

public class SearchBar {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;

    public SearchBar(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.employeeManagementSystem = employeeManagementSystem;
    }

    // this is the creation of the searchbar panel
    public JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        JPanel searchInputPanel = new JPanel(new FlowLayout());

        // Increase the width of the search field and set a larger font size
        JTextField searchField = new JTextField(30); // Increased width
        searchField.setFont(new Font("Verdana", Font.PLAIN, 16)); // Larger font size

        // Create the search button and set a larger font size
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Verdana", Font.PLAIN, 16)); // Larger font size

        // Set a custom background color
        searchButton.setBackground(new Color(10, 50, 10)); // Example: Blue color
        searchButton.setForeground(Color.WHITE); // Set text color to white for contrast

        searchInputPanel.add(new JLabel("Search For Employee "));
        searchInputPanel.add(searchField);
        searchInputPanel.add(searchButton);

        JPanel resultsPanel = new JPanel(new BorderLayout());
        JTextArea resultsArea = new JTextArea(20, 40);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);

        searchPanel.add(searchInputPanel, BorderLayout.NORTH);
        searchPanel.add(resultsPanel, BorderLayout.CENTER);

        // This is the method for the search
        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim();

            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter a search query.");
                return;
            }

            // This is what displays whatever the search results are
            String results = searchForEmployee(query);
            resultsArea.setText(results.isEmpty() ? "No results found." : results);
        });

        return searchPanel;
    }

    // this is the method used to search in the text file, I must admit that I had
    // to use ChatGPT to help with this part, as the search wasn't working without
    // this section.
    private String searchForEmployee(String query) {
        if (employeeManagementSystem.getEmployees() == null || employeeManagementSystem.getEmployees().isEmpty()) {
            return "No employees found in the system.";
        }

        StringBuilder results = new StringBuilder();
        for (Employee employee : employeeManagementSystem.getEmployees()) {
            if (employee.getFullName().toLowerCase().contains(query.toLowerCase()) ||
                    employee.getEmployeeID().toLowerCase().contains(query.toLowerCase()) ||
                    employee.getEmail().toLowerCase().contains(query.toLowerCase())) {
                results.append("ID: ").append(employee.getEmployeeID()).append("\n")
                        .append("Name: ").append(employee.getFullName()).append("\n")
                        .append("Email: ").append(employee.getEmail()).append("\n")
                        .append("-----------------------------\n");
            }
        }

        return results.length() > 0 ? results.toString() : "No matching employees found.";
    }
}
