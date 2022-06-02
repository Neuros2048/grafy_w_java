package graf.algorytmy;

import java.util.ArrayList;

public class BFS extends Algorytm_przechodzenia{
    private ArrayList<Integer> lista;
    @Override
    protected void stworzenie(int poczontek) {
        lista = new ArrayList<>();
        lista.add(poczontek);
    }

    @Override
    protected int nastepny() {
        int startx = lista.get(0);
        lista.remove(0);
        return startx;
    }

    @Override
    protected void nowy_wierzcholek(int xdo, int startx, int od_1_do_4) {
        graf.ustaw_status(xdo, 2);
        graf.ustaw_wage(xdo, startx, 1);
        lista.add(xdo);
        return;
    }

    @Override
    protected boolean ponowien_odwiedzic(int xdo, int startx, int od_1_do_4) {
        return false;
    }

    @Override
    protected void ponownie_odwiedzony(int xdo, int startx, int od_1_do_4) {
        return;
    }
    
}
