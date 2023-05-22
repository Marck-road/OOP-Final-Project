package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class LevelController {
	private Rat rat;
	boolean levelCompleted = false;
	private List<Line> lines = new ArrayList<>();
	MediaPlayer mediaPlayer;
	private Scene startScreen;
	private LevelTransition levelTransition;

	@FXML ImageView you;
	@FXML ImageView cheese;
	@FXML AnchorPane rootPane;
	
	public void initialize(Scene scene, LevelTransition levelTransition) {
		this.levelTransition = levelTransition;
	    rat = new Rat(you);
	    System.out.println("\nDisplaying all: ");

	    you.getScene().setOnKeyPressed(event -> {
	        KeyCode keyCode = event.getCode();
	        if (keyCode == KeyCode.LEFT) {
	            rat.moveLeft();
	        } else if (keyCode == KeyCode.RIGHT) {
	        	rat.moveRight();
	        } else if (keyCode == KeyCode.SPACE) {
	            rat.goForth(lines);
	            
	        }else if (keyCode == KeyCode.DOWN) {
	            rat.turnAround();
	        }
	        
	    });
	    
	    

	    rat.getImageView().boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue.intersects(cheese.getBoundsInParent())) {
	        	rat.stopMovement();
	            handleCollisionWithCheese();
	        }
	    });
	}
	
	public boolean isLevelCompleted() {
    
		return levelCompleted;
    }
	
	public void handleCollisionWithCheese() {
		rat.stopMovement();
		
        System.out.println("Completed Working");
        String musicFile = "src/sounds/Victory.mp3"; // Replace with your audio file's path
		Media sound = new Media(new File(musicFile).toURI().toString());

		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.05);
		mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.dispose());
		mediaPlayer.play();
		
		String ratCheesePath = "file:src/images/ratCheese.png"; // Replace with the correct path to your image file
	    Image ratCheeseImage = new Image(ratCheesePath);
	    cheese.setImage(ratCheeseImage);

	    rootPane.getChildren().remove(you);
	    levelCompleted = true;
	    
	    PauseTransition delay = new PauseTransition(Duration.seconds(3));
	    delay.setOnFinished(event -> loadNextLevel());
	    delay.play();
    }
	
	private void loadNextLevel() {
		levelTransition.goToNextLevel(); 
	}
	
	
	
}
