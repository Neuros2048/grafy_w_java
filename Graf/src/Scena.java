import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Scena {
    double Scala = 1;
    double Fitwysokosc;
    double Fitszetokosc;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView obraz;
    @FXML
    private ScrollPane pole;

    @FXML
    void minus(ActionEvent event) {
        if (Scala>1){
            Scala-=0.2;
            System.out.println(obraz.getFitHeight());
            obraz.setFitHeight(Fitwysokosc*Scala);
            obraz.setFitWidth(Fitszetokosc*Scala);
        }
        //obraz.setScaleX(Scala);
        //obraz.setScaleY(Scala);
        //obraz.setX(0);
       // obraz.setY(0);
        //obraz.resize(obraz.getX()*Scala, obraz.getY()*Scala);
        //pole.setHmax(Scala);
        //pole.setVmax(Scala);
    }

    @FXML
    void plus(ActionEvent event) {
        //obraz.setFitWidth(obraz.getFitWidth()*1.2);
        
        Scala+=0.2;
        System.out.println(obraz.getFitHeight());
        obraz.setFitHeight(Fitwysokosc*Scala);
        obraz.setFitWidth(Fitszetokosc*Scala);
        //obraz.resize(arg0, arg1);
        /*
        obraz.setScaleX(Scala);
        obraz.setScaleY(Scala);
        obraz.setLayoutX(0);
        obraz.setLayoutY(0);
        //pole.requestFocus();*/
        //pole.requestLayout();
        //obraz.resize(obraz.getX()*Scala, obraz.getY()*Scala);
        //pole.setHmax(Scala);
       // pole.setVmax(Scala);
    }
    
    @FXML
    private TextField zawartosc;

    @FXML
    void wcisnienty(ActionEvent event) {
        System.out.println(zawartosc.getText());

    }

    @FXML
    void initialize() {
        assert zawartosc != null : "fx:id=\"zawartosc\" was not injected: check your FXML file 'Scena.fxml'.";
        //obraz.setImage(new Image());
        Fitwysokosc = obraz.getFitHeight();
        Fitszetokosc = obraz.getFitWidth();
    }

}
