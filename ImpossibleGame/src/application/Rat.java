package application;

import javafx.scene.image.ImageView;


public class Rat {
	
	private ImageView imageView;
	private double rotationAngle = 0.0;
	
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

	
}
