import java.io.File;

import javax.print.attribute.SupportedValuesAttribute;

public class Start {
    public static void main(String[] args) {
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
        aklorytm.rozwiarz(0);
        System.out.println(graf[2].DP);
        for (i=0;i<x*y;i++){
            System.out.println(graf[i].waga);
        }
        new Obraz(x,y,graf);

    }
}