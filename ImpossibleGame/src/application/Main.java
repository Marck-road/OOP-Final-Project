	package application;
		
	import java.io.File;

//import java.awt.Image;
	
	import javafx.application.Application;
	import javafx.fxml.FXMLLoader;
	import javafx.stage.Stage;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.image.Image;
	import javafx.scene.image.ImageView;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.AnchorPane;
	
	
	public class Main extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
	
				Image image1 = new Image(new File("src/images/logo.png").toURI().toString());
				MainSceneController controller = loader.getController();
				ImageView view1 = controller.view1;
	
				view1.setImage(image1);
	
				primaryStage.setScene(scene);
				primaryStage.show();
	
	
	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
	}
