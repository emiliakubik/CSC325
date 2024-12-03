import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.*;

public class SetStyle {
    //For text that describes to the user what they should do
    public static void setInstructionText(JLabel text) {
        text.setFont(new Font("Verdana", Font.PLAIN, 16));
        text.setForeground(new Color(10, 50, 10));
        text.setOpaque(true);
    }

    //For the header of any display table 
    public static void setTableHeaderText(JTableHeader header) {
        header.setFont(new Font("Verdana", Font.PLAIN, 12));
        header.setForeground(new Color(255, 255, 240));
        header.setOpaque(true);
        header.setBackground(new Color(10, 50, 10));
        header.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 210), 2));
    }

    //For all text boxes, sets the color of what is written inside, to make it disappear when clicked use setFocusListener()
    public static void setPlaceHolder(JTextField text) {
        text.setFont(new Font("Verdana", Font.PLAIN, 12));
        text.setForeground(Color.LIGHT_GRAY);
    }

    //For any buttons (specifically save buttons and the side ones in main display)
    public static void setButton(JButton button) {
        button.setFont(new Font("Verdana", Font.PLAIN, 16));
        button.setForeground(new Color(245, 245, 220));
        button.setOpaque(true);
        button.setBackground(new Color(20, 60, 20));
    
        // Set the button size (width, height)
        button.setPreferredSize(new Dimension(280, 65)); // Adjust the width and height as needed
    
        // Set the black border
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4)); // 2px thickness
    }    

    public static void setBackground(JPanel panel) {
        // panel.setBackground(new Color(10, 50, 10));
    }

    //For the entire display table, besides the header, it has its own method above
    public static void setTable(JTable table) {
        table.setFont(new Font("Verdana", Font.PLAIN, 12));
        table.setForeground(new Color(10, 50, 10));
        table.setOpaque(true);
        table.setBackground(new Color(255, 255, 240));
    }

    //For all text boxes, describes what should be typed in the text box and disappears when clicked on box
    public static void setFocusListener(JTextField field, String holder) {
        SetStyle.setPlaceHolder(field);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(holder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(holder);
                    field.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    //This sets the style of the boxes that appear to give specific details on sprint eval/job history
    public static void setInfoStyle(JLabel questionLabel, JLabel answerLabel){
        questionLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setOpaque(true);
        questionLabel.setBackground(new Color(10, 50, 10));
        questionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        answerLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        answerLabel.setForeground(Color.DARK_GRAY);
    }
}
