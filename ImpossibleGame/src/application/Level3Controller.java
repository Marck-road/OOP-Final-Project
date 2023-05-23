package application;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Level3Controller extends LevelController{
	
	@FXML ImageView you;
	@FXML private Line dangerZone;
	@FXML private Rectangle l1;
    private Timeline timeline = null; 
    private double speed = 15.0;

	public void initialize(Scene scene, LevelTransition levelTransition) {	
		System.out.println("Initializing Level 3 Controller");
        super.initialize(scene, levelTransition);

	    
        moveLine();
        
        getRat().getImageView().boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue.intersects(dangerZone.getBoundsInParent())) {
	        	getRat().stopMovement();
	            handleDeath();
	        }
	    });

	}
	
	private void moveLine() {
		timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {

			double startX = dangerZone.getStartX();
	        double endX = dangerZone.getEndX();

			if (startX <= 0 && endX <= 0) {
	            speed = Math.abs(speed); // Change direction to move right
	        } else if (startX >= 380 && endX >= 380) {
	            speed = -Math.abs(speed); // Change direction to move left
	        }

	        // Update the position of the imageView
			updatePosition(speed, 0);
	       
	      
	    }));
		timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	    
	    
	    
	}
	
	private void updatePosition(double deltaX, double deltaY) {
	    double newEndX = dangerZone.getEndX() + deltaX;
	    double newEndY = dangerZone.getEndY() + deltaY;

	    // Calculate the new start position based on the difference between the new and old end positions
	    double newStartX = dangerZone.getStartX() + (newEndX - dangerZone.getEndX());
	    double newStartY = dangerZone.getStartY() + (newEndY - dangerZone.getEndY());

	    // Update the positions of the line's start and end points
	    dangerZone.setStartX(newStartX);
	    dangerZone.setStartY(newStartY);
	    dangerZone.setEndX(newEndX);
	    dangerZone.setEndY(newEndY);
	    
	    
	    
	    
	}
	
	

	
	
}
