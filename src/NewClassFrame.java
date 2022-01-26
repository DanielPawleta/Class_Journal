import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewClassFrame extends JFrame implements ActionListener {
    private MyFrame myFrame;
    private Insets insets = new Insets(10,10,10,10);

    private JButton backButton;
    private JButton addButton;

    private String className;
    private String supervisingTeacher;
    private String student1;
    private String student2;
    private String student3;
    private String student4;

    private JTextField classNameField;
    private JTextField supervisingTeacherField;
    private JTextField student1Field;
    private JTextField student2Field;
    private JTextField student3Field;
    private JTextField student4Field;

    public NewClassFrame(MyFrame myFrame){
        this.myFrame = myFrame;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("New Class");
        this.setLayout(new GridBagLayout());
        this.initializeTitleLabel();
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
            supervisingTeacherField = new JTextField(15);
            GridBagConstraints e = new GridBagConstraints();
            e.insets = insets;
            e.gridx = 1;
            e.gridy = 2;
            this.add(supervisingTeacherField, e);

            //student 1
            JLabel student1Label = new JLabel("Student 1: ");
            GridBagConstraints f = new GridBagConstraints();
            f.insets = insets;
            f.gridx = 0;
            f.gridy = 3;
            this.add(student1Label, f);
            student1Field = new JTextField(15);
            GridBagConstraints g = new GridBagConstraints();
            g.insets = insets;
            g.gridx = 1;
            g.gridy = 3;
            this.add(student1Field, g);

            //student 2
            JLabel student2Label = new JLabel("Student 2: ");
            GridBagConstraints h = new GridBagConstraints();
            h.insets = insets;
            h.gridx = 0;
            h.gridy = 4;
            this.add(student2Label, h);
            student2Field = new JTextField(15);
            GridBagConstraints i = new GridBagConstraints();
            i.insets = insets;
            i.gridx = 1;
            i.gridy = 4;
            this.add(student2Field, i);

            //student 3
            JLabel student3Label = new JLabel("Student 3: ");
            GridBagConstraints j = new GridBagConstraints();
            j.insets = insets;
            j.gridx = 0;
            j.gridy = 6;
            this.add(student3Label, j);
            student3Field = new JTextField(15);
            GridBagConstraints k = new GridBagConstraints();
            k.insets = insets;
            k.gridx = 1;
            k.gridy = 6;
            this.add(student3Field, k);

            //student 4
            JLabel student4Label = new JLabel("Student 4:  ");
            GridBagConstraints l = new GridBagConstraints();
            l.insets = insets;
            l.gridx = 0;
            l.gridy = 7;
            this.add(student4Label, l);
            student4Field = new JTextField(15);
            GridBagConstraints m = new GridBagConstraints();
            m.insets = insets;
            m.gridx = 1;
            m.gridy = 7;
            this.add(student4Field, m);

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
                //myFrame.addClass(className, supervisingTeacher, student1,student2,student3,student4);
                JOptionPane.showMessageDialog(this,"Class added!","New class", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    private boolean checkFields(){
        className = classNameField.getText();
        supervisingTeacher = supervisingTeacherField.getText();
        student1 = student1Field.getText();
        student2 = student2Field.getText();
        student3 = student3Field.getText();
        student4 = student4Field.getText();

        if      (!className.equals(""))
        {
            return true;
        }
        else {
            JOptionPane.showMessageDialog(this,"Class name is required","Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
}
