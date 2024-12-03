import javax.swing.*;
import java.awt.*;

public class CreateEvaluationGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Create New Evaluation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 2, 10, 10));
            
            // Employee Selection
            panel.add(new JLabel("Select Employee:"));
            JComboBox<String> employeeComboBox = new JComboBox<>(new String[]{"Employee 1", "Employee 2", "Employee 3"});
            panel.add(employeeComboBox);
            
            // Criteria
            panel.add(new JLabel("Criteria:"));
            JTextField criteriaField = new JTextField();
            panel.add(criteriaField);
            
            // Comments
            panel.add(new JLabel("Comments:"));
            JTextArea commentsArea = new JTextArea(5, 20);
            panel.add(new JScrollPane(commentsArea));
            
            // Submit Button
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new SubmitButtonClickEventHandler(employeeComboBox, criteriaField, commentsArea));
            panel.add(submitButton);
            
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}