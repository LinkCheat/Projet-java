package Forme;

public class Forme{
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	public Forme(float x1,float y1,float x2,float y2) {
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
	public void dP (float dx,float dy) {
		this.x1 += dx;
		this.y1 += dy;
		this.x2 += dx;
		this.y2 += dy;
	}
	public void redimensionner(float kx, float ky) {
		this.x2 += kx;
		this.y2 += ky;
	}
}