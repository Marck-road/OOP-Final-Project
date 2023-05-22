package application;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Level2Controller extends Level1Controller {

	@FXML ImageView you;
	public void initialize(Scene scene, LevelTransition levelTransition) {
		System.out.println("Initializing Level 2 Controller");
        super.initialize(scene, levelTransition);
		
		
	}
	
}

