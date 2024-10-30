package view;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.type.PokemonType;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            BorderPane root = FXMLLoader.load(getClass().getResource("interface.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image(new FileInputStream("Pictures/pokeball.png")));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pokemon Battle");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
    	
    	PokemonType.generatePokemonType();
        launch(args);
        
    }
}