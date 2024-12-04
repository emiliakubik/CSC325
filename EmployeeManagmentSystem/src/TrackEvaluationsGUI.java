import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TrackEvaluationsGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Track Evaluations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        // Create the main panel with BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Create the filter panel with GridLayout
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(1, 2, 10, 10));
        
        // Add filter by employee label and combo box
        filterPanel.add(new JLabel("Filter by Employee:"));
        JComboBox<String> employeeFilterComboBox = new JComboBox<>(new String[]{"All", "Employee 1", "Employee 2", "Employee 3"});
        filterPanel.add(employeeFilterComboBox);
        
        // Add filter by time period label and text field
        filterPanel.add(new JLabel("Filter by Time Period:"));
        JTextField timePeriodFilterField = new JTextField();
        filterPanel.add(timePeriodFilterField);
        
        // Add the filter panel to the main panel at the top
        panel.add(filterPanel, BorderLayout.NORTH);
        
        // Define column names for the table
        String[] columnNames = {"Employee ID", "Date", "Criteria", "Comments"};
        
        // Create the table model and table
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add the scroll pane to the main panel at the center
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Add the main panel to the frame
        frame.add(panel);
        
        // Make the frame visible
        frame.setVisible(true);
        
        // Retrieve and display evaluations from the database
        List<Evaluation> evaluations = DatabaseHandler.getEvaluations();
        for (Evaluation evaluation : evaluations) {
            // Add each evaluation to the table model
            tableModel.addRow(new Object[]{
                evaluation.getEmployeeID(),
                evaluation.getEvaluationDate(),
                evaluation.getCriteria(),
                evaluation.getComments()
            });
        }
    }
}