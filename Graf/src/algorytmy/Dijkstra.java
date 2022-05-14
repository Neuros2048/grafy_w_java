package algorytmy;

import java.util.ArrayList;

public abstract class Dijkstra {
    protected Graf graf;
    protected int x;
    public void dodaj_dane(Graf graf){
        this.graf = graf;
        this.x = graf.dostan_wymiar_x();
    }
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
    public boolean BFS(int poczontek){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int wpentli = 1;
        int xdo;
        int startx = poczontek;
        list.add(poczontek);
        graf.ustaw_status(startx, 2);
        while(wpentli>0){
            startx = list.get(0);
            list.remove(0);
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
                        graf.ustaw_status(xdo, 2);
                        graf.ustaw_wage(xdo, startx, 1);
                        list.add(xdo);
                        wpentli++;
                    }
                }
            }
            wpentli--;
        }
        boolean wynik = true;
        for (int j =0 ;j <graf.dlugosc_grafu();j++){
            if (graf.dostan_status(j)!=2 ){
                wynik = false;
            }
        }
        graf.zeruj_status();
        return wynik;
    }
}
