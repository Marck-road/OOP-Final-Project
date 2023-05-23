package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.util.Duration;



public class Rat {
	
	private ImageView imageView;
	private double rotationAngle;
	private double xVelocity = 0.0;
    private double yVelocity = 0.0;
    private double speed = 3.0;
    private Timeline timeline = null; 
    private boolean isMoving = false;
	
	public Rat(ImageView imageView) {
        this.imageView = imageView;
        this.rotationAngle = imageView.getRotate();

    }
	
	
	public void moveLeft() {
		if (isRatImageRemoved()) {
            return;
        }
		
		rotationAngle -= 5.0;
		imageView.setRotate(rotationAngle);
		System.out.println(imageView.getX() + " " + imageView.getY());
		
//		if(checkCollision()) {
//			rotationAngle += 5.0;
//			imageView.setRotate(rotationAngle);
//			
//			System.out.println("Working");
//			return;
//		}
	}
	
	public void moveRight() {
		if (isRatImageRemoved()) {
            return;
        }
		
		
		rotationAngle += 5.0;
		imageView.setRotate(rotationAngle);
		
//		if(checkCollision()) {
//			rotationAngle -= 5.0;
//			imageView.setRotate(rotationAngle);
//			System.out.println("Working");
//			return;
//		}
	}
	
	public void turnAround() {
		if (isRatImageRemoved()) {
            return;
        }
		
		rotationAngle -= 180.0;
		imageView.setRotate(rotationAngle);
	}
	
	public void goForth(List<Line> lines) {
		
		
		if (!isMoving && imageView != null && imageView.getScene() != null) {
			isMoving = true;
		
	    
	        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
	        xVelocity = Math.cos(Math.toRadians(rotationAngle)) * speed;
	        yVelocity = Math.sin(Math.toRadians(rotationAngle)) * speed;

	        // Update the position of the imageView
	        updatePosition(xVelocity, yVelocity);
	        if (isRatImageRemoved()) {
	            return;
	        }
	        // Check if the rat hits the edge of the screen
	 
	        double sceneWidth = imageView.getScene().getWidth();
	        double sceneHeight = imageView.getScene().getHeight();

	        if (imageView.getBoundsInParent().getMinX() <= 0 || imageView.getBoundsInParent().getMaxX() >= sceneWidth) {
	            // Hit the left or right edge, bounce back horizontally
	            xVelocity *= -1;
	            rotationAngle += 90.0;
	            imageView.setRotate(rotationAngle);
	        }

	        if (imageView.getBoundsInParent().getMinY() <= 0 || imageView.getBoundsInParent().getMaxY() >= sceneHeight) {
	            // Hit the top or bottom edge, bounce back vertically
	            rotationAngle += 90.0;
	            imageView.setRotate(rotationAngle);
	            yVelocity *= -1;
	        }


	        // Update the position again after potential bounce
	        updatePosition(xVelocity, yVelocity);
	        
	        if(checkCollision()) {
		    	timeline.stop();
                isMoving = false; // Reset the flag when stopped
                
                rotationAngle -= 180.0;
        		imageView.setRotate(rotationAngle);
        		
        		while(checkCollision()) {
        			double stepDistance = 2.0;
            		double newX = imageView.getX() + Math.cos(Math.toRadians(rotationAngle)) * stepDistance;
            		double newY = imageView.getY() + Math.sin(Math.toRadians(rotationAngle)) * stepDistance;

            		imageView.setX(newX);
            		imageView.setY(newY);
        		}
        		
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
		 	if (isRatImageRemoved()) {
	            return false;
	        }
		 
		    List<Node> nodes = new ArrayList<>();
		    collectLines(imageView.getScene().getRoot(), nodes);

		    Bounds bounds = imageView.getBoundsInParent();
		    double hitboxWidth = bounds.getWidth() * 0.5; 
		    double hitboxHeight = bounds.getHeight() * 0.5;
		    Shape imageShape = new Rectangle(bounds.getMinX(), bounds.getMinY(), hitboxWidth, hitboxHeight);

		    for (Node node : nodes) {
		        if (node instanceof Line) {
		            Line line = (Line) node;
		            Bounds lineBounds = line.getBoundsInParent();
		            Shape lineShape = new javafx.scene.shape.Line(lineBounds.getMinX(), lineBounds.getMinY(), lineBounds.getMaxX(), lineBounds.getMaxY());
		            Shape intersect = Shape.intersect(lineShape, imageShape);
		            if (lineBounds.intersects(bounds) && !intersect.getBoundsInLocal().isEmpty()) {
		                
		            	System.out.println(line.getId());
		            	
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
	 
	public ImageView getImageView() {
        return imageView;
    }
	 
	
	public void stopMovement() {
		if(timeline != null) {
			timeline.stop();
		}
	}
	
	public boolean isRatImageRemoved() {
        return imageView == null || imageView.getParent() == null;
    }




}
