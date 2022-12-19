package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Car extends Sprite{

	public Car(String fileName, int startX, int startY) {
		// assignment statements for attributes
		super(fileName, startX, startY);
		x = startX;
		y = startY;
		width = 50;
		height = 50;
		vx = -1;
		vy = 0;
	}

	public Car(String fileName) {
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
			
			x=1200;
			tx.setToTranslation(x, y);
		}

	}


}
