package application;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Level4Controller extends LevelController {
	

	@FXML ImageView you;
	public void initialize(Scene scene, LevelTransition levelTransition) {
		System.out.println("Initializing Level 2 Controller");
        super.initialize(scene, levelTransition);
        Rat rat = getRat();
        
        
	}
	
	
	
	
	 
	 
	 
}

