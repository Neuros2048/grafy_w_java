import java.io.File;

public class Start {
    public static void main(String[] args) {
        //Czytacz dane_pliku = new Czytacz(new File(args[0]));
        Czytacz dane_pliku = new Czytacz(new File("mygraph.txt"));
        System.out.println(dane_pliku.czytaj_int());
        int x = 100;
        int y = 100;
        Wieszcholek[] graf = new Wieszcholek[x*y];
        int i;
        for (i=0;i<x*y;i++){
            graf[i] = new Wieszcholek(i);
        }
        new Obraz(x,y,graf);

    }
}