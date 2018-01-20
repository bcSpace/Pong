package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import Enity.Ball;
import Enity.Player;
import Enity.Score;
import Main.GameInfo;

public class PlayState extends State{
	
	Color backGroundColor = Color.BLACK;  
	
	public long CURRENT_TIME = System.currentTimeMillis();
	
	Player player1; 
	Player player2; 
	Ball ball;
	Score score = new Score(); 
	
	Random r = new Random(); 
	
	boolean aiEnabled = false; 
	
	int playerWidth = 25; 
	int playerHeight = 150; 
	boolean paused = true;
	long pauseMenuDelay = 500; 
	long pauseMenuStartTimer = CURRENT_TIME; 
	
	float maxPlayerSpeed = 10f; 
	int maxBallSpeed = 10;
	
	Font pauseFont = new Font("Arial", Font.BOLD, 24); 
	
	
	public PlayState(GameStateManger gsm) {
		super(gsm); 
	}

	public void init(int i) {
		this.acceptsKeys = true; 
		this.acceptsMouse = true;
		player1 = new Player();
		player2 = new Player(); 
		ball = new Ball();
		player1.create(100, GameInfo.HEIGHT/2+playerHeight/2, playerWidth, playerHeight);
		player1.setSpeeds(7, 2, 3);
		player2.create(GameInfo.WIDTH-100-playerWidth, GameInfo.HEIGHT/2+playerHeight/2, playerWidth, playerHeight);
		player2.setSpeeds(7, 2, 3);
		ball.create(500, 150, 25, 25, -5, 3, 7, 5, -1);
		
		if(i == 2) aiEnabled = true;
		else aiEnabled = false; 
		
		if(aiEnabled) ball.setDrawLine(true);
		else ball.setDrawLine(false);
		paused = false; 
	}

	public void draw(Graphics2D g) {
		g.setColor(backGroundColor); 
		g.fillRect(0, 0, GameInfo.WIDTH, GameInfo.HEIGHT);
		try {
		player1.draw(g); 
		player2.draw(g);
		ball.draw(g);
		} catch(Exception e) {System.out.println("Error: " + e.getMessage());}
		if(paused) {
			g.setFont(pauseFont); 
			g.setColor(Color.cyan);
			g.drawString("Resume(SPACE)", GameInfo.WIDTH/2-6*24, 150);
			g.drawString("Resart(E)", GameInfo.WIDTH/2-6*24, 200);
			g.drawString("Quit(Q)", GameInfo.WIDTH/2-6*24, 250);
		}
		
		score.draw(g);
		
	}
	
	public void update() {
		if(!paused) {
		ball.update();
		player1.update();
		player2.update();
		if(aiEnabled) {
			int player2Center = (int)player2.y+player2.height/2; 
			int player1Center = (int)player1.y+player1.height/2;
			if(ball.xSpeed > 0) {
			if(ball.bounds.y < player2Center) {
				player2.move(-1);
			} else if(ball.bounds.y > player2Center) {
				player2.move(1);
			}
			} else {
				
				if(player2Center < GameInfo.HEIGHT/2) {
					player2.move(1);
				} else if(player2Center > GameInfo.HEIGHT/2) {
					player2.move(-1);
				}
			
			}
			
			if(ball.xSpeed < 0) {
				if(ball.bounds.y < player1Center) {
					player1.move(-1);
				} else if(ball.bounds.y > player1Center) {
					player1.move(1);
				}
				} else {
					
					if(player1Center < GameInfo.HEIGHT/2) {
						player1.move(1);
					} else if(player2Center > GameInfo.HEIGHT/2) {
						player1.move(-1);
					}
				
				}
			
			
			
		}
		if(player1.isCollision(ball.bounds)){ ball.bounce((int)player1.speed);player1.maxSpeed+=.2f; player2.maxSpeed+=.2f;}
		if(player2.isCollision(ball.bounds)){ ball.bounce((int)player2.speed);player1.maxSpeed+=.2f; player2.maxSpeed+=.2f;}
		
		if(ball.bounds.x < player1.x) {
			score.score(2);
			ball.create(500, r.nextInt(GameInfo.HEIGHT-26), 25, 25, -5, 3, 7, 5, -1);
			player1.setSpeeds(7, 2, 3);
			player2.setSpeeds(7, 2, 3);
			player1.setY(GameInfo.HEIGHT/2-player1.height/2);
			player2.setY(GameInfo.HEIGHT/2-player2.height/2);
		}
		
		if(ball.bounds.x > player2.x+player2.width) {
			score.score(1);
			ball.create(r.nextInt(150)+250, r.nextInt(GameInfo.HEIGHT-26), 25, 25, +5, 3, 7, 5, +1);
			player1.setSpeeds(7, 2, 3);
			player2.setSpeeds(7, 2, 3);
			player1.setY(GameInfo.HEIGHT/2-player1.height/2);
			player2.setY(GameInfo.HEIGHT/2-player2.height/2);
		}
		
		
		}
		CURRENT_TIME = System.currentTimeMillis();
	}

	public void mousePressed(int x, int y) {
	
	}

	public void keyPressed(int key) {
		if(CURRENT_TIME - pauseMenuStartTimer > pauseMenuDelay) {
		if(key == KeyEvent.VK_SPACE) {
			if(paused) paused = false; 
			else paused = true;
			pauseMenuStartTimer = CURRENT_TIME;
		}
		
		if(key == KeyEvent.VK_Q && paused) {goBackToMainMenu();pauseMenuStartTimer = CURRENT_TIME;}
		if(key == KeyEvent.VK_E && paused) {
			if(aiEnabled == true) init(2); 
			else init(1); 
			score.resetScores();
			pauseMenuStartTimer = CURRENT_TIME; 
		}
		
		}
		if(!paused) {
			if(key == KeyEvent.VK_W) {
				player1.move(-1);
			} else if(key == KeyEvent.VK_S) {
				player1.move(1);
			}
			if(!aiEnabled) {
				if(key == KeyEvent.VK_UP) {
					player2.move(-1);
				} else if(key == KeyEvent.VK_DOWN) {
					player2.move(1);
				}
			}
		} 
			
			
		
	}
	
	public void goBackToMainMenu() {
		score.resetScores();
		gsm.setState(1);
	}

}