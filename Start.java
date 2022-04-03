import java.io.File;


public class Start {
    public static void main(String[] args) {
        int poczontek = Integer.parseInt(args[1]);
        int szukane = Integer.parseInt(args[2]);
        Czytacz dane_pliku = new Czytacz(new File(args[0]));
        //Czytacz dane_pliku = new Czytacz(new File("mygraph.txt"));
        int x,y;
        y = dane_pliku.czytaj_int();
        x = dane_pliku.czytaj_int();
        System.out.println(y +" i "+ x);
        Wieszcholek[] graf = new Wieszcholek[x*y];
        
        int i;
        for (i=0;i<x*y;i++){
            graf[i] = new Wieszcholek(i);
        }
        dane_pliku.wypelnij(graf, x,y);
        Dijkstra aklorytm = new Dijkstra(graf, x);
        aklorytm.rozwiarz(poczontek);
        /*for (i=0;i<x*y;i++){
            System.out.println(graf[i].waga);
        }*/
        aklorytm.okresl_scieszke(graf,szukane);
        new Obraz(x,y,graf);

    }
}