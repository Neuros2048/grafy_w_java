import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
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
        
        String [] dane_wejsciowe = new String[3];
        dane_wejsciowe[0]="Graf/Pliki_textowe/javatest.txt";
        dane_wejsciowe[1]="10000";
        dane_wejsciowe[2]="7000";
        int poczontek = Integer.parseInt(dane_wejsciowe[1]);
        int szukane = Integer.parseInt(dane_wejsciowe[2]);
        Czytacz dane_pliku = new Czytacz(new File(dane_wejsciowe[0]));
        //Czytacz dane_pliku = new Czytacz(new File("mygraph.txt"));
        int x,y;
        
        y = dane_pliku.czytaj_int();
        x = dane_pliku.czytaj_int();
        System.out.println(y +" i "+ x);
        Wieszcholek[] graf = new Wieszcholek[x*y];
        
        int i;
        for (i=0;i<x*y;i++){
            graf[i] = new Wieszcholek();
        }
        dane_pliku.wypelnij(graf, x,y);
        //Dijkstra algorytm = new Dijkstra(graf, x);
        Dijkstra algorytm1 = new Fibonacci();
        algorytm1.dodaj_dane(graf, x);
        //if(algorytm.czy_istnieje(poczontek, szukane, x*y)){
           // System.out.println("Nie ma połaczenia miedzy elementami");
           // return;
        //}
        System.out.println("start");
        Long times = System.currentTimeMillis();
        algorytm1.rozwiarz(poczontek);
        Long timek = System.currentTimeMillis() - times;
        System.out.println(timek);
        /*for (i=0;i<x*y;i++){
            System.out.println(graf[i].waga);
        }*/
        algorytm1.okresl_scieszke(szukane);
        System.out.println(graf[szukane].waga);
        //new Obraz(x,y,graf);
        //Pisarz generator = new Pisarz("plikawypisany1.txt");
        //generator.napisz(x, y);
        Widok wynik =  new Widok(x, y, graf, 1600, 1000);
        Image toczos = SwingFXUtils.toFXImage(wynik.pomalowane(), null);
        obraz.setImage( toczos);
        Fitwysokosc = obraz.getFitHeight();
        Fitszetokosc = obraz.getFitWidth();
    }

}
