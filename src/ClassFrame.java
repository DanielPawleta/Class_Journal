import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ClassFrame extends JFrame implements ActionListener {
    private Vector<Vector<String>> dataRowClass; //in case of multi results from SQL it's vector of vectors of classes
    private Vector<String> studentsWithoutClass;
    private Vector<String> teachersWithoutSupervisingClass;
    private MyFrame myFrame;
    private int selectedClassId;
    private int selectedClassIdInDataRow;
    private Insets insets = new Insets(10,10,10,10);

    private JButton backButton;
    private JButton updateClassNameButton;
    private JButton updateSupervisingTeacherButton;
    private JButton updateStudent1Button;
    private JButton updateStudent2Button;
    private JButton updateStudent3Button;
    private JButton updateStudent4Button;
    private JButton updateStudent5Button;
    private JButton updateStudent6Button;
    private JButton updateStudent7Button;
    private JButton updateStudent8Button;
    private JButton updateStudent9Button;
    private JButton updateStudent10Button;
    private JButton deleteButton;

    private String className;
    private String supervisingTeacher;
    private String student1;
    private String student2;
    private String student3;
    private String student4;
    private String student5;
    private String student6;
    private String student7;
    private String student8;
    private String student9;
    private String student10;

    private JLabel classNameTextField;
    private JLabel supervisingTeacherTextField;
    private JLabel student1TextField;
    private JLabel student2TextField;
    private JLabel student3TextField;
    private JLabel student4TextField;
    private JLabel student5TextField;
    private JLabel student6TextField;
    private JLabel student7TextField;
    private JLabel student8TextField;
    private JLabel student9TextField;
    private JLabel student10TextField;

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
        this.setSize(500,750);
        this.setResizable(true);
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
        a.insets = new Insets(20,50,20,50);
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

            JLabel student6Label = new JLabel("Student 6: ");
            GridBagConstraints r = new GridBagConstraints();
            r.insets = insets;
            r.gridx = 0;
            r.gridy = 8;
            this.add(student6Label, r);

            JLabel student7Label = new JLabel("Student 7: ");
            GridBagConstraints s = new GridBagConstraints();
            s.insets = insets;
            s.gridx = 0;
            s.gridy = 9;
            this.add(student7Label, s);

            JLabel student8Label = new JLabel("Student 8: ");
            GridBagConstraints t = new GridBagConstraints();
            t.insets = insets;
            t.gridx = 0;
            t.gridy = 10;
            this.add(student8Label, t);

            JLabel student9Label = new JLabel("Student 9: ");
            GridBagConstraints u = new GridBagConstraints();
            u.insets = insets;
            u.gridx = 0;
            u.gridy = 11;
            this.add(student9Label, u);

            JLabel student10Label = new JLabel("Student 10: ");
            GridBagConstraints w = new GridBagConstraints();
            w.insets = insets;
            w.gridx = 0;
            w.gridy = 12;
            this.add(student10Label, w);
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

        String student6Id;
        if ((student6Id = dataRowClass.get(selectedClassIdInDataRow).get(8))!=null){
            student6 = myFrame.getStudentNameAndLastName(Integer.parseInt(student6Id));
        }
        else student6 = "";
        student6TextField = new JLabel(student6);
        student6TextField.setBorder(blackline);
        student6TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints y = new GridBagConstraints();
        y.insets = insets;
        y.gridx = 1;
        y.gridy = 8;
        this.add(student6TextField, y);

        String student7Id;
        if ((student7Id = dataRowClass.get(selectedClassIdInDataRow).get(9))!=null){
            student7 = myFrame.getStudentNameAndLastName(Integer.parseInt(student7Id));
        }
        else student7 = "";
        student7TextField = new JLabel(student7);
        student7TextField.setBorder(blackline);
        student7TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints z = new GridBagConstraints();
        z.insets = insets;
        z.gridx = 1;
        z.gridy = 9;
        this.add(student7TextField, z);

        String student8Id;
        if ((student8Id = dataRowClass.get(selectedClassIdInDataRow).get(10))!=null){
            student8 = myFrame.getStudentNameAndLastName(Integer.parseInt(student8Id));
        }
        else student8 = "";
        student8TextField = new JLabel(student8);
        student8TextField.setBorder(blackline);
        student8TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints a1 = new GridBagConstraints();
        a1.insets = insets;
        a1.gridx = 1;
        a1.gridy = 10;
        this.add(student8TextField, a1);

        String student9Id;
        if ((student9Id = dataRowClass.get(selectedClassIdInDataRow).get(11))!=null){
            student9 = myFrame.getStudentNameAndLastName(Integer.parseInt(student9Id));
        }
        else student9 = "";
        student9TextField = new JLabel(student9);
        student9TextField.setBorder(blackline);
        student9TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints a2 = new GridBagConstraints();
        a2.insets = insets;
        a2.gridx = 1;
        a2.gridy = 11;
        this.add(student9TextField, a2);

        String student10Id;
        if ((student10Id = dataRowClass.get(selectedClassIdInDataRow).get(12))!=null){
            student10 = myFrame.getStudentNameAndLastName(Integer.parseInt(student10Id));
        }
        else student10 = "";
        student10TextField = new JLabel(student10);
        student10TextField.setBorder(blackline);
        student10TextField.setPreferredSize(new Dimension(150,20));
        GridBagConstraints a3 = new GridBagConstraints();
        a3.insets = insets;
        a3.gridx = 1;
        a3.gridy = 12;
        this.add(student10TextField, a3);
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

        updateStudent1Button = new JButton("update");
        updateStudent1Button.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = insets;
        c.gridx = 2;
        c.gridy = 3;
        this.add(updateStudent1Button, c);

        updateStudent2Button = new JButton("update");
        updateStudent2Button.addActionListener(this);
        GridBagConstraints d = new GridBagConstraints();
        d.insets = insets;
        d.gridx = 2;
        d.gridy = 4;
        this.add(updateStudent2Button, d);

        updateStudent3Button = new JButton("update");
        updateStudent3Button.addActionListener(this);
        GridBagConstraints e = new GridBagConstraints();
        e.insets = insets;
        e.gridx = 2;
        e.gridy = 5;
        this.add(updateStudent3Button, e);

        updateStudent4Button = new JButton("update");
        updateStudent4Button.addActionListener(this);
        GridBagConstraints f = new GridBagConstraints();
        f.insets = insets;
        f.gridx = 2;
        f.gridy = 6;
        this.add(updateStudent4Button, f);

        updateStudent5Button = new JButton("update");
        updateStudent5Button.addActionListener(this);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = insets;
        g.gridx = 2;
        g.gridy = 7;
        this.add(updateStudent5Button, g);

        updateStudent6Button = new JButton("update");
        updateStudent6Button.addActionListener(this);
        GridBagConstraints h = new GridBagConstraints();
        h.insets = insets;
        h.gridx = 2;
        h.gridy = 8;
        this.add(updateStudent6Button, h);

        updateStudent7Button = new JButton("update");
        updateStudent7Button.addActionListener(this);
        GridBagConstraints i = new GridBagConstraints();
        i.insets = insets;
        i.gridx = 2;
        i.gridy = 9;
        this.add(updateStudent7Button, i);

        updateStudent8Button = new JButton("update");
        updateStudent8Button.addActionListener(this);
        GridBagConstraints j = new GridBagConstraints();
        j.insets = insets;
        j.gridx = 2;
        j.gridy = 10;
        this.add(updateStudent8Button, j);

        updateStudent9Button = new JButton("update");
        updateStudent9Button.addActionListener(this);
        GridBagConstraints k = new GridBagConstraints();
        k.insets = insets;
        k.gridx = 2;
        k.gridy = 11;
        this.add(updateStudent9Button, k);

        updateStudent10Button = new JButton("update");
        updateStudent10Button.addActionListener(this);
        GridBagConstraints l = new GridBagConstraints();
        l.insets = insets;
        l.gridx = 2;
        l.gridy = 12;
        this.add(updateStudent10Button, l);

    }

    private void initializeButtons() {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints r = new GridBagConstraints();
        r.insets = new Insets(20,10,10,10);
        r.gridx = 0;
        r.gridy = 13;
        this.add(backButton, r);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        GridBagConstraints s = new GridBagConstraints();
        s.insets = new Insets(20,10,10,10);
        s.gridx = 1;
        s.gridy = 13;
        this.add(deleteButton, s);
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
        if (e.getSource()==updateClassNameButton){
            System.out.println("update class name button in class frame");
            showUpdateDialog("New class name: ",0);
        }
        if (e.getSource()==updateSupervisingTeacherButton){
            System.out.println("update supervising teacher button in class frame");
            showUpdateDialog("New supervising teacher name: ",1);
        }
        if (e.getSource()== updateStudent1Button){
            System.out.println("update student 1 button in class frame");
            showUpdateDialog("Student 1: ",2);
        }
        if (e.getSource()== updateStudent2Button){
            System.out.println("update student 2 button in class frame");
            showUpdateDialog("Student 2: ",3);
        }
        if (e.getSource()== updateStudent3Button){
            System.out.println("update student 3 button in class frame");
            showUpdateDialog("Student 3: ",4);
        }
        if (e.getSource()== updateStudent4Button){
            System.out.println("update student 4 button in class frame");
            showUpdateDialog("Student 4: ",5);
        }
        if (e.getSource()== updateStudent5Button){
            System.out.println("update student 5 button in class frame");
            showUpdateDialog("Student 5: ",6);
        }
        if (e.getSource()== updateStudent6Button){
            System.out.println("update student 6 button in class frame");
            showUpdateDialog("Student 6: ",7);
        }
        if (e.getSource()== updateStudent7Button){
            System.out.println("update student 7 button in class frame");
            showUpdateDialog("Student 7: ",8);
        }
        if (e.getSource()== updateStudent8Button){
            System.out.println("update student 8 button in class frame");
            showUpdateDialog("Student 8: ",9);
        }
        if (e.getSource()== updateStudent9Button){
            System.out.println("update student 9 button in class frame");
            showUpdateDialog("Student 9: ",10);
        }
        if (e.getSource()== updateStudent10Button){
            System.out.println("update student 10 button in class frame");
            showUpdateDialog("Student 10: ",11);
        }
        if (e.getSource()==deleteButton){
            System.out.println("delete class button in class frame");
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel("Are you sure you want to delete this class?"));
            int result = JOptionPane.showConfirmDialog(null, jPanel, "Delete", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                myFrame.deleteRow(1,String.valueOf(selectedClassId));
                dispose();
            }
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
        //7 - student 6
        //8 - student 7
        //9 - student 8
        //10 - student 9
        //11 - student 10


        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(text));

        if (i == 0) {
            // if there's string field to be updated
            JTextField textField = new JTextField(5);
            jPanel.add(textField);
            int result = JOptionPane.showConfirmDialog(null, jPanel, "Please enter new value", JOptionPane.OK_CANCEL_OPTION);


            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                String newVaule = textField.getText();
                if (!myFrame.checkClassName(newVaule)){
                    JOptionPane.showMessageDialog(jPanel, "This class name is already in usage", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return; //if this class name is already in DB
                }
                    if (myFrame.updateClass(i, selectedClassId, newVaule) == 1) {
                    JOptionPane.showMessageDialog(jPanel, "Update successful!", "Update", JOptionPane.INFORMATION_MESSAGE);
                    super.dispose();
                    myFrame.findClass(selectedClassId);
                    System.out.println("class updated");
                } else System.out.println("Something went wrong when updating class");
            }
        } else if (i==1) {
            // if it's combobox with teachers names
            initializeTeachersWithoutSupervisingClass();
            JComboBox<String> teacherComboBox = new JComboBox<>(teachersWithoutSupervisingClass);
            teacherComboBox.setSelectedIndex(-1);
            jPanel.add(teacherComboBox);
            int result = JOptionPane.showConfirmDialog(null, jPanel, "Please choose new supervising teacher", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.CANCEL_OPTION) return;
            else {
                String teacherId;
                if (teacherComboBox.getItemAt(teacherComboBox.getSelectedIndex()) != null) {
                    teacherId = teacherComboBox.getItemAt(teacherComboBox.getSelectedIndex()).split(" id : ")[1];
                    if (myFrame.updateClass(i, selectedClassId, teacherId) == 1) {
                        JOptionPane.showMessageDialog(jPanel, "Update successful!", "Update", JOptionPane.INFORMATION_MESSAGE);
                        super.dispose();
                        myFrame.updateTeacher(5,Integer.parseInt(teacherId),String.valueOf(selectedClassId));
                        myFrame.findClass(selectedClassId);
                        System.out.println("class updated");
                    } else System.out.println("Something went wrong when updating class");
                }
            }

        }else {
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
                        myFrame.updateStudent(6,Integer.parseInt(studentId),String.valueOf(selectedClassId));
                        myFrame.findClass(selectedClassId);
                        System.out.println("class updated");
                    } else System.out.println("Something went wrong when updating class");
                }
            }
        }
    }

    private void initializeTeachersWithoutSupervisingClass() {
        teachersWithoutSupervisingClass = new Vector<>();
        Vector<Vector<String>> teachersWithoutSupervisingClassVectorOfVectors = myFrame.findTeachersWithoutSupervisingClass();
        if (teachersWithoutSupervisingClassVectorOfVectors.size()!=0){
            for (Vector<String> vector : teachersWithoutSupervisingClassVectorOfVectors){
                int id = Integer.parseInt(vector.get(0));
                String firstName = vector.get(1);
                String lastName = vector.get(2);
                teachersWithoutSupervisingClass.add(firstName + " " + lastName + " id : " + id);
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