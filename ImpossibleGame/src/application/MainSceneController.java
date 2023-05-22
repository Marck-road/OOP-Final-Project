package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.lang.System.Logger.Level;


public class MainSceneController implements LevelTransition{

	@FXML ImageView view1;
	@FXML ImageView view2;
	private Scene startScreen;
	
	int currentLvl = 0;
    private LevelController currentLevelController;

	
	public void setStartScreen(Scene startScreen) {
        this.startScreen = startScreen;
	    goToNextLevel();

    }
	
	public void goToNextLevel() {
		
		if(currentLvl == 0) {
			startScreen.setOnKeyPressed(e -> {
	            if(e.getCode() == KeyCode.SPACE) {
	            	if (currentLevelController == null || currentLevelController.isLevelCompleted()) {
	                    // Move to the next level
	                    currentLvl++;
	                    System.out.println("Loaded Level " + currentLvl);
	                    loadLevel(currentLvl);
	                }
	            }
	        });
		}
		
		else {
			currentLvl++;
            System.out.println("Loaded Level " + currentLvl);
            loadLevel(currentLvl);
		}
        
    }
	
	private void loadLevel(int levelNumber) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Level" + levelNumber + ".fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            
            LevelController levelController;
        	levelController = loader.getController();
            levelController.initialize(newScene, this);
            
            Window currentWindow = startScreen.getWindow();
            Stage stage = (Stage) currentWindow;
            stage.setScene(newScene);
            stage.show();
            
//            currentWindow.hide();
            
            startScreen = newScene;

			            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	public void resetLevel() {
		System.out.println("Resetted Level " + currentLvl);
        loadLevel(currentLvl);
	}

}


