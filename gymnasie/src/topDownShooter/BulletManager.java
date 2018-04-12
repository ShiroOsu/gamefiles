package topDownShooter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import audio.GameAudio;

public class BulletManager {
	
	private Player player;
	private BufferedImage image;
	private GameAudio ga;
	
	
	private ArrayList<Bullet> bullets = new ArrayList<>();
	
	public BulletManager(Player player){
		this.player = player;
		ga = new GameAudio("src/audioFiles/Firing.mp3");
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public void render(Graphics g){
		/*
		 * Görs så att när skottet ritas ut blir det mitten av spelaren
		 */
		for(int i = 0; i < bullets.size(); i++){
			g.drawImage(image, (int)bullets.get(i).getX() + 27, (int)bullets.get(i).getY() - 15,
					(int)bullets.get(i).getWidth(), (int)bullets.get(i).getHeight(), null);
		}
	}
	
	public int getBullets(){
		return bullets.size();
	}
	
	public ArrayList<Bullet> getBulletArray(){
		return bullets;
	}
	
	
	/*
	 * Skapar en bullet vid x, y
	 */
	public void addBullet(int x, int y){
		bullets.add(new Bullet(x, y));
	}
	
	

	public void update(){
		if(player.isFiring()){
			bullets.add(new Bullet((int)player.getX(), (int)player.getY()));
			try {
				ga.playSound();
			} catch (Exception e) {
				e.printStackTrace();
			}
			player.setFiring(false);	
			
		}
		/*
		 * utanför skärmen
		 */
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).move();
			if(bullets.get(i).getY() < 0){
				bullets.remove(i);
			}	
		}
	}
}
