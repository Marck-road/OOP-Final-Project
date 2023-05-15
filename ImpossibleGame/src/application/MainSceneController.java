package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class MainSceneController {

	@FXML ImageView view1;
	@FXML ImageView view2;
	private Scene startScreen;
	
	public void setStartScreen(Scene startScreen) {
        this.startScreen = startScreen;
	    goToNextLevel();

    }
	
	public void goToNextLevel() {
        startScreen.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE) {
                try {
                    FXMLLoader upNextLvl = new FXMLLoader(getClass().getResource("Level1.fxml"));
                    Parent root = upNextLvl.load();
                    Scene newScene = new Scene(root);
                    
                    Stage stage = (Stage) startScreen.getWindow();
                    stage.setScene(newScene);
    				stage.show();

                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
	
	// Event Listener on Button.onAction
}


