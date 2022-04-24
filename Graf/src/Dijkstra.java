public abstract class Dijkstra {
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
                    graf[xdo].z = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DG;
                    dodaj(xdo);
                    wpentli++;
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DG){
                    graf[xdo].z = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DG;
                    zmien_wage(xdo);
                }
            }
            if (graf[startx].DP != -1){
                xdo = startx+1;
                if (graf[xdo].status==0){
                    graf[xdo].z = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DP;
                    dodaj(xdo);
                    wpentli++;
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DP){
                    graf[xdo].z = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DP;
                    zmien_wage(xdo);
                }
            }
            if (graf[startx].DD != -1){
                xdo = startx+x;
                if (graf[xdo].status==0){
                    graf[xdo].z = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DD;
                    dodaj(xdo);
                    wpentli++;
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DD){
                    graf[xdo].z = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DD;
                    zmien_wage(xdo);
                }
            }
            if (graf[startx].DL != -1){
                xdo = startx-1;
                if (graf[xdo].status==0){
                    graf[xdo].z = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DL;
                    dodaj(xdo);
                    wpentli++;
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DL){
                    graf[xdo].z = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DL;
                    zmien_wage(xdo);
                }
            }
            wpentli--;
        }
    }    
    
    public void okresl_scieszke(int szukane){
        while(szukane != -2){
            graf[szukane].status = 3;
            szukane = graf[szukane].z; 
        }
    }/*
    public boolean czy_istnieje(int start,int koniec,int xy){
        boolean wynik = true; // czy nie działa
        Lista przedstawicel = new Lista(start);
        przedstawicel.stworzenie();
        int startx;
        int xdo; //bo czemu nie 
        
        graf[start].status = 2;
        while(przedstawicel.ile_elemntow()>0){
            startx = przedstawicel.zabierz_start();
            if (graf[startx].DG != -1){
                xdo = startx-x;
                if (graf[xdo].status==0){
                    graf[xdo].status = 2;
                    new Lista(xdo).na_koniec();;
                }
            }
            if (graf[startx].DP != -1){
                xdo = startx+1;
                if (graf[xdo].status==0){
                    graf[xdo].status = 2;
                    new Lista(xdo).na_koniec();;
                }
            }
            if (graf[startx].DD != -1){
                xdo = startx+x;
                if (graf[xdo].status==0){
                    graf[xdo].status = 2;
                    new Lista(xdo).na_koniec();;
                }
            }
            if (graf[startx].DL != -1){
                xdo = startx-1;
                if (graf[xdo].status==0){
                    graf[xdo].status = 2;
                    new Lista(xdo).na_koniec();;
                }
            }
        }
        if (graf[koniec].status==2){
            wynik = false;
        }
        for (int j =0 ;j <xy;j++){
            if (graf[j].status==2 ){
            }
        }
        for (int i = 0 ;i<xy;i++){
            graf[i].status=0;
        }


        return wynik;
    }*/
}
