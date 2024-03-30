package Forme;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFrame;

public class OS extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -778200993024314706L;
	private ArrayList<Cercle> circles;
    private boolean a_press;
    private boolean s_press;
    
    public OS() {
        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .setFullScreenWindow(this);
        
        loadCircles(); 
        
        a_press = false;
        s_press = false;

        this.addKeyListener(new KeyAdapter() {
             public void keyTyped(KeyEvent key) {}

             public void keyPressed(KeyEvent key) {
                 if (key.getKeyChar() == 'q') {
                	 saveCircles();
                     System.exit(0);
                 } else if (key.getKeyChar() == 'a') {
                     a_press = true; 
                 } else if (key.getKeyChar() == 's') {
                     s_press = true; 
                 }
             }

             public void keyReleased(KeyEvent key) {
                if (key.getKeyChar() == 'a') {
                    a_press = false;
                } else if (key.getKeyChar() == 's') {
                    s_press = false;
                }
            }
        });
        
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent click) {
                if (a_press) {
                    circles.add(new Cercle(click.getX(), click.getY(), 50));
                    repaint();
                } else if (s_press) {
                    for (Cercle c : circles) {
                        c.unselect();
                    }
                    for (Cercle c : circles) {
                        c.select(click.getX(), click.getY());
                        if (c.isSelected()) {
                            break;
                        }
                    }
                    repaint();
                }
            }
        });
        
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Cercle c : circles) {
            c.paint(g);
        }
    }
    
    private void saveCircles() {
        try {
            String filePath = System.getProperty("user.dir") + File.separator + "circles.ser";
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(circles);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in circles.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadCircles() {
        try {
            String filePath = System.getProperty("user.dir") + File.separator + "circles.ser";
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            circles = (ArrayList<Cercle>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Circles are loaded from circles.ser");
        } catch (IOException | ClassNotFoundException e) {
            circles = new ArrayList<>();
            System.out.println("No circles found. Starting with an empty list.");
        }
    }
    
    public static void main(String[] args) {
        new OS();
    }
}