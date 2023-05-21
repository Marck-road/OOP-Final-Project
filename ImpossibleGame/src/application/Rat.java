package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.PixelReader;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;



public class Rat {
	
	private ImageView imageView;
	private double rotationAngle = 0.0;
	private double xVelocity = 0.0;
    private double yVelocity = 0.0;
    private double speed = 3.0;
    private boolean movingForward = false;
    private Timeline timeline = null; 
    private boolean isMoving = false;
	
	public Rat(ImageView imageView) {
        this.imageView = imageView;
    }
	
	
	
	public void moveLeft() {
		rotationAngle -= 5.0;
		imageView.setRotate(rotationAngle);
	}
	
	public void moveRight() {
		rotationAngle += 5.0;
		imageView.setRotate(rotationAngle);
	}
	
	public void turnAround() {
		rotationAngle -= 180.0;
		imageView.setRotate(rotationAngle);
	}
	
	public void goForth() {
		
		if(!isMoving) {
			isMoving = true;
		
	    
	        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
	        xVelocity = Math.cos(Math.toRadians(rotationAngle)) * speed;
	        yVelocity = Math.sin(Math.toRadians(rotationAngle)) * speed;

	        // Update the position of the imageView
	        updatePosition(xVelocity, yVelocity);

	        // Check if the rat hits the edge of the screen
	        double screenWidth = imageView.getScene().getWidth();
	        double screenHeight = imageView.getScene().getHeight();

	        if (imageView.getX() <= -10 || imageView.getX() >= 538) {
	            // Hit the left or right edge, bounce back horizontally
	            System.out.println(screenWidth + " Left " + screenHeight + " " + imageView.getY() + " " + imageView.getX());
	            xVelocity *= -1;
	            rotationAngle += 75.0;
	            imageView.setRotate(rotationAngle);
	        }

	        if (imageView.getY() <= -340 || imageView.getY() >= 20) {
	            // Hit the top or bottom edge, bounce back vertically
	            System.out.println(screenWidth + " Up " + screenHeight + " " + imageView.getY() + " " + imageView.getX());
	            rotationAngle += 75.0;
	            imageView.setRotate(rotationAngle);
	            yVelocity *= -1;
	        }

	        // Update the position again after potential bounce
	        updatePosition(xVelocity, yVelocity);
	        
	        if(checkCollision()) {
		    	timeline.stop();
                isMoving = false; // Reset the flag when stopped
		    	return;
		    }
	        
	        
	    }));

	    // Set the timeline to loop indefinitely
	    timeline.setCycleCount(Animation.INDEFINITE);
	    // Start the timeline
	    timeline.play();
	    
		}
	}
	
	public void bounceBack() {
        xVelocity *= -1;
        yVelocity *= -1;
        updatePosition(xVelocity, yVelocity);
    }
	
	 private void updatePosition(double deltaX, double deltaY) {
        double newX = imageView.getX() + deltaX;
        double newY = imageView.getY() + deltaY;
        
        // Set the new position of the imageView
        imageView.setX(newX);
        imageView.setY(newY);
        
    }
	 
	 private boolean checkCollision() {
		    List<Node> nodes = new ArrayList<>();
		    collectLines(imageView.getScene().getRoot(), nodes); // Collect all Line nodes in the scene graph

		    Rectangle hitBox = new Rectangle(imageView.getX(), imageView.getY(), imageView.getFitWidth(), imageView.getFitHeight());

		    for (Node node : nodes) {
		        if (node instanceof Line) {
		            Line currentLine = (Line) node;

		            if (hitBox.getBoundsInParent().intersects(currentLine.getBoundsInParent())) {
		                // Rat has collided with a line, stop moving
		                System.out.println("You hit a line!" + currentLine.getId());
		                timeline.stop();
		                return true;
		            }
		        }
		    }

		    return false;
		}

		private void collectLines(Node node, List<Node> nodes) {
		    if (node instanceof Line) {
		        nodes.add(node);
		    }

		    if (node instanceof Parent) {
		        Parent parent = (Parent) node;
		        for (Node child : parent.getChildrenUnmodifiable()) {
		            collectLines(child, nodes); // Recursively collect lines from child nodes
		        }
		    }
		}




	
}
