import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class StartupScreen {

    private JFrame frame;

    public StartupScreen() {
        // Initialize the frame
        frame = new JFrame("Startup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Create a label for the startup message
        JLabel welcomeLabel = new JLabel("Welcome to Employee Tracker", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(welcomeLabel, BorderLayout.CENTER);

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