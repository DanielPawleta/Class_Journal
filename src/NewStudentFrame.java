import javax.swing.*;
import java.awt.*;

public class NewStudentFrame extends JFrame {
    MyFrame myFrame;


    public NewStudentFrame(MyFrame myFrame){
        this.myFrame = myFrame;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("New Student");
        this.setLayout(new GridBagLayout());
        this.initializeLabels();

        this.setVisible(true);
        this.setSize(500,500);
        this.setResizable(false);
    }

    private void initializeLabels() {
        //first name
        JLabel firstNameLabel = new JLabel("First Name: ");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        this.add(firstNameLabel,c);
        JTextField firstNameField = new JTextField("First Name",15);
        GridBagConstraints d = new GridBagConstraints();
        d.gridx=1;
        d.gridy=0;
        this.add(firstNameField,d);

        //last name
        JLabel lastNameLabel = new JLabel("Last Name: ");
        GridBagConstraints e = new GridBagConstraints();
        e.gridx=0;
        e.gridy=1;
        this.add(lastNameLabel,e);
        JTextField lastNameField = new JTextField("Last Name",15);
        GridBagConstraints f = new GridBagConstraints();
        f.gridx=1;
        f.gridy=1;
        this.add(lastNameField,f);

        //city
        JLabel cityLabel = new JLabel("City: ");
        GridBagConstraints g = new GridBagConstraints();
        g.gridx=0;
        g.gridy=2;
        this.add(cityLabel,g);
        JTextField cityField = new JTextField("City",15);
        GridBagConstraints h = new GridBagConstraints();
        h.gridx=1;
        h.gridy=2;
        this.add(cityField,h);

        //phone number
        JLabel phoneNumberLabel = new JLabel("Phone number: ");
        GridBagConstraints i = new GridBagConstraints();
        i.gridx=0;
        i.gridy=3;
        this.add(phoneNumberLabel,i);
        JTextField phoneNumberField = new JTextField("Phone number",15);
        GridBagConstraints j = new GridBagConstraints();
        j.gridx=1;
        j.gridy=3;
        this.add(phoneNumberField,j);

    }

    @Override
    public void dispose() {
        myFrame.setVisible(true);
        super.dispose();
    }
}
