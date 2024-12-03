
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.util.List;

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
        String titleInstruction = "Enter Job Title";
        JTextField jobTitleField = new JTextField(titleInstruction);
        SetStyle.setFocusListener(jobTitleField, titleInstruction);
        String nameInstruction = "Enter Company Name";
        JTextField companyNameField = new JTextField(nameInstruction);
        SetStyle.setFocusListener(companyNameField, nameInstruction);
        String dateInstruction = "YYYY-MM-DD";
        JTextField startDateField = new JTextField(dateInstruction);
        SetStyle.setFocusListener(startDateField, dateInstruction);
        JTextField endDateField = new JTextField(dateInstruction);
        SetStyle.setFocusListener(endDateField, dateInstruction);
        String descInstruction = "Enter Job Description";
        JTextField jobDescriptionField = new JTextField();
        SetStyle.setFocusListener(jobDescriptionField, descInstruction);
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
                    jobHistory = new JobHistory(selectedEmployeeName, jobTitle, companyName, startDate, endDate, jobDescription, department, position);
                }
        
                employeeManagementSystem.addJobHistory(jobHistory);
                JOptionPane.showMessageDialog(mainPanel, "Job History Saved:\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, "Error Saving: " + ex.getMessage());
            }
        });
    }

    public void showViewJobHistory(Employee employee){
        JPanel viewJobHistoryPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(employee.getFullName() + " Job History", JLabel.CENTER);
        viewJobHistoryPanel.add(titleLabel, BorderLayout.NORTH);

        List<String> jobHistories = employeeManagementSystem.getJobHistories(employee);
        if(jobHistories.isEmpty()){
            viewJobHistoryPanel.add(new JLabel("No job history available."), BorderLayout.CENTER);
        } else {
            String[] columnNames = {"Job Title", "Company", "Start Date", "End Date"};
            String[][] data = new String[jobHistories.size()][4];

            for(int i = 0; i < jobHistories.size(); i++){
                String historyLine = jobHistories.get(i);
                String[] historyParts = historyLine.split(",");

                if(historyParts.length >= 7){
                    data[i][0] = historyParts[1].trim();
                    data[i][1] = historyParts[2].trim();
                    data[i][2] = historyParts[3].trim();
                    data[i][3] = historyParts[4].trim();
                }
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            viewJobHistoryPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(viewJobHistoryPanel, "View Job History");
            cardLayout.show(mainPanel, "View Job History");

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());

                    if (col == 0){
                        String title = (String) table.getValueAt(row, 0);
                        viewSpecificJobHistory(title, jobHistories);
                    }
                }
            });
        }
    }

    public void viewSpecificJobHistory(String title, List<String> jobHistories){
        JPanel detailPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        detailPanel.add(titleLabel, BorderLayout.NORTH);

        SetStyle.setBackground(detailPanel);

        String matchedHistory = null;
        for (String history : jobHistories){
            String[] historyParts = history.split(",");
            if (historyParts.length >= 7 && historyParts[1].trim().equals(title)){
                matchedHistory = history;
                break;
            }
        }

        if (matchedHistory != null){
            String[] historyParts = matchedHistory.split(",");
            JPanel infoPanel = new JPanel(new GridLayout(7, 1, 5, 5));

            JLabel jobDescription = new JLabel("Job Description");
            JLabel jDesc = new JLabel(historyParts[5].trim());
            SetStyle.setInfoStyle(jobDescription, jDesc);
            infoPanel.add(jobDescription);
            infoPanel.add(jDesc);
            
            JLabel department = new JLabel("Department");
            JLabel dep = new JLabel(historyParts[6].trim());
            SetStyle.setInfoStyle(department, dep);
            infoPanel.add(department);
            infoPanel.add(dep);

            JLabel position = new JLabel("Position");
            JLabel pos = new JLabel(historyParts[7].trim());
            SetStyle.setInfoStyle(position, pos);
            infoPanel.add(position);
            infoPanel.add(pos);

            SetStyle.setBackground(infoPanel);

            detailPanel.add(infoPanel, BorderLayout.CENTER);
        }

        JFrame frame = new JFrame("Job History Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(detailPanel);
        frame.setSize(400, 300); //Adjust size as needed
        frame.setLocationRelativeTo(null); //Center the frame on screen
        frame.setVisible(true);
    }

    //this is what adds the fields I created to the panel
    private void addFields(JPanel panel, String[] labels, JComponent[] fields) {
        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i] + ":"));
            panel.add(fields[i]);
        }
    }
    public void addJobHistoryToEmployee(String employeeID, JobHistory jobHistory) {
        List<Employee> employees = employeeManagementSystem.getEmployees(); 
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeID)) {
                employee.addJobHistory(jobHistory);
                employeeManagementSystem.saveEmployees(employees); 
                System.out.println("Job history added successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    private void populateEmployeeDropdown(JComboBox<String> employeeDropdown) {
        for (Employee employee : employeeManagementSystem.getEmployees()) {
            employeeDropdown.addItem(employee.getFullName());
        }
    }
    
}
