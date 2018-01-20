package Game;

import java.awt.Graphics2D;

public abstract class State {
	
	public boolean acceptsKeys; 
	public boolean acceptsMouse; 
	public GameStateManger gsm;
	
	public State(GameStateManger gsm) {this.gsm = gsm;}
	
	public abstract void init(int i); 
	public abstract void draw(Graphics2D g); 
	public abstract void update();
	public abstract void mousePressed(int x, int y); 
	public abstract void keyPressed(int key); 
	
	
}
