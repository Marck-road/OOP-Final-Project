package application;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Level6Controller extends LevelController {
	

	@FXML ImageView you;
	public void initialize(Scene scene, LevelTransition levelTransition) {
		System.out.println("Initializing Level 6 Controller");
		 String musicFile = "src/sounds/Victory.mp3"; // Replace with your audio file's path
		Media sound = new Media(new File(musicFile).toURI().toString());

		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.05);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		

        
	}
}

