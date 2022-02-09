import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ClassFrame extends JFrame implements ActionListener {
    private Vector<Vector<String>> dataRowClass; //in case of multi results from SQL it's vector of vectors of classes
    private Vector<String> studentsWithoutClass;
    private MyFrame myFrame;
    private int selectedClassId;
    private int selectedClassIdInDataRow;
    private Insets insets = new Insets(10,10,10,10);

    private JButton backButton;
    private JButton updateClassNameButton;
    private JButton updateSupervisingTeacherButton;
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
        //fired from main when there is only one search result
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
        //fired from choose student frame when there are multi search results
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

        String supervisingTeacherId;
        if ((supervisingTeacherId = dataRowClass.get(selectedClassIdInDataRow).get(2))!=null){
            supervisingTeacher = myFrame.getTeacherNameAndLastName(Integer.parseInt(supervisingTeacherId));
        }
        else supervisingTeacher = "";
        supervisingTeacherTextField = new JLabel(supervisingTeacher);
        supervisingTeacherTextField.setBorder(blackline);
        supervisingTeacherTextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = insets;
        f.gridx = 1;
        f.gridy = 2;
        this.add(supervisingTeacherTextField, f);

        String student1Id;
        if ((student1Id = dataRowClass.get(selectedClassIdInDataRow).get(3))!=null){
            student1 = myFrame.getStudentNameAndLastName(Integer.parseInt(student1Id));
        }
        else student1 = "";
        student1TextField = new JLabel(student1);
        student1TextField.setBorder(blackline);
        student1TextField.setBorder(blackline);
        student1TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints h = new GridBagConstraints();
        h.insets = insets;
        h.gridx = 1;
        h.gridy = 3;
        this.add(student1TextField, h);

        String student2Id;
        if ((student2Id = dataRowClass.get(selectedClassIdInDataRow).get(4))!=null){
            System.out.println("here");
            student2 = myFrame.getStudentNameAndLastName(Integer.parseInt(student2Id));
        }
        else student2 = "";
        student2TextField = new JLabel(student2);
        student2TextField.setBorder(blackline);
        student2TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints j = new GridBagConstraints();
        j.insets = insets;
        j.gridx = 1;
        j.gridy = 4;
        this.add(student2TextField, j);

        String student3Id;
        if ((student3Id = dataRowClass.get(selectedClassIdInDataRow).get(5))!=null){
            student3 = myFrame.getStudentNameAndLastName(Integer.parseInt(student3Id));
        }
        else student3 = "";
        student3TextField = new JLabel(student3);
        student3TextField.setBorder(blackline);
        student3TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints l = new GridBagConstraints();
        l.insets = insets;
        l.gridx = 1;
        l.gridy = 5;
        this.add(student3TextField, l);

        String student4Id;
        if ((student4Id = dataRowClass.get(selectedClassIdInDataRow).get(6))!=null){
            student4 = myFrame.getStudentNameAndLastName(Integer.parseInt(student4Id));
        }
        else student4 = "";
        student4TextField = new JLabel(student4);
        student4TextField.setBorder(blackline);
        student4TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints n = new GridBagConstraints();
        n.insets = insets;
        n.gridx = 1;
        n.gridy = 6;
        this.add(student4TextField, n);

        String student5Id;
        if ((student5Id = dataRowClass.get(selectedClassIdInDataRow).get(7))!=null){
            student5 = myFrame.getStudentNameAndLastName(Integer.parseInt(student5Id));
        }
        else student5 = "";
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
        updateClassNameButton = new JButton("update");
        updateClassNameButton.addActionListener(this);
        GridBagConstraints a = new GridBagConstraints();
        a.insets = insets;
        a.gridx = 2;
        a.gridy = 1;
        this.add(updateClassNameButton, a);

        updateSupervisingTeacherButton = new JButton("update");
        updateSupervisingTeacherButton.addActionListener(this);
        GridBagConstraints b = new GridBagConstraints();
        b.insets = insets;
        b.gridx = 2;
        b.gridy = 2;
        this.add(updateSupervisingTeacherButton, b);

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
        if (e.getSource()== updateClassNameButton){
            System.out.println("update class name button in class frame");
            showUpdateDialog("New class name: ",0);
        }
        if (e.getSource()== updateSupervisingTeacherButton){
            System.out.println("update supervising teacher button in class frame");
            showUpdateDialog("New supervising teacher name: ",1);
        }
        if (e.getSource()==updateCityButton){
            System.out.println("update student 1 button in class frame");
            showUpdateDialog("Student 1: ",2);
        }
        if (e.getSource()==updatePhoneNumberButton){
            System.out.println("update student 2 button in class frame");
            showUpdateDialog("Student 2: ",3);
        }
        if (e.getSource()==updateDateOfBirthButton){
            System.out.println("update student 3 button in class frame");
            showUpdateDialog("Student 3: ",4);
        }
        if (e.getSource()==updateParentsPhoneNumberButton){
            System.out.println("update student 4 button in class frame");
            showUpdateDialog("Student 4: ",5);
        }
        if (e.getSource()==updateClassButton){
            System.out.println("update student 5 button in class frame");
            showUpdateDialog("Student 5: ",6);
        }


    }

    private void showUpdateDialog(String text, int i) {
        //i stands for column name to be updated
        //0 - class name
        //1 - supervising teacher
        //2 - student 1
        //3 - student 2
        //4 - student 3
        //5 - student 4
        //6 - student 5


        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(text));

        if (i == 0 || i == 1) {
            // if there's string field to be updated
            JTextField textField = new JTextField(5);
            jPanel.add(textField);
            int result = JOptionPane.showConfirmDialog(null, jPanel, "Please enter new value", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                String newVaule = textField.getText();
                if (myFrame.updateClass(i, selectedClassId, newVaule) == 1) {
                    JOptionPane.showMessageDialog(jPanel, "Update successful!", "Update", JOptionPane.INFORMATION_MESSAGE);
                    super.dispose();
                    myFrame.findClass(selectedClassId);
                    System.out.println("class updated");
                } else System.out.println("Something went wrong when updating class");
            }
        } else {
            //if it's combobox with students names
            initializeStudentsWithoutClassVector();
            JComboBox<String> studentComboBox = new JComboBox<>(studentsWithoutClass);
            studentComboBox.setSelectedIndex(-1);
            jPanel.add(studentComboBox);
            int result = JOptionPane.showConfirmDialog(null, jPanel, "Please choose new student", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                String studentId;
                if (studentComboBox.getItemAt(studentComboBox.getSelectedIndex()) != null) {
                    studentId = studentComboBox.getItemAt(studentComboBox.getSelectedIndex()).split(" id : ")[1];
                    if (myFrame.updateClass(i, selectedClassId, studentId) == 1) {
                        JOptionPane.showMessageDialog(jPanel, "Update successful!", "Update", JOptionPane.INFORMATION_MESSAGE);
                        super.dispose();
                        myFrame.findClass(selectedClassId);
                        System.out.println("class updated");
                    } else System.out.println("Something went wrong when updating class");
                }
            }
        }
    }

    private void initializeStudentsWithoutClassVector(){
        studentsWithoutClass = new Vector<>();

        Vector<Vector<String>> studentsWithoutClassVectorOfVectors = myFrame.findStudentsWithoutClass();
        if (studentsWithoutClassVectorOfVectors.size()!=0){
            for (Vector<String> vector : studentsWithoutClassVectorOfVectors){
                int id = Integer.parseInt(vector.get(0));
                String firstName = vector.get(1);
                String lastName = vector.get(2);
                studentsWithoutClass.add(firstName + " " + lastName + " id : " + id);
            }
        }
    }

}