import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class SprintEvaluationPanel {
    private JPanel mainPanel;
    private JPanel sprintEvalPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;

    public SprintEvaluationPanel(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem){
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.employeeManagementSystem = employeeManagementSystem;
        sprintEvalPanel = new JPanel();
    }

    //show the Sprint Evaluation Panel
    public void showSprintEvalPanel(){
        //setting up the panel to hold the content for sprint evaluations
        JPanel chooseEmployeePanel = new JPanel(new BorderLayout());
        //instructs the user on what action to take
        JLabel instructionLabel = new JLabel("Select an employee to create/view Sprint Evaluation.");
        SetStyle.setInstructionText(instructionLabel);
        //user first chooses which employee they would like to evaluate
        JComboBox<String> employeeComboBox = new JComboBox<>();
        //then choose whether they want to create a new eval or look at past ones
        JComboBox<String> actionComboBox = new JComboBox<>();

        //inputting all the employees entered into the system as options
        for(Employee employee : employeeManagementSystem.getEmployees()){
            employeeComboBox.addItem((String) employee.getFullName());
        }

        //inputting the two options: create new eval or view past evals
        actionComboBox.addItem("Create New Sprint Evaluation");
        actionComboBox.addItem("View Past Sprint Evaluations");
        //creating a panel to hold both combo boxes and them adding the boxes to the panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(employeeComboBox);
        centerPanel.add(actionComboBox);

        SetStyle.setBackground(centerPanel);

        //adding the instructions and the dropdown boxes to the main panel
        chooseEmployeePanel.add(instructionLabel, BorderLayout.NORTH);
        chooseEmployeePanel.add(centerPanel, BorderLayout.CENTER);

        SetStyle.setBackground(chooseEmployeePanel);

        //action listener to react when the choices are made from the drop down menus
        actionComboBox.addActionListener(e -> {
            //gets the selected employee
            Employee selectedEmployee = employeeManagementSystem.getEmployeeByName((String)employeeComboBox.getSelectedItem());
            //gets the selected action
            String selectedAction = (String) actionComboBox.getSelectedItem();

            //check which action was selected (Create or View)
            if(selectedAction.equals("Create New Sprint Evaluation")){
                //if create was selected, calls the create panel from SprintEvaluationPanel class
                mainPanel.add(showCreateEvalPanel(selectedEmployee), selectedEmployee.getFullName() + "'s Sprint Evaluation");
                cardLayout.show(mainPanel, selectedEmployee.getFullName() + "'s Sprint Evaluation");
            } else if(selectedAction.equals("View Past Sprint Evaluations")){
                //if view was selected, calls the view panel from SprintEvaluationPanel class
                mainPanel.add(viewSprintEvalPanel(selectedEmployee), selectedEmployee.getFullName() + "'s Sprint Evaluations");
                cardLayout.show(mainPanel, selectedEmployee.getFullName() + "'s Sprint Evaluations");
            }
        });

        //adds chooseEmployeePanel to the main panel and shows the panel
        mainPanel.add(chooseEmployeePanel, "Choose Employee");
        cardLayout.show(mainPanel, "Choose Employee");
    }

    public JPanel showCreateEvalPanel(Employee employee){
        sprintEvalPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Sprint Evaluation for" + employee.getFullName(), JLabel.CENTER);
        SetStyle.setInstructionText(titleLabel);
        //JTextArea evaluationArea = new JTextArea(10, 30);
        
        JButton submitButton = new JButton("Submit Sprint Evalution");
        JTextField title = new JTextField();
        String responseHolder = "Enter response here";
        JTextField question1 = new JTextField(responseHolder);
        SetStyle.setFocusListener(question1, responseHolder);
        JTextField question2 = new JTextField(responseHolder);
        SetStyle.setFocusListener(question2, responseHolder);
        JTextField question3 = new JTextField(responseHolder);
        SetStyle.setFocusListener(question3, responseHolder);
        JComboBox<String> evalDayCombo = new JComboBox<>();
        JComboBox<String> evalMonthCombo = new JComboBox<>();
        JComboBox<String> evalYearCombo = new JComboBox<>();

        evalDayCombo.addItem("-day-");
        for(int i = 1; i <= 31; i++){
            evalDayCombo.addItem(String.valueOf(i));
        }

        evalMonthCombo.addItem("-month-");
        for(int i = 1; i <= 12; i++){
            evalMonthCombo.addItem(String.valueOf(i));
        }

        evalYearCombo.addItem("-year-");
        for(int i = 1950; i <= 2024; i++){
            evalYearCombo.addItem(String.valueOf(i));
        }

        JPanel formPanel = new JPanel(new GridLayout(6, 5));

        JLabel evalTitle = new JLabel("Title of Evaluation: ");
        SetStyle.setInstructionText(evalTitle);
        formPanel.add(evalTitle);
        formPanel.add(title);

        JLabel q1Label = new JLabel("What have you completed during this sprint?");
        SetStyle.setInstructionText(q1Label);
        formPanel.add(q1Label);
        formPanel.add(question1);

        JLabel q2Label = new JLabel("Is there anything that was not completed?");
        SetStyle.setInstructionText(q2Label);
        formPanel.add(q2Label);
        formPanel.add(question2);

        JLabel q3Label = new JLabel("Any extra comments regarding the sprint: ");
        SetStyle.setInstructionText(q3Label);
        formPanel.add(q3Label);
        formPanel.add(question3);

        JLabel evalDate = new JLabel("Date of Evaluation: ");
        SetStyle.setInstructionText(evalDate);
        formPanel.add(evalDate);
        formPanel.add(evalDayCombo);
        formPanel.add(evalMonthCombo);
        formPanel.add(evalYearCombo);

        sprintEvalPanel.add(titleLabel, BorderLayout.NORTH);
        sprintEvalPanel.add(formPanel, BorderLayout.CENTER);
        sprintEvalPanel.add(submitButton, BorderLayout.SOUTH);

        SetStyle.setBackground(formPanel);
        SetStyle.setBackground(sprintEvalPanel);

        submitButton.addActionListener(e -> {
            String evaluationTitle = title.getText();
            String response1 = question1.getText();
            String response2 = question2.getText();
            String response3 = question3.getText();

            String selectedDay = (String) evalDayCombo.getSelectedItem();
            String selectedMonth = (String) evalMonthCombo.getSelectedItem();
            String selectedYear = (String) evalYearCombo.getSelectedItem();

            //SprintEvaluation eval = new SprintEvaluation(employee.getFullName().trim(), evaluationTitle, response1, response2, response3, selectedDay, selectedMonth, selectedYear);
            if (evaluationTitle.isEmpty()) {
                JOptionPane.showMessageDialog(sprintEvalPanel, "Please provide a title for the evaluation.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (response1.isEmpty()) {
                JOptionPane.showMessageDialog(sprintEvalPanel, "Please answer Question 1.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (response2.isEmpty()) {
                JOptionPane.showMessageDialog(sprintEvalPanel, "Please answer Question 2.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (response3.isEmpty()) {
                JOptionPane.showMessageDialog(sprintEvalPanel, "Please answer Question 3.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if ("-day-".equals(selectedDay) || "-month-".equals(selectedMonth) || "-year-".equals(selectedYear)) {
                JOptionPane.showMessageDialog(sprintEvalPanel, "Please select a valid evaluation date.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // If all validations pass, create and save the evaluation
            SprintEvaluation eval = new SprintEvaluation(
                employee.getFullName().trim(),
                evaluationTitle,
                response1,
                response2,
                response3,
                selectedDay,
                selectedMonth,
                selectedYear
            );
        
            employee.addSprintEval(eval);
            employeeManagementSystem.addSprintEval(eval);

            JOptionPane.showMessageDialog(sprintEvalPanel, employee.getFullName() + "'s Sprint Evaluation saved successfully.");

            title.setText("");
            question1.setText("");
            question2.setText("");
            question3.setText("");
            evalDayCombo.setSelectedIndex(0);
            evalMonthCombo.setSelectedIndex(0);
            evalYearCombo.setSelectedIndex(0);
        });

        return sprintEvalPanel;
    }

    public JPanel viewSprintEvalPanel(Employee employee){
        JPanel viewPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Sprint Evaluations for " + employee.getFullName(), JLabel.CENTER);
        SetStyle.setInstructionText(titleLabel);
        viewPanel.add(titleLabel, BorderLayout.NORTH);

        List<String> evals = employeeManagementSystem.getSprintEvals(employee);
        if(evals.isEmpty()){
            viewPanel.add(new JLabel("No sprint evaluations available."), BorderLayout.CENTER);
        } else {
            String[] columnNames = {"Evaluation Title", "Date of Evaluation"};
            String[][] data = new String[evals.size()][2];

            for(int i = 0; i < evals.size(); i++){
                String evalLine = evals.get(i);
                String[] evalParts = evalLine.split(",");

                if(evalParts.length >= 7){
                    data[i][0] = evalParts[1].trim();
                    data[i][1] = evalParts[3].trim() + "/" + evalParts[2].trim() + "/" + evalParts[4].trim(); //  month/day/year
                }
            }

            JTable table = new JTable(data, columnNames);
            JTableHeader tableHeader = table.getTableHeader();
            SetStyle.setTableHeaderText(tableHeader);
            JScrollPane scrollPane = new JScrollPane(table);
            viewPanel.add(scrollPane, BorderLayout.CENTER);

            SetStyle.setTable(table);
            SetStyle.setBackground(viewPanel);

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());

                    //only allows action to happen if name column is clicked, not any other column of their info
                    if (col == 0){
                        String title = (String) table.getValueAt(row, 0);
                        //calls private showEditEmployeePanel with the argument being the employee's ID
                        viewSpecificSprintEvalPanel(title, evals);
                    }
                }
            });
        }
        return viewPanel;
    }

    private void viewSpecificSprintEvalPanel(String title, List<String> evals){
        JPanel detailPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        detailPanel.add(titleLabel, BorderLayout.NORTH);

        SetStyle.setBackground(detailPanel);

        String matchedEval = null;
        for(String eval : evals){
            String[] evalParts = eval.split(",");
            if (evalParts.length >= 7 && evalParts[1].trim().equals(title)){
                matchedEval = eval;
                break;
            }
        }

        if(matchedEval != null){
            String[] evalParts = matchedEval.split(",");
            JPanel infoPanel = new JPanel(new GridLayout(7, 1, 5, 5));

            // infoPanel.add(new JLabel("Date: "));
            // infoPanel.add(new JLabel(evalParts[2].trim() + "/" + evalParts[3].trim() + "/" + evalParts[4].trim()));

            JLabel question1label = new JLabel("What have you completed during this sprint?");
            JLabel answer1Label = new JLabel(evalParts[5].trim());
            setStyle(question1label, answer1Label);
            infoPanel.add(question1label);
            infoPanel.add(answer1Label);

            JLabel question2label = new JLabel("Is there anything that was not completed?");
            JLabel answer2Label = new JLabel(evalParts[6].trim());
            setStyle(question2label, answer2Label);
            infoPanel.add(question2label);
            infoPanel.add(answer2Label);

            JLabel question3label = new JLabel("Any extra comments regarding the sprint: ");
            JLabel answer3Label = new JLabel(evalParts[7].trim());
            setStyle(question3label, answer3Label);
            infoPanel.add(question3label);
            infoPanel.add(answer3Label);

            SetStyle.setBackground(infoPanel);

            detailPanel.add(infoPanel, BorderLayout.CENTER);
        } 
        
    JFrame frame = new JFrame("Sprint Evaluation Details");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(detailPanel);
    frame.setSize(400, 300); // Adjust size as needed
    frame.setLocationRelativeTo(null); // Center the frame on screen
    frame.setVisible(true);
    }

    private void setStyle(JLabel questionLabel, JLabel answerLabel){
        questionLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setOpaque(true);
        questionLabel.setBackground(new Color(10, 50, 10));
        questionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        answerLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        answerLabel.setForeground(Color.DARK_GRAY);
    }
}
