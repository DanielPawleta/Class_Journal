import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

public class StudentFrame extends JFrame implements ActionListener {
    private Vector<Vector<String>> dataRow;
    private MyFrame myFrame;
    private int selectedStudentId;
    private Insets insets = new Insets(10,10,10,10);

    private JButton backButton;
    private JButton updateNameButton;
    private JButton updateLastNameButton;
    private JButton updateCityButton;
    private JButton updatePhoneNumberButton;
    private JButton updateDateOfBirthButton;
    private JButton updateParentsPhoneNumberButton;
    private JButton updateClassButton;

    private String firstName;
    private String lastName;
    private String city;
    private String phoneNumber;
    private String parentsPhoneNumber;
    private JDatePickerImpl datePicker;
    private String dateOfBirth;

    private JLabel firstNameTextField;
    private JLabel lastNameTextField;
    private JLabel cityTextField;
    private JLabel phoneNumberTextField;
    private JLabel dateOfBirthTextField;
    private JLabel parentsPhoneNumberTextField;
    private JLabel classTextField;

    private int phoneNumberInt;
    private int parentsPhoneNumberInt;


    public StudentFrame(MyFrame myFrame, Vector<Vector<String>> dataRow){
        this.myFrame = myFrame;
        this.dataRow = dataRow;
        this.selectedStudentId=0;

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Student");
        this.setLayout(new GridBagLayout());
        this.initializeTitleLabel();
        this.initializeLabels();
        this.initializeTextFields();
        this.initializeUpdateButtons();
        this.initializeButtons();

        this.setVisible(true);
        this.setSize(500,600);
        this.setResizable(false);
    }

    public StudentFrame(MyFrame myFrame, Vector<Vector<String>> dataRow,int selectedStudentId) {
        this(myFrame, dataRow);
        this.selectedStudentId = selectedStudentId;
    }

    private void initializeTitleLabel() {
        JLabel titleLabel = new JLabel("Student");
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        GridBagConstraints a = new GridBagConstraints();
        a.insets = new Insets(50,50,50,50);
        a.gridwidth=3;
        a.gridx=0;
        a.gridy=0;
        this.add(titleLabel,a);
    }

    private void initializeLabels() {
            //first name
            JLabel firstNameLabel = new JLabel("First Name: ");
            GridBagConstraints b = new GridBagConstraints();
            b.insets = insets;
            b.gridx = 0;
            b.gridy = 1;
            this.add(firstNameLabel, b);

            //last name
            JLabel lastNameLabel = new JLabel("Last Name: ");
            GridBagConstraints e = new GridBagConstraints();
            e.insets = insets;
            e.gridx = 0;
            e.gridy = 2;
            this.add(lastNameLabel, e);

            //city
            JLabel cityLabel = new JLabel("City: ");
            GridBagConstraints g = new GridBagConstraints();
            g.insets = insets;
            g.gridx = 0;
            g.gridy = 3;
            this.add(cityLabel, g);

            //phone number
            JLabel phoneNumberLabel = new JLabel("Phone number: ");
            GridBagConstraints i = new GridBagConstraints();
            i.insets = insets;
            i.gridx = 0;
            i.gridy = 4;
            this.add(phoneNumberLabel, i);

            //Date of birth
            JLabel dateOfBirthLabel = new JLabel("Date of birth: ");
            GridBagConstraints k = new GridBagConstraints();
            k.insets = insets;
            k.gridx = 0;
            k.gridy = 5;
            this.add(dateOfBirthLabel, k);

            //parents phone number
            JLabel parentsPhoneNumberLabel = new JLabel("Parents phone number: ");
            GridBagConstraints m = new GridBagConstraints();
            m.insets = insets;
            m.gridx = 0;
            m.gridy = 6;
            this.add(parentsPhoneNumberLabel, m);

            //class
            JLabel classLabel = new JLabel("Class: ");
            GridBagConstraints o = new GridBagConstraints();
            o.insets = insets;
            o.gridx = 0;
            o.gridy = 7;
            this.add(classLabel, o);
        }

