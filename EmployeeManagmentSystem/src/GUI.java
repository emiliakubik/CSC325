import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
=======
>>>>>>> e9c01f6b7365e3085fb09980e2f941842d545f6a

public class GUI {

    private JFrame frame;
    private JPanel mainPanel, buttonPanel;
    private CardLayout cardLayout;

    private EmployeeManagementSystem employeeManagementSystem;

    public GUI() {
        employeeManagementSystem = new EmployeeManagementSystem();

        // Initialize the main frame
        frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create button panel for navigation
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        // Add buttons for each key feature
        addButton("Create Employee", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateEmployeePanel createEmployeePanel = new CreateEmployeePanel(mainPanel, cardLayout, employeeManagementSystem);
                createEmployeePanel.showCreateEmployeePanel();
            }
        });

        addButton("View Employees", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewAndEditEmployee viewAndEditEmployee = new ViewAndEditEmployee(mainPanel, cardLayout, employeeManagementSystem);
                viewAndEditEmployee.showViewEmployeesPanel();
            }
        });

        addButton("Delete Employee", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteEmployeePanel deleteEmployeePanel = new DeleteEmployeePanel(mainPanel, cardLayout, employeeManagementSystem);
                deleteEmployeePanel.showDeleteEmployeePanel();
            }  
        });

        addButton("Job History", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showJobHistoryPanel();
            }
        });

        addButton("Sprint Evaluations", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SprintEvaluationPanel sprintEvaluationPanel = new SprintEvaluationPanel(mainPanel, cardLayout, employeeManagementSystem);
                sprintEvaluationPanel.showSprintEvalPanel();
            }
        });

        // Add the panels to the main panel (CardLayout for switching views)
        mainPanel.add(new JPanel(), "Create Employee"); // Placeholder panel
        mainPanel.add(new JPanel(), "View Employees");  // Placeholder panel
        mainPanel.add(new JPanel(), "Delete Employee"); // Placeholder panel
        mainPanel.add(new JPanel(), "Job History"); // Placeholder panel
        
        // Layout for the frame
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.WEST);  // Buttons on the left
        frame.add(mainPanel, BorderLayout.CENTER);  // Dynamic content in the center

        frame.setVisible(true);
    }

    // Add a button to the button panel
    private void addButton(String title, ActionListener actionListener) {
        JButton button = new JButton(title);
        SetStyle.setButton(button);
        button.addActionListener(actionListener);
        buttonPanel.add(button);
    }

    
    // Show the Manage Job History panel
    private void showJobHistoryPanel() {
        // Initialize panel and layout for job history
        cardLayout.show(mainPanel, "Job History");
        JPanel jobHistoryPanel = new JPanel(new GridLayout(13, 2, 10, 10));
    
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
                if (!jobHistory.validateFields()) {
                    JOptionPane.showMessageDialog(mainPanel, "Please fill all required fields.");
                    return;
                }
    
        //saving confirmation message
        JOptionPane.showMessageDialog(mainPanel, "Job History Saved.\n" + jobHistory.toString());
        //throws an exception if there was an error while saving
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainPanel, "Error Saving. " + ex.getMessage());
         }
     });
    }
    
  //this is what adds the fields I created to the panel
    private void addFields(JPanel panel, String[] labels, JComponent[] fields) {
        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i] + ":"));
            panel.add(fields[i]);
        }
    }
<<<<<<< HEAD
}
    
=======
}
>>>>>>> e9c01f6b7365e3085fb09980e2f941842d545f6a
