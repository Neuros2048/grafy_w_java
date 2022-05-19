
import algorytmy.*;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Scena {
    double Scala = 1;
    double Fitwysokosc;
    double Fitszetokosc;
    int ktury_algorytm;
    Czytacz dane_pliku;
    int x,y;
    Widok wynik;
    Graf graf;
    Algorytm_przechodzenia algorytm1;
    Algorytm_przechodzenia algorytm2;
    Algorytm_przechodzenia algorytm3;
    Pisarz generator;
    
   
    @FXML
    private GridPane Calosc;
    @FXML
    private RadioButton Fibo;
    @FXML
    private Label Komunikator;
    @FXML
    private Label KomunikatorGen;
    @FXML
    private Label KomunikatorZap;
    @FXML
    private RadioButton Lista;
    @FXML
    private Pane Plansza;
    @FXML
    private ToggleGroup algorytm;
    @FXML
    private ImageView obraz;
    @FXML
    private RadioButton poczatek;
    @FXML
    private ScrollPane pole;
    @FXML
    private TextField zawartosc;
    @FXML
    private Button GEN1;
    @FXML
    private Button GEN2; 
    @FXML
    private TextField MAXG1;
    @FXML
    private TextField MAXG2;
    @FXML
    private TextField MING1;
    @FXML
    private TextField MING2;
    @FXML
    private TextField NAZWAG2;
    @FXML
    private TextField XG1;
    @FXML
    private TextField XG2;
    @FXML
    private TextField YG1;
    @FXML
    private TextField YG2;
    @FXML
    private ImageView puste_mejsce;
    @FXML
    void Gen1W(ActionEvent event) {
        double max;
        double min;
        try {
        this.y = Integer.valueOf(YG1.getText()) ;
        this.x = Integer.valueOf(XG1.getText());
        max = Double.valueOf(MAXG1.getText());
        min = Double.valueOf(MING1.getText());
        } catch (java.lang.NumberFormatException e) {
            KomunikatorGen.setText("Podane wartości nie są liczbami");
            return;
        }
        if(this.x<1 || this.y <1 || max < min ){
            KomunikatorGen.setText("Podana wartości są nieodpowiednie");
            return;
        }
        this.graf = new Graf();
        this.graf.dodaj_graf(x, y);
        generator.napisz(max, min, graf);
        Scala = 1;
        wygeneruj_obraz();
        algorytm1.dodaj_dane(graf);
        algorytm2.dodaj_dane(graf);
        algorytm3.dodaj_dane(graf);
        KomunikatorGen.setText("Udało się wygenerować graf");
    }

    @FXML
    void Gen2W(ActionEvent event) {
        double max;
        double min;
        try {
        this.y = Integer.valueOf(YG2.getText()) ;
        this.x = Integer.valueOf(XG2.getText());
        max = Double.valueOf(MAXG2.getText());
        min = Double.valueOf(MING2.getText());
        } catch (java.lang.NumberFormatException e) {
            KomunikatorZap.setText("Podane wartości nie są liczbami");
            return;
        }
        if(this.x<1 || this.y <1 || max < min ){
            KomunikatorGen.setText("Podana wartości są nieodpowiednie");
            return;
        }
        if(generator.napisz(x,y, max, min,"Graf/Pliki_textowe/"+NAZWAG2.getText())){
            KomunikatorZap.setText("Udało się wygenerować plik");
            return;
        }
        KomunikatorZap.setText("Nie udało się wygenerować pliku");
    }
    @FXML
    void clikniencie(MouseEvent event) {
        
        boolean prawy_lewy;
        if(event.getButton() == MouseButton.PRIMARY){
            prawy_lewy = true;
        }else if(event.getButton()==MouseButton.SECONDARY){
            prawy_lewy = false;
        }else{
            return;
        }
        znajdz_wieszcholek(event.getX(), event.getY(),prawy_lewy);
        Image toczos = SwingFXUtils.toFXImage(wynik.pomalowane(), null);
        obraz.setImage(toczos);
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
    void Wybur(ActionEvent event) {
        if(Lista.isSelected()){
            ktury_algorytm = 1;
        }else if(Fibo.isSelected()){
            ktury_algorytm = 0;
        }else{
            ktury_algorytm = 2;
        }
    }
    @FXML
    void wcisnienty(ActionEvent event) {

        Czytacz plik = new Czytacz();
        if(plik.dodaj_plik("Graf/Pliki_textowe/"+zawartosc.getText())){
            Komunikator.setText("Udało się odczytać plik");
            this.dane_pliku = plik;
            this.y = dane_pliku.czytaj_int();
            this.x = dane_pliku.czytaj_int();
            this.graf = new Graf();
            this.graf.dodaj_graf(x, y);
            dane_pliku.wypelnij(graf, x,y);
            Scala = 1;
            wygeneruj_obraz();
            algorytm1.dodaj_dane(graf);
            algorytm2.dodaj_dane(graf);
            algorytm3.dodaj_dane(graf);
        }else{
            Komunikator.setText("Nie udało się odczytać pliku");
        }
        

    }private void wygeneruj_obraz(){
        int rozmiar = 8;
        int Dx= (int) Math.round(pole.getWidth());
        int Dy= (int) Math.round(pole.getHeight());
        if (Dx < rozmiar*x*3/2){
            Dx = rozmiar*x*3/2;
        }
        if (Dy < rozmiar*y*3/2){
            Dy = rozmiar*y*3/2;
        }
        
        wynik =  new Widok();
        wynik.Stworz_widok(graf, Dx, Dy, rozmiar);
        Image toczos = SwingFXUtils.toFXImage(wynik.pomalowane(), null);
        obraz.setFitHeight(Dy);
        obraz.setFitWidth(Dx);
        Plansza.setMaxSize(Dx, Dy);
        Plansza.setMinSize(Dx, Dy);
        
        obraz.setImage(toczos);
        Fitwysokosc = obraz.getFitHeight();
        Fitszetokosc = obraz.getFitWidth();
    }
    private void znajdz_wieszcholek(double x,double y,boolean prawy_lewy){
        int pozycja_x;
        int pozycja_y;
        pozycja_x = (int) Math.floor(x/(this.Fitszetokosc*this.Scala/this.x));
        pozycja_y = (int) Math.floor(y/(this.Fitwysokosc*this.Scala/this.y));
        if (prawy_lewy){
            graf.zeruj_dane();
            if(ktury_algorytm == 0){
                algorytm1.rozwiarz(pozycja_x+pozycja_y*this.x);
            }else if(ktury_algorytm == 1){
                algorytm2.rozwiarz(pozycja_x+pozycja_y*this.x);
            }else if(ktury_algorytm == 2){
                algorytm3.rozwiarz(pozycja_x+pozycja_y*this.x);
            }
            
            this.wynik.pomaluj();
        }else {
            graf.okresl_scieszke(pozycja_x+pozycja_y*this.x);
            this.wynik.pomaluj_wynik();
        }
        return ;
    }
    
    @FXML
    void initialize() {
        assert zawartosc != null : "fx:id=\"zawartosc\" was not injected: check your FXML file 'Scena.fxml'.";
        puste_mejsce.setImage(new Image(getClass().getResourceAsStream("Logo.png")));
        algorytm1 = new Fibonacci();
        algorytm2 = new Kolejka();
        algorytm3 = new BFS();
        generator = new Pisarz();
    }

}