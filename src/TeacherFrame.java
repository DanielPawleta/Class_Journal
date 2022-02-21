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

public class TeacherFrame extends JFrame implements ActionListener {
    private Vector<Vector<String>> dataRowTeacher;
    private MyFrame myFrame;
    private int selectedTeacherId;
    private int selectedTeacherIdInDataRow;
    private final Insets insets = new Insets(10,10,10,10);

    private JButton backButton;
    private JButton updateFirstNameButton;
    private JButton updateLastNameButton;
    private JButton updateCityButton;
    private JButton updatePhoneNumberButton;
    private JButton updateDateOfBirthButton;
    private JButton updateSupervisingClassButton;
    private JButton deleteButton;

    private String firstName;
    private String lastName;
    private String city;
    private String phoneNumber;
    private JDatePickerImpl datePicker;
    private String dateOfBirth;
    private String supervisingClass;

    private JLabel firstNameTextField;
    private JLabel lastNameTextField;
    private JLabel cityTextField;
    private JLabel phoneNumberTextField;
    private JLabel dateOfBirthTextField;
    private JLabel supervisingClassTextField;

    public TeacherFrame(MyFrame myFrame, Vector<Vector<String>> dataRowTeacher){
        //fired from main when there is only one search result
        this();
        this.myFrame = myFrame;
        this.dataRowTeacher = dataRowTeacher;
        this.selectedTeacherIdInDataRow =0;
        this.initializeTextFields();
    }

    public TeacherFrame(MyFrame myFrame, Vector<Vector<String>> dataRowTeacher, int selectedTeacherIdInDataRow) {
        //fired from choose teacher frame when there are multi search results
        this();
        this.myFrame = myFrame;
        this.dataRowTeacher = dataRowTeacher;
        this.selectedTeacherIdInDataRow = selectedTeacherIdInDataRow;
        this.initializeTextFields();
    }

    public TeacherFrame(){
        //default constructor
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Teacher");
        this.setLayout(new GridBagLayout());
        this.initializeTitleLabel();
        this.initializeLabels();
        this.initializeUpdateButtons();
        this.initializeButtons();

        this.setVisible(true);
        this.setSize(500,600);
        this.setResizable(false);
    }

    private void initializeTitleLabel() {
        JLabel titleLabel = new JLabel("Teacher");
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

            //supervising class
            JLabel classLabel = new JLabel("Supervising class: ");
            GridBagConstraints o = new GridBagConstraints();
            o.insets = insets;
            o.gridx = 0;
            o.gridy = 7;
            this.add(classLabel, o);
        }

    private void initializeTextFields(){
        //border for all text fields
        Border blackline = BorderFactory.createLineBorder(Color.black);

        selectedTeacherId = Integer.parseInt(dataRowTeacher.get(selectedTeacherIdInDataRow).get(0));
        firstName = dataRowTeacher.get(selectedTeacherIdInDataRow).get(1);
        firstNameTextField = new JLabel(firstName);
        firstNameTextField.setBorder(blackline);
        firstNameTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = insets;
        c.gridx = 1;
        c.gridy = 1;
        this.add(firstNameTextField, c);

        lastName = dataRowTeacher.get(selectedTeacherIdInDataRow).get(2);
        lastNameTextField = new JLabel(lastName);
        lastNameTextField.setBorder(blackline);
        lastNameTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = insets;
        f.gridx = 1;
        f.gridy = 2;
        this.add(lastNameTextField, f);

        String city = dataRowTeacher.get(selectedTeacherIdInDataRow).get(3);
        cityTextField = new JLabel(city);
        cityTextField.setBorder(blackline);
        cityTextField.setBorder(blackline);
        cityTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints h = new GridBagConstraints();
        h.insets = insets;
        h.gridx = 1;
        h.gridy = 3;
        this.add(cityTextField, h);

        String phoneNumber = dataRowTeacher.get(selectedTeacherIdInDataRow).get(4);
        phoneNumberTextField = new JLabel(phoneNumber);
        phoneNumberTextField.setBorder(blackline);
        phoneNumberTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints j = new GridBagConstraints();
        j.insets = insets;
        j.gridx = 1;
        j.gridy = 4;
        this.add(phoneNumberTextField, j);

        String dateOfBirth = dataRowTeacher.get(selectedTeacherIdInDataRow).get(5);
        dateOfBirthTextField = new JLabel(dateOfBirth);
        dateOfBirthTextField.setBorder(blackline);
        dateOfBirthTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints l = new GridBagConstraints();
        l.insets = insets;
        l.gridx = 1;
        l.gridy = 5;
        this.add(dateOfBirthTextField, l);

        String supervisingClass;
        if (dataRowTeacher.get(selectedTeacherIdInDataRow).get(6)!=null){
            //supervisingClass = myFrame.getClassNameByClassId(Integer.parseInt(supervisingClassId));
            supervisingClass = dataRowTeacher.get(selectedTeacherIdInDataRow).get(6);
        }
        else supervisingClass = "";
        supervisingClassTextField = new JLabel(supervisingClass);
        supervisingClassTextField.setBorder(blackline);
        supervisingClassTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints p = new GridBagConstraints();
        p.insets = insets;
        p.gridx = 1;
        p.gridy = 7;
        this.add(supervisingClassTextField, p);
    }

