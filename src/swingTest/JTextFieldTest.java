package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTextFieldTest {
    public static void main(String[] args) {
        MyFrame2 myFrame2 = new MyFrame2();
    }

}

class MyFrame2 extends JFrame implements ActionListener {
    JButton button;
    JTextField textField;


    public MyFrame2(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setSize(500,500);
        this.setLayout(new FlowLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        textField.setFont(new Font("Console",Font.PLAIN,35));
        textField.setForeground(Color.GREEN);
        textField.setBackground(Color.BLUE);
        textField.setCaretColor(Color.RED);
        textField.setText("User name");
        textField.setEditable(false);

        button = new JButton("Submit");
        button.addActionListener(this);

        this.add(button);
        this.add(textField);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
            System.out.println(textField.getText());
            textField.setEditable(true);
            button.setEnabled(false);
        }

    }
}
