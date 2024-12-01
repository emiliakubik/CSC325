import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login {

    private JFrame frame; // Make the frame an instance variable

    public Login() {
        // Initialize the frame
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Load the logo  
        ImageIcon customIcon = new ImageIcon("EmployeeManagmentSystem/src/images/HR_APP_logo.png");
        
        // Set the logo as the window icon
        frame.setIconImage(customIcon.getImage());

        // Create a panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        // Add username and password fields
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        // Add Login button
        JButton loginButton = new JButton("Login");

        // Add components to login panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());  // Empty label for spacing
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
                new GUI();  // Replace with your main GUI class
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}