
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.event.EventHandler;

public class Start1 extends Application {
    public static void main(String[] args) {
        
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("Scena.fxml"));
        if (root==null){
            System.out.println("mnie działa");
        }else{
            System.out.println("niby działa");
        }
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        }catch(IOException e){
            System.out.println("błąd");
        }
    }
    
}