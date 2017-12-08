package app;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/app/resource/css/login.css");
		primaryStage.getIcons().add(new Image("/app/resource/tomato.png"));
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
