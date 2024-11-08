import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class JobHistory {
    private JFrame frame;
    // Fields for Current Job
    private JCheckBox hasPreviousJobCheckbox;
    private JTextField currentJobTitleField;
    private JTextField currentCompanyNameField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea jobDescriptionArea;
    private JComboBox<String> currentPositionDropdown;
    private JComboBox<String> currentJobDepartment;

    private JButton saveButton;

    // Fields for past job
    private JTextField previousJobField;
    private JTextField previousJobDurationField;
    private JTextField reasonForLeavingJobField;
    private JPanel pastJobPanel;
    private HashMap<String, String[]> departmentPositionsMap;

    public TestGUI() {

        frame = new JFrame("Employee Job History Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(11, 2, 10, 10)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        hasPreviousJobCheckbox = new JCheckBox("Has Previous Job?");
        mainPanel.add(hasPreviousJobCheckbox);
        mainPanel.add(new JLabel());
//what makes the panel visible
        pastJobPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        pastJobPanel.setVisible(false);

        mainPanel.add(new JLabel("Current Job Title:"));
        currentJobTitleField = new JTextField();
        mainPanel.add(currentJobTitleField);

        //company name
        mainPanel.add(new JLabel("Company Name:"));
        currentCompanyNameField = new JTextField();
        mainPanel.add(currentCompanyNameField);

        //job start
        mainPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField();
        mainPanel.add(startDateField);

        mainPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField();
        mainPanel.add(endDateField);

        //job desc
        mainPanel.add(new JLabel("Job Description:"));
        jobDescriptionArea = new JTextArea(5, 20);
        jobDescriptionArea.setLineWrap(true);
        jobDescriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(jobDescriptionArea);
        mainPanel.add(scrollPane);

        //department. from this, the diff positions are determined
        mainPanel.add(new JLabel("Select Department:"));
        currentJobDepartment = new JComboBox<>(new String[]{"Select Department", "Engineering", "Sales", "Finance"});
        mainPanel.add(currentJobDepartment);

        mainPanel.add(new JLabel("Select Position:"));
        currentPositionDropdown = new JComboBox<>(new String[]{"Select Position"});
        mainPanel.add(currentPositionDropdown);

        departmentPositionsMap = new HashMap<>();
        departmentPositionsMap.put("Engineering", new String[]{"Junior Developer", "Senior Developer", "Lead Engineer"});
        departmentPositionsMap.put("Sales", new String[]{"Sales Representative", "Sales Manager", "Sales VP"});
        departmentPositionsMap.put("Finance", new String[]{"Finance Analyst", "Accountant", "Director of Finance", "Chief Financial Officer"});

        //past job
        pastJobPanel.add(new JLabel("Past Job Title:"));
        previousJobField = new JTextField();
        pastJobPanel.add(previousJobField);

        //duration
        pastJobPanel.add(new JLabel("Past Job Duration:"));
        previousJobDurationField = new JTextField();
        pastJobPanel.add(previousJobDurationField);

        //reason for leaving past job
        pastJobPanel.add(new JLabel("Reason for Leaving:"));
        reasonForLeavingJobField = new JTextField();
        pastJobPanel.add(reasonForLeavingJobField);

        mainPanel.add(pastJobPanel);
        saveButton = new JButton("Save Job History");
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(saveButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        //action listeners for checkbox, save and department
        hasPreviousJobCheckbox.addActionListener(new PreviousJobCheckboxHandler());
        saveButton.addActionListener(new JobHistorySaveEventHandler());
        currentJobDepartment.addActionListener(new DepartmentSelectionHandler());

        frame.setVisible(true);
    }

    // event handler that determines the job positions based on the department
    class DepartmentSelectionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedDepartment = (String) currentJobDepartment.getSelectedItem();
            if (departmentPositionsMap.containsKey(selectedDepartment)) {
                currentPositionDropdown.removeAllItems();
                for (String position : departmentPositionsMap.get(selectedDepartment)) {
                    currentPositionDropdown.addItem(position);
                }
            } else {
                currentPositionDropdown.removeAllItems();
                currentPositionDropdown.addItem("Select Position");
            }
        }
    }

    //visibitly of past job fields
    class PreviousJobCheckboxHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (hasPreviousJobCheckbox.isSelected()) {
                pastJobPanel.setVisible(true);
                frame.setSize(500, 700);
            } else {
                pastJobPanel.setVisible(false);
                frame.setSize(500, 600);
            }
        }
    }

    //save
    class JobHistorySaveEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String jobTitle = currentJobTitleField.getText();
            String companyName = currentCompanyNameField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            String jobDescription = jobDescriptionArea.getText();
            String selectedDepartment = (String) currentJobDepartment.getSelectedItem();
            String selectedPosition = (String) currentPositionDropdown.getSelectedItem();

            //error messages
            if (jobTitle.isEmpty() || companyName.isEmpty() || startDate.isEmpty() || endDate.isEmpty() ||
                selectedDepartment.equals("Select Department") || selectedPosition.equals("Select Position")) {
                JOptionPane.showMessageDialog(frame, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (hasPreviousJobCheckbox.isSelected()) {
                String pastJobTitle = previousJobField.getText();
                String pastJobDuration = previousJobDurationField.getText();
                String reasonForLeaving = reasonForLeavingJobField.getText();

                if (pastJobTitle.isEmpty() || pastJobDuration.isEmpty() || reasonForLeaving.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill out all past job fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
//display after the save with past job info
                JOptionPane.showMessageDialog(frame, "Job History Saved:\n" +
                        "Job Title: " + jobTitle + "\n" +
                        "Company: " + companyName + "\n" +
                        "Start Date: " + startDate + "\n" +
                        "End Date: " + endDate + "\n" +
                        "Job Description: " + jobDescription + "\n" +
                        "Department: " + selectedDepartment + "\n" +
                        "Position: " + selectedPosition + "\n" +
                        "Past Job Title: " + pastJobTitle + "\n" +
                        "Past Job Duration: " + pastJobDuration + " months\n" +
                        "Reason for Leaving: " + reasonForLeaving);
            } else {
//without job info
                JOptionPane.showMessageDialog(frame, "Job History Saved:\n" +
                        "Job Title: " + jobTitle + "\n" +
                        "Company: " + companyName + "\n" +
                        "Start Date: " + startDate + "\n" +
                        "End Date: " + endDate + "\n" +
                        "Job Description: " + jobDescription + "\n" +
                        "Department: " + selectedDepartment + "\n" +
                        "Position: " + selectedPosition);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestGUI::new);
    }
}