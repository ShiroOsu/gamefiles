package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import backGrounds.Background;
import topDownShooter.Game;

public class GameOver extends GameState{

	private String[] options = {"Restart", "Quit" };
	private int currentChoice = 0;
	private Font font;
	private Background bg;


	public GameOver(GameStateManager gsm){
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

		/*
		 * Ritar ut texten när man har dött.
		 */
		font = new Font("Arial", Font.PLAIN, 70);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("GAME OVER", Game.WIDTH / 2 - 220, 130);
		
		font = new Font("Arial", Font.PLAIN, 25);
		g.setFont(font);
		
		/*
		 * Score och Enemies killed
		 */
		g.drawString("Enemies killed:" + " " + Level1State.getEnemyKilled(), 100, 200);
		g.drawString("Score:" + " " + (Level1State.getEnemyKilled() * 5 / 3 + 1 / 6 * 2), 350, 200);
		
		/*
		 * Ritar ut options
		 */
		for(int i = 0; i < options.length; i++){
			if(i == currentChoice){
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], Game.WIDTH/2 - 130 + (i * 185), 250);
		}
	}

	/*
	 * Vilket option man väljer så utförs det här..
	 */
	private void select(){
		if(currentChoice == 0){
			gsm.setState(GameStateManager.LEVEL1STATE);
		}

		if(currentChoice == 1){
			System.exit(1);
		}
	}

	/*
	 * När man trycker på piltagenterna flyttar den till annan option
	 */
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
	public void keyReleased(int k) {}
}
