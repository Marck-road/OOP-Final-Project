package application;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;


public class Level1Controller extends LevelController {
	

	@FXML ImageView you;
	public void initialize(Scene scene, LevelTransition levelTransition) {
		System.out.println("Initializing LevelController");
		super.initialize(scene, levelTransition);
		
	}
	
}
