package audio;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

@SuppressWarnings({ "restriction" })
public class GameAudio {
	private MediaPlayer mediaPlayer;
	private Media m;
	

	public GameAudio(String path){
		new JFXPanel();
		m = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(m);
		mediaPlayer.setRate(1);
		
	}
	/*
	 * Innan den försöker spela upp ljudet sätter den tiden till noll så ljudet inte bara hörs en gång
	 */
	public void playSound() throws Exception {
		try{		
			mediaPlayer.seek(javafx.util.Duration.ZERO);
			mediaPlayer.play();
			
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
	}
}
