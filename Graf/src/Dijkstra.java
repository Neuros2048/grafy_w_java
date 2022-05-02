public abstract class Dijkstra {
    protected Graf graf;
    protected int x;
    protected abstract void dodaj_dane(Graf graf,int x ); 
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
                        graf.ustaw_status(xdo, 1);
                        graf.ustaw_wage(xdo, startx, graf.dostan_droge(startx, od_1_do_4));
                        dodaj(xdo);
                        wpentli++;
                    }else if(graf.dostan_status(xdo)==1&&graf.dostan_waga(xdo) >graf.dostan_waga(startx)+graf.dostan_droge(startx, od_1_do_4)){
                        graf.ustaw_wage(xdo, startx, graf.dostan_droge(startx, od_1_do_4));
                        zmien_wage(xdo);
                    }
                }
            }
            wpentli--;
        }
    }    
    
}
