package Enity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GameInfo;

public class Player {
	public float x, y;
	public int width, height; 
	public float speed, maxSpeed, deaccel, accel; 
	Rectangle bounds;
	BufferedImage img; 
	
	
	public void create(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width; 
		this.height = height; 
		updateBounds(); 
	}
	
	public void setSpeeds(float maxSpeed, float deaccel, float accel) {
		this.maxSpeed = maxSpeed;
		this.deaccel = deaccel;
		this.accel = accel+deaccel;
	}
	
	public void update() {
		if(maxSpeed > 9f) maxSpeed = 9f; 
		if(speed < 0) {
			speed+=deaccel;
			if(speed < maxSpeed * -1) speed = maxSpeed * -1; 
			if(speed > 0) speed = 0;
		} else {
			speed-=deaccel; 
			if(speed > maxSpeed) speed = maxSpeed; 
			if(speed < 0) speed = 0;
		}
		y+=speed;
		if(y <= 0) y = 0;
		if(y+height >= GameInfo.HEIGHT) y = GameInfo.HEIGHT - height; 
		updateBounds(); 
		
	}
	
	public void move(int i) {
		speed+=i*accel; 
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	
	public void updateBounds() {
		bounds = new Rectangle((int)x,(int)y,width, height); 
	}
	
	public boolean isCollision(Rectangle bounds) {
		return this.bounds.intersects(bounds); 
	}

	public void setY(int y) {
		this. y = y; 
	}
	
}
