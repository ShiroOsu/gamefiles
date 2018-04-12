package topDownShooter;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Bullet extends Rectangle {
	
		private int speed = 7;
		
		private boolean shouldBeRemoved = false;
		
		
		public Bullet(int x, int y){
			super(x, y, 25, 25);
		}
		
	
		
		public void setSpeed(int speed){
			this.speed = speed;
		}
	
		public int getSpeed(){
			return speed;
		}
		
		public void move(){
			y -= speed;
			
		}
		
		public void remove()
		{
			shouldBeRemoved = true;
		}
		
		public boolean shouldBeRemoved()
		{
			return shouldBeRemoved;
		}
}



