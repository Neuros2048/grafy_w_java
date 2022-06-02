package graf.algorytmy;

public abstract class Dijkstra extends Algorytm_przechodzenia {
    protected abstract int get_min();
    protected abstract void zdejmij_min();
    protected abstract void zmien_wage(int xdo);
    protected abstract void dodaj(int xdo);

    @Override
    protected int nastepny() {
        int startx = get_min();
        zdejmij_min();
        return startx;
    }

    @Override
    protected void nowy_wierzcholek(int xdo, int startx, int od_1_do_4) {
        graf.ustaw_status(xdo, 1);
        graf.ustaw_wage(xdo, startx, graf.dostan_droge(startx, od_1_do_4));
        dodaj(xdo);
    }

    @Override
    protected boolean ponowien_odwiedzic(int xdo, int startx, int od_1_do_4) {
        return graf.dostan_status(xdo)==1&&graf.dostan_waga(xdo) >graf.dostan_waga(startx)+graf.dostan_droge(startx, od_1_do_4);
    }

    @Override
    protected void ponownie_odwiedzony(int xdo, int startx, int od_1_do_4) {
        graf.ustaw_wage(xdo, startx, graf.dostan_droge(startx, od_1_do_4));
        zmien_wage(xdo);
        return;
    }
    
}
