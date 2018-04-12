package topDownShooter;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Enemy extends Mob {
	
	

	public Enemy(BufferedImage image, int x, int y, int width, int height, int speed) {
		super(image, x, y, width, height, speed);
	}
	
	
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}

}


