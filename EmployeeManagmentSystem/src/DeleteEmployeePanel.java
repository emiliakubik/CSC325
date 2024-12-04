import javax.swing.*;
import java.awt.*;

public class DeleteEmployeePanel {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;

    public DeleteEmployeePanel(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem){
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.employeeManagementSystem = employeeManagementSystem;
    }

    // Show the Delete Employee popup
    public void showDeleteEmployeePopup() {
        // Create a panel for the input
        JPanel deleteEmployeePanel = new JPanel();
        deleteEmployeePanel.setLayout(new GridLayout(2, 2, 10, 10));

        // Set up components for Employee ID input
        String instructionHolder = "Enter Employee ID";
        JTextField employeeIdField = new JTextField(instructionHolder);
        SetStyle.setFocusListener(employeeIdField, instructionHolder);
        JLabel instruction = new JLabel("Employee ID: ");
        SetStyle.setInstructionText(instruction);

        // Add components to the panel
        deleteEmployeePanel.add(instruction);
        deleteEmployeePanel.add(employeeIdField);
        
        // Show the popup dialog
        int option = JOptionPane.showConfirmDialog(mainPanel, deleteEmployeePanel, "Remove Employee", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String employeeID = employeeIdField.getText().trim();

            // If the input is empty, ask for input
            if(employeeID.isEmpty()){
                JOptionPane.showMessageDialog(mainPanel, "Please enter an Employee ID");
                return;
            }

            // Call removeEmployee method in employeeManagementSystem - if successful, returns true
            boolean success = employeeManagementSystem.removeEmployee(employeeID);

            if(success){
                // Show success message
                JOptionPane.showMessageDialog(mainPanel, "Employee " + employeeID + " removed successfully.");
            } else {
                // Show error message if employee is not found
                JOptionPane.showMessageDialog(mainPanel, "Employee " + employeeID + " not found.");
            }
        }
    }
}