    private void initializeTextFields(){
        //border for all text fields
        Border blackline = BorderFactory.createLineBorder(Color.black);

        String firstName = dataRow.get(selectedStudentId).get(1);
        firstNameTextField = new JLabel(firstName);
        firstNameTextField.setBorder(blackline);
        firstNameTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = insets;
        c.gridx = 1;
        c.gridy = 1;
        this.add(firstNameTextField, c);

        String lastName = dataRow.get(selectedStudentId).get(2);
        lastNameTextField = new JLabel(lastName);
        lastNameTextField.setBorder(blackline);
        lastNameTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = insets;
        f.gridx = 1;
        f.gridy = 2;
        this.add(lastNameTextField, f);

        String city = dataRow.get(selectedStudentId).get(3);
        cityTextField = new JLabel(city);
        cityTextField.setBorder(blackline);
        cityTextField.setBorder(blackline);
        cityTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints h = new GridBagConstraints();
        h.insets = insets;
        h.gridx = 1;
        h.gridy = 3;
        this.add(cityTextField, h);

        String phoneNumber = dataRow.get(selectedStudentId).get(4);
        phoneNumberTextField = new JLabel(phoneNumber);
        phoneNumberTextField.setBorder(blackline);
        phoneNumberTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints j = new GridBagConstraints();
        j.insets = insets;
        j.gridx = 1;
        j.gridy = 4;
        this.add(phoneNumberTextField, j);

        String dateOfBirth = dataRow.get(selectedStudentId).get(5);
        dateOfBirthTextField = new JLabel(dateOfBirth);
        dateOfBirthTextField.setBorder(blackline);
        dateOfBirthTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints l = new GridBagConstraints();
        l.insets = insets;
        l.gridx = 1;
        l.gridy = 5;
        this.add(dateOfBirthTextField, l);

        String parentsPhoneNumber = dataRow.get(selectedStudentId).get(6);
        parentsPhoneNumberTextField = new JLabel(parentsPhoneNumber);
        parentsPhoneNumberTextField.setBorder(blackline);
        parentsPhoneNumberTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints n = new GridBagConstraints();
        n.insets = insets;
        n.gridx = 1;
        n.gridy = 6;
        this.add(parentsPhoneNumberTextField, n);

        String className = dataRow.get(selectedStudentId).get(7);
        classTextField = new JLabel(className);
        classTextField.setBorder(blackline);
        classTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints p = new GridBagConstraints();
        p.insets = insets;
        p.gridx = 1;
        p.gridy = 7;
        this.add(classTextField, p);
    }

    private void initializeUpdateButtons(){
        updateNameButton = new JButton("update");
        updateNameButton.addActionListener(this);
        GridBagConstraints a = new GridBagConstraints();
        a.insets = insets;
        a.gridx = 2;
        a.gridy = 1;
        this.add(updateNameButton, a);

        updateLastNameButton = new JButton("update");
        updateLastNameButton.addActionListener(this);
        GridBagConstraints b = new GridBagConstraints();
        b.insets = insets;
        b.gridx = 2;
        b.gridy = 2;
        this.add(updateLastNameButton, b);

        updateCityButton = new JButton("update");
        updateCityButton.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = insets;
        c.gridx = 2;
        c.gridy = 3;
        this.add(updateCityButton, c);

        updatePhoneNumberButton = new JButton("update");
        updatePhoneNumberButton.addActionListener(this);
        GridBagConstraints d = new GridBagConstraints();
        d.insets = insets;
        d.gridx = 2;
        d.gridy = 4;
        this.add(updatePhoneNumberButton, d);

        updateDateOfBirthButton = new JButton("update");
        updateDateOfBirthButton.addActionListener(this);
        GridBagConstraints e = new GridBagConstraints();
        e.insets = insets;
        e.gridx = 2;
        e.gridy = 5;
        this.add(updateDateOfBirthButton, e);

        updateParentsPhoneNumberButton = new JButton("update");
        updateParentsPhoneNumberButton.addActionListener(this);
        GridBagConstraints f = new GridBagConstraints();
        f.insets = insets;
        f.gridx = 2;
        f.gridy = 6;
        this.add(updateParentsPhoneNumberButton, f);

        updateClassButton = new JButton("update");
        updateClassButton.addActionListener(this);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = insets;
        g.gridx = 2;
        g.gridy = 7;
        this.add(updateClassButton, g);
    }

    private void initializeButtons() {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints r = new GridBagConstraints();
        r.insets = new Insets(50,10,10,10);
        r.gridx = 1;
        r.gridy = 8;
        this.add(backButton, r);
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

        if (e.getSource()==updateNameButton){

        }

    }
}
