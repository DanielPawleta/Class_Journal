import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class ChooseStudentFrame extends JFrame implements ActionListener {
    private Vector<Vector<String>> dataRow;
    private Vector<String> columnNames;

    private Insets insets = new Insets(10,10,10,10);
    private JButton backButton;
    private JButton selectButton;

    private String firstName;
    private String lastName;
    private String city;
    private String phoneNumber;
    private String parentsPhoneNumber;
    private JDatePickerImpl datePicker;
    private String dateOfBirth;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField cityField;
    private JTextField phoneNumberField;
    private JTextField parentsPhoneNumberField;

    private int phoneNumberInt;
    private int parentsPhoneNumberInt;


    public ChooseStudentFrame(Vector<Vector<String>> dataRow){
        this.dataRow = dataRow;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Choose Student");
        this.setLayout(new GridBagLayout());
        this.initializeTitleLabel();
        this.initializeTab();
        this.initializeButtons();

        this.setVisible(true);
        this.setSize(500,600);
        this.setResizable(true);
        //this.pack();
    }

    private void initializeTitleLabel() {
        JLabel titleLabel = new JLabel("Choose student");
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(50,50,50,50);
        b.gridwidth=2;
        b.gridheight=1;
        b.gridx=0;
        b.gridy=0;
        this.add(titleLabel,b);
    }

    private void initializeTab() {
        initializeColumnNamesRow();

        JTable jTable = new JTable(dataRow,columnNames);
        JScrollPane scrollPane = new JScrollPane(jTable);
        jTable.setFillsViewportHeight(true);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth=2;
        c.gridheight=8;
        c.insets = insets;
        c.gridx = 0;
        c.gridy = 2;

        this.add(scrollPane,c);
    }

    private void initializeColumnNamesRow() {
        columnNames = new Vector<>();
        columnNames.add("id");
        columnNames.add("first_name");
        columnNames.add("last_name");
        columnNames.add("city");
        columnNames.add("phone_number");
        columnNames.add("parents_phone_number");
        columnNames.add("class");
    }

    private void initializeButtons() {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(50,10,10,10);
        g.gridx = 0;
        g.gridy = 10;
        this.add(backButton, g);

        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        GridBagConstraints h = new GridBagConstraints();
        h.insets = new Insets(50,10,10,10);
        h.gridx = 1;
        h.gridy = 10;
        this.add(selectButton, h);
    }

    @Override
    public void dispose() {
        //myFrame.setVisible(true);
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backButton){
            System.out.println("back button");
            dispose();
        }

        if (e.getSource()== selectButton){
            System.out.println("Find button");

            if (checkFields()) {
                ArrayList<Integer> list = new ArrayList<>();
                if (list.size()==1) {
                    JOptionPane.showMessageDialog(this,"No results","Info", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (list.size()==2) {
                    System.out.println("Found student at ID: " + list.get(1));
                }
                if (list.size()>2) {
                    System.out.printf("Found %d students",list.get(0));


                }

                    dispose();
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
            JOptionPane.showMessageDialog(this,"Fields first name or last name are required","Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
}
