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

    //Show the Delete Employee Panel
    public void showDeleteEmployeePanel() {
        //implementing attributes of "Remove Employee" button
        JButton removeButton = new JButton("Remove Employee");
        String instructionHolder = "Enter Employee ID";
        JTextField employeeIdField = new JTextField(instructionHolder);
        SetStyle.setFocusListener(employeeIdField, instructionHolder);

        //setting up panel for removing a person and the way it's laid out
        JPanel removeEmployeePanel = new JPanel();
        removeEmployeePanel.setLayout(new GridLayout(2, 2, 10, 10));
        SetStyle.setBackground(removeEmployeePanel);

        //adding components to panel- must type in the employees ID to remove them
        JLabel instruction = new JLabel("Employee ID: ");
        SetStyle.setInstructionText(instruction);
        removeEmployeePanel.add(instruction);
        removeEmployeePanel.add(employeeIdField);
        //adding a remove button to finalize the action
        removeEmployeePanel.add(new JLabel());
        removeEmployeePanel.add(removeButton);

        //add panel to the mainPanel with name "Remove Employees"
        mainPanel.add(removeEmployeePanel, "Remove Employee");

        //show "Remove Employee" panel using cardLayout
        cardLayout.show(mainPanel, "Remove Employee");

        //action listener- responds to the remove button being clicked 
        removeButton.addActionListener(e -> {
            String employeeID = employeeIdField.getText().trim();
            //if nothing is input before clicking the button, user receives a message asking for input
            if(employeeID.isEmpty()){
                JOptionPane.showMessageDialog(mainPanel, "Please enter an Employee ID");
                return;
            }

            //calls removeEmployee method in employeeManagementSystem- if successful, returns true
            boolean success = employeeManagementSystem.removeEmployee(employeeID);

            if(success){
                //if successful, user receives confirmation through success message
                JOptionPane.showMessageDialog(mainPanel, "Employee " + employeeID + " removed successfully.");
            } else {
                //if unsuccessful, user receives a message saying the employee was not found, therefore not removed
                JOptionPane.showMessageDialog(mainPanel, "Employee " + employeeID + " not found.");
            }
            //resets the text field to empty for next time use
            employeeIdField.setText("");
        });
    }
}
