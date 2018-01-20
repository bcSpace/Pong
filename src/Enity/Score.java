package Enity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Main.GameInfo;

public class Score {
	
	int player1Score = 0;
	int player2Score = 0; 
	
	Font f = new Font("Arial", Font.BOLD, 36);
	
	public void score(int i) {
		if(i == 1) player1Score++;
		if(i == 2) player2Score++; 
	}
	
	public void resetScores() {
		this.player1Score = 0;
		this.player2Score = 0;
	}

	public void draw(Graphics2D g) {
		g.setFont(f); 
		g.setColor(Color.white);
		g.drawString(player1Score + "", 150, 50);
		g.drawString(player2Score + "", GameInfo.WIDTH-150, 50); 
	}
	
}
