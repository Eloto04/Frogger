
package frog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener
		 {

	int screen_width = 900;
	int screen_height = 900;
	Froggy froggy;
	boolean won = false;
	boolean onLog = false;
	
	// instantiate objct log

	Car[] car2 = new Car[10];
	//ArrayList containing Log objects
	ArrayList<Log> logs = new ArrayList<Log>();
	ArrayList<Log> logs1 = new ArrayList<Log>();
	ArrayList<Log> logs2 = new ArrayList<Log>();
	ArrayList<Log> logs3 = new ArrayList<Log>();
	ArrayList<Truck> trucks = new ArrayList<Truck>();
	
	Background bg;
	int my_variable = 3; // example
	int lifeCounter = 3;
	
	String lose = "";
	String win = "";
	String lost = "";

	// ****************************paint method******************************************
	public void paint(Graphics g) {

		super.paintComponent(g);
		bg.paint(g);

		//draw how many lives you hve
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(("Lives:") + Integer.toString(lifeCounter), 25, 75);
		g.setFont(font2);

		// paint sprites for carss
		for (int i = 0; i < car2.length; i++) {
			car2[i].paint(g);
		}

		for(int i = 0; i < logs.size(); i++) {
			logs.get(i).paint(g);
			logs1.get(i).paint(g);
			logs2.get(i).paint(g);
			logs3.get(i).paint(g);
			trucks.get(i).paint(g);
		}
		
		//paint and update froggy 
		froggy.paint(g);

		// car one
		g.drawString(lost, 0, 50);
		if (lifeCounter == 0) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 900, 900);
			g.setColor(Color.white);
			g.setFont(font3);
			lose = "You lost :/";
			g.drawString(lose, 200, 300);
			g.setFont(font4);
			g.drawString("Click or press any key to exit", 200, 400);
		}
		
		//resetting
		if (lifeCounter > 0 && froggy.getY() == 0) {
			lose = "";
			win = "You won! :D";
			won = true;
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 900, 900);
			g.setColor(Color.white);
			g.setFont(font3);
			g.drawString(win, 200, 300);
			g.setFont(font4);
			g.drawString("Click or press any key to exit", 215, 400);
		}

	}

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	Font font3 = new Font("Courier New", 1, 75);
	Font font4 = new Font("Courier New", 1, 25);
	
	//
	public void update() {

		froggy.move();

		// car two
		for (int i = 0; i < car2.length; i++) {

			car2[i].setVx(-3);
			car2[i].move();
			if(froggy.getRect().intersects(car2[i].getRect())) {
				
				System.out.println("Hit by a car!");
				froggy.reset();
				lifeCounter--;
				
			}
			
		}
		
		if(froggy.getX()<=0-froggy.getWidth() || 
				froggy.getX()>= 900) {
			froggy.reset();
			lifeCounter--;
			System.out.println("Went off the screen!");
		}
		
		for(int i = 0; i < logs.size(); i++) {
			
			if(froggy.getY() == 100 || froggy.getY() == 400) {
				if(froggy.getOnLog() == false) {
					froggy.reset();
					lifeCounter--;
					System.out.println("Drowned!");
				}
			}
			
			
		}
		
		for (int i = 0; i < trucks.size(); i++) {

			if(froggy.getRect().intersects(trucks.get(i).getRect())) {
				froggy.reset();
				lifeCounter--;
				System.out.println("Hit by a truck!");
			}
			
		}
		
		//check for collision
		
		boolean col = false;
		
		for(int i = 0; i < logs.size(); i++) {
			logs.get(i).move();
			logs1.get(i).move();
			logs1.get(i).setVx(-2);
			logs2.get(i).move();
			logs2.get(i).setVx(-2);
			logs3.get(i).move();
			logs3.get(i).setVx(-3);
			trucks.get(i).move();
			
			if(froggy.getRect().intersects(logs3.get(i).getRect())) {
				froggy.setX(logs3.get(i).getX());
				froggy.setVx(-3);
				col = true;
				froggy.onLog();
			} 

			if(froggy.getRect().intersects(logs2.get(i).getRect())) {
				froggy.setX(logs2.get(i).getX());
				froggy.setVx(-2);
				col = true;
				froggy.onLog();
			}
			
			if(froggy.getRect().intersects(logs1.get(i).getRect())) {
				froggy.setX(logs1.get(i).getX());
				froggy.setVx(-2);
				col = true;
				froggy.onLog();
			}
			
			if(froggy.getRect().intersects(logs.get(i).getRect())) {
				froggy.setX(logs.get(i).getX());
				froggy.setVx(-1);
				col = true;
				froggy.onLog();
			}
			
			if(!col){ froggy.setVx(0);
			froggy.notOnLog();}
			
		}
		//
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	//sets up all objects on screen
	
	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Frogger");
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this);

		// sprite instantiation

		froggy = new Froggy("newfrog.png");

		for (int i = 0; i < car2.length; i++) {
			car2[i] = new Car("smartcar.png", i * 250, 350);
		}

		
		int spacing = 50;
		int spacing2 = 50;
		int spacingTruck = 350;
		//setup the logs
		for(int i = 0; i < 12; i++) {
			if(i%2==0) {
				spacing = 150;
			} else {
				spacing = 100;
			}
			
			Log temp = new Log("logpic.png", i*spacing, 108);
			Log temp1 = new Log("logpic.png", i*spacing2*2, 153);
			Log temp2 = new Log("logpic.png", i*spacing, 408);
			Log temp3 = new Log("logpic.png", i*spacing2*2, 450);
			Truck tempTruck = new Truck("truckpic.png", i*spacingTruck, 525);
			logs.add(temp);
			logs1.add(temp1);
			logs2.add(temp2);
			logs3.add(temp3);
			trucks.add(tempTruck);
		}
		
		//Add background
		bg = new Background("background1.png");
		// do not add to frame, call paint on
		// these objects in paint method

		f.addMouseListener(this);
		
		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		// detect up, down, left, right arrow keypresses
		// call setters for volovety attributes accordingly
		// 37 <- ,
		// 38 up ,
		// 40 down,
		// 39 ->
		System.out.println("x = " + froggy.getX()
		+ " and y = " + froggy.getY());
		/*if (e.getKeyCode() == KeyEvent.VK_W) {
			froggy.setVy(-5);
		}*/

		//on water at 100, 150, 400, 450
		
		if(lifeCounter<=0 || won) {
			System.exit(-1);
		}
		
	/** assignment **/
	//be able to move the frog up, down, left, and right

	switch(e.getKeyCode()) {
	
	case KeyEvent.VK_W:
		froggy.hop(0);
		break;
	case KeyEvent.VK_S:
		froggy.hop(1);
		break;
	case KeyEvent.VK_A:
		froggy.hop(2);
		break;
	case KeyEvent.VK_D:
		froggy.hop(3);
		break;
	
	}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/* turn off velocity for Frog if you don't want it moving when
		 * you have stopped pressing the keys
		 */
		if (e.getKeyCode() == 38) {
			froggy.setVy(0);
			froggy.setVx(0);
		}

		if (e.getKeyCode() == 40) {
			froggy.setVy(0);
			froggy.setVx(0);
		}

		//do the same thing for the other keys
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("click");
		if(lifeCounter<=0 || won) {
			System.exit(-1);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}