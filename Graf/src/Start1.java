
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start1 extends Application {
    public static void main(String[] args) {
        
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("Scena.fxml"));
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Logo.png")));
        primaryStage.show();
        }catch(IOException e){
            System.out.println("błąd");
        }
    }
    
}