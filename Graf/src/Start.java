import java.io.File;

public class Start {
    public static void main(String[] args) {
        
        String [] dane_wejsciowe=new String[3];
        dane_wejsciowe[0]="Pliki textowe/javatest.txt";
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
        new Obraz(x,y,graf);
        //Pisarz generator = new Pisarz("plikawypisany1.txt");
        //generator.napisz(x, y);

    }
}