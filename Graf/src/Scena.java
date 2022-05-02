import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Scena {
    double Scala = 1;
    double Fitwysokosc;
    double Fitszetokosc;
    int ktury_algorytm;
    Scanner skan;
    int x,y;
    Widok wynik;
    Graf graf;
    Dijkstra algorytm1;
    int co_narysowac;
    @FXML
    private RadioButton rysuj_droge;

    @FXML
    private RadioButton rysuj_graf;
    @FXML
    private GridPane Calosc;

    @FXML
    private ToggleGroup Droga;

    @FXML
    private RadioButton Fibo;

    @FXML
    private Label Komunikator;

    @FXML
    private RadioButton Lista;

    @FXML
    private Pane Plansza;

    @FXML
    private Button Startdij;

    @FXML
    private ToggleGroup algorytm;

    @FXML
    private TextField do_kond;

    @FXML
    private ImageView obraz;

    @FXML
    private RadioButton poczatek;

    @FXML
    private ScrollPane pole;

    @FXML
    private TextField z_kond;

    @FXML
    private TextField zawartosc;

    @FXML
    void clikniencie(MouseEvent event) {
        System.out.println(event.getX());
        System.out.println( event.getY());
        znajdz_wieszcholek(event.getX(), event.getY());
        Image toczos = SwingFXUtils.toFXImage(wynik.pomalowane(), null);
        obraz.setImage(toczos);

        System.out.println("jestes we mnie");
    }
    @FXML
    void minus(ActionEvent event) {
        if (Scala>0.3){
            Scala-=0.2;
            obraz.setFitHeight(Fitwysokosc*Scala);
            obraz.setFitWidth(Fitszetokosc*Scala);
            Plansza.setMaxSize( Fitszetokosc*Scala,Fitwysokosc*Scala);
            Plansza.setMinSize(Fitszetokosc*Scala,Fitwysokosc*Scala);
        }
    }

    @FXML
    void plus(ActionEvent event) {
        Scala+=0.2;
        Plansza.setMaxSize(Fitszetokosc*Scala,Fitwysokosc*Scala);
        Plansza.setMinSize(Fitszetokosc*Scala,Fitwysokosc*Scala);
        obraz.setFitHeight(Fitwysokosc*Scala);
        obraz.setFitWidth(Fitszetokosc*Scala);
    }
    @FXML
    void co_rysuje(ActionEvent event) {
        if(rysuj_graf.isSelected()){
            co_narysowac = 0;
        }else if(rysuj_droge.isSelected()){
            co_narysowac = 1;
        }
    }
    
    @FXML
    void Wybur(ActionEvent event) {
        if(Lista.isSelected()){
            ktury_algorytm = 1;
        }else if(Fibo.isSelected()){
            ktury_algorytm = 0;
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
    private void znajdz_wieszcholek(double x,double y){
        int pozycja_x;
        int pozycja_y;
        pozycja_x = (int) Math.floor(x/(this.Fitszetokosc*this.Scala/this.x));
        pozycja_y = (int) Math.floor(y/(this.Fitwysokosc*this.Scala/this.y));
        if (co_narysowac ==0){
            graf.zeruj_dane();
            algorytm1.rozwiarz(pozycja_x+pozycja_y*this.x);
            this.wynik.paint();
        }else if (co_narysowac ==1 ){
            graf.okresl_scieszke(pozycja_x+pozycja_y*this.x);
            this.wynik.pomaluj_wynik();
        }
        System.out.println(pozycja_x+"    "+pozycja_y);
        return ;
    }
    @FXML
    void Startdij(ActionEvent event) {
        Czytacz dane_pliku = new Czytacz(skan);
        this.y = dane_pliku.czytaj_int();
        this.x = dane_pliku.czytaj_int();
        System.out.println(this.y +" i "+ this.x);
        this.graf = new Graf();
        this.graf.dodaj_graf(x, y);
        
        
        dane_pliku.wypelnij(graf, x,y);
        
        //Dijkstra algorytm = new Dijkstra(graf, x);
        if (ktury_algorytm == 0){
            algorytm1 = new Fibonacci();
        }else{
            algorytm1 = new Kolejka();
        }
        
        algorytm1.dodaj_dane(graf, x);
        System.out.println("czyto aj");
        //if(algorytm.czy_istnieje(poczontek, szukane, x*y)){
           // System.out.println("Nie ma połaczenia miedzy elementami");
           // return;
        //}
        System.out.println(this.y +" i "+ this.x);
        /*for (i=0;i<x*y;i++){
            System.out.println(graf[i].waga);
        }*/
        //algorytm1.okresl_scieszke(szukane);
        //new Obraz(x,y,graf);
        //Pisarz generator = new Pisarz("plikawypisany1.txt");
        //generator.napisz(x, y);
        int rozmiar = 8;
        int Dx= (int) Math.round(pole.getWidth());
        int Dy= (int) Math.round(pole.getHeight());
        if (Dx < rozmiar*x*3/2){
            Dx = rozmiar*x*3/2;
        }
        if (Dy < rozmiar*y*3/2){
            Dy = rozmiar*y*3/2;
        }
        
        wynik =  new Widok(x, y, graf, Dx, Dy,rozmiar);
        System.out.println(this.y +" i "+ this.x);
        Image toczos = SwingFXUtils.toFXImage(wynik.pomalowane(), null);
        obraz.setFitHeight(Dy);
        obraz.setFitWidth(Dx);
        Plansza.setMaxSize(Dx, Dy);
        Plansza.setMinSize(Dx, Dy);
        
        obraz.setImage(toczos);
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