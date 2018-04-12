package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import backGrounds.Background;
import topDownShooter.Game;

public class PauseState extends GameState {
	
	private String[] options = {"Menu", "Restart", "Quit" };
	private int currentChoice = 0;
	private Font font;
	private Background bg;
	
	/*
	 * En pause state som inte direkt uppfyller någon funktion i spelet, men där är kvar bara för att
	 */
	public PauseState(GameStateManager gsm){
		this.gsm = gsm;
		bg = new Background("/topDownShooter/lmao/menubg.png");
		init();
		
		
	}

	@Override
	public void init() {
		
		
	}

	@Override
	public void update() {
		bg.update();
		
	}

	
	@Override
	public void render(Graphics g) {
		bg.render(g);
		
		font = new Font("Arial", Font.PLAIN, 100);
		g.setFont(font);
		g.drawString("PAUSED", Game.WIDTH / 2 - 200, 150);
		font = new Font("Arial", Font.PLAIN, 25);
		g.setFont(font);
		
		for(int i = 0; i < options.length; i++){
			if(i == currentChoice){
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], Game.WIDTH/2 - 220 + (i * 185), 250);
		}
		
	}
	
	private void select(){
		if(currentChoice == 0){
			gsm.setState(GameStateManager.MENUSTATE);
			
		}
		
		if(currentChoice == 1){
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		
		if(currentChoice == 2){
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_LEFT){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length -1;
			}
		}
		
		if(k == KeyEvent.VK_RIGHT){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
		
	}

	@Override
	public void keyReleased(int k) {
	}

}
