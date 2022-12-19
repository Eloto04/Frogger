package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Froggy extends Sprite{

	// attributes of a frog

	private boolean alive; // lives
	protected boolean onLog = false;
	
	// write the constructor for froggy which tales in
	// a string fileName that will be used for the image setup
	// set x and y to be in the middle of a 400x400 screen
	// set width and height to 50

	protected Image left, right, up, down;
	
	public Froggy(String fileName) {
		// assignment statements for attributes

		super(fileName);
		
		x = 400;
		y = 800;
		vx = 0;
		vy = 0;
		width = 50;
		height = 50;
		
		left = getImage("newfrogleft.png");
		right = getImage("newfrogright.png");
		up = getImage("newfrog.png");
		down = getImage("newfrogdown.png");

	}

	public void onLog() {
		
		onLog = true;
		
	}
	
	public void notOnLog() {
		
		onLog = false;
		
	}
	
	public boolean getOnLog() {
		return onLog;
	}
	
	public boolean collided(int ox, int oy, int ow, int oh) {

		Rectangle obs = new Rectangle(ox, oy, ow, oh);
		Rectangle froggy = new Rectangle(x, y, width, height);
		System.out.println(obs);
		System.out.println(froggy);
		return obs.intersects(froggy);
	}

	// gets image and procces it

	public void move() {
		
		y += vy;
		x += vx;
		tx.setToTranslation(x, y);

	}

	public void hop(int dir) {
		
		switch(dir) {
		case 0:
			img = up;
			y-=height;
			break;
		case 1:
			img = down;
			y+=height;
			break;
		case 2:
			img = left;
			if(onLog == true) {
				x-=(width*2);
			} else {
				x-=width;
			}
			
			break;
		case 3:
			img = right;
			if(onLog == true) {
				x+=(width*2);
			} else {
				x+=width;
			}
			
			break;
			
		}
		
		
	}

	public void colMove() {
		x += vx;
	}
	
	public void reset() {
		
		x = 400;
		y = 800;
		img = up;
		
	}
	
	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move(); //ask frog to update its location variables
		g2.drawImage(img, tx, null);
		
	}

	// converts image to make it drawable in paint

	// setters and getters

}
