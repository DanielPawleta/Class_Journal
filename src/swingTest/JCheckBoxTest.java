package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JCheckBoxTest {
    public static void main(String[] args) {
        MyFrame3 myFrame = new MyFrame3();
    }


}

    class MyFrame3 extends JFrame implements ActionListener {
        JButton button;
        JCheckBox checkBox;
        ImageIcon crossIcon;
        ImageIcon selectedIcon;

        public MyFrame3() {
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setLayout(new FlowLayout());

            crossIcon = new ImageIcon("C:/Users/dpawleta/Downloads/CodeGymTasks/4.JavaCollections/src/test/swingTest/crossIcon.png");

            selectedIcon = new ImageIcon("C:/Users/dpawleta/Downloads/CodeGymTasks/4.JavaCollections/src/test/swingTest/selectedIcon.png");


            button = new JButton();
            button.setText("submit");
            button.addActionListener(this);

            checkBox = new JCheckBox();
            checkBox.setText("I'm not a robot!");
            checkBox.setFocusable(false);
            checkBox.setFont(new Font("name", Font.PLAIN,35));
            checkBox.setIcon(crossIcon);
            //checkBox.setPressedIcon(selectedIcon);
            checkBox.setSelectedIcon(selectedIcon);


            this.add(button);
            this.add(checkBox);

            this.pack();
            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==button){
                System.out.println(checkBox.isSelected());
            }

        }
    }
