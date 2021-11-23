package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class JFileChooserTest {
    public static void main(String[] args) {
        JFileChooserTestDemo fileChooser = new JFileChooserTestDemo();
    }
}

class JFileChooserTestDemo extends JFrame implements ActionListener {

    JButton button;

    public JFileChooserTestDemo() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(new FlowLayout());

        button = new JButton("Select file");
        button.addActionListener(this);

        this.add(button);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button) {
            System.out.println("button");

            JFileChooser fileChooser = new JFileChooser();
            //int response = fileChooser.showOpenDialog(null);

            fileChooser.setCurrentDirectory(new File("."));


            int response = fileChooser.showSaveDialog(null);

            if (response==JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file.toString());

            }




        }




    }
}
