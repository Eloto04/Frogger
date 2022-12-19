package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Truck extends Sprite{

	public Truck(String fileName, int startX, int startY) {
		// assignment statements for attributes
		super(fileName, startX, startY);
		x = startX;
		y = startY;
		width = 150;
		height = 51;
		vx = -1;
		vy = 0;
	}

	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x,y,width,height);
		return temp;
	}
	
	public Truck(String fileName) {
		// assignment statements for attributes
		super(fileName);
		width = 50;
		height = 50;

	}

	public void move() {
		tx.translate(vx, 0);
		x += vx;
		
		//what happens when car exits on the left side?
		if(x<0-width){
			
			x=1250;
			tx.setToTranslation(x, y);
		}

	}


}
