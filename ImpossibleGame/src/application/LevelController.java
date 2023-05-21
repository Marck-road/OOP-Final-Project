package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Line;

public class LevelController {
	private Rat rat;
	boolean levelCompleted = false;
	private List<Line> lines = new ArrayList<>();

	@FXML ImageView you;
	
	public void initialize(Scene scene) {
	    rat = new Rat(you);
	    System.out.println("\nDisplaying all: ");
	    collectLinesFromFXML();

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
	}
	
	public boolean isLevelCompleted() {
        return true;
    }
	
	private void collectLinesFromFXML() {
	    Scene scene = you.getScene();
	    Parent root = scene.getRoot();
	    collectLines(root);
	}
	
	private void collectLines(Node node) {
	    if (node instanceof Line) {
	        Line line = (Line) node;
	        lines.add(line);
	    }

	    if (node instanceof Parent) {
	        Parent parent = (Parent) node;
	        for (Node child : parent.getChildrenUnmodifiable()) {
	            collectLines(child); // Recursively collect lines from child nodes
	        }
	    }
	}
}
