import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import javax.swing.text.*;

public class Login {

    private JFrame frame;

    public Login() {
        // Initialize the frame
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Make the window unresizable
        frame.setResizable(false);

        // Load the logo
        ImageIcon customIcon = new ImageIcon("EmployeeManagmentSystem/src/images/HR_APP_logo.png");
        frame.setIconImage(customIcon.getImage());

        // Create a panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        // Add employee ID field
        JLabel employeeIDLabel = new JLabel("Employee ID: ");
        JTextField employeeIDField = new JTextField(20);

        // Add birthdate label and field with placeholder
        JLabel birthdateLabel = new JLabel("Birthdate (DD/MM/YYYY): ");
        JFormattedTextField birthdateField = new JFormattedTextField(createFormatter("##/##/####"));
        birthdateField.setValue(null); // No initial value
        birthdateField.setColumns(10); // Fixed width for birthdate field
        birthdateField.setFont(new Font("Arial", Font.PLAIN, 14));
        birthdateField.setForeground(Color.BLACK); // Text color is black
        birthdateField.setBackground(Color.WHITE);
        birthdateField.setCaretColor(Color.BLACK); // Cursor color

        // Set placeholder color (gray)
        birthdateField.setForeground(Color.black); // Placeholder text

        // Add login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(20, 60, 20)); // Green background for the login button
        loginButton.setForeground(new Color(245, 245, 220)); // White text for the button

        // Add hover effect to the login button
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(89, 88, 87)); // Change the background color when hovered
                loginButton.setForeground(Color.decode("#F2F2F2")); // Change the text color when hovered
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(20, 60, 20)); // Restore original background color
                loginButton.setForeground(new Color(245, 245, 220)); // Restore original text color
            }
        });

        // Add components to login panel
        loginPanel.add(employeeIDLabel);
        loginPanel.add(employeeIDField);
        loginPanel.add(birthdateLabel);
        loginPanel.add(birthdateField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(loginButton);

        // Add login panel to the frame
        frame.add(loginPanel, BorderLayout.CENTER);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the login screen
                frame.dispose();

                // Launch the main GUI (Employee Management System)
                new GUI(); // Replace with your main GUI class
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Helper method to create a formatter for the birthdate field
    private MaskFormatter createFormatter(String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
}
