package gameStates;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameStateManager {
	
	private int currentState = 0;
	
	public static final int MENUSTATE = 0, LEVEL1STATE = 1, HELPSTATE = 2, PAUSESTATE = 3, GAMEOVER = 4, PRESSTATE = 5;
	private ArrayList<GameState> gameStates;
	
	
	/*
	 * GameStateManager klassen lägger till gamestates
	 */
	public GameStateManager(){
		
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		
		gameStates.add(new MenuState(this));
		gameStates.add(new Level1State(this));
		gameStates.add(new HelpState(this));
		gameStates.add(new PauseState(this));
		gameStates.add(new GameOver(this));
		gameStates.add(new PresState(this));
		
	}
	
	public void setState(int state){
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	
	public void update(){
		gameStates.get(currentState).update();
	}
	
	public void render(Graphics g){
		gameStates.get(currentState).render(g);
	}
	
	public void keyPressed(int k){
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k){
		gameStates.get(currentState).keyReleased(k);
	}

}
