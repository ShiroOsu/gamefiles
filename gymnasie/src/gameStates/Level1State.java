package gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import audio.GameAudio;
import backGrounds.Background;
import hud.Hud;
import topDownShooter.Bullet;
import topDownShooter.BulletManager;
import topDownShooter.Enemy;
import topDownShooter.Entity;
import topDownShooter.Game;
import topDownShooter.Player;
/**
 * 
 * @author Niklas
 *
 */
public class Level1State extends GameState {
	private Background bg;
	private Player player;
	private BulletManager bulletmanager;
	private Hud playerHud;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private int enemyCount = 0;
	private static int enemyKilled = 0;
	private int health = 100;
	private long t;
	private GameAudio gaKilled, gaOut, gaExit;

	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}

	/*
	 * Lägger till en enemy
	 */
	public void addEnemy(int x, int y, int width, int height, int speed){
		enemies.add(new Enemy(Entity.ENEMY, x, y, width, height, speed));
		enemyCount++;
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount += enemyCount;
	}

	public static int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int enemyKilled) {
		Level1State.enemyKilled += enemyKilled;
	}

	@Override
	public void init() {
		
		health = 100;
		player = new Player(loadImage("/topDownShooter/lmao/SpaceShip.png"), Game.WIDTH/2 - 40, 550, 80, 80, 6);
		playerHud = new Hud(0, 600);
		bg = new Background("/topDownShooter/lmao/levelbg.png");
		bulletmanager = new BulletManager(player);
		bulletmanager.setImage(Entity.BULLET);
		enemies.clear();	//för att det ska inte vara några enemies innan spelets början
		
		//gaKilled = new GameAudio("src/audioFiles/Killed.mp3");
		//gaOut = new GameAudio("src/audioFiles/EnemyOut.mp3");
		//gaExit = new GameAudio("src/audioFiles/GameOver.wav");

		t = 0;
		
	}

	@Override
	public void update() {
		bullets = bulletmanager.getBulletArray();

		bg.update();
		player.update();
		bulletmanager.update();
		playerHud.update();
		setEnemyCount(enemies.size());
		t++;
		

		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).update();
			enemies.get(i).move();
		}
		

		for(Enemy e : enemies)
		{
			
			for(Bullet b : bullets)
			{
				if(b.intersects(e))
				{
					//om fienden nuddar skottet
					e.remove();
					try {
						gaKilled.playSound();
					} catch (Exception eaItsInTheGame) {
						eaItsInTheGame.printStackTrace();
					}
					b.remove();
					setEnemyKilled(1);
					
					

				}
			}
			if(player.intersects(e))
			{
				//om spelaren nuddar fienden
				health -= 20;
				e.remove();
				setEnemyKilled(1);
				
				
			}
		}

		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getY() >= Game.HEIGHT){
				enemies.get(i).remove(); //Fiender som åker utanför skärmen
				health -= 20;
				
				try {
					gaOut.playSound();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if(enemies.get(i).shouldBeRemoved()) {
				enemies.remove(i);
			}
		}

		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).shouldBeRemoved()) {
				bullets.remove(i); //Skott som träffar fienderna
			}
		}
		/*
		 * Man förlorar/dör
		 */
		if(health <= 0){
			player.setDead();
			try {
				gaExit.playSound();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			gsm.setState(GameStateManager.GAMEOVER);
		}
		/*
		 * Olika typer av fiender som skapas vid olika intervaller
		 */
		
		if(t % 180 == 179){
			addEnemy((int)(Math.random()*(Game.WIDTH - 80)), -100, 80, 60, 3);
			setEnemyCount(1);
			
		}
		
		if(t % (60 * 10) == (60*10)-1){
			addEnemy((int)(Math.random()*(Game.WIDTH - 80)), -100, 100, 70, 4);
			setEnemyCount(1);
			
		}
		
		if(t % (60 * 20) == (60*20)-1){
			addEnemy((int)(Math.random()*(Game.WIDTH - 80)), -50, 50, 70, 2);
			setEnemyCount(1);
			
		}
		
		if(t % (60 * 30) == (60*30)-1){
			addEnemy((int)(Math.random()*(Game.WIDTH - 80)), -100, 150, 100, 4);
			setEnemyCount(1);
		}
	}

	@Override
	public void render(Graphics g) {
		bg.render(g);
		player.render(g);
		bulletmanager.render(g);

		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).render(g);
		}
		
		/*
		 * Ritar ut ens "hälsa"/health
		 */
		playerHud.render(g);
		g.setColor(Color.GRAY);
		g.fillRect((int)playerHud.getX() + 104, 677, 492, 18);
		g.setColor(Color.RED);
		g.fillRect((int)playerHud.getX() + 104, 677, (int)(492*(health/100.0)), 18);
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_W){
			player.setUp(true);
		}
		if(k == KeyEvent.VK_S){
			player.setDown(true);
		}
		if(k == KeyEvent.VK_A){
			player.setLeft(true);
		}
		if(k == KeyEvent.VK_D){
			player.setRight(true);
		}
		if(k == KeyEvent.VK_F){
			player.setFiring(true);
		}
		
		if(k == KeyEvent.VK_P){
			gsm.setState(GameStateManager.PAUSESTATE);
		}
		if(k == KeyEvent.VK_T){
			gsm.setState(GameStateManager.PRESSTATE);
		}
	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_W){
			player.setUp(!true);
		}
		if(k == KeyEvent.VK_S){
			player.setDown(!true);
		}
		if(k == KeyEvent.VK_A){
			player.setLeft(!true);
		}
		if(k == KeyEvent.VK_D){
			player.setRight(!true);
		}
		if(k == KeyEvent.VK_F){
			player.setFiring(!true);
		}
	}

	private BufferedImage loadImage(String path){
		try{
			return ImageIO.read(getClass().getResourceAsStream(path));
		}catch (IOException e){
			System.err.println(e.getMessage());
			return null;
		}
	}
}

















