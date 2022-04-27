import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Scena {
    double Scala = 1;
    double Fitwysokosc;
    double Fitszetokosc;
    int algorytm;
    Scanner skan;
    @FXML
    private GridPane Calosc;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private RadioButton Fibo;

    @FXML
    private Label Komunikator;

    @FXML
    private RadioButton Lista;

    @FXML
    private Button Startdij;

    
    @FXML
    private TextField do_kond;

    @FXML
    private ImageView obraz;

    @FXML
    private ScrollPane pole;

    @FXML
    private TextField z_kond;
    @FXML
    void minus(ActionEvent event) {
        if (Scala>0.3){
            Scala-=0.2;
            obraz.setFitHeight(Fitwysokosc*Scala);
            obraz.setFitWidth(Fitszetokosc*Scala);
        }
    }

    @FXML
    void plus(ActionEvent event) {
        Scala+=0.2;
        obraz.setFitHeight(Fitwysokosc*Scala);
        obraz.setFitWidth(Fitszetokosc*Scala);
    }
    
    @FXML
    private TextField zawartosc;
    @FXML
    void Wybur(ActionEvent event) {
        if(Lista.isSelected()){
            algorytm = 1;
        }else if(Fibo.isSelected()){
            algorytm = 0;
        }
    }
    @FXML
    void wcisnienty(ActionEvent event) {

        try {
            Scanner skan = new Scanner(new File("Graf/Pliki_textowe/"+zawartosc.getText()));
            Komunikator.setText("Udało się odczytać plik");
            this.skan = skan;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Komunikator.setText("Nie udało się odczytać pliku");
            this.skan = null;
        }

    }
    @FXML
    void Startdij(ActionEvent event) {


        int poczontek = Integer.parseInt(z_kond.getText());
        int szukane = Integer.parseInt(do_kond.getText());
        Czytacz dane_pliku = new Czytacz(skan);
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
        Dijkstra algorytm1;
        if (algorytm == 0){
            algorytm1 = new Fibonacci();
        }else{
            algorytm1 = new Kolejka();
        }
        
        algorytm1.dodaj_dane(graf, x);
        //if(algorytm.czy_istnieje(poczontek, szukane, x*y)){
           // System.out.println("Nie ma połaczenia miedzy elementami");
           // return;
        //}
        algorytm1.rozwiarz(poczontek);
        /*for (i=0;i<x*y;i++){
            System.out.println(graf[i].waga);
        }*/
        algorytm1.okresl_scieszke(szukane);
        //new Obraz(x,y,graf);
        //Pisarz generator = new Pisarz("plikawypisany1.txt");
        //generator.napisz(x, y);
        int rozmiar = 8;
        int Dx= (int) Math.round(pole.getWidth());
        int Dy= (int) Math.round(pole.getHeight());
        if (Dx < rozmiar*x*3/2){
            Dx = rozmiar*x*3/2;
        }
        if (Dy < rozmiar*x*3/2){
            Dy = rozmiar*x*3/2;
        }
        Widok wynik =  new Widok(x, y, graf, Dx, Dy,rozmiar);
        Image toczos = SwingFXUtils.toFXImage(wynik.pomalowane(), null);
        obraz.setFitHeight(Dx);
        obraz.setFitWidth(Dy);
        obraz.setImage( toczos);
        Fitwysokosc = obraz.getFitHeight();
        Fitszetokosc = obraz.getFitWidth();
    }
    @FXML
    void initialize() {
        assert zawartosc != null : "fx:id=\"zawartosc\" was not injected: check your FXML file 'Scena.fxml'.";
        /*
        String [] dane_wejsciowe = new String[3];
        dane_wejsciowe[0]="Graf/Pliki_textowe/javatest.txt";
        dane_wejsciowe[1]="10000";
        dane_wejsciowe[2]="7000";
        int poczontek = Integer.parseInt(dane_wejsciowe[1]);
        int szukane = Integer.parseInt(dane_wejsciowe[2]);
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
        }
        algorytm1.okresl_scieszke(szukane);
        System.out.println(graf[szukane].waga);
        //new Obraz(x,y,graf);
        //Pisarz generator = new Pisarz("plikawypisany1.txt");
        //generator.napisz(x, y);
        int Dx=16000;
        int Dy=10000;
        Widok wynik =  new Widok(x, y, graf, Dx, Dy);
        Image toczos = SwingFXUtils.toFXImage(wynik.pomalowane(), null);
        obraz.setFitHeight(Dx);
        obraz.setFitWidth(Dy);
        obraz.setImage( toczos);
        Fitwysokosc = obraz.getFitHeight();
        Fitszetokosc = obraz.getFitWidth();*/
    }

}
