package farmes;
import java.awt.*;

public abstract class Figure extends Thread{
	
	Point point = new Point(50, 50);	
	Canvas cnv;
	Graphics g;
	Color color;
	public int speed;
	double angle;
	int id;
	int step = 4;

	void moveTo() {
		this.show(false);
		this.point.x += this.speed*Math.cos(this.angle)*step;
		this.point.y += this.speed*Math.sin(this.angle)*step;
		checkBorder();
		this.show(true);		
	}
	
	void show(boolean sh) {
		//
	}
	
	void checkBorder() {
		//
	}
	
	public void run() {
		while(true) {
			moveTo();
			try {
				sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
