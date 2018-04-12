package hud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import backGrounds.Background;

public class Hud {
	
	private Background bg;
	private BufferedImage playerIcon;
	private double x, y;
	
	/*
	 * Hud är bara en stil bild av spelarn och de svarta linjerna på nedre delen av spelet för att se skillnad på spelet och health.
	 */
	public Hud(double x, double y){
		this.x = x;
		this.y = y;
		init();
	}
	
	public void init(){
		playerIcon = loadImage("/topDownShooter/lmao/SpaceShip.png");
		//bg = new Background("/topDownShooter/lmao/hud.png");
		//bg.setPosition(x, y);
		
	}
	
	private BufferedImage loadImage(String path){
        try{
            return ImageIO.read(getClass().getResourceAsStream(path));
        }catch (IOException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void render(Graphics g){
		bg.render(g);
		g.drawImage(playerIcon, 0, 600, 100, 100, null);
	}
	
	public void update(){
		bg.update();
	}
}
