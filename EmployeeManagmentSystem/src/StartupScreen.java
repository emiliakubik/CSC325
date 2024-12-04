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
        frame.setSize(350, 450);

        // Load the logo image
        ImageIcon logoIcon = new ImageIcon("EmployeeManagmentSystem/src/images/HR_APP_logo.png");
        Image logoImage = logoIcon.getImage();

        // Set the taskbar icon
        frame.setIconImage(logoImage);

        // Resize the image to 400x400
        Image resizedImage = logoImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the resized image
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create a label with the resized logo
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the image

        // Create the content panel and set the layout manager
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE); // Set background to white

        // Add the logo label to the content panel
        contentPanel.add(Box.createVerticalGlue()); // Add glue to center the logo
        contentPanel.add(logoLabel); // Add the logo image
        contentPanel.add(Box.createVerticalGlue()); // Add glue to center the logo

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