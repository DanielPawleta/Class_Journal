import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ClassFrame extends JFrame implements ActionListener {
    private Vector<Vector<String>> dataRowClass;
    private MyFrame myFrame;
    private int selectedClassId;
    private int selectedClassIdInDataRow;
    private Insets insets = new Insets(10,10,10,10);

    private JButton backButton;
    private JButton updateFirstNameButton;
    private JButton updateLastNameButton;
    private JButton updateCityButton;
    private JButton updatePhoneNumberButton;
    private JButton updateDateOfBirthButton;
    private JButton updateParentsPhoneNumberButton;
    private JButton updateClassButton;

    private String className;
    private String supervisingTeacher;
    private String student1;
    private String student2;
    private String student3;
    private String student4;
    private String student5;

    private JLabel classNameTextField;
    private JLabel supervisingTeacherTextField;
    private JLabel student1TextField;
    private JLabel student2TextField;
    private JLabel student3TextField;
    private JLabel student4TextField;
    private JLabel student5TextField;

    public ClassFrame(MyFrame myFrame, Vector<Vector<String>> dataRowClass){
        this.myFrame = myFrame;
        this.dataRowClass = dataRowClass;
        this.selectedClassIdInDataRow =0;

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Class");
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

    public ClassFrame(MyFrame myFrame, Vector<Vector<String>> dataRowClass, int selectedClassIdInDataRow) {
        this(myFrame, dataRowClass);
        this.selectedClassIdInDataRow = selectedClassIdInDataRow;
    }

    private void initializeTitleLabel() {
        JLabel titleLabel = new JLabel("Class");
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        GridBagConstraints a = new GridBagConstraints();
        a.insets = new Insets(50,50,50,50);
        a.gridwidth=3;
        a.gridx=0;
        a.gridy=0;
        this.add(titleLabel,a);
    }

    private void initializeLabels() {
            //class name
            JLabel classNameLabel = new JLabel("Class Name: ");
            GridBagConstraints b = new GridBagConstraints();
            b.insets = insets;
            b.gridx = 0;
            b.gridy = 1;
            this.add(classNameLabel, b);

            //supervising teacher
            JLabel supervisingTeacherLabel = new JLabel("Supervising Teacher: ");
            GridBagConstraints e = new GridBagConstraints();
            e.insets = insets;
            e.gridx = 0;
            e.gridy = 2;
            this.add(supervisingTeacherLabel, e);

            //student1
            JLabel student1Label = new JLabel("Student 1: ");
            GridBagConstraints g = new GridBagConstraints();
            g.insets = insets;
            g.gridx = 0;
            g.gridy = 3;
            this.add(student1Label, g);

            //student2
            JLabel student2Label = new JLabel("Student 2: ");
            GridBagConstraints i = new GridBagConstraints();
            i.insets = insets;
            i.gridx = 0;
            i.gridy = 4;
            this.add(student2Label, i);

            //student3
            JLabel student3Label = new JLabel("Student 3: ");
            GridBagConstraints k = new GridBagConstraints();
            k.insets = insets;
            k.gridx = 0;
            k.gridy = 5;
            this.add(student3Label, k);

            //student4
            JLabel student4Label = new JLabel("Student 4: ");
            GridBagConstraints m = new GridBagConstraints();
            m.insets = insets;
            m.gridx = 0;
            m.gridy = 6;
            this.add(student4Label, m);

            //student5
            JLabel student5Label = new JLabel("Student 5: ");
            GridBagConstraints o = new GridBagConstraints();
            o.insets = insets;
            o.gridx = 0;
            o.gridy = 7;
            this.add(student5Label, o);
        }

    private void initializeTextFields(){
        //border for all text fields
        Border blackline = BorderFactory.createLineBorder(Color.black);

        selectedClassId = Integer.parseInt(dataRowClass.get(selectedClassIdInDataRow).get(0));

        className = dataRowClass.get(selectedClassIdInDataRow).get(1);
        classNameTextField = new JLabel(className);
        classNameTextField.setBorder(blackline);
        classNameTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = insets;
        c.gridx = 1;
        c.gridy = 1;
        this.add(classNameTextField, c);

        supervisingTeacher = dataRowClass.get(selectedClassIdInDataRow).get(2);
        supervisingTeacherTextField = new JLabel(supervisingTeacher);
        supervisingTeacherTextField.setBorder(blackline);
        supervisingTeacherTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = insets;
        f.gridx = 1;
        f.gridy = 2;
        this.add(supervisingTeacherTextField, f);

        int student1Id = Integer.parseInt(dataRowClass.get(selectedClassIdInDataRow).get(3));
        student1 = myFrame.getStudentNameAndLastName(student1Id);
        student1TextField = new JLabel(student1);
        student1TextField.setBorder(blackline);
        student1TextField.setBorder(blackline);
        student1TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints h = new GridBagConstraints();
        h.insets = insets;
        h.gridx = 1;
        h.gridy = 3;
        this.add(student1TextField, h);

        student2 = dataRowClass.get(selectedClassIdInDataRow).get(4);
        student2TextField = new JLabel(student2);
        student2TextField.setBorder(blackline);
        student2TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints j = new GridBagConstraints();
        j.insets = insets;
        j.gridx = 1;
        j.gridy = 4;
        this.add(student2TextField, j);

        student3 = dataRowClass.get(selectedClassIdInDataRow).get(5);
        student3TextField = new JLabel(student3);
        student3TextField.setBorder(blackline);
        student3TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints l = new GridBagConstraints();
        l.insets = insets;
        l.gridx = 1;
        l.gridy = 5;
        this.add(student3TextField, l);

        student4 = dataRowClass.get(selectedClassIdInDataRow).get(6);
        student4TextField = new JLabel(student4);
        student4TextField.setBorder(blackline);
        student4TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints n = new GridBagConstraints();
        n.insets = insets;
        n.gridx = 1;
        n.gridy = 6;
        this.add(student4TextField, n);

        student5 = dataRowClass.get(selectedClassIdInDataRow).get(7);
        student5TextField = new JLabel(student5);
        student5TextField.setBorder(blackline);
        student5TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints p = new GridBagConstraints();
        p.insets = insets;
        p.gridx = 1;
        p.gridy = 7;
        this.add(student5TextField, p);
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
        if (e.getSource()== updateFirstNameButton){
            System.out.println("update first name button in student frame");
            showUpdateDialog("New first name: ",0);
        }
        if (e.getSource()==updateLastNameButton){
            System.out.println("update last name button in student frame");
            showUpdateDialog("New last name: ",1);
        }
        if (e.getSource()==updateCityButton){
            System.out.println("update city button in student frame");
            showUpdateDialog("New city: ",2);
        }
        if (e.getSource()==updatePhoneNumberButton){
            System.out.println("update phone number button in student frame");
            showUpdateDialog("New phone number: ",3);
        }
        if (e.getSource()==updateDateOfBirthButton){
            System.out.println("update date of birth button in student frame");
            showUpdateDialog("New date of birth: ",4);
        }
        if (e.getSource()==updateParentsPhoneNumberButton){
            System.out.println("update parents phone number button in student frame");
            showUpdateDialog("New parents phone number: ",5);
        }
        if (e.getSource()==updateClassButton){
            System.out.println("update class button in student frame");
            showUpdateDialog("Choose class ",6);
        }


    }

    private void showUpdateDialog(String text, int i) {
        //i stands for column name to be updated
        //0 - first name
        //1 - last name
        //2 - city
        //3 - phone number
        //4 - date of birth
        //5 - parents phone number
        //6 - class

        JTextField textField = new JTextField(5);
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(text));
        jPanel.add(textField);


        int result = JOptionPane.showConfirmDialog(null, jPanel, "Please enter new value", JOptionPane.OK_CANCEL_OPTION);


        if (result == JOptionPane.CANCEL_OPTION) return;
        else {
            String newVaule = textField.getText();
            if (myFrame.updateStudent(i, selectedClassId, newVaule) == 1) {
                JOptionPane.showMessageDialog(jPanel, "Update successful!", "Update", JOptionPane.INFORMATION_MESSAGE);
                super.dispose();
                myFrame.findStudent(selectedClassId);
                System.out.println("student updated");
            } else System.out.println("Something went wrong when updating student");
        }
    }

}
