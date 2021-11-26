

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    private JLabel loggedLabel;
    private JPanel upPanel;
    private JPanel downPanel;
    private JButton newStudentButton;
    private JButton findStudentButton;
    private final String PAUSE_BUTTON_TEXT = "Pause";
    private JButton saveButton;
    private boolean areButtonsVisible=true;
    private JPanel leftPanel ;
    private JPanel rightPanel;
    private JPanel centerPanel;
    private final int PANEL_SIZE =30;
    private final int GAP_IN_BORDER_LAYOUT_SIZE =10;
    private Main main;

    //Constructor
    public MyFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Class Journal v1.0");
        this.setLayout(new BorderLayout(GAP_IN_BORDER_LAYOUT_SIZE,GAP_IN_BORDER_LAYOUT_SIZE));
        this.getContentPane().setBackground(Color.BLACK);
        initializePanels();
        initializeFrontLabel();
        this.setVisible(true);
        this.setSize(500,500);
        //this.setResizable(false);


    }

    //Methods
    private void initializePanels(){
        upPanel = new JPanel();
        downPanel = new JPanel();
        downPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        loggedLabel = new JLabel("Please log in");
        downPanel.add(loggedLabel);
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

        JLabel frontLabelSecondPart = new JLabel("   Journal ");
        frontLabelSecondPart.setFont(new Font("MV Boli", Font.PLAIN, 50));
        welcomePanel.add(frontLabelSecondPart);

        centerPanel.add(welcomePanel);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel loginPanel = new JPanel();
        tabbedPane.addTab("Login",loginPanel);

        JPanel studentsPanel = new JPanel();
        studentsPanel.setLayout(new BoxLayout(studentsPanel, BoxLayout.Y_AXIS));
        studentsPanel.add(Box.createVerticalGlue());
        newStudentButton = new JButton("New Student");
        newStudentButton.addActionListener(this);
        newStudentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newStudentButton.setFocusable(false);
        studentsPanel.add(newStudentButton);
        studentsPanel.add(Box.createVerticalGlue());

        findStudentButton = new JButton("Find Student");
        findStudentButton.addActionListener(this);
        findStudentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentsPanel.add(findStudentButton);
        studentsPanel.add(Box.createVerticalGlue());

        tabbedPane.addTab("Students",studentsPanel);

        JPanel teachersPanel = new JPanel();
        tabbedPane.addTab("Teachers",teachersPanel);

        JPanel classesPanel = new JPanel();
        tabbedPane.addTab("Classes",classesPanel);

        JPanel statisticsPanel = new JPanel();
        tabbedPane.addTab("Statistics",statisticsPanel);

        centerPanel.add(tabbedPane);

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

        }
        if (e.getSource()==findStudentButton){
            System.out.println("find student");


        }
        if (e.getSource()== saveButton){

        }
    }
}
