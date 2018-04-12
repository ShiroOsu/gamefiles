package topDownShooter;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public abstract class Entity extends Rectangle {
	
	
	// Fields
	
	protected int speed;
	protected BufferedImage image;
	
	/*
	 * Static, så att man inte behöver ladda om bilden flera gånger
	 */
	public static final BufferedImage ENEMY = loadImage("src/topDownShooter/lmao/red.png"),
			BULLET = loadImage("src/topDownShooter/lmao/bullet.png");
	
	private boolean shouldBeRemoved = false;
	
	public Entity(BufferedImage image, int x, int y, int width, int height, int speed){
		super(x, y , width, height);
		this.image = image;
		this.speed = speed;
	}
	
	
	public void render(Graphics g){
		g.drawImage(image, x, y, width, height, null);
		
	}
	
	public void move(){
		y += speed;
	}
	
	public void update(){
		
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public void remove()
	{
		shouldBeRemoved = true;
	}
	
	public boolean shouldBeRemoved()
	{
		return shouldBeRemoved;
	}
	

	private static BufferedImage loadImage(String path){
		try{
			return ImageIO.read(new File(path));
		}catch (IOException e){
			System.err.println(e.getMessage());
			return null;
		}
	}
}
