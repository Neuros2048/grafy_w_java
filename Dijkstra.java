public class Dijkstra {
    Wieszcholek[] graf;
    int x;
    Dijkstra(Wieszcholek[] graf,int x){
        this.graf = graf;
        this.x = x;
    }
    public void rozwiarz(int poczontek){
        Lista przedstawicel = new Lista(poczontek);
        przedstawicel.stworzenie();
        int startx;
        int xdo;
        double droga;
        graf[poczontek].waga = 0;
        graf[poczontek].x = -1;
        while(przedstawicel.ile_elemntow()>0){
            System.out.println(przedstawicel.co_start().weź_klucz()+" ile "+przedstawicel.ile_elemntow());
            startx = przedstawicel.zabierz_start();
            graf[startx].status = 2;
            if (graf[startx].DG != -1){
                xdo = startx-x;
                if (graf[xdo].status==0){
                    graf[xdo].x = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DG;
                    new Lista(xdo).wtasuj(graf);
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DG){
                    graf[xdo].x = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DG;
                    przedstawicel.znajc_klucz(xdo).przetasuj(graf);
                }
            }
            if (graf[startx].DP != -1){
                xdo = startx+1;
                if (graf[xdo].status==0){
                    graf[xdo].x = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DP;
                    new Lista(xdo).wtasuj(graf);
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DP){
                    graf[xdo].x = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DP;
                    przedstawicel.znajc_klucz(xdo).przetasuj(graf);
                }
            }
            if (graf[startx].DD != -1){
                xdo = startx+x;
                if (graf[xdo].status==0){
                    graf[xdo].x = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DD;
                    new Lista(xdo).wtasuj(graf);
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DD){
                    graf[xdo].x = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DD;
                    przedstawicel.znajc_klucz(xdo).przetasuj(graf);
                }
            }
            if (graf[startx].DL != -1){
                xdo = startx-1;
                if (graf[xdo].status==0){
                    graf[xdo].x = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DL;
                    new Lista(xdo).wtasuj(graf);
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DL){
                    graf[xdo].x = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DL;
                    przedstawicel.znajc_klucz(xdo).przetasuj(graf);
                }
            }
            System.out.println("koniec pentli");
        }
    }
}