    private void initializeUpdateButtons(){
        updateFirstNameButton = new JButton("update");
        updateFirstNameButton.addActionListener(this);
        GridBagConstraints a = new GridBagConstraints();
        a.insets = insets;
        a.gridx = 2;
        a.gridy = 1;
        this.add(updateFirstNameButton, a);

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

        updateSupervisingClassButton = new JButton("update");
        updateSupervisingClassButton.addActionListener(this);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = insets;
        g.gridx = 2;
        g.gridy = 7;
        this.add(updateSupervisingClassButton, g);
    }

    private void initializeButtons() {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints r = new GridBagConstraints();
        r.insets = new Insets(50,10,10,10);
        r.gridx = 0;
        r.gridy = 8;
        this.add(backButton, r);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        GridBagConstraints s = new GridBagConstraints();
        s.insets = new Insets(50,10,10,10);
        s.gridx = 1;
        s.gridy = 8;
        this.add(deleteButton,s);
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
        if (e.getSource()== updateFirstNameButton){
            System.out.println("update first name button in teacher frame");
            showUpdateDialog("New first name: ",0);
        }
        if (e.getSource()==updateLastNameButton){
            System.out.println("update last name button in teacher frame");
            showUpdateDialog("New last name: ",1);
        }
        if (e.getSource()==updateCityButton){
            System.out.println("update city button in teacher frame");
            showUpdateDialog("New city: ",2);
        }
        if (e.getSource()==updatePhoneNumberButton){
            System.out.println("update phone number button in teacher frame");
            showUpdateDialog("New phone number: ",3);
        }
        if (e.getSource()==updateDateOfBirthButton){
            System.out.println("update date of birth button in teacher frame");
            showUpdateDialog("New date of birth: ",4);
        }
        if (e.getSource()== updateSupervisingClassButton){
            System.out.println("update supervising class button in teacher frame");
            showUpdateDialog("Choose class ",5);
        }
        if (e.getSource()==deleteButton){
            System.out.println("delete teacher button in teacher frame");
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel("Are you sure you want to delete this teacher?"));
            int result = JOptionPane.showConfirmDialog(null, jPanel, "Delete", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                myFrame.deleteRow(2,String.valueOf(selectedTeacherId));
                dispose();
            }
        }
    }

    private void showUpdateDialog(String text, int i){
        //i stands for column name to be updated
        //0 - first name
        //1 - last name
        //2 - city
        //3 - phone number
        //4 - date of birth
        //5 - supervising class



        if (i!=4 && i!=5) { //if it's not date of birth and not supervising class update
            JTextField textField = new JTextField(5);
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(text));
            jPanel.add(textField);

            int result = JOptionPane.showConfirmDialog(null, jPanel, "Please enter new value", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                String newVaule = textField.getText();
                if (myFrame.updateTeacher(i, selectedTeacherId, newVaule)==1){
                    JOptionPane.showMessageDialog(jPanel, "Update successful!", "Update", JOptionPane.INFORMATION_MESSAGE);
                    super.dispose();
                    myFrame.findTeacher(selectedTeacherId);
                    System.out.println("teacher updated");
                }
                else System.out.println("Something went wrong when updating teacher");
            }
        }

        else if (i==4) { //it is date of birth update
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(text));

            UtilDateModel model = new UtilDateModel();
            Properties properties = new Properties();
            properties.put("text.today", "Today");
            properties.put("text.month", "Month");
            properties.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
            datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            jPanel.add(datePicker);

            int result = JOptionPane.showConfirmDialog(null, jPanel, "Please enter new value", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                if (datePicker.getModel().getValue()==null) return;
                Date date = (Date) datePicker.getModel().getValue();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String newValue = simpleDateFormat.format(date);
                System.out.println("new date as string: " + newValue);
                if(myFrame.updateTeacher(i, selectedTeacherId, newValue)==1) {
                    JOptionPane.showMessageDialog(jPanel, "Update successful!", "Update", JOptionPane.INFORMATION_MESSAGE);
                    super.dispose();
                    myFrame.findTeacher(selectedTeacherId);
                    System.out.println("teacher updated");
                }
                else System.out.println("Something went wrong when updating teacher");
            }
        }

        else { //it is supervising class update
            Vector<String> classNames = new Vector<>();
            Vector<Vector<String>> classesWithoutSupervisingTeacher = myFrame.findClassWithoutSupervisingTeacher();
            if (classesWithoutSupervisingTeacher.size()!=0){
                for (Vector<String> classVector : classesWithoutSupervisingTeacher){
                    classNames.add(classVector.get(0));
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "No class without supervising teacher found", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(text));
            JComboBox<String> classNamesComboBox = new JComboBox<>(classNames);
            classNamesComboBox.setSelectedIndex(-1);
            jPanel.add(classNamesComboBox);

            int result = JOptionPane.showConfirmDialog(null, jPanel, "Please select class", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                if (classNamesComboBox.getItemAt(classNamesComboBox.getSelectedIndex())==null) return;
                String className = classNamesComboBox.getItemAt(classNamesComboBox.getSelectedIndex());
                String classID = myFrame.getClassIdByClassName(className);
                if (myFrame.updateTeacher(i, selectedTeacherId, classID)==1){
                    JOptionPane.showMessageDialog(jPanel, "Update successful!", "Update", JOptionPane.INFORMATION_MESSAGE);
                    super.dispose();
                    myFrame.updateClass(1,Integer.parseInt(classID),String.valueOf(selectedTeacherId));
                    myFrame.findTeacher(selectedTeacherId);
                    System.out.println("student updated");
                }
                else System.out.println("Something went wrong when updating student");
            }
        }
    }
}