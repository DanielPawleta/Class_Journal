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
    private JButton addButton;
    private JButton updateNameButton;

    private String firstName;
    private String lastName;
    private String city;
    private String phoneNumber;
    private String parentsPhoneNumber;
    private JDatePickerImpl datePicker;
    private String dateOfBirth;

    private JLabel firstNameTextField;
    private JTextField lastNameField;
    private JTextField cityField;
    private JTextField phoneNumberField;
    private JTextField parentsPhoneNumberField;

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
        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(50,50,50,50);
        b.gridwidth=3;
        b.gridx=0;
        b.gridy=0;
        this.add(titleLabel,b);
    }

    private void initializeLabels() {
            //border for all text fields
            Border blackline = BorderFactory.createLineBorder(Color.black);


            //first name
            JLabel firstNameLabel = new JLabel("First Name: ");
            GridBagConstraints c = new GridBagConstraints();
            c.insets = insets;
            c.gridx = 0;
            c.gridy = 1;
            this.add(firstNameLabel, c);
            String firstName = dataRow.get(selectedStudentId).get(1);
            firstNameTextField = new JLabel(firstName);
            firstNameTextField.setBorder(blackline);
            firstNameTextField.setPreferredSize(new Dimension(150,20));
            GridBagConstraints d = new GridBagConstraints();
            d.insets = insets;
            d.gridx = 1;
            d.gridy = 1;
            this.add(firstNameTextField, d);
            updateNameButton = new JButton("update");
            GridBagConstraints e = new GridBagConstraints();
            e.insets = insets;
            e.gridx = 2;
            e.gridy = 1;
            this.add(updateNameButton, e);



/*
            //last name
            JLabel lastNameLabel = new JLabel("Last Name: ");
            GridBagConstraints e = new GridBagConstraints();
            e.insets = insets;
            e.gridx = 0;
            e.gridy = 2;
            this.add(lastNameLabel, e);
            lastNameField = new JTextField(15);
            GridBagConstraints f = new GridBagConstraints();
            f.insets = insets;
            f.gridx = 1;
            f.gridy = 2;
            this.add(lastNameField, f);

 */

            //city
            JLabel cityLabel = new JLabel("City: ");
            GridBagConstraints g = new GridBagConstraints();
            g.insets = insets;
            g.gridx = 0;
            g.gridy = 3;
            this.add(cityLabel, g);
            cityField = new JTextField(15);
            GridBagConstraints h = new GridBagConstraints();
            h.insets = insets;
            h.gridx = 1;
            h.gridy = 3;
            this.add(cityField, h);

            //phone number
            JLabel phoneNumberLabel = new JLabel("Phone number: ");
            GridBagConstraints i = new GridBagConstraints();
            i.insets = insets;
            i.gridx = 0;
            i.gridy = 4;
            this.add(phoneNumberLabel, i);
            phoneNumberField = new JTextField(15);
            GridBagConstraints j = new GridBagConstraints();
            j.insets = insets;
            j.gridx = 1;
            j.gridy = 4;
            this.add(phoneNumberField, j);

            //Date of birth
            JLabel dateOfBirthLabel = new JLabel("Date of birth: ");
            GridBagConstraints k = new GridBagConstraints();
            k.insets = insets;
            k.gridx = 0;
            k.gridy = 5;
            this.add(dateOfBirthLabel, k);

            UtilDateModel model = new UtilDateModel();
            Properties properties = new Properties();
            properties.put("text.today", "Today");
            properties.put("text.month", "Month");
            properties.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
            datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            GridBagConstraints l = new GridBagConstraints();
            l.insets = insets;
            l.gridx = 1;
            l.gridy = 5;
            this.add(datePicker, l);


            //parents phone number
            JLabel parentsPhoneNumberLabel = new JLabel("Parents phone number: ");
            GridBagConstraints m = new GridBagConstraints();
            m.insets = insets;
            m.gridx = 0;
            m.gridy = 6;
            this.add(parentsPhoneNumberLabel, m);
            parentsPhoneNumberField = new JTextField(15);
            GridBagConstraints n = new GridBagConstraints();
            n.insets = insets;
            n.gridx = 1;
            n.gridy = 6;
            this.add(parentsPhoneNumberField, n);

            //class
            JLabel classLabel = new JLabel("Class: ");
            GridBagConstraints o = new GridBagConstraints();
            o.insets = insets;
            o.gridx = 0;
            o.gridy = 7;
            this.add(classLabel, o);
            JTextField classField = new JTextField(15);
            GridBagConstraints p = new GridBagConstraints();
            p.insets = insets;
            p.gridx = 1;
            p.gridy = 7;
            this.add(classField, p);

        }

    private void initializeButtons() {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints r = new GridBagConstraints();
        r.insets = new Insets(50,10,10,10);
        r.gridx = 0;
        r.gridy = 8;
        this.add(backButton, r);

        addButton = new JButton("Add student");
        addButton.addActionListener(this);
        GridBagConstraints s = new GridBagConstraints();
        s.insets = new Insets(50,10,10,10);
        s.gridx = 1;
        s.gridy = 8;
        this.add(addButton, s);
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

        if (e.getSource()==addButton){
            System.out.println("Add button");

            if (checkFields()) {
                myFrame.addStudent(firstName,lastName,city,phoneNumberInt,dateOfBirth,parentsPhoneNumberInt);
                JOptionPane.showMessageDialog(this,"Student added!","New student", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    private boolean checkFields(){
        //firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        city = cityField.getText();
        phoneNumber = phoneNumberField.getText();
        parentsPhoneNumber = parentsPhoneNumberField.getText();


        if      (!firstName.equals("") &&
                !lastName.equals("") &&
                !city.equals("")
                )
        {
            try {
                phoneNumberInt = Integer.parseInt(phoneNumber);
                parentsPhoneNumberInt = Integer.parseInt(parentsPhoneNumber);

                Date date = (Date) datePicker.getModel().getValue();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateOfBirth = simpleDateFormat.format(date);

            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Unsupported phone number value","Info", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            catch (NullPointerException e){
                JOptionPane.showMessageDialog(this,"Please select date of birth","Info", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            return true;
        }
        else {
            JOptionPane.showMessageDialog(this,"Fields first name, last name and city are required","Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

}
