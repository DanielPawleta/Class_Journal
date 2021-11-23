package swingTest.DragAndDropTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragPanel extends JPanel {

    ImageIcon imageIcon = new ImageIcon("C:\\Users\\dpawleta\\Downloads\\CodeGymTasks\\4.JavaCollections\\src\\test\\swingTest\\logo.jpg");
    int width = imageIcon.getIconWidth();
    int height = imageIcon.getIconHeight();

    Point imageCorner;
    Point prevPt;

    public DragPanel() {
        imageCorner = new Point(0,0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);


        //this.setIm

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        imageIcon.paintIcon(this,g,(int)imageCorner.getX(),(int)imageCorner.getY());

    }

    private class ClickListener extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e){
            prevPt = e.getPoint();
        }

    }

    private class DragListener extends MouseMotionAdapter{

        public void mouseDragged(MouseEvent e){
            Point currentPt = e.getPoint();

            imageCorner.translate(
                    (int)(currentPt.getX() - prevPt.getX()),
                    (int)(currentPt.getY()-prevPt.getY())
            );

            prevPt=currentPt;
            repaint();

        }

    }
}
