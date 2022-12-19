package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
//any object seen on the JFrame is a sprite
public class Sprite {

	protected int x; // the x position of Car
	protected int y; // the y position of Car
	protected int vx; // the movement of car
	protected int vy; // the movement of the car in the y direction
	protected String fileName;
	protected Image img;// the image of the Car
	protected int width;
	protected int height;
	protected AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	
	public Sprite(String fileName, int startX, int startY) {
		// assignment statements for attributes

		x = startX;
		y = startY;
		width = 50;
		height = 50;
		vx = -1;
		vy = 0;

		img = getImage(fileName);
		init(x, y);

	}

	//Helper function for collision detection later
	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x,y,width,height);
		return temp;
	}
	
	public Sprite(String fileName) {
		// assignment statements for attributes

		x = 0;
		y = 0;
		vx = 0;
		width = 50;
		height = 50;

		img = getImage(fileName);
		init(x, y);

	}

	// getters and setters

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVx() {
		return vx;
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}
	
	public void setVy(int vy) {
		this.vy = vy;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	//converts image to make it drawable in paint
	protected Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sprite.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
