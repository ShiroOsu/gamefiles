package topDownShooter;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;



@SuppressWarnings("serial")
public class Player extends Mob implements KeyListener {
	private boolean up, down, left, right, dead = false;
	private static boolean isFiring;

	public Player(BufferedImage image, int x, int y, int width, int height, int speed) {
		super(image, x, y, width, height, speed);	
	}

	/*
	 * vart spelarn ska fylltas
	 */
	@Override
	public void update() {
		if(up){
			y -= speed;
			if(y < 0){
				y = 0;
			}
		}
		if(down){
			y += speed;
			if(y + 80 > 700){
				y = 620;
			}
		}
		if(left){
			x -= speed;
			if(x < 0){
				x = 0;
			}
		}
		if(right){
			x += speed;
			if(x + 80 > 600){
				x = 520;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}

	@Override
	public void setImage(BufferedImage image){
		this.image = image;
	}


	public boolean isDead(){
		return dead;
	}

	public void setDead(){
		dead = true;
	}


	public void setUp(boolean up){
		this.up = up;
	}

	public void setDown(boolean down){
		this.down = down;
	}

	public void setLeft(boolean left){
		this.left = left;
	}

	public void setRight(boolean right){
		this.right = right;
	}

	public void setFiring(boolean f){
		isFiring = f;
	}

	public boolean isFiring(){
		return isFiring;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_W){
			setUp(true);
		}
		if(k == KeyEvent.VK_S){
			setDown(true);
		}
		if(k == KeyEvent.VK_A){
			setLeft(true);
		}
		if(k == KeyEvent.VK_D){
			setRight(true);
		}
		if(k == KeyEvent.VK_F){
			setFiring(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_W){
			setUp(false);
		}
		if(k == KeyEvent.VK_S){
			setDown(false);
		}
		if(k == KeyEvent.VK_A){
			setLeft(false);
		}
		if(k == KeyEvent.VK_D){
			setRight(false);
		}
		if(k == KeyEvent.VK_F){
			setFiring(false);
		}
	}
}
