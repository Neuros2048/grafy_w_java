public abstract class BFS {
    Wieszcholek[] graf;
    int x;
    protected abstract void dodaj_dane(Wieszcholek[] graf,int x ); //może do zamiany na konstruktor
    protected abstract int get_min();
    protected abstract void zdejmij_min();
    protected abstract void zmien_wage(int xdo);
    protected abstract void dodaj(int xdo);
    protected abstract void stworzenie(int poczontek);
    public void rozwiarz(int poczontek){
        stworzenie(poczontek);
        int wpentli = 1;
        int xdo;
        int startx;
        while(wpentli>0){
            startx = get_min();
            zdejmij_min();
            graf[startx].status = 2;
            if (graf[startx].DG != -1){
                xdo = startx-x;
                if (graf[xdo].status==0){
                    graf[xdo].status = 1;
                    dodaj(xdo);
                    wpentli++;
                }
            }
            if (graf[startx].DP != -1){
                xdo = startx+1;
                if (graf[xdo].status==0){
                    graf[xdo].status = 1;
                    dodaj(xdo);
                    wpentli++;
                }
            }
            if (graf[startx].DD != -1){
                xdo = startx+x;
                if (graf[xdo].status==0){
                    graf[xdo].status = 1;
                    dodaj(xdo);
                    wpentli++;
                }
            }
            if (graf[startx].DL != -1){
                xdo = startx-1;
                if (graf[xdo].status==0){
                    graf[xdo].status = 1;
                    dodaj(xdo);
                    wpentli++;
                }
            }
            wpentli--;
        }
    }    
}
