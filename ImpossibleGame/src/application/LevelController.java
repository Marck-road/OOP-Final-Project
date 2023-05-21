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

	            rat.moveLeft();
	        } else if (keyCode == KeyCode.RIGHT) {

	        	rat.moveRight();
	        } else if (keyCode == KeyCode.SPACE) {
	            rat.goForth();
	            

	        }else if (keyCode == KeyCode.DOWN) {
	            rat.turnAround();
	            

	        }
	        
	    });
	}
	
	public boolean isLevelCompleted() {
        return true;
    }
}
