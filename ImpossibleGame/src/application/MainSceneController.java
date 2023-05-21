package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.System.Logger.Level;


public class MainSceneController {

	@FXML ImageView view1;
	@FXML ImageView view2;
	private Scene startScreen;
	
	int currentLvl = 0;
    private LevelController currentLevelController;

	
	public void setStartScreen(Scene startScreen) {
        this.startScreen = startScreen;
	    goToNextLevel();

    }
	
	private void goToNextLevel() {
        startScreen.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE) {
            	if (currentLevelController == null || currentLevelController.isLevelCompleted()) {
                    // Move to the next level
                    currentLvl++;
                    loadLevel(currentLvl);
                }
            }
        });
        
    }
	
	private void loadLevel(int levelNumber) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Level" + levelNumber + ".fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            
            Level1Controller levelController = loader.getController();
            levelController.initialize(newScene);
            
            Stage stage = (Stage) startScreen.getWindow();
            stage.setScene(newScene);
			stage.show();
			
//			currentLevelController = loader.getController();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


