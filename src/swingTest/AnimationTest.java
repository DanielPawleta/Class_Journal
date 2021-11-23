package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationTest {
    public static void main(String[] args) {
        MyFrame6 frame = new MyFrame6();
    }
}

class MyFrame6 extends JFrame{

    MyPanel2 panel;

    public MyFrame6() {
        panel = new MyPanel2();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.add(panel);
        this.pack();


        this.setVisible(true);
    }



}

class MyPanel2 extends JPanel implements ActionListener {
    int PANEL_WIDTH = 500;
    int PANEL_HEIGHT = 500;
    Image enemy;
    Image background;
    Timer timer;
    int xVelocity=1;
    int yVelocity=1;
    int x=0;
    int y=0;



    public MyPanel2() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.BLACK);


        enemy = new ImageIcon("C:/Users/dpawleta/Downloads/CodeGymTasks/4.JavaCollections/src/test/swingTest/logo.jpg").getImage();
        timer = new Timer(10,this);
        timer.start();


    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(enemy,x,y,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((x+enemy.getWidth(null))>=PANEL_WIDTH){
            xVelocity=-xVelocity;
        }
        if ((y+enemy.getHeight(null))>=PANEL_HEIGHT){
            yVelocity=-yVelocity;
        }

        if (x<0){
            xVelocity=-xVelocity;
        }
        if (y<0){
            yVelocity=-yVelocity;
        }





        x=x+xVelocity;
        y=y+yVelocity;

        repaint();
    }
}
