
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class JobHistoryPanel {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;

    public JobHistoryPanel(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem){
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.employeeManagementSystem = employeeManagementSystem;
    }

    // Show the Manage Job History panel
    public void showJobHistoryPanel() {
        // Initialize panel and layout for job history
        cardLayout.show(mainPanel, "Job History");
        JPanel jobHistoryPanel = new JPanel(new GridLayout(13, 2, 10, 10));
        
        JLabel employeeLabel = new JLabel("Select Employee:");
        JComboBox<String> employeeDropdown = new JComboBox<>();
        populateEmployeeDropdown(employeeDropdown);
        jobHistoryPanel.add(employeeLabel);
        jobHistoryPanel.add(employeeDropdown);

        // Text fields for job details
        JTextField jobTitleField = new JTextField();
        JTextField companyNameField = new JTextField();
        JTextField startDateField = new JTextField("YYYY-MM-DD");
        JTextField endDateField = new JTextField("YYYY-MM-DD");
        JTextField jobDescriptionField = new JTextField();
        JTextField pastJobTitleField = new JTextField();
        JTextField pastJobDurationField = new JTextField();
        JTextField reasonForLeavingField = new JTextField();
    
        // Dropdowns for department and position
        String[] departments = {"Select Department", "Engineering", "Human Resources", "Finance", "Marketing", "Operations Management", "Production"};
        Map<String, String[]> positionsMap = new HashMap<>();
        positionsMap.put("Engineering", new String[]{"Engineer","Junior Engineer", "Graduate Engineer","Project Engineer","Senior Engineer", "Lead Engineer", "Chief Engineer", "Executive Engineer" });
        positionsMap.put("Human Resources", new String[]{"Human Resources Specialist", "Analyst", "HR Specialist", "Talent Aquisition Specialist", "Human Resources Manager", "Human Resources Executive"});
        positionsMap.put("Finance", new String[]{"Accountant", "Accounting Clerk","Financial Analyst","Administrative Specialist", "Auditor", "Controller", "Chief Financial Officer"});
        positionsMap.put("Marketing", new String[]{"Marketing Assistant", "Marketing Manager", "Communications Manager", "Content Strategist", "SEO Specialist"});
        positionsMap.put("Operations Management", new String[]{" Operations Coordinator", "Operations Analyst", "Operations Supervisor", "Operations Manager", "Project Manager", "Director of Operations"});
        positionsMap.put("Production", new String[]{"Assembly Operator", "Control Room Operator", "Offshore Production Operator", "Onshore Production Operator", "Production Operations Supervisor", "Director of Manufacturing", "VP of Production"});
        JComboBox<String> departmentDropDown = new JComboBox<>(departments);
        JComboBox<String> positionDropDown = new JComboBox<>(new String[]{"Select Position"});
    
        //this populates the position field based on the input of the department field
        departmentDropDown.addActionListener(e -> {
            String selectedDepartment = (String) departmentDropDown.getSelectedItem();
            positionDropDown.removeAllItems();
            positionDropDown.addItem("Select Position");
            if (positionsMap.containsKey(selectedDepartment)) {
                for (String position : positionsMap.get(selectedDepartment)) {
                    positionDropDown.addItem(position);
                }
            }
        });
    
        //makes the past job history panel visible if the checkbox is filled.
        JCheckBox hasPastJobCheckBox = new JCheckBox("Has This Person Previously Been Employed?");
        JPanel pastJobDetailsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        pastJobDetailsPanel.setVisible(false);
        hasPastJobCheckBox.addActionListener(e -> pastJobDetailsPanel.setVisible(hasPastJobCheckBox.isSelected()));
  
        addFields(jobHistoryPanel, new String[]{
            "Job Title", "Company Name", "Start Date", "End Date", "Job Description", "Department", "Position"
        }, new JComponent[]{
            jobTitleField, companyNameField, startDateField, endDateField, jobDescriptionField, departmentDropDown, positionDropDown
        });
    
  
        jobHistoryPanel.add(hasPastJobCheckBox);
        jobHistoryPanel.add(new JLabel());
        pastJobDetailsPanel.add(new JLabel("Past Job Title:"));
        pastJobDetailsPanel.add(pastJobTitleField);
        pastJobDetailsPanel.add(new JLabel("Past Job Duration (Months):"));
        pastJobDetailsPanel.add(pastJobDurationField);
        pastJobDetailsPanel.add(new JLabel("Reason for Leaving:"));
        pastJobDetailsPanel.add(reasonForLeavingField);
        jobHistoryPanel.add(pastJobDetailsPanel);
    
        //creating the submit button
        JButton submitButton = new JButton("Save Job History");
        jobHistoryPanel.add(new JLabel()); 
        jobHistoryPanel.add(submitButton);
    
        // Add job history panel to main panel
        mainPanel.add(jobHistoryPanel, "Job History");
        cardLayout.show(mainPanel, "Job History");
    
        //action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                String selectedEmployeeName = (String) employeeDropdown.getSelectedItem();
                Employee selectedEmployee = employeeManagementSystem.getEmployeeByName(selectedEmployeeName);
        
                if (selectedEmployee == null) {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a valid employee.");
                    return;
                }
        
                String jobTitle = jobTitleField.getText();
                String companyName = companyNameField.getText();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String jobDescription = jobDescriptionField.getText();
                String department = (String) departmentDropDown.getSelectedItem();
                String position = (String) positionDropDown.getSelectedItem();
        
                if ("Select Department".equals(department) || "Select Position".equals(position)) {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a valid department and position.");
                    return;
                }
        
                JobHistory jobHistory;
                if (hasPastJobCheckBox.isSelected()) {
                    String pastJobTitle = pastJobTitleField.getText();
                    String pastJobDuration = pastJobDurationField.getText();
                    String reasonForLeaving = reasonForLeavingField.getText();
        
                    if (pastJobTitle.isEmpty() || pastJobDuration.isEmpty() || reasonForLeaving.isEmpty()) {
                        JOptionPane.showMessageDialog(mainPanel, "Please fill all past job details.");
                        return;
                    }
                    jobHistory = new JobHistory(jobTitle, companyName, startDate, endDate, jobDescription, department, position,
                                                pastJobTitle, pastJobDuration, reasonForLeaving);
                } else {
                    jobHistory = new JobHistory(jobTitle, companyName, startDate, endDate, jobDescription, department, position);
                }
        
                attachJobHistoryToEmployee(selectedEmployee, jobHistory);
                JOptionPane.showMessageDialog(mainPanel, "Job History Saved:\n" + jobHistory.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, "Error Saving: " + ex.getMessage());
            }
        });

    //this is what adds the fields I created to the panel
    private void addFields(JPanel panel, String[] labels, JComponent[] fields) {
        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i] + ":"));
            panel.add(fields[i]);
        }
    }
    public void addJobHistoryToEmployee(String employeeID, JobHistory jobHistory) {
        List<Employee> employees = getEmployees(); 
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeID)) {
                employee.addJobHistory(jobHistory);
                saveEmployees(employees); 
                System.out.println("Job history added successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    private void populateEmployeeDropdown(JComboBox<String> employeeDropdown) {
        for (Employee employee : employeeManagementSystem.getAllEmployees()) {
            employeeDropdown.addItem(employee.getFullName());
        }
    }
    
}
