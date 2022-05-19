package algorytmy;

public abstract class Algorytm_przechodzenia {
    protected Graf graf;
    protected int x;
    public void dodaj_dane(Graf graf){
        this.graf = graf;
        this.x = graf.dostan_wymiar_x();
    }
    protected abstract void stworzenie(int poczontek);
    protected abstract int nastepny();
    protected abstract void nowy_wierzcholek(int xdo, int startx, int od_1_do_4);
    protected abstract boolean ponowien_odwiedzic(int xdo, int startx, int od_1_do_4);
    protected abstract void ponownie_odwiedzony(int xdo, int startx, int od_1_do_4 );
    public void rozwiarz(int poczontek){
        stworzenie(poczontek);
        int wpentli = 1;
        int xdo;
        int startx;
        while(wpentli>0){
            startx = nastepny();
            graf.ustaw_status(startx, 2);
            for(int od_1_do_4 = 1;od_1_do_4 < 5;od_1_do_4++ ){
                if (graf.dostan_droge(startx, od_1_do_4) != -1){
                    if(od_1_do_4==1)
                        xdo = startx -1;
                    else if(od_1_do_4==2)
                        xdo = startx -x;
                    else if(od_1_do_4==3)
                        xdo = startx +1;
                    else if(od_1_do_4==4)
                        xdo = startx +x;
                    else
                        break;
                    if (graf.dostan_status(xdo)==0){
                        nowy_wierzcholek(xdo,startx, od_1_do_4);
                        wpentli++;
                    }else if(graf.dostan_status(xdo)==1&&graf.dostan_waga(xdo) >graf.dostan_waga(startx)+graf.dostan_droge(startx, od_1_do_4)){
                        ponownie_odwiedzony(xdo, startx, od_1_do_4);
                    }
                }
            }
            wpentli--;
        }
        
    }    
}
