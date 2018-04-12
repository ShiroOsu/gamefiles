package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import backGrounds.Background;
import topDownShooter.Game;

public class PresState extends GameState {
	
	
	
	private Font font;
	
	
	public PresState(GameStateManager gsm){
		this.gsm = gsm;
		init();
		
		
	}

	@Override
	public void init() {
		
		
	}

	@Override
	public void update() {
		
		
	}

	
	@Override
	public void render(Graphics g) {	
		g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		font = new Font("Arial", Font.PLAIN, 100);
		g.setFont(font);
		g.drawString("PAUSED", Game.WIDTH / 2 - 200, 150);
		font = new Font("Arial", Font.PLAIN, 25);
		g.setFont(font);
		
		
		
		
	}
	
	private void quit(){
		System.exit(0);
	}


	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			quit();
		}	
	}

	@Override
	public void keyReleased(int k) {
	}
}
