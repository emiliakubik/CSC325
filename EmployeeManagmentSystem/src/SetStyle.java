import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import java.awt.*;

public class SetStyle {
    public static void setInstructionText(JLabel text) {
        text.setFont(new Font("Verdana", Font.PLAIN, 16));
        text.setForeground(new Color(10, 50, 10));
        text.setOpaque(true);
        // text.setBackground(new Color(10, 50, 10));
        // text.setBorder(BorderFactory.createLineBorder(new Color(20, 60, 20), 2));
    }

    public static void setTableHeaderText(JTableHeader header) {
        header.setFont(new Font("Verdana", Font.PLAIN, 12));
        header.setForeground(new Color(255, 255, 240));
        header.setOpaque(true);
        header.setBackground(new Color(10, 50, 10));
        header.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 210), 2));
    }

    public static void setPlaceHolder(JTextField text) {
        text.setFont(new Font("Verdana", Font.PLAIN, 12));
        text.setForeground(Color.LIGHT_GRAY);
        // text.setOpaque(true);
        // text.setBackground(new Color(255, 255, 240));
        // text.setBorder(BorderFactory.createLineBorder(new Color(10, 50, 10), 5));
    }

    public static void setButton(JButton button) {
        button.setFont(new Font("Verdana", Font.PLAIN, 16));
        button.setForeground(new Color(245, 245, 220));
        button.setOpaque(true);
        button.setBackground(new Color(20, 60, 20));

        // Ensure padding inside the button to prevent color bleed outside the border
        button.setMargin(new Insets(10, 20, 10, 20)); // Adjust margins as needed

        // Set the rounded corners with a custom border
        button.setBorder(new RoundedBorder(40)); // 40px radius for rounded corners
    }

    public static void setBackground(JPanel panel) {
        // panel.setBackground(new Color(10, 50, 10));
    }

    public static void setTable(JTable table) {
        table.setFont(new Font("Verdana", Font.PLAIN, 12));
        table.setForeground(new Color(10, 50, 10));
        table.setOpaque(true);
        table.setBackground(new Color(255, 255, 240));
    }

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

    // RoundedBorder class to create a custom rounded border
    public static class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 5, this.radius + 5, this.radius + 5, this.radius + 5);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set color for the border
            g2d.setColor(Color.BLACK);
            g2d.draw(new RoundRectangle2D.Float(x, y, width - 1, height - 1, radius, radius));
        }
    }
}