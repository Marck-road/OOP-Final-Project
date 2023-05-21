package application;

import javafx.scene.Node;
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
	    int x;
	    
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
		    	return;
		    }
	        
	        
	    }));

	    // Set the timeline to loop indefinitely
	    timeline.setCycleCount(Animation.INDEFINITE);
	    // Start the timeline
	    timeline.play();
	    
	    
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
		    List<Node> lines = imageView.getScene().getRoot().getChildrenUnmodifiable();

		    Rectangle hitBox = new Rectangle(imageView.getX(), imageView.getY(), imageView.getFitWidth(), imageView.getFitHeight());

		    for (Node line : lines) {
		        if (line instanceof Line) {
		            Line currentLine = (Line) line;

		            if (hitBox.getBoundsInParent().intersects(currentLine.getBoundsInParent())) {
		                // Rat has collided with a line, stop moving
		                System.out.println("You hit a line!");
		                timeline.stop();
		                return true;
		            }
		        }
		    }

		    return false;
		}



	
}
