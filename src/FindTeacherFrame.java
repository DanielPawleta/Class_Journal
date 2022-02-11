import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindTeacherFrame extends JFrame implements ActionListener {
    private MyFrame myFrame;
    private Insets insets = new Insets(10,10,10,10);

    private JButton backButton;
    private JButton findButton;

    private String firstName;
    private String lastName;

    private JTextField firstNameField;
    private JTextField lastNameField;

    public FindTeacherFrame(MyFrame myFrame){
        this.myFrame = myFrame;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Find Teacher");
        this.setLayout(new GridBagLayout());
        this.initializeTitleLabel();
        this.initializeLabels();
        this.initializeButtons();

        this.setVisible(true);
        this.setSize(500,600);
        this.setResizable(false);
    }

    private void initializeTitleLabel() {
        JLabel titleLabel = new JLabel("Find teacher");
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(50,50,50,50);
        b.gridwidth=2;
        b.gridx=0;
        b.gridy=0;
        this.add(titleLabel,b);
    }

    private void initializeLabels() {
            //first name
            JLabel firstNameLabel = new JLabel("First Name: ");
            GridBagConstraints c = new GridBagConstraints();
            c.insets = insets;
            c.gridx = 0;
            c.gridy = 1;
            this.add(firstNameLabel, c);
            firstNameField = new JTextField("Mateusz",15);
            GridBagConstraints d = new GridBagConstraints();
            d.insets = insets;
            d.gridx = 1;
            d.gridy = 1;
            this.add(firstNameField, d);

            //last name
            JLabel lastNameLabel = new JLabel("Last Name: ");
            GridBagConstraints e = new GridBagConstraints();
            e.insets = insets;
            e.gridx = 0;
            e.gridy = 2;
            this.add(lastNameLabel, e);
            lastNameField = new JTextField("Kawulok",15);
            GridBagConstraints f = new GridBagConstraints();
            f.insets = insets;
            f.gridx = 1;
            f.gridy = 2;
            this.add(lastNameField, f);
        }

    private void initializeButtons() {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(50,10,10,10);
        g.gridx = 0;
        g.gridy = 8;
        this.add(backButton, g);

        findButton = new JButton("Find teacher");
        findButton.addActionListener(this);
        GridBagConstraints h = new GridBagConstraints();
        h.insets = new Insets(50,10,10,10);
        h.gridx = 1;
        h.gridy = 8;
        this.add(findButton, h);
    }

    @Override
    public void dispose() {
        myFrame.setVisible(true);
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backButton){
            System.out.println("back button");
            dispose();
        }

        if (e.getSource()== findButton){
            System.out.println("Find button");

            if (checkFields()) {
                int result = myFrame.findTeacher(firstName,lastName);
                if (result==0) {
                    JOptionPane.showMessageDialog(this,"No results","Info", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                super.dispose();
            }
        }
    }

    private boolean checkFields(){
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();

        if      (!firstName.equals("") && !lastName.equals(""))
        {
            return true;
        }
        else {
            JOptionPane.showMessageDialog(this,"Fields first name and last name are required","Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
}
