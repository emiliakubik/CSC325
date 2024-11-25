import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AbstractGuiPanel{
    protected JPanel panel;
    protected JButton submitButton;

    public AbstractGuiPanel(){
        panel = new JPanel(new BorderLayout());
        initialize();
    }

    public abstract void initialize();

    protected void setSubmitButtonAction(ActionListener actionListener){
        submitButton.addActionListener(actionListener);
    }

    public JPanel getPanel(){
        return panel;
    }
}
