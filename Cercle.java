package Forme;


import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cercle implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    private int r;
    private boolean selected;
    private List<Cercle> history = new ArrayList<Cercle>();
    
    public Cercle() {
    	this(0,0,1);
    }

    public Cercle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.selected = false;	
        this.history.add(0,this);
    }

    public void modifier(int x,int y,int r) {
    	this.x=x;
    	this.y=y;
    	this.r=r;
    }
    
    public void paint(Graphics g) {
        if (selected) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawOval(x - r, y - r, r * 2, r * 2);
    }

    public void select(int clickX, int clickY) {
        double distance = Math.sqrt(Math.pow(clickX - x, 2) + Math.pow(clickY - y, 2));
        if (distance <= r) {
            selected = true;
        }
    }

    public void unselect() {
        selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public Cercle moins(Cercle c) {
        int x = this.x - c.x;
        int y = this.y - c.y;
        int r = this.r - c.r;
        Cercle nouv = new Cercle(x,y,r);
        nouv.history.addAll(c.history);
        return nouv;
    }

    public Cercle plus(Cercle c) {
        int x = this.x + c.x;
        int y = this.y + c.y;
        int r = this.r + c.r;
        Cercle nouv = new Cercle(x,y,r);
        nouv.history.addAll(c.history);
        return nouv;
    }
}
