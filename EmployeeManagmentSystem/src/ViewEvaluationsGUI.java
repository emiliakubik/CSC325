import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewEvaluationsGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("View Evaluations");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            
            JPanel filterPanel = new JPanel();
            filterPanel.setLayout(new GridLayout(1, 2, 10, 10));
            filterPanel.add(new JLabel("Filter by Employee:"));
            JComboBox<String> employeeFilterComboBox = new JComboBox<>(new String[]{"All", "Employee 1", "Employee 2", "Employee 3"});
            filterPanel.add(employeeFilterComboBox);
            filterPanel.add(new JLabel("Filter by Date:"));
            JTextField dateFilterField = new JTextField();
            filterPanel.add(dateFilterField);
            
            panel.add(filterPanel, BorderLayout.NORTH);
            
            String[] columnNames = {"Employee ID", "Date", "Criteria", "Comments"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);
            
            frame.add(panel);
            frame.setVisible(true);
            
            List<Evaluation> evaluations = DatabaseHandler.getEvaluations();
            for (Evaluation evaluation : evaluations) {
                tableModel.addRow(new Object[]{
                    evaluation.getEmployeeID(),
                    evaluation.getEvaluationDate(),
                    evaluation.getCriteria(),
                    evaluation.getComments()
                });
            }
        });
    }
}