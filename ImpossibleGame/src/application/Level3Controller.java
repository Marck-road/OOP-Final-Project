package application;

import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Level3Controller extends LevelController{
	
	@FXML ImageView you;
	@FXML private Line l1;

	public void initialize(Scene scene, LevelTransition levelTransition) {	
		Rectangle rectangle = new Rectangle(100, 10);
			
		PathTransition transition = new PathTransition();
		transition.setNode(l1);
		transition.setDuration(Duration.seconds(3));
		transition.setPath(rectangle);
		transition.setCycleCount(PathTransition.INDEFINITE);
		transition.play();
			
		System.out.println("Initializing Level 3 Controller");
        super.initialize(scene, levelTransition);
        
     
	}
}
