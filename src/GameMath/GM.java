package GameMath;

public class GM {
	
	public static double getSpeed(double startx, double targetx, double starty, double targety, int speedWanted, double speedC) {
		double xSpeed = 1;
		double ySpeed = 2;
		int playerXX = (int)(startx - targetx);
		int playerYY = (int)(starty - targety); 
		playerXX = Math.abs(playerXX);
		playerYY = Math.abs(playerYY); 
		double totalChange = Math.sqrt(playerXX * playerXX + playerYY * playerYY);
		if(totalChange/speedC != 0) {
		if(startx != targetx) xSpeed = (playerXX/(totalChange/speedC));  else xSpeed = 0; 
		if(starty != targety) ySpeed = (playerYY/(totalChange/speedC)); else ySpeed = 0; 
		}
		if(targetx > startx && xSpeed < 0) xSpeed *= -1;  
		if(targetx < startx && xSpeed  > 0) xSpeed *= -1; 
		if(targetx == startx) xSpeed = 0;
		if(targety > starty  && ySpeed < 0) ySpeed *= -1;  
		if(targety < starty  && ySpeed  > 0) ySpeed *= -1; 
		if(targety == starty ) ySpeed = 0;
		
		if(speedWanted == 1) return xSpeed; 
		else  return ySpeed;
	}

}
