import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SprintEvaluationPanel {
    private JPanel panel;
    EmployeeManagementSystem employeeManagementSystem = new EmployeeManagementSystem();

    public SprintEvaluationPanel(Employee employee){
        panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Sprint Evaluation for" + employee.getFullName(), JLabel.CENTER);
        //JTextArea evaluationArea = new JTextArea(10, 30);
        
        JButton submitButton = new JButton("Submit Sprint Evalution");
        JTextField title = new JTextField();
        JTextField question1 = new JTextField(20);
        JTextField question2 = new JTextField(20);
        JTextField question3 = new JTextField(20);
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

        formPanel.add(new JLabel("Title of Evaluation: "));
        formPanel.add(title);

        formPanel.add(new JLabel("What have you completed during this sprint?"));
        formPanel.add(question1);

        formPanel.add(new JLabel("Is there anything that was not completed?"));
        formPanel.add(question2);

        formPanel.add(new JLabel("Any extra comments/thoughts regarding the sprint: "));
        formPanel.add(question3);

        formPanel.add(new JLabel("Date of Evaluation: "));
        formPanel.add(evalDayCombo);
        formPanel.add(evalMonthCombo);
        formPanel.add(evalYearCombo);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            String evalTitle = title.getText();
            String response1 = question1.getText();
            String response2 = question2.getText();
            String response3 = question3.getText();

            String selectedDay = (String) evalDayCombo.getSelectedItem();
            String selectedMonth = (String) evalMonthCombo.getSelectedItem();
            String selectedYear = (String) evalYearCombo.getSelectedItem();

            SprintEvaluation eval = new SprintEvaluation(employee.getFullName().trim(), evalTitle, response1, response2, response3, selectedDay, selectedMonth, selectedYear);

            employee.addSprintEval(eval);
            employeeManagementSystem.addSprintEval(eval);

            JOptionPane.showMessageDialog(panel, employee.getFullName() + "'s Sprint Evaluation saved successfully.");

            title.setText("");
            question1.setText("");
            question2.setText("");
            question3.setText("");
            evalDayCombo.setSelectedIndex(0);
            evalMonthCombo.setSelectedIndex(0);
            evalYearCombo.setSelectedIndex(0);
        });
    }

    public JPanel getCreatePanel(){
        return panel;
    }

    public JPanel viewSprintEvalPanel(Employee employee){
        JPanel viewPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Sprint Evaluations for " + employee.getFullName(), JLabel.CENTER);
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
            JScrollPane scrollPane = new JScrollPane(table);
            viewPanel.add(scrollPane, BorderLayout.CENTER);

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

            JLabel question3label = new JLabel("Any extra comments/thoughts regarding the sprint: ");
            JLabel answer3Label = new JLabel(evalParts[7].trim());
            setStyle(question3label, answer3Label);
            infoPanel.add(question3label);
            infoPanel.add(answer3Label);

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
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setOpaque(true);
        questionLabel.setBackground(new Color(230, 230, 250));
        questionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        answerLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        answerLabel.setForeground(Color.DARK_GRAY);
    }
}
