package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import backGrounds.Background;
import topDownShooter.Game;

public class HelpState extends GameState {
	private Color textColor;
	private Font font;
	private String[] options = {"Quit", "Back"};
	private int currentChoice = 1;
	private Background bg;
	
	public HelpState(GameStateManager gsm){
		this.gsm = gsm;
		bg = new Background("/topDownShooter/lmao/helpbg.png");
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
		textColor = Color.red;
		font = new Font("Arial", Font.PLAIN, 20);
		
		/*
		 * Ritar ut lite text om spelet 
		 */
		g.setFont(font);
		g.setColor(textColor);
		g.drawString("Welcome to the game 'Shooter'. ", 50, 50);
		g.drawString("You're a space captain. ", 50, 75);
		g.drawString("Your job as a space captain is to kill all the alien spaceships.", 50, 105);
		g.drawString("W A S D to move your spaceship.", 50, 135);
		g.drawString("F to fire your cannonballs for tons of damage.", 50, 165);
		
		for(int i = 0; i < options.length; i++){
			if(i == currentChoice){
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], Game.WIDTH/2 - 30, 200 + i * 30);
		}
	
		
	}
	
	private void select(){
		if(currentChoice == 0){
			System.exit(0);
		}
		if(currentChoice == 1){
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}


	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		
		if(k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length -1;
			}
		}
		
		if(k == KeyEvent.VK_DOWN){
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
