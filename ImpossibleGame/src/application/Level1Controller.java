package application;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Level1Controller extends LevelController {
	

	@FXML ImageView you;
	public void initialize(Scene scene) {
		System.out.println("Initializing LevelController");
		super.initialize(scene);
		
	}
	
}