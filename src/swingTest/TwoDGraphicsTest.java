package swingTest;

import javax.swing.*;
import java.awt.*;

public class TwoDGraphicsTest {
    public static void main(String[] args) {
        MyFrame6 frame = new MyFrame6();
    }
}

class MyFrame5 extends JFrame{

    MyPanel2 panel;

    public MyFrame5() {
        panel = new MyPanel2();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.add(panel);
        this.pack();


        this.setVisible(true);
    }



}

class MyPanel extends JPanel{
    public MyPanel() {
        this.setPreferredSize(new Dimension(550,550));
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Image image = new ImageIcon("C:/Users/dpawleta/Downloads/CodeGymTasks/4.JavaCollections/src/test/swingTest/logo.jpg").getImage();
        g2d.drawImage(image,50,50,null);

        g2d.setStroke(new BasicStroke(10));
        g2d.setPaint(Color.BLUE);
        g2d.drawLine(10,10,490,490);

        g2d.setPaint(Color.RED);
        g2d.drawRect(10,10,490,490);
        //g2d.drawOval(50,50,100,150);

        g2d.fillArc(100,100,200,200,0,45);

        g2d.setPaint(Color.MAGENTA);
        g2d.setFont(new Font("Ink Free",Font.BOLD,50));
        g2d.drawString("Eloszka",100,50);





    }
}
