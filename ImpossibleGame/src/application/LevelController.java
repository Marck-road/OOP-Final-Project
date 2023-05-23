package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Bounds;



public class LevelController {
	private Rat rat;
	boolean levelCompleted = false;
	private List<Line> lines = new ArrayList<>();
	MediaPlayer mediaPlayer;
	private LevelTransition levelTransition;
	private boolean controlsEnabled = true;
	@FXML ImageView you;
	@FXML ImageView cheese;
	@FXML AnchorPane rootPane;
	
	public void initialize(Scene scene, LevelTransition levelTransition) {
		this.levelTransition = levelTransition;
	    rat = new Rat(you);
	    System.out.println("\nDisplaying all: ");

		    you.getScene().setOnKeyPressed(event -> {
		    	 if (!controlsEnabled) {
		                return; // Ignore key presses if controls are disabled
	            }
		    	 
		        KeyCode keyCode = event.getCode();
		        if (keyCode == KeyCode.LEFT) {
		            rat.moveLeft();
		        } else if (keyCode == KeyCode.RIGHT) {
		        	rat.moveRight();
		        } else if (keyCode == KeyCode.SPACE) {
		            rat.goForth(lines);
		            
		        }else if (keyCode == KeyCode.DOWN) {
		            rat.turnAround();
		        } else if(keyCode == KeyCode.E) {
		        	handleCollisionWithCheese();
	        	} else if (keyCode == KeyCode.R) {
	            		restartLevel();
	        	}
		        
		    });
	    
	    

	    rat.getImageView().boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue.intersects(cheese.getBoundsInParent())) {
	        	rat.stopMovement();
	            handleCollisionWithCheese();
	        }
	    });
	    
	    
	    rat.getImageView().boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
	        if (areYouDead()) {
            	System.out.println("You dead");
            	controlsEnabled = false;
	            handleDeath();
	        }
	    });
	}
	
	public boolean isLevelCompleted() {
    
		return levelCompleted;
    }
	
	public void handleCollisionWithCheese() {
		System.out.println("Completed Working");
		rat.stopMovement();
		controlsEnabled = false;
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
	
	private void restartLevel() {
		levelTransition.resetLevel();
	}
	
	protected Rat getRat() {
		return rat;
	}
	
	private boolean areYouDead() {
		if (this.rat.isRatImageRemoved()) {
            return false;
        }
		
		
	    List<Node> nodes = new ArrayList<>();
	    collectSpikes(you.getScene().getRoot(), nodes);
	    
	    Bounds bounds = you.getBoundsInParent();
	    double hitboxWidth = bounds.getWidth() * 0.5; 
	    double hitboxHeight = bounds.getHeight() * 0.5;
	    Shape imageShape = new Rectangle(bounds.getMinX(), bounds.getMinY(), hitboxWidth, hitboxHeight);

	    for (Node node : nodes) {
	        if (node instanceof Polygon) {
	        	Polygon spike = (Polygon) node;
	            Bounds spikeBounds = spike.getBoundsInParent();
	            Shape spikeShape = new javafx.scene.shape.Line(spikeBounds.getMinX(), spikeBounds.getMinY(), spikeBounds.getMaxX(), spikeBounds.getMaxY());
	            Shape intersect = Shape.intersect(spikeShape, imageShape);
	            if (spikeBounds.intersects(bounds) && !intersect.getBoundsInLocal().isEmpty()) {
	                
	            	System.out.println(spike.getId());
	            	
	            	return true;
	            }
	        }
	    }


	    return false;
	}
	
	public void handleDeath(){
		rat.stopMovement();
    	controlsEnabled = false;

        System.out.println("Completed Working Death");
        String musicFile = "src/sounds/Death.mp3"; // Replace with your audio file's path
		Media sound = new Media(new File(musicFile).toURI().toString());

		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.05);
		mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.dispose());
		mediaPlayer.play();
		
		String deadgePath = "file:src/images/deadge.png"; // Replace with the correct path to your image file
	    Image deadgeImage = new Image(deadgePath);
	    you.setImage(deadgeImage);
	    levelCompleted = false;
	    
	    PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
	    delay.setOnFinished(event -> restartLevel());
	    delay.play();
	}

	 private void collectSpikes(Node node, List<Node> nodes) {
		    if (node instanceof Polygon) {
		        nodes.add(node);
		    }
	
		    if (node instanceof Parent) {
		        Parent parent = (Parent) node;
		        for (Node child : parent.getChildrenUnmodifiable()) {
		            collectSpikes(child, nodes); // Recursively collect spikes from child nodes
		        }
		    }
			
		}
	
	
	
}
