package Forme;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Forme{
	 	private static final long serialVersionUID = 1L;
	    private int x1;
	    private int y1;
	    private int x2;
	    private int y2;
	    private boolean selected;
	    private List<Forme> history = new ArrayList<Forme>();
	    
	    public Forme() {
	    	this(0,0,1,1);
	    }

	    public Forme(int x1, int y1, int x2, int y2) {
	        this.x1 = x1;
	        this.y1 = y1;
	        this.x2 = x2;
	        this.y2 = y2;
	        this.selected = false;	
	        this.history.add(0,this);
	    }

	    public void modifier(int x1, int y1, int x2, int y2) {
	        this.x1 = x1;
	        this.y1 = y1;
	        this.x2 = x2;
	        this.y2 = y2;
	    }
	    
	    public void paint(Graphics g) {
	        if (selected) {
	            g.setColor(Color.RED);
	        } else {
	            g.setColor(Color.BLACK);
	        }
	        g.drawRect(x1,y1,x2-x1,y2-y1);
	    }

	    public void select(int clickX, int clickY) {
	        if (x1 <= clickX && clickX <= x2 && y1 <= clickY && y2 <= clickY) {
	            selected = true;
	        }
	    }

	    public void unselect() {
	        selected = false;
	    }

	    public boolean isSelected() {
	        return selected;
	    }

	    public Forme moins(Forme c) {
	        int x1 = this.x1 - c.x1;
	        int y1 = this.y1 - c.y1;
	        int x2 = this.x2 - c.x2;
	        int y2 = this.y2 - c.y2;
	        Forme nouv = new Forme(x1,y1,x2,y2);
	        nouv.history.addAll(c.history);
	        return nouv;
	    }

	    public Forme plus(Forme c) {
	        int x1 = this.x1 + c.x1;
	        int y1 = this.y1 + c.y1;
	        int x2 = this.x2 + c.x2;
	        int y2 = this.y2 + c.y2;
	        Forme nouv = new Forme(x1,y1,x2,y2);
	        nouv.history.addAll(c.history);
	        return nouv;
	    }
	}
