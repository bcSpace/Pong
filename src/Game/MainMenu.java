package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MainMenu extends State{
	
	public MainMenu(GameStateManger gsm) {super(gsm);}

	String title = "Main Menu"; 
	String choices[] = new String[10];
	int currentChoice, amountOfChoices; 
	int yGrid; 
	Color selectedColor, titleColor, choiceColor; 
	
	Font f = new Font("Arial", Font.BOLD, 36);
	
	
	public void init(int i) {
		this.acceptsKeys = true; 
		this.acceptsMouse = true;
		choices[1] = "Play";
		choices[2] = "Watch AI Play"; 
		choices[3] = "Quit"; 
		currentChoice = 1; 
		amountOfChoices = 3; 
		titleColor = Color.red; 
		selectedColor = Color.pink; 
		choiceColor = Color.green;
		yGrid = (Main.Panel.HEIGHT+25 - (Main.Panel.HEIGHT/4))/amountOfChoices;  
		
		
	}

	public void draw(Graphics2D g) {
		g.setFont(f); 
		g.setColor(Color.BLACK); 
		g.fillRect(0, 0, Main.Panel.WIDTH, Main.Panel.HEIGHT);
		g.setColor(Color.white);
		g.fillRect(0, 25-g.getFont().getSize(), Main.Panel.WIDTH, g.getFont().getSize());
		g.setColor(titleColor);
		g.drawString(title, 25, 25);
		for(int i = 1; i < amountOfChoices+1; i++) {
			if(currentChoice == i) {
				g.setColor(selectedColor); 
				g.drawString(choices[i], 50, yGrid*i); 
			} else {
				g.setColor(choiceColor);
				g.drawString(choices[i], 25, yGrid*i);
			}
		}
		
		
	}

	public void update() {
		
	}

	public void mousePressed(int x, int y) {
		
	}

	public void keyPressed(int key) {
		if(key == KeyEvent.VK_DOWN) {
			currentChoice++;
		}
		if(key == KeyEvent.VK_UP) {
			currentChoice--;
		}
		if(currentChoice < 1) currentChoice  = amountOfChoices; 
		if(currentChoice > amountOfChoices) currentChoice = 1; 
		if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
			if(currentChoice == 1)gsm.startGame(1);
			if(currentChoice == 2) gsm.startGame(2);
			if(currentChoice == 3)gsm.closeGame();
		}
		
	}

}
