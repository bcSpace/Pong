package Game;
import java.awt.Graphics2D;

import Main.GameInfo;

public class GameStateManger {

	public int currentState = 1; 
	public int amountOfGameStates = 100; 
	State states[] = new State[amountOfGameStates]; 
	
	public boolean reset = false; 

	public void createStates() {
		//create states here
		states[1] = new MainMenu(this);
		states[1].init(1);
		states[2] = new PlayState(this);
	}
	
	public void update() {
		states[currentState].update(); 
	}
	
	public void draw(Graphics2D g) {
		states[currentState].draw(g); 
	}
	
	public void startGame(int i) {
		currentState = 2; 
		System.out.println("Gone to playState"); 
		System.out.println("State: " + currentState); 
		states[2].init(i);
		currentState = 2; 
		reset = true; 
	}
	
	public void closeGame() {
		System.exit(0);
	}
	
	public void mousePressed(int x, int y) {
		if(states[currentState].acceptsMouse == true) states[currentState].mousePressed(x,y); 
	}
	
	public void setState(int i) {
		currentState = i; 
	}
	public void keyPressed(int key) {
		if(states[currentState].acceptsKeys == true) states[currentState].keyPressed(key); 
	}
	
	public void keyReleased(int key) {
		
	}
	
	
}
