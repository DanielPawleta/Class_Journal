import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ChooseClassFrame extends JFrame implements ActionListener {
    private int selectedClassId;
    private MyFrame myFrame;

    private Vector<Vector<String>> dataRow;
    private Vector<Vector<String>> dataRowChangedIdToNames;
    private Vector<String> columnNames;
    private JTable jTable;

    private Insets insets = new Insets(10,10,10,10);
    private JButton backButton;
    private JButton selectButton;

    public ChooseClassFrame(MyFrame myFrame, Vector<Vector<String>> dataRow){
        this.myFrame = myFrame;
        this.dataRow = dataRow;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Choose Student");
        this.setLayout(new GridBagLayout());
        this.initializeTitleLabel();
        this.initializeTab();
        this.initializeButtons();

        this.setVisible(true);
        this.setSize(500,600);
        this.setResizable(false);
        this.pack();
    }

    private void initializeTitleLabel() {
        JLabel titleLabel = new JLabel("Choose class");
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
        initializeDataRowChangedIdToNames();

        jTable = new JTable();
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableModel defaultTableModel = new DefaultTableModel(dataRowChangedIdToNames,columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable.setModel(defaultTableModel);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(jTable.getSelectedRow());
                selectButton.setEnabled(true);
            }
        });

        jTable.getColumnModel().getColumn(0).setPreferredWidth(40); //id
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100); //class name
        jTable.getColumnModel().getColumn(2).setPreferredWidth(130); //supervising teacher
        jTable.getColumnModel().getColumn(3).setPreferredWidth(70); //student 1
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70); //student 2
        jTable.getColumnModel().getColumn(5).setPreferredWidth(70); //student 3
        jTable.getColumnModel().getColumn(6).setPreferredWidth(70); //student 4
        jTable.getColumnModel().getColumn(7).setPreferredWidth(70); //student 5
        jTable.getColumnModel().getColumn(8).setPreferredWidth(70); //student 6
        jTable.getColumnModel().getColumn(9).setPreferredWidth(70); //student 7
        jTable.getColumnModel().getColumn(10).setPreferredWidth(70); //student 8
        jTable.getColumnModel().getColumn(11).setPreferredWidth(70); //student 9
        jTable.getColumnModel().getColumn(12).setPreferredWidth(70); //student 10

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setPreferredSize(new Dimension(675,150));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.gridwidth=2;
        c.gridheight=8;
        c.gridx=0;
        c.gridy=1;

        this.add(scrollPane,c);
    }

    private void initializeColumnNamesRow() {
        columnNames = new Vector<>();
        columnNames.add("id");
        columnNames.add("class_name");
        columnNames.add("supervising_teacher");
        columnNames.add("student_1");
        columnNames.add("student_2");
        columnNames.add("student_3");
        columnNames.add("student_4");
        columnNames.add("student_5");
        columnNames.add("student_6");
        columnNames.add("student_7");
        columnNames.add("student_8");
        columnNames.add("student_9");
        columnNames.add("student_10");
    }

    private void initializeDataRowChangedIdToNames(){
        dataRowChangedIdToNames = new Vector<>();

        for (Vector<String> row : dataRow){
            Vector<String> vectorChangedIdToNames = new Vector<>();
            vectorChangedIdToNames.add(row.get(0));
            vectorChangedIdToNames.add(row.get(1));


            if (row.get(2)!=null){ //supervising_teacher
                String supervisingTeacherId = row.get(2);
                vectorChangedIdToNames.add(2,myFrame.getTeacherNameAndLastName(Integer.parseInt(supervisingTeacherId)));
            }
            else vectorChangedIdToNames.add(2,null);
            for (int i=3;i<13;i++){
                if (row.get(i)!=null){//students
                    String studentId = row.get(i);
                    vectorChangedIdToNames.add(i,myFrame.getStudentNameAndLastName(Integer.parseInt(studentId)));
                }
                else vectorChangedIdToNames.add(i,null);
            }
            dataRowChangedIdToNames.add(vectorChangedIdToNames);
        }

    }

    private void initializeButtons() {
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = insets;
        g.gridx = 1;
        g.gridy = 10;
        selectButton.setEnabled(false);
        this.add(selectButton, g);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints h = new GridBagConstraints();
        h.insets = insets;
        h.gridx = 1;
        h.gridy = 11;
        this.add(backButton, h);
    }

    @Override
    public void dispose() {
        //myFrame.setVisible(true);
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            System.out.println("back button");
            myFrame.setVisible(true);
            dispose();
        }

        if (e.getSource() == selectButton) {
            System.out.println("Select button");
            int selectedRow = jTable.getSelectedRow();
            selectedClassId = Integer.parseInt(String.valueOf(jTable.getValueAt(selectedRow, 0)));
            System.out.println("Selected id: " + selectedClassId);

            System.out.println("Show class frame from choose class frame");

            for (String st : dataRow.get(selectedRow)){
                System.out.println(st);
            }


            ClassFrame classFrame = new ClassFrame(myFrame, dataRow, selectedRow);

            dispose();
        }
    }
}
