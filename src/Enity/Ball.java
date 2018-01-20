package Enity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Main.GameInfo;

public class Ball {
	
	int x, y; 
	int width, height; 
	public int xSpeed = 0;
	public Rectangle bounds; 
	
	int ySpeedMin = 3, ySpeedMax = 7;
	int ySpeedCurrent; 
	int yDir = -1; 
	
	Random r = new Random(); 
	
	boolean drawLine = false; 
	
	public void create(int x, int y, int width, int height, int xSpeed, int ySpeedMin, int ySpeedMax, int ySpeedCurrent, int direction) {
		this.x = x; 
		this.y = y;
		this.width = width;
		this.height = height; 
		this.xSpeed = xSpeed; 
		this.ySpeedMin = ySpeedMin; 
		this.ySpeedMax = ySpeedMax; 
		this.yDir = direction; 
		this.ySpeedCurrent = ySpeedCurrent; 
		updateBounds(); 
		
	}
	
	public void updateBounds() {
		bounds = new Rectangle(x,y,width,height); 
	}
	
	public void setDrawLine(boolean v) { drawLine = v; }
	
	public void update() {
		x+=xSpeed; 
		y+=ySpeedCurrent * yDir;
		if(y+height >= GameInfo.HEIGHT) yDir *= -1;
		if(y  <= 0) yDir *= -1;
		updateBounds();
	}
	
	public void bounce(int i) {
		xSpeed*=-1;
		if(xSpeed < 0) xSpeed-=1; 
		else xSpeed+=1; 
		
		if(r.nextInt(6) < 3) {
			ySpeedCurrent--;
		} else {
			ySpeedCurrent++; 
		}
		
		if(ySpeedCurrent < ySpeedMin) ySpeedCurrent = ySpeedMin; 
		if(ySpeedCurrent > ySpeedMax) ySpeedCurrent = ySpeedMax; 
		
		if(xSpeed < 0 && xSpeed < -28) xSpeed = -28;
		else if(xSpeed > 0 && xSpeed > 28) xSpeed = 28; 
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.white); 
		g.fillOval(x,y,width,height); 
		if(drawLine) {
			g.setColor(Color.red);
			g.drawLine(x+width/2, y+height/2, x+width/2+(xSpeed * 100), y+height/2+(ySpeedCurrent * yDir * 100));
			
			
		}
	}
}
