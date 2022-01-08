

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener {
    private JLabel loggedLabel;
    private JPanel upPanel;
    private JPanel downPanel;
    private JPanel studentsTab;
    private JPanel teachersTab;
    private JPanel classesTab;
    private LoginPanel loginPanel;
    private JButton newStudentButton;
    private JButton findStudentButton;
    private JButton logOutButton;
    private String loginText = "Please log in";
    private String emptyText = "please update!";
    private int emptyNumber = 000;
    private String emptyDate = "1900-01-01";

    private final String PAUSE_BUTTON_TEXT = "Pause";
    private JButton saveButton;
    private boolean areButtonsVisible=true;
    private JPanel leftPanel ;
    private JPanel rightPanel;
    private JPanel centerPanel;
    private final int PANEL_SIZE =30;
    private int GAP_IN_BORDER_LAYOUT_SIZE =10;
    private Main main;
    private JTabbedPane tabbedPane;

    //Constructor
    public MyFrame(Main main) {
        this.main = main;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("School Database v1.0");
        this.setLayout(new BorderLayout(GAP_IN_BORDER_LAYOUT_SIZE,GAP_IN_BORDER_LAYOUT_SIZE));
        this.getContentPane().setBackground(Color.BLACK);
        initializePanels();
        initializeFrontLabel();

        this.ableAllTabs();
        this.setVisible(true);
        this.setSize(500,500);
        this.setResizable(false);
    }

    //Methods
    private void initializePanels(){
        upPanel = new JPanel();
        downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1,2));
        loggedLabel = new JLabel(loginText);
        downPanel.add(loggedLabel);
        logOutButton = new JButton("Logout");
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(this);
        logOutButton.setVisible(false);
        downPanel.add(logOutButton);

        leftPanel = new JPanel();
        rightPanel = new JPanel();
        centerPanel = new JPanel();

        upPanel.setBackground(new Color(0x94D914));
        downPanel.setBackground(new Color(0x91D914));
        leftPanel.setBackground(new Color(0x2E4F07));
        rightPanel.setBackground(new Color(0x2E4F07));
        centerPanel.setBackground(new Color(0xA8D0E1));

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

        loginPanel = new LoginPanel(this);
        tabbedPane.addTab("Login",loginPanel);

        studentsTab = new JPanel();
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

        tabbedPane.addTab("Students", studentsTab);
        tabbedPane.setEnabledAt(1,false);

        teachersTab = new JPanel();
        tabbedPane.addTab("Teachers", teachersTab);
        tabbedPane.setEnabledAt(2,false);

        classesTab = new JPanel();
        tabbedPane.addTab("Classes", classesTab);
        tabbedPane.setEnabledAt(3,false);

        JPanel statisticsTab = new JPanel();
        tabbedPane.addTab("Statistics",statisticsTab);

        centerPanel.add(tabbedPane);
    }

    private void disableAllTabs(){
        tabbedPane.setEnabledAt(1,false);
        tabbedPane.setEnabledAt(2,false);
        tabbedPane.setEnabledAt(3,false);
    }

    private void ableAllTabs(){
        tabbedPane.setEnabledAt(1,true);
        tabbedPane.setEnabledAt(2,true);
        tabbedPane.setEnabledAt(3,true);
    }

    protected void setLoggedAs(int loggedNumber){
        // 0 - logged out
        // 1 - student
        // 2 - teacher
        // 3 - admin
        if (loggedNumber == 0){
            loggedLabel.setText(loginText);
            logOutButton.setVisible(false);
            disableAllTabs();
            tabbedPane.setEnabledAt(0,true);
            tabbedPane.setSelectedIndex(0);
        }
        else if (loggedNumber == 1){
            loggedLabel.setText("You are logged as Student");
            logOutButton.setVisible(true);
            tabbedPane.setEnabledAt(1,true);
            tabbedPane.setEnabledAt(0,false);
            tabbedPane.setSelectedIndex(1);
        }

    }

    protected String addStudent(String first_name, String last_name, String city, int phone_number, String date_of_birth, int parents_phone_number, String class_attender){
        return main.addStudent(first_name, last_name, city, phone_number, date_of_birth, parents_phone_number, class_attender);
    }

    protected String addStudent(String first_name, String last_name, String city, int phone_number, String date_of_birth, int parents_phone_number){
        return main.addStudent(first_name, last_name, city, phone_number, date_of_birth, parents_phone_number, emptyText);
    }


    /*
    public void initializeDownPanel() {
        downPanel.setLayout(new GridLayout(1,3,50,10) );

        returnButton = new JButton("Return");
        pauseButton = new JButton(PAUSE_BUTTON_TEXT);
        saveButton = new JButton("Save game");

        returnButton.addActionListener(this);
        pauseButton.addActionListener(this);
        saveButton.addActionListener(this);

        downPanel.add(returnButton);
        downPanel.add(pauseButton);
        downPanel.add(saveButton);

        returnButton.setFocusable(false);
        pauseButton.setFocusable(false);
        saveButton.setFocusable(false);

        returnButton.setVisible(false);
        pauseButton.setVisible(false);
        saveButton.setVisible(false);

        saveButton.setEnabled(false);
    }

    public void ableUpAndDownButtonsVisibility(){
        returnButton.setVisible(true);
        pauseButton.setVisible(true);
        saveButton.setVisible(true);
    }

    public void disableUpAndDownButtonsVisibility(){

        returnButton.setVisible(false);
        pauseButton.setVisible(false);
        saveButton.setVisible(false);
    }

     */

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
        if (e.getSource()== logOutButton){
            setLoggedAs(0);
            System.out.println("logout");

        }
    }


    protected int findStudent(String firstName, String lastName) {
        return main.findStudent(firstName,lastName);
    }
}
