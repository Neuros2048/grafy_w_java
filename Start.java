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
        System.out.println(graf[2].DP);
        new Obraz(x,y,graf);

    }
}