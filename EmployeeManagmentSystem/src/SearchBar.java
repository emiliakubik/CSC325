import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.*;


public class SearchBar {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;
    
    public SearchBar(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem){
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this. employeeManagementSystem = employeeManagementSystem;
    }
    public void showSearchPanel(){
        //here i am creating the search panel
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        JPanel searchInputPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");
        searchInputPanel.add(new JLabel("Search For Employee "));
        searchInputPanel.add(searchField);
        searchInputPanel.add(searchButton);
        searchInputPanel.add(backButton);

        //This is the results display section
        JPanel resultsPanel = new JPanel(new BorderLayout());
        JTextArea resultsArea = new JTextArea(20,40);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        resultsPanel.add(scrollPane, BorderLayout. CENTER);

        searchPanel.add(searchInputPanel, BorderLayout.NORTH);
        searchPanel.add(resultsPanel, BorderLayout.CENTER);
        mainPanel.add(searchPanel, "Search");
        cardLayout.show(mainPanel, "Search");
        //This presents the user with a message, indicating to them that there is a search bar.
        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim();
            
            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter a search query.");
                return;
            }
         //this is what displays the results of the search
            String results = searchForEmployee(query);
            resultsArea.setText(results.isEmpty() ? "No results found." : results);
        });
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
    }
    
    //this is the method used to search in the text file, I must admit that I had to use ChatGPT to help with this part, as the search wasn't working without this section.
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
