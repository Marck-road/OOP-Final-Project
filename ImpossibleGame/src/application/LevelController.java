package application;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class LevelController {
	private Rat rat;
	boolean levelCompleted = false;
	@FXML ImageView you;
	
	public void initialize(Scene scene) {
	    rat = new Rat(you);
	    System.out.println("\nDisplaying all: ");

	    you.getScene().setOnKeyPressed(event -> {
	        KeyCode keyCode = event.getCode();
	        if (keyCode == KeyCode.LEFT) {
	    		System.out.println("\nLeft working");

	            rat.moveLeft();
	        } else if (keyCode == KeyCode.RIGHT) {
	    		System.out.println("\nRight working");

	        	rat.moveRight();
	        } 
	        
//	        if (/* condition to check level completion */) {
//                levelCompleted = true;
//           }
	    });
	}
	
	public boolean isLevelCompleted() {
        return true;
    }
}
