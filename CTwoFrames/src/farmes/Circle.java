package farmes;
import java.awt.*; 

public class Circle extends Figure{

	int radius = 40;
	
	void show(boolean sh) {
		if (sh) 
			g.setColor(color);  
		else 
			g.setColor(Color.pink);  
		
		g.fillOval(point.x, point.y, radius, radius);
		g.drawString(this.id + "", point.x, point.y);
	}
	
	void checkBorder() {
		boolean border = false;
		
		if ((cnv.getWidth()-point.x <= this.radius) && (Math.cos(this.angle) > 0)) {
			this.angle = Math.PI - this.angle;
			border = true;
		}
		else if ((point.x <= 0) && (Math.cos(this.angle) < 0)) {
			this.angle = Math.PI - this.angle;
			border = true;
		}
		
		if ((cnv.getHeight()-point.y <= this.radius) && (Math.sin(this.angle) > 0)) {
			this.angle *= (-1);
			border = true;
		}
		else if ((point.y <= 0) && (Math.sin(this.angle) < 0)) {
			this.angle *= (-1);
			border = true;
		}
		
		if (border) {			
			this.point.x += this.speed*Math.cos(this.angle);
			this.point.y += this.speed*Math.sin(this.angle);
		}		
	}
	
	Circle(Canvas cnv, Color color, int speed, int id) {
		this.cnv = cnv;
		this.color = color;
		this.speed = speed;
		this.angle = Math.random()*2*Math.PI;
		this.g = cnv.getGraphics();
		this.id = id;
	}
}
