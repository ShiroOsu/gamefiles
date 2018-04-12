package gameStates;

import java.awt.Graphics;

public abstract class GameState {
	
	protected GameStateManager gsm;
	/*
	 * Den abstrakta klassen som har metoderna som alla gamestates behöver
	 */
	public abstract void init();
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);

}
