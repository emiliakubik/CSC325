import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class StartupScreen {

    private JFrame frame;

    public StartupScreen() {
        // Initialize the frame
        frame = new JFrame("Startup");
        frame.setUndecorated(true); // Remove title bar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        // Create the content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Set the background color
        contentPanel.setBackground(Color.lightGray);

        // Create a label for the startup message
        JLabel welcomeLabel = new JLabel("Welcome to Employee Tracker", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Load the logo
        ImageIcon logoIcon = new ImageIcon("CSC325\\EmployeeManagmentSystem\\src\\images\\logov1.png");
        Image logoImage = logoIcon.getImage();
        Image resizedLogoImage = logoImage.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedLogoImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the image

        // Add the components to the content panel
        contentPanel.add(Box.createVerticalGlue());  // Add glue to push content to the center
        contentPanel.add(logoLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some spacing between text and logo
        contentPanel.add(welcomeLabel);
        contentPanel.add(Box.createVerticalGlue());  // Add glue to push content to the center

        // Add the content panel to the frame
        frame.add(contentPanel, BorderLayout.CENTER);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Display the frame
        frame.setVisible(true);

        // Timer to automatically close the splash screen and open the login screen
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Close the startup screen
                frame.dispose();

                // Open the login screen
                new Login(); // Transition to the login screen
            }
        }, 2500); // 2.5 second delay before opening the login screen
    }
}