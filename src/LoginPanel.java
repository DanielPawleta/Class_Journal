import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {
    private JButton teacherButton;
    private JButton principalButton;
    private JButton adminButton;
    private String password;
    private MyFrame myFrame;


    public LoginPanel(MyFrame myFrame) {
        this.myFrame = myFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());

        teacherButton = this.addButton("Teacher");
        this.add(teacherButton);
        this.add(Box.createVerticalGlue());

        principalButton = this.addButton("Principal");
        this.add(principalButton);
        this.add(Box.createVerticalGlue());

        adminButton = this.addButton("Admin");
        this.add(adminButton);
        this.add(Box.createVerticalGlue());
    }

    private JButton addButton(String buttonText){
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusable(false);
        return button;
    }

    private boolean checkPassword(){
        JTextField passwordField = new JTextField(10);
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Password: "));
        jPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null,jPanel,"Please enter password", JOptionPane.OK_CANCEL_OPTION);

        if (result==JOptionPane.CANCEL_OPTION) return false;
        else {
            if (password.equals(passwordField.getText())){
                JOptionPane.showMessageDialog(jPanel,"Login successful!","Welcome!", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("password correct");
                return true;
                //disableButtons();
            }
            else {
                JOptionPane.showMessageDialog(jPanel,"Invalid password","Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
    }

    private void disableButtons(){
        teacherButton.setEnabled(false);
        principalButton.setEnabled(false);
        adminButton.setEnabled(false);
    }

    protected void ableButtons(){
        teacherButton.setEnabled(true);
        principalButton.setEnabled(true);
        adminButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == teacherButton){
            System.out.println("teacher login");
            password = "teacher";
            if(checkPassword()) myFrame.setLoggedAs(1);
        }
        if (e.getSource() == principalButton){
            System.out.println("principal login");
            password = "principal";
            if(checkPassword()) myFrame.setLoggedAs(2);
        }
        if (e.getSource() == adminButton){
            System.out.println("admin login");
            password = "admin";
            if(checkPassword()) myFrame.setLoggedAs(3);
        }
    }
}
