import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class MyFrame extends JFrame implements ActionListener {
    private JLabel loggedLabel;
    private JPanel centerPanel;
    private final Main main;
    private JTabbedPane tabbedPane;
    private JPanel statisticsTab;
    private JLabel studentsNumberLabel;
    private JLabel teachersNumberLabel;
    private JLabel classesNumberLabel;
    private JLabel studentsNotAssignedToClassNumberLabel;
    private JLabel teachersWithoutSupervisingClassNumberLabel;
    private JLabel freeStudentSlotsInAllClassesNumberLabel;

    private JButton newStudentButton;
    private JButton findStudentButton;
    private JButton showAllStudentButton;
    private JButton newTeacherButton;
    private JButton findTeacherButton;
    private JButton showAllTeacherButton;
    private JButton newClassButton;
    private JButton findClassButton;
    private JButton showAllClassButton;
    private JButton showStudentsNotAssignedToClassButton;
    private JButton showTeachersWithoutSupervisingClassButton;
    private JButton showClassesWithFreeStudentSlotsButton;
    private JButton logOutButton;

    private final Insets insets = new Insets(10,10,10,10);
    private Border blackline = BorderFactory.createLineBorder(Color.black);


    private final String loginText = "Please log in";

    //Constructor
    public MyFrame(Main main) {
        this.main = main;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("School Database v1.0");
        int GAP_IN_BORDER_LAYOUT_SIZE = 10;
        this.setLayout(new BorderLayout(GAP_IN_BORDER_LAYOUT_SIZE, GAP_IN_BORDER_LAYOUT_SIZE));
        this.getContentPane().setBackground(Color.BLACK);
        initializePanels();
        initializeFrontLabel();
        initializeNumbersFromDB();

        this.ableAllTabs();
        this.setVisible(true);
        this.setSize(550,500);
        this.setResizable(false);
    }

    //Methods for GUI
    private void initializePanels(){
        JPanel upPanel = new JPanel();
        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1,2));
        loggedLabel = new JLabel(loginText);
        downPanel.add(loggedLabel);
        logOutButton = new JButton("Logout");
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(this);
        logOutButton.setVisible(false);
        downPanel.add(logOutButton);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        centerPanel = new JPanel();

        upPanel.setBackground(new Color(0x94D914));
        downPanel.setBackground(new Color(0x91D914));
        leftPanel.setBackground(new Color(0x2E4F07));
        rightPanel.setBackground(new Color(0x2E4F07));
        centerPanel.setBackground(new Color(0xA8D0E1));

        int PANEL_SIZE = 30;
        upPanel.setPreferredSize(new Dimension(0, PANEL_SIZE));
        downPanel.setPreferredSize(new Dimension(0, PANEL_SIZE));
        leftPanel.setPreferredSize(new Dimension(PANEL_SIZE,0));
        rightPanel.setPreferredSize(new Dimension(PANEL_SIZE,0));

        this.add(upPanel, BorderLayout.NORTH);
        this.add(downPanel, BorderLayout.SOUTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(centerPanel,BorderLayout.CENTER);
    }

    private void initializeFrontLabel(){
        centerPanel.setLayout(new GridLayout(2,1));

        JPanel welcomePanel = new JPanel();

        JLabel frontLabelFirstPart = new JLabel("School   ");
        frontLabelFirstPart.setFont(new Font("MV Boli", Font.PLAIN, 50));
        welcomePanel.add(frontLabelFirstPart);

        JLabel frontLabelSecondPart = new JLabel("   Database ");
        frontLabelSecondPart.setFont(new Font("MV Boli", Font.PLAIN, 50));
        welcomePanel.add(frontLabelSecondPart);

        centerPanel.add(welcomePanel);

        tabbedPane = new JTabbedPane();

        LoginPanel loginPanel = new LoginPanel(this);
        tabbedPane.addTab("Login", loginPanel);

        JPanel studentsTab = new JPanel();
        studentsTab.setLayout(new BoxLayout(studentsTab, BoxLayout.Y_AXIS));

        studentsTab.add(Box.createVerticalGlue());
        newStudentButton = new JButton("New Student");
        newStudentButton.addActionListener(this);
        newStudentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newStudentButton.setFocusable(false);
        studentsTab.add(newStudentButton);
        studentsTab.add(Box.createVerticalGlue());

        findStudentButton = new JButton("Find Student");
        findStudentButton.addActionListener(this);
        findStudentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentsTab.add(findStudentButton);
        studentsTab.add(Box.createVerticalGlue());

        showAllStudentButton = new JButton("Show all students");
        showAllStudentButton.addActionListener(this);
        showAllStudentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentsTab.add(showAllStudentButton);
        studentsTab.add(Box.createVerticalGlue());

        tabbedPane.addTab("Students", studentsTab);
        tabbedPane.setEnabledAt(1,false);

        JPanel teachersTab = new JPanel();
        teachersTab.setLayout(new BoxLayout(teachersTab, BoxLayout.Y_AXIS));
        teachersTab.add(Box.createVerticalGlue());
        newTeacherButton = new JButton("New Teacher");
        newTeacherButton.addActionListener(this);
        newTeacherButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newTeacherButton.setFocusable(false);
        teachersTab.add(newTeacherButton);
        teachersTab.add(Box.createVerticalGlue());

        findTeacherButton = new JButton("Find Teacher");
        findTeacherButton.addActionListener(this);
        findTeacherButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        teachersTab.add(findTeacherButton);
        teachersTab.add(Box.createVerticalGlue());
        tabbedPane.addTab("Teachers", teachersTab);
        tabbedPane.setEnabledAt(2,false);

        showAllTeacherButton = new JButton("Show all teachers");
        showAllTeacherButton.addActionListener(this);
        showAllTeacherButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        teachersTab.add(showAllTeacherButton);
        teachersTab.add(Box.createVerticalGlue());

        JPanel classesTab = new JPanel();
        classesTab.setLayout(new BoxLayout(classesTab, BoxLayout.Y_AXIS));
        classesTab.add(Box.createVerticalGlue());
        newClassButton = new JButton("New Class");
        newClassButton.addActionListener(this);
        newClassButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newClassButton.setFocusable(false);
        classesTab.add(newClassButton);
        classesTab.add(Box.createVerticalGlue());

        findClassButton = new JButton("Find Class");
        findClassButton.addActionListener(this);
        findClassButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        classesTab.add(findClassButton);
        classesTab.add(Box.createVerticalGlue());
        tabbedPane.addTab("Classes", classesTab);
        tabbedPane.setEnabledAt(3,false);

        showAllClassButton = new JButton("Show all classes");
        showAllClassButton.addActionListener(this);
        showAllClassButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        classesTab.add(showAllClassButton);
        classesTab.add(Box.createVerticalGlue());

        statisticsTab = new JPanel();
        statisticsTab.setLayout(new GridBagLayout());

        JLabel studentsLabel = new JLabel("Students: ");
        GridBagConstraints a = new GridBagConstraints();
        a.insets = insets;
        a.gridx = 0;
        a.gridy = 0;
        statisticsTab.add(studentsLabel, a);

        JLabel teachersLabel = new JLabel("Teachers: ");
        GridBagConstraints b = new GridBagConstraints();
        b.insets = insets;
        b.gridx = 0;
        b.gridy = 1;
        statisticsTab.add(teachersLabel, b);

        JLabel classesLabel = new JLabel("Classes: ");
        GridBagConstraints c = new GridBagConstraints();
        c.insets = insets;
        c.gridx = 0;
        c.gridy = 2;
        statisticsTab.add(classesLabel, c);

        JLabel studentsNotAssignedToClassLabel = new JLabel("Students not assigned to class: ");
        studentsNotAssignedToClassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = insets;
        g.gridx = 2;
        g.gridy = 0;
        statisticsTab.add(studentsNotAssignedToClassLabel, g);

        JLabel teachersWithoutSupervisingClassLabel = new JLabel("Teachers without supervising class: ");
        teachersWithoutSupervisingClassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints h = new GridBagConstraints();
        h.insets = insets;
        h.gridx = 2;
        h.gridy = 1;
        statisticsTab.add(teachersWithoutSupervisingClassLabel, h);

        JLabel freeSlotsInClassesLabel = new JLabel("Number of free slots in classes: ");
        freeSlotsInClassesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints i = new GridBagConstraints();
        i.insets = insets;
        i.gridx = 2;
        i.gridy = 2;
        statisticsTab.add(freeSlotsInClassesLabel, i);

        showStudentsNotAssignedToClassButton = new JButton("Show");
        showStudentsNotAssignedToClassButton.addActionListener(this);
        GridBagConstraints m = new GridBagConstraints();
        m.insets = insets;
        m.gridx = 4;
        m.gridy = 0;
        statisticsTab.add(showStudentsNotAssignedToClassButton, m);

        showTeachersWithoutSupervisingClassButton = new JButton("Show");
        showTeachersWithoutSupervisingClassButton.addActionListener(this);
        GridBagConstraints n = new GridBagConstraints();
        n.insets = insets;
        n.gridx = 4;
        n.gridy = 1;
        statisticsTab.add(showTeachersWithoutSupervisingClassButton, n);

        showClassesWithFreeStudentSlotsButton = new JButton("Show");
        showClassesWithFreeStudentSlotsButton.addActionListener(this);
        GridBagConstraints o = new GridBagConstraints();
        o.insets = insets;
        o.gridx = 4;
        o.gridy = 2;
        statisticsTab.add(showClassesWithFreeStudentSlotsButton, o);

        tabbedPane.addTab("Statistics",statisticsTab);
        tabbedPane.setEnabledAt(4,false);

        centerPanel.add(tabbedPane);
    }

    private void initializeNumbersFromDB(){
        int studentsNumber = main.findAllStudent().size();
        studentsNumberLabel = new JLabel(String.valueOf(studentsNumber));
        studentsNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        studentsNumberLabel.setBorder(blackline);
        studentsNumberLabel.setPreferredSize(new Dimension(30,20));
        GridBagConstraints d = new GridBagConstraints();
        d.insets = insets;
        d.gridx = 1;
        d.gridy = 0;
        statisticsTab.add(studentsNumberLabel, d);

        int teachersNumber = main.findAllTeacher().size();
        teachersNumberLabel = new JLabel(String.valueOf(teachersNumber));
        teachersNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        teachersNumberLabel.setBorder(blackline);
        teachersNumberLabel.setPreferredSize(new Dimension(30,20));
        GridBagConstraints e = new GridBagConstraints();
        e.insets = insets;
        e.gridx = 1;
        e.gridy = 1;
        statisticsTab.add(teachersNumberLabel, e);

        int classesNumber = main.findAllClass().size();
        classesNumberLabel = new JLabel(String.valueOf(classesNumber));
        classesNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        classesNumberLabel.setBorder(blackline);
        classesNumberLabel.setPreferredSize(new Dimension(30,20));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = insets;
        f.gridx = 1;
        f.gridy = 2;
        statisticsTab.add(classesNumberLabel, f);

        int studentsNotAssignedToClassNumber = main.findStudentsWithoutClass().size();
        studentsNotAssignedToClassNumberLabel = new JLabel(String.valueOf(studentsNotAssignedToClassNumber));
        studentsNotAssignedToClassNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        studentsNotAssignedToClassNumberLabel.setBorder(blackline);
        studentsNotAssignedToClassNumberLabel.setPreferredSize(new Dimension(30,20));
        GridBagConstraints j = new GridBagConstraints();
        j.insets = insets;
        j.gridx = 3;
        j.gridy = 0;
        statisticsTab.add(studentsNotAssignedToClassNumberLabel, j);

        int teachersWithoutSupervisingClassNumber = main.findTeachersWithoutSupervisingClass().size();
        teachersWithoutSupervisingClassNumberLabel = new JLabel(String.valueOf(teachersWithoutSupervisingClassNumber));
        teachersWithoutSupervisingClassNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        teachersWithoutSupervisingClassNumberLabel.setBorder(blackline);
        teachersWithoutSupervisingClassNumberLabel.setPreferredSize(new Dimension(30,20));
        GridBagConstraints k = new GridBagConstraints();
        k.insets = insets;
        k.gridx = 3;
        k.gridy = 1;
        statisticsTab.add(teachersWithoutSupervisingClassNumberLabel, k);

        int freeStudentSlotsInAllClassesNumber = main.countFreeStudentSlotsInAllClasses();
        freeStudentSlotsInAllClassesNumberLabel = new JLabel(String.valueOf(freeStudentSlotsInAllClassesNumber));
        freeStudentSlotsInAllClassesNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        freeStudentSlotsInAllClassesNumberLabel.setBorder(blackline);
        freeStudentSlotsInAllClassesNumberLabel.setPreferredSize(new Dimension(30,20));
        GridBagConstraints l = new GridBagConstraints();
        l.insets = insets;
        l.gridx = 3;
        l.gridy = 2;
        statisticsTab.add(freeStudentSlotsInAllClassesNumberLabel, l);
    }

    private void disableAllTabs(){
        tabbedPane.setEnabledAt(1,false);
        tabbedPane.setEnabledAt(2,false);
        tabbedPane.setEnabledAt(3,false);
        tabbedPane.setEnabledAt(4,false);
    }

    private void ableAllTabs(){
        tabbedPane.setEnabledAt(1,true);
        tabbedPane.setEnabledAt(2,true);
        tabbedPane.setEnabledAt(3,true);
        tabbedPane.setEnabledAt(4,true);
    }

    protected void setLoggedAs(int loggedNumber){
        // 0 - logged out
        // 1 - teacher
        // 2 - principal
        // 3 - admin
        if (loggedNumber == 0){
            loggedLabel.setText(loginText);
            logOutButton.setVisible(false);
            disableAllTabs();
            tabbedPane.setEnabledAt(0,true); //login tab
            tabbedPane.setSelectedIndex(0);
        }
        else if (loggedNumber == 1){
            loggedLabel.setText("You are logged as Teacher");
            logOutButton.setVisible(true);
            tabbedPane.setEnabledAt(0,false); //login tab
            tabbedPane.setEnabledAt(1,true); //student tab
            tabbedPane.setSelectedIndex(1);
        }
        else if (loggedNumber == 2){
            loggedLabel.setText("You are logged as Principal");
            logOutButton.setVisible(true);
            tabbedPane.setEnabledAt(0,false); //login tab
            tabbedPane.setEnabledAt(1,true); //student tab
            tabbedPane.setEnabledAt(2,true); //teacher tab
            tabbedPane.setEnabledAt(3,true); //class tab
            tabbedPane.setSelectedIndex(1);
        }
        else if (loggedNumber == 3){
            loggedLabel.setText("You are logged as Admin");
            logOutButton.setVisible(true);
            tabbedPane.setEnabledAt(0,false); //login tab
            tabbedPane.setEnabledAt(1,true); //student tab
            tabbedPane.setEnabledAt(2,true); //teacher tab
            tabbedPane.setEnabledAt(3,true); //class tab
            tabbedPane.setEnabledAt(4,true); //statistics tab
            tabbedPane.setSelectedIndex(1);
        }

    }

    @Override
    public void setVisible(boolean b) {
        statisticsTab.remove(studentsNumberLabel);
        statisticsTab.remove(teachersNumberLabel);
        statisticsTab.remove(classesNumberLabel);
        statisticsTab.remove(studentsNotAssignedToClassNumberLabel);
        statisticsTab.remove(teachersWithoutSupervisingClassNumberLabel);
        statisticsTab.remove(freeStudentSlotsInAllClassesNumberLabel);
        initializeNumbersFromDB();
        super.setVisible(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==newStudentButton){
            System.out.println("new student");
            NewStudentFrame newStudentFrame = new NewStudentFrame(this);
            this.setVisible(false);
        }
        if (e.getSource()==findStudentButton){
            System.out.println("find student");
            FindStudentFrame findStudentFrame = new FindStudentFrame(this);
            this.setVisible(false);
        }
        if (e.getSource()==showAllStudentButton){
            Vector<Vector<String>> allStudents = this.findAllStudent();
            if (allStudents.size()==0) JOptionPane.showMessageDialog(this,"No students found");
            else {
                ChooseStudentFrame chooseStudentFrame = new ChooseStudentFrame(this,allStudents);
                this.setVisible(false);
            }
        }
        if (e.getSource()==newTeacherButton){
            System.out.println("new teacher");
            NewTeacherFrame newTeacherFrame = new NewTeacherFrame(this);
            this.setVisible(false);
        }
        if (e.getSource()==findTeacherButton){
            System.out.println("find teacher");
            FindTeacherFrame findTeacherFrame = new FindTeacherFrame(this);
            this.setVisible(false);
        }
        if (e.getSource()==showAllTeacherButton){
            Vector<Vector<String>> allTeachers = this.findAllTeacher();
            if (allTeachers.size()==0) JOptionPane.showMessageDialog(this,"No teachers found");
            else {
                ChooseTeacherFrame chooseTeacherFrame = new ChooseTeacherFrame(this,allTeachers);
                this.setVisible(false);
            }
        }
        if (e.getSource()==newClassButton){
            System.out.println("new class");
            NewClassFrame newClassFrame = new NewClassFrame(this);
            this.setVisible(false);
        }
        if (e.getSource()==findClassButton){
            System.out.println("find class");
            FindClassFrame findClassFrame = new FindClassFrame(this);
            this.setVisible(false);
        }
        if (e.getSource()==showAllClassButton){
            Vector<Vector<String>> allClasses = this.findAllClass();
            if (allClasses.size()==0) JOptionPane.showMessageDialog(this,"No class found");
            else {
                ChooseClassFrame chooseClassFrame = new ChooseClassFrame(this,allClasses);
                this.setVisible(false);
            }
        }
        if (e.getSource()==showStudentsNotAssignedToClassButton){
            Vector<Vector<String>> studentsWithoutClass = this.findStudentsWithoutClass();
            if (studentsWithoutClass.size()==0) JOptionPane.showMessageDialog(this,"No students to show");
            else {
                ChooseStudentFrame chooseStudentFrame = new ChooseStudentFrame(this,studentsWithoutClass);
                this.setVisible(false);
            }
        }
        if (e.getSource()== showTeachersWithoutSupervisingClassButton){
            Vector<Vector<String>> teachersWithoutSupervisingClass = this.findTeachersWithoutSupervisingClass();
            if (teachersWithoutSupervisingClass.size()==0) JOptionPane.showMessageDialog(this,"No teachers to show");
            else {
                ChooseTeacherFrame chooseTeacherFrame = new ChooseTeacherFrame(this,teachersWithoutSupervisingClass);
                this.setVisible(false);
            }
        }
        if (e.getSource()== showClassesWithFreeStudentSlotsButton){
            Vector<Vector<String>> classWithEmptyStudentPlaces = this.findClassWithEmptyStudentPlaces();
            if (classWithEmptyStudentPlaces.size()==0) JOptionPane.showMessageDialog(this,"No classes to show");
            else {
                ChooseClassFrame chooseClassFrame = new ChooseClassFrame(this,classWithEmptyStudentPlaces);
                this.setVisible(false);
            }
        }
        if (e.getSource()== logOutButton){
            setLoggedAs(0);
            System.out.println("logout");

        }
    }


    //Methods for working with DB table 'students'
    protected String addStudent(String first_name, String last_name, String city, int phone_number, String date_of_birth, int parents_phone_number, String class_attending){
        return main.addStudent(first_name, last_name, city, phone_number, date_of_birth, parents_phone_number, class_attending);
    }

    protected int findStudent(String firstName, String lastName) {
        return main.findStudent(firstName,lastName);
    }

    protected int findStudent(int selectedStudentId) {
        return main.findStudent(selectedStudentId);
    }

    protected int updateStudent(int i, int selectedStudentId, String newValue) {
        return main.updateStudent(i,selectedStudentId,newValue);
    }

    protected Vector<Vector<String>> findStudentsWithoutClass () {
        return main.findStudentsWithoutClass();
    }

    public String getStudentNameAndLastName(int studentId) {
        return main.getStudentNameAndLastName(studentId);
    }

    protected Vector<Vector<String>> findAllStudent() {
        return main.findAllStudent();
    }


    //Methods for working with DB table 'teachers'
    protected String addTeacher(String first_name, String last_name, String city, int phone_number, String date_of_birth, String supervising_class){
        return main.addTeacher(first_name, last_name, city, phone_number, date_of_birth, supervising_class);
    }

    protected int findTeacher(String firstName, String lastName) {
        return main.findTeacher(firstName,lastName);
    }

    protected int findTeacher(int selectedTeacherId) {
        return main.findTeacher(selectedTeacherId);
    }

    protected int updateTeacher(int i, int selectedTeacherId, String newValue) {
        return main.updateTeacher(i,selectedTeacherId,newValue);
    }

    public String getTeacherNameAndLastName(int teacherId) {
        return main.getTeacherNameAndLastName(teacherId);
    }

    protected Vector<Vector<String>> findTeachersWithoutSupervisingClass () {
        return main.findTeachersWithoutSupervisingClass();
    }

    protected Vector<Vector<String>> findAllTeacher() {
        return main.findAllTeacher();
    }


    //Methods for working with DB table 'class'
    protected String addClass(String className,int supervisingTeacherId, ArrayList<Integer> studentsId){
        return main.addClass(className, supervisingTeacherId, studentsId);
    }

    protected boolean checkClassName(String className){
        return main.checkClassName(className);
    }

    protected int findClass(String className) {
        return main.findClass(className);
    }

    protected void findClass(int classId) {
        main.findClass(classId);
    }

    protected int findClass(boolean classNameKnown, String supervisingTeacher) {
        return main.findClass(classNameKnown,supervisingTeacher);
    }

    protected Vector<Vector<String>> findClassWithoutSupervisingTeacher () {
        return main.findClassWithoutSupervisingTeacher();
    }

    protected Vector<Vector<String>> findClassWithEmptyStudentPlaces () {
        return main.findClassWithEmptyStudentPlaces();
    }

    protected int updateClass(int i, int selectedClassId, String newValue) {
        return main.updateClass(i,selectedClassId,newValue);
    }

    public String getClassIdByClassName(String classAttendingName) {
        return main.getClassIdByClassName(classAttendingName);
    }

    public String getClassNameByClassId(int classId) {
        return main.getClassNameByClassId(classId);
    }

    protected void addStudentToClassOnFirstFreeSlot(String studentId, String class_attending){
        main.addStudentToClassOnFirstFreeSlot(studentId, class_attending);
    }

    protected Vector<Vector<String>> findAllClass() {
        return main.findAllClass();
    }

    //Method for all tables in DB
    protected void deleteRow(int i, String rowId){
        main.deleteRow(i, rowId);
    }
}
