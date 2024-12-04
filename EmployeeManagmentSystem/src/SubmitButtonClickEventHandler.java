import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class SubmitButtonClickEventHandler implements ActionListener {
    private JComboBox<String> employeeComboBox;
    private JTextField criteriaField;
    private JTextArea commentsArea;

    public SubmitButtonClickEventHandler(JComboBox<String> employeeComboBox, JTextField criteriaField, JTextArea commentsArea) {
        this.employeeComboBox = employeeComboBox;
        this.criteriaField = criteriaField;
        this.commentsArea = commentsArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String employeeID = (String) employeeComboBox.getSelectedItem();
        String criteria = criteriaField.getText();
        String comments = commentsArea.getText();

        if (Validation.validateEvaluationData(employeeID, criteria, comments)) {
            Evaluation evaluation = new Evaluation(employeeID, new Date(), criteria, comments);
            try {
                DatabaseHandler.insertEvaluation(evaluation);
                JOptionPane.showMessageDialog(null, "Evaluation submitted successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error submitting evaluation: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
        }
    }
}