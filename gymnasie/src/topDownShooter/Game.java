package topDownShooter;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import gameStates.GameStateManager;

/**
 * 
 * @author Niklas
 *
 */
@SuppressWarnings("serial")
public class Game extends Canvas implements KeyListener {

	private JFrame frame;
	private GameStateManager gsm;



	public static final int WIDTH = 600, HEIGHT = 700;

	public Game(){
		gsm = new GameStateManager();


		addKeyListener(this);
		frame = new JFrame("Shooter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		super.setSize(new Dimension(WIDTH, HEIGHT));
		frame.add(this);
		frame.pack();
		setFocusable(true);

		frame.setVisible(true);

		loop();
	}

	private void loop(){
		int frames = 0, ticks = 0;
		long lastTime = System.nanoTime();
		double ns = 1000000000.0D/60.0D, delta = 0.0D;
		long timer = System.currentTimeMillis();

		createBufferStrategy(3);

		requestFocus();



		//game loop
		while(true){


			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				delta--;
				ticks++;
				update();
			}


			frames++;
			render();



			try{
				Thread.sleep(5);
			}catch(Exception e){
				e.printStackTrace();
			}



			if(System.currentTimeMillis() - timer >= 1000){
				ticks = frames = 0;
				timer += 1000;

			}
		}
	}

	private void update(){
		gsm.update();




	}

	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);


		gsm.render(g);


		g.dispose();
		bs.show();

	}

	@Override
	public void keyPressed(KeyEvent k) {
		gsm.keyPressed(k.getKeyCode());
	}


	@Override
	public void keyReleased(KeyEvent k) {
		gsm.keyReleased(k.getKeyCode());
	}


	@Override
	public void keyTyped(KeyEvent k) {}


	public static void main(String[]args){
		new Game();
	}
}



