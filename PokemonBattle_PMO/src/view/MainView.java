package view;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView {
	
	private static MainView mainView= new MainView();
	
	public static void changeScene(Stage currentStage, String fxml) throws IOException {
	
    	currentStage.setScene(new Scene(FXMLLoader.load(mainView.getClass().getResource(fxml))));
	}
	

}

