import java.awt.*;
import javax.swing.*;

public class CreateEmployeePanel {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private EmployeeManagementSystem employeeManagementSystem;

    public CreateEmployeePanel(JPanel mainPanel, CardLayout cardLayout, EmployeeManagementSystem employeeManagementSystem){
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.employeeManagementSystem = employeeManagementSystem;
    }

    public void showCreateEmployeePanel() {
        //components for the "Create Employee" panel
        JButton createButton = new JButton("Create Employee");
        String nameHolder = "Enter Name";
        JTextField nameField = new JTextField(nameHolder);
        SetStyle.setFocusListener(nameField, nameHolder);

        String emailHolder = "Enter Email";
        JTextField emailField = new JTextField(emailHolder);
        SetStyle.setFocusListener(emailField, emailHolder);

        String numberHolder = "Enter Phone Number";
        JTextField phoneNumberField = new JTextField(numberHolder);
        SetStyle.setFocusListener(phoneNumberField, numberHolder);

        //String positionHolder = "Enter Position";
        //JTextField positionField = new JTextField(positionHolder);
        //SetStyle.setFocusListener(positionField, positionHolder);
        
        //address fields 
        String streetHolder = "Enter Street Address";
        JTextField streetField = new JTextField(streetHolder);
        SetStyle.setFocusListener(streetField, streetHolder);

        String cityHolder = "Enter City";
        JTextField cityField = new JTextField(cityHolder);
        SetStyle.setFocusListener(cityField, cityHolder);


        String zipHolder = "Enter Zip Code";
        JTextField zipField = new JTextField(zipHolder);
        SetStyle.setFocusListener(zipField, zipHolder);

        //date of birth and gender fields
        JComboBox<String> dayCombo = new JComboBox<>();
        JComboBox<String> monthCombo = new JComboBox<>();
        JComboBox<String> yearCombo = new JComboBox<>();
        JComboBox<String> roleCombo = new JComboBox<>();
        JComboBox<String> genderCombo = new JComboBox<>();
        JComboBox<String> stateCombo = new JComboBox<>();
        //date of hire fields
        JComboBox<String> empDayCombo = new JComboBox<>();
        JComboBox<String> empMonthCombo = new JComboBox<>();
        JComboBox<String> empYearCombo = new JComboBox<>();

        roleCombo.addItem("-role-");
        String[] roles ={"Admin", "Manager", "Employee"};
        for (String role : roles){
            roleCombo.addItem(role);
        }
        //adding a label on the drop down menu to explain to user what to select
        genderCombo.addItem("-gender-");
        //options for gender in drop down menu
        String[] genders = {"Female", "Male"};
        for(String gender : genders){
            genderCombo.addItem(gender);
        }

        stateCombo.addItem("-state-");
        String[] states = {"AL", "AK", "AZ", "AR", "AS", "CA", "CO", "CT", "DE", "DC", "FL","GA","GU","HI"
    ,"ID", "IL","IN","IA", "KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC"
    ,"ND", "OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"};
    for(String state : states){
        stateCombo.addItem(state);
    }

        //adding a label on the drop down menu to explain to user what to select
        dayCombo.addItem("-day-");
        empDayCombo.addItem("-day-");
        //all the options for day in drop down menu- for both dob and hire date
        for(int i = 1; i <= 31; i++){
            dayCombo.addItem(String.valueOf(i));
            empDayCombo.addItem(String.valueOf(i));
        }

        //adding a label on the drop down menu to explain to user what to select
        monthCombo.addItem("-month-");
        empMonthCombo.addItem("-month-");
        //all the options for month in drop down menu- for both dob and hire date
        for (int i = 1; i <= 12; i++){
            monthCombo.addItem(String.valueOf(i));
            empMonthCombo.addItem(String.valueOf(i));
        }

        //adding a label on the drop down menu to explain to user what to select
        yearCombo.addItem("-year-");
        empYearCombo.addItem("-year-");
        //all the options for year in drop down menu- for both dob and hire date
        for(int i = 1950; i <= 2024; i++){
            yearCombo.addItem(String.valueOf(i));
            empYearCombo.addItem(String.valueOf(i));
        }

        //panel for creating an employee and setting the panels layout
        JPanel createEmployeePanel = new JPanel(new GridLayout(12, 2));

        //adding components to panel
        JLabel nameLabel = new JLabel("Name: ");
        SetStyle.setInstructionText(nameLabel);
        createEmployeePanel.add(nameLabel);
        createEmployeePanel.add(nameField);

        JLabel emailLabel = new JLabel("Email: ");
        SetStyle.setInstructionText(emailLabel);
        createEmployeePanel.add(emailLabel);
        createEmployeePanel.add(emailField);
        
        JLabel numberLabel = new JLabel("Phone Number: ");
        SetStyle.setInstructionText(numberLabel);
        createEmployeePanel.add(numberLabel);
        createEmployeePanel.add(phoneNumberField);

        JLabel roleLabel = new JLabel("Role: ");
        SetStyle.setInstructionText(roleLabel);
        createEmployeePanel.add(roleLabel);
        createEmployeePanel.add(roleCombo);

        JLabel streetLabel = new JLabel("Street Address: ");
        SetStyle.setInstructionText(streetLabel);
        createEmployeePanel.add(streetLabel);
        createEmployeePanel.add(streetField);

        JLabel cityLabel = new JLabel("City: ");
        SetStyle.setInstructionText(cityLabel);
        createEmployeePanel.add(cityLabel);
        createEmployeePanel.add(cityField);

        JLabel stateLabel = new JLabel("State/Territory: ");
        SetStyle.setInstructionText(stateLabel);
        createEmployeePanel.add(stateLabel);
        createEmployeePanel.add(stateCombo);

        JLabel zipLabel = new JLabel("Zip Code: ");
        SetStyle.setInstructionText(zipLabel);
        createEmployeePanel.add(zipLabel);
        createEmployeePanel.add(zipField);

        JLabel genderLabel = new JLabel("Gender: ");
        SetStyle.setInstructionText(genderLabel);
        createEmployeePanel.add(genderLabel);
        createEmployeePanel.add(genderCombo);

        JLabel dobLabel = new JLabel("Date of Birth: ");
        SetStyle.setInstructionText(dobLabel);
        createEmployeePanel.add(dobLabel);
        JPanel dobPanel = new JPanel();
        SetStyle.setBackground(dobPanel);
        dobPanel.add(dayCombo);
        dobPanel.add(monthCombo);
        dobPanel.add(yearCombo);
        createEmployeePanel.add(dobPanel);

        JLabel hireLabel = new JLabel("Date of Employment: ");
        SetStyle.setInstructionText(hireLabel);
        createEmployeePanel.add(hireLabel);
        JPanel hirePanel = new JPanel();
        SetStyle.setBackground(hirePanel);
        hirePanel.add(empDayCombo);
        hirePanel.add(empMonthCombo);
        hirePanel.add(empYearCombo);
        createEmployeePanel.add(hirePanel);

        createEmployeePanel.add(createButton);
        SetStyle.setBackground(createEmployeePanel);

        //add panel to the mainPanel with name "Create Employee"
        mainPanel.add(createEmployeePanel, "Create Employee");

        //show "Create Employee" panel using cardLayout
        cardLayout.show(mainPanel, "Create Employee");

        //action listener so that once create button is clicked, input in each box will be taken and put in constructor to create a new object in employee class
        createButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            String role = (String) roleCombo.getSelectedItem();
            String street = streetField.getText();
            String city = cityField.getText();
            String state = (String)stateCombo.getSelectedItem();
            String zip = zipField.getText();
            String day = (String) dayCombo.getSelectedItem();
            String month = (String) monthCombo.getSelectedItem();
            String year = (String) yearCombo.getSelectedItem();
            String gender = (String) genderCombo.getSelectedItem();
            String empDay = (String) empDayCombo.getSelectedItem();
            String empMonth = (String) empMonthCombo.getSelectedItem();
            String empYear = (String) empYearCombo.getSelectedItem();

            //calling constructor to create a new employee in Employee class
            Employee newEmployee = new Employee(name, email, phoneNumber, role, street, city, state, zip, day, month, year, gender, empDay, empMonth, empYear);
            //adding employee to the system
            employeeManagementSystem.addEmployee(newEmployee);
            //success message displayed to user
            JOptionPane.showMessageDialog(mainPanel, name + " added successfully.");
        });
    }
}