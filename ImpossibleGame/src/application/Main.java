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
	
	import javafx.scene.media.Media;
	import javafx.scene.media.MediaPlayer;

	
	public class Main extends Application {
		private MediaPlayer mediaPlayer;
		@Override
		public void start(Stage primaryStage) {
			try {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				
				String musicFile = "src/sounds/GameMusic.mp3"; // Replace with your audio file's path
				Media sound = new Media(new File(musicFile).toURI().toString());

				MediaPlayer mediaPlayer = new MediaPlayer(sound);
				mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				mediaPlayer.setVolume(0.05);

				mediaPlayer.play();
				
				Image image1 = new Image(new File("src/images/tempLogo.jpg").toURI().toString());
				MainSceneController controller = loader.getController();
				ImageView view1 = controller.view1;
				ImageView view2 = controller.view2;

				Image logo = new Image(new File("src/images/rat-spinning.gif").toURI().toString());
	
				view1.setImage(image1);
				view2.setImage(logo);
				
				controller.setStartScreen(scene);
				
				primaryStage.setScene(scene);
				primaryStage.show();
	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
	    public void stop() throws Exception {
	        super.stop();
	        mediaPlayer.stop();
	    }
		
		public static void main(String[] args) {
			launch(args);
		} 
	}
