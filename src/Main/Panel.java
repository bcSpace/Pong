package Main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Game.GameStateManger;

@SuppressWarnings("serial")
public class Panel extends JPanel 
	implements Runnable, KeyListener,MouseMotionListener,MouseListener{
	
	// dimensions
	public static final int WIDTH = GameInfo.WIDTH;
	public static final int HEIGHT = GameInfo.HEIGHT;
	public static final int SCALE = GameInfo.SCALE;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManger gsm;
	
	boolean mousePressed = false; 
	int mouseX,mouseY;
	
	boolean keys[] = new boolean[256]; 
	
	public Panel() {
		super();
		setPreferredSize(
			new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this); 
			thread.start();
		}
	}
	
	private void init() {
		
		image = new BufferedImage(
					WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB
				);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManger();
		gsm.createStates();
		
	}
	
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void update() {
		if(gsm.reset) setAllInputFalse(); 
		if(gsm.currentState == 2) for(int i = 0; i < 255; i++) if(keys[i] ==  true) gsm.keyPressed(i);
		if(gsm.currentState == 2) if(mousePressed == true) gsm.mousePressed(mouseX/SCALE,mouseY/SCALE);
		gsm.update();
	}
	
	public void setAllInputFalse() {
		if(gsm.currentState == 2) for(int i = 0; i < 255; i++) keys[i] = false; 
		gsm.reset = false; 
	}
	
	private void draw() {
		gsm.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				WIDTH * SCALE, HEIGHT * SCALE,
				null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		if(gsm.currentState == 1) gsm.keyPressed(key.getKeyCode());else {
			keys[key.getKeyCode()] = true;
		}
	}
	public void keyReleased(KeyEvent key) {
		if(gsm.currentState == 1) gsm.keyReleased(key.getKeyCode());else {
			keys[key.getKeyCode()] = false;
		}
	
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true; 
		mouseX = e.getX()/GameInfo.SCALE;
		mouseY = e.getY()/GameInfo.SCALE; 
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false; 
		mouseX = e.getX();
		mouseY = e.getY(); 
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY(); 
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY(); 
		
	}

}


