import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ChooseStudentFrame extends JFrame implements ActionListener {
    private int selectedStudentId;
    private MyFrame myFrame;

    private Vector<Vector<String>> dataRow;
    private Vector<String> columnNames;
    private JTable jTable;

    private Insets insets = new Insets(10,10,10,10);
    private JButton backButton;
    private JButton selectButton;

    public ChooseStudentFrame(MyFrame myFrame,Vector<Vector<String>> dataRow){
        this.myFrame = myFrame;
        this.dataRow = dataRow;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Choose Student");
        this.setLayout(new GridBagLayout());
        this.initializeTitleLabel();
        this.initializeTab();
        this.initializeButtons();

        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.pack();
    }

    private void initializeTitleLabel() {
        JLabel titleLabel = new JLabel("Choose student");
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

        jTable = new JTable();
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableModel defaultTableModel = new DefaultTableModel(dataRow,columnNames) {
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
        jTable.getColumnModel().getColumn(1).setPreferredWidth(80); //first name
        jTable.getColumnModel().getColumn(2).setPreferredWidth(90); //last name
        jTable.getColumnModel().getColumn(3).setPreferredWidth(90); //city
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100); //phone number
        jTable.getColumnModel().getColumn(5).setPreferredWidth(80); //date of birth
        jTable.getColumnModel().getColumn(6).setPreferredWidth(150); //parents phone number
        jTable.getColumnModel().getColumn(7).setPreferredWidth(40); //class

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
        columnNames.add("first_name");
        columnNames.add("last_name");
        columnNames.add("city");
        columnNames.add("phone_number");
        columnNames.add("date_of_birth");
        columnNames.add("parents_phone_number");
        columnNames.add("class");
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
            selectedStudentId = Integer.parseInt(String.valueOf(jTable.getValueAt(selectedRow, 0)));
            System.out.println("Selected id: " + selectedStudentId);

            System.out.println("Show student frame from choose student frame");

            for (String st : dataRow.get(selectedRow)){
                System.out.println(st);
            }
            StudentFrame studentFrame = new StudentFrame(myFrame, dataRow, selectedStudentId);
            dispose();
        }
    }
}
