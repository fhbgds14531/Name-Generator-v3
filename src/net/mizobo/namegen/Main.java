package net.mizobo.namegen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage mainStage;
	static NG generator;

	@Override
	public void start(Stage primaryStage) throws Exception{
		generator = new NG();
		mainStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("generator.fxml"));
		mainStage.setTitle("Name Generator");
		mainStage.setScene(new Scene(root));
		mainStage.sizeToScene();
		mainStage.setResizable(false);
		mainStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
