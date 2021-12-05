import javax.swing.*;
import java.awt.*;

public class NewStudentFrame extends JFrame {


    public NewStudentFrame(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("New Student");
        this.setLayout(new GridLayout(8,2));
        this.initializeLabels();

        this.setVisible(true);
        this.setSize(500,500);
        this.setResizable(false);
    }

    private void initializeLabels() {
        JLabel firstNameLabel = new JLabel("First Name: ");
        this.add(firstNameLabel,0);
        JTextField firstNameFiled = new JTextField("First Name",5);
        this.add(firstNameFiled);
    }




}
