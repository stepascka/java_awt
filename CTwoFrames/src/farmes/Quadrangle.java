package farmes;
import java.awt.*; 

public class Quadrangle extends Figure{

	int height = 40;
	
	void show(boolean sh) {
		if (sh) 
			g.setColor(color); 
		else 
			g.setColor(Color.pink);
		
		g.fillRect(point.x-height/2, point.y-height/2, height, height);
		g.drawString(this.id + "", point.x-height/2-2, point.y-height/2-2);
	}
	
	void checkBorder() {
		boolean border = false;
		
		if ((cnv.getWidth()-point.x <= this.height/2) && (Math.cos(this.angle) > 0)) {
			this.angle = Math.PI - this.angle;
			border = true;
		}
		else if ((point.x <= this.height/2) && (Math.cos(this.angle) < 0)) {
			this.angle = Math.PI - this.angle;
			border = true;
		}
		
		if ((cnv.getHeight()-point.y <= this.height/2) && (Math.sin(this.angle) > 0)) {
			this.angle *= (-1);
			border = true;
		}
		else if ((point.y <= this.height/2) && (Math.sin(this.angle) < 0)) {
			this.angle *= (-1);
			border = true;
		}
		
		if (border) {			
			this.point.x += this.speed*Math.cos(this.angle);
			this.point.y += this.speed*Math.sin(this.angle);
		}		
	}
	
	Quadrangle(Canvas cnv, Color color, int speed, int id) {
		this.cnv = cnv;
		this.color = color;
		this.speed = speed;
		this.angle = Math.random()*2*Math.PI;
		this.g = cnv.getGraphics();
		this.id = id;
	}
}
