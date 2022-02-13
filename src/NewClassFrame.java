import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class NewClassFrame extends JFrame implements ActionListener {
    private MyFrame myFrame;
    private Insets insets = new Insets(10,10,10,10);

    private JButton backButton;
    private JButton addButton;

    private String className;
    private int supervisingTeacherId;
    private int student1Id;
    private int student2Id;
    private int student3Id;
    private int student4Id;
    private int maxNumberOfStudents=10;

    private JTextField classNameField;
    private JComboBox<String> supervisingTeacherComboBox;
    private JComboBox<String> student1ComboBox;
    private JComboBox<String> student2ComboBox;
    private JComboBox<String> student3ComboBox;
    private JComboBox<String> student4ComboBox;

    private Vector<String> studentsWithoutClass;
    private Vector<String> teachersWithoutSupervisingClass;
    private ArrayList<Integer> studentsId; //list with all students (-1 for null)

    public NewClassFrame(MyFrame myFrame){
        this.myFrame = myFrame;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("New Class");
        this.setLayout(new GridBagLayout());
        this.initializeTitleLabel();
        this.initializeStudentsWithoutClassVector();
        this.initializeTeachersWithoutClassSupervisingVector();
        this.initializeLabels();
        this.initializeButtons();

        this.setVisible(true);
        this.setSize(500,600);
        this.setResizable(false);

    }

    private void initializeTitleLabel() {
        JLabel titleLabel = new JLabel("Add new class");
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        GridBagConstraints a = new GridBagConstraints();
        a.insets = new Insets(50,50,50,50);
        a.gridwidth=2;
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
            classNameField = new JTextField(15);
            GridBagConstraints c = new GridBagConstraints();
            c.insets = insets;
            c.gridx = 1;
            c.gridy = 1;
            this.add(classNameField, c);

            //supervising teacher
            JLabel supervisingTeacherLabel = new JLabel("Supervising teacher: ");
            GridBagConstraints d = new GridBagConstraints();
            d.insets = insets;
            d.gridx = 0;
            d.gridy = 2;
            this.add(supervisingTeacherLabel, d);
            supervisingTeacherComboBox = new JComboBox<>(teachersWithoutSupervisingClass);
            supervisingTeacherComboBox.setSelectedIndex(-1);
            GridBagConstraints e = new GridBagConstraints();
            e.insets = insets;
            e.gridx = 1;
            e.gridy = 2;
            this.add(supervisingTeacherComboBox, e);

            //student 1
            JLabel student1Label = new JLabel("Student 1: ");
            GridBagConstraints f = new GridBagConstraints();
            f.insets = insets;
            f.gridx = 0;
            f.gridy = 3;
            this.add(student1Label, f);
            student1ComboBox = new JComboBox<>(studentsWithoutClass);
            student1ComboBox.setSelectedIndex(-1);
            GridBagConstraints g = new GridBagConstraints();
            g.insets = insets;
            g.gridx = 1;
            g.gridy = 3;
            this.add(student1ComboBox, g);

            //student 2
            JLabel student2Label = new JLabel("Student 2: ");
            GridBagConstraints h = new GridBagConstraints();
            h.insets = insets;
            h.gridx = 0;
            h.gridy = 4;
            this.add(student2Label, h);
            student2ComboBox = new JComboBox<>(studentsWithoutClass);
            student2ComboBox.setSelectedIndex(-1);
            GridBagConstraints i = new GridBagConstraints();
            i.insets = insets;
            i.gridx = 1;
            i.gridy = 4;
            this.add(student2ComboBox, i);

            //student 3
            JLabel student3Label = new JLabel("Student 3: ");
            GridBagConstraints j = new GridBagConstraints();
            j.insets = insets;
            j.gridx = 0;
            j.gridy = 6;
            this.add(student3Label, j);
            student3ComboBox = new JComboBox<>(studentsWithoutClass);
            student3ComboBox.setSelectedIndex(-1);
            GridBagConstraints k = new GridBagConstraints();
            k.insets = insets;
            k.gridx = 1;
            k.gridy = 6;
            this.add(student3ComboBox, k);

            //student 4
            JLabel student4Label = new JLabel("Student 4:  ");
            GridBagConstraints l = new GridBagConstraints();
            l.insets = insets;
            l.gridx = 0;
            l.gridy = 7;
            this.add(student4Label, l);
            student4ComboBox = new JComboBox<>(studentsWithoutClass);
            student4ComboBox.setSelectedIndex(-1);
            GridBagConstraints m = new GridBagConstraints();
            m.insets = insets;
            m.gridx = 1;
            m.gridy = 7;
            this.add(student4ComboBox, m);
        }

    private void initializeButtons() {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints r = new GridBagConstraints();
        r.insets = new Insets(50,10,10,10);
        r.gridx = 0;
        r.gridy = 8;
        this.add(backButton, r);

        addButton = new JButton("Add class");
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
        if (e.getSource() == backButton) {
            System.out.println("back button");
            dispose();
        }

        if (e.getSource() == addButton) {
            System.out.println("Add button");

            if (checkFields()) {
                myFrame.addClass(className, supervisingTeacherId, studentsId);
                JOptionPane.showMessageDialog(this, "Class added!", "New class", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    private boolean checkFields(){
        className = classNameField.getText();

        if (supervisingTeacherComboBox.getItemAt(supervisingTeacherComboBox.getSelectedIndex())!=null) {
            supervisingTeacherId = Integer.parseInt(supervisingTeacherComboBox.getItemAt(supervisingTeacherComboBox.getSelectedIndex()).split(" id : ")[1]);
        }
        else supervisingTeacherId=-1;

        studentsId = new ArrayList<>(); //list with all students (-1 for null)

        if (student1ComboBox.getItemAt(student1ComboBox.getSelectedIndex())!=null) {
            student1Id = Integer.parseInt(student1ComboBox.getItemAt(student1ComboBox.getSelectedIndex()).split(" id : ")[1]);
            studentsId.add(student1Id);
        }
        else studentsId.add(-1);

        if (student2ComboBox.getItemAt(student2ComboBox.getSelectedIndex())!=null) {
            student2Id = Integer.parseInt(student2ComboBox.getItemAt(student2ComboBox.getSelectedIndex()).split(" id : ")[1]);
            studentsId.add(student2Id);
        }
        else studentsId.add(-1);

        if (student3ComboBox.getItemAt(student3ComboBox.getSelectedIndex())!=null) {
            student3Id = Integer.parseInt(student3ComboBox.getItemAt(student3ComboBox.getSelectedIndex()).split(" id : ")[1]);
            studentsId.add(student3Id);
        }
        else studentsId.add(-1);

        if (student4ComboBox.getItemAt(student4ComboBox.getSelectedIndex())!=null) {
            student4Id = Integer.parseInt(student4ComboBox.getItemAt(student4ComboBox.getSelectedIndex()).split(" id : ")[1]);
            studentsId.add(student4Id);
        }
        else studentsId.add(-1);

        ArrayList<Integer> studentsNotnull = new ArrayList<>();
        for (Integer studentId : studentsId){
            if (studentId!=-1) studentsNotnull.add(studentId);
        }

        if (studentsNotnull.size()!= new HashSet<>(studentsNotnull).size()){
            JOptionPane.showMessageDialog(this,"Each student can be selected only once","Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if      (!className.equals(""))
        {
            if (myFrame.checkClassName(className)) {
                return true;
            }
            else {
                JOptionPane.showMessageDialog(this,"This class name is already in usage","Info", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        else {
            JOptionPane.showMessageDialog(this,"Class name is required","Info", JOptionPane.WARNING_MESSAGE);
            return false;
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

    private void initializeTeachersWithoutClassSupervisingVector(){
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
}
