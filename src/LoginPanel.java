import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {
    private JButton studentButton;
    private JButton teacherButton;
    private JButton adminButton;
    private String password;
    private MyFrame myFrame;


    public LoginPanel(MyFrame myFrame) {
        this.myFrame = myFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());

        studentButton = this.addButton("Student");
        this.add(studentButton);
        this.add(Box.createVerticalGlue());

        teacherButton = this.addButton("Teacher");
        this.add(teacherButton);
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

    private void checkPassword(){
        JTextField passwordField = new JTextField("student",5);
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Password: "));
        jPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null,jPanel,"Please enter password", JOptionPane.OK_CANCEL_OPTION);

        if (result==JOptionPane.CANCEL_OPTION) return;
        else {
            if (password.equals(passwordField.getText())){
                JOptionPane.showMessageDialog(jPanel,"Login successful!","Welcome!", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("password correct");
                myFrame.setLoggedAs(1);
                //disableButtons();
            }
            else {
                JOptionPane.showMessageDialog(jPanel,"Invalid password","Warning", JOptionPane.WARNING_MESSAGE);
            }
        }


    }

    private void disableButtons(){
        studentButton.setEnabled(false);
        teacherButton.setEnabled(false);
        adminButton.setEnabled(false);
    }

    protected void ableButtons(){
        studentButton.setEnabled(true);
        teacherButton.setEnabled(true);
        adminButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentButton){
            System.out.println("student login");
            password = "student";
            checkPassword();
        }
    }
}
