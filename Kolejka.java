public class Kolejka extends Dijkstra{

    private Lista start;
    private Lista koniec;
    private class Lista {
        private Lista nastepny;
        private int klucz;
        Lista(int klucz){
            this.klucz = klucz;
            nastepny = null;
        }
    }
    
    @Override
    public void dodaj_dane(Wieszcholek[] graf, int x) {
        super.graf = graf;
        super.x = x;
        return;
    }

    @Override 
    protected int get_min() {
        return this.start.klucz;
    }

    @Override
    protected void zdejmij_min() {
        start = start.nastepny;
        return;
    }

    @Override
    protected void zmien_wage(int xdo) {
        Lista zmieniony = znajc_klucz(xdo);
        przetasuj(zmieniony);
        return;
    }

    @Override
    protected void dodaj(int xdo) {
        Lista nowy = new Lista(xdo);
        if (start==null){
            start = nowy;
            return;
        }
        if (graf[start.klucz].waga>graf[nowy.klucz].waga){
            nowy.nastepny = start;
            start = nowy;
            return;
        }
        Lista tymczasowa = start;
        Lista porzedni;
        while(tymczasowa.nastepny!=null){
            porzedni = tymczasowa;
            tymczasowa = tymczasowa.nastepny;
            if (graf[tymczasowa.klucz].waga>graf[nowy.klucz].waga){
                porzedni.nastepny=nowy;
                nowy.nastepny= tymczasowa;
                return;
            }

        }
        tymczasowa.nastepny = nowy;

        return;
    }
    @Override
    protected void stworzenie(int poczontek) {
        this.start = new Lista(poczontek);
        this.koniec = this.start;
        return;
    }
    private void na_koniec(Lista nowy){
        if (start==null){
            start = nowy;
            koniec = nowy;
            return;
        }
        koniec.nastepny =nowy;
        koniec = nowy;
    }
    private Lista znajdz_poprzednika(Lista ja){
        Lista tymczasowy = start;
        while(tymczasowy.nastepny!=ja){
            tymczasowy = tymczasowy.nastepny;
        }
        return tymczasowy;
    }
    public void przetasuj(Lista zmieniony){
        if (start==zmieniony){
            return;
        }
        if (graf[start.klucz].waga>graf[zmieniony.klucz].waga){
            znajdz_poprzednika(zmieniony).nastepny = zmieniony.nastepny;
            zmieniony.nastepny = start;
            start = zmieniony;
            return;
        }
        Lista tymczasowy = start;
        Lista poprzedni;
        while(tymczasowy.nastepny!=zmieniony){
            poprzedni = tymczasowy;
            tymczasowy = tymczasowy.nastepny;
            if(graf[tymczasowy.klucz].waga>graf[zmieniony.klucz].waga){
                znajdz_poprzednika(zmieniony).nastepny = zmieniony.nastepny;
                zmieniony.nastepny = tymczasowy;
                poprzedni.nastepny = zmieniony;
                return;
            }
            
        }
        
    }
    public Lista znajc_klucz(int klucz){
        Lista tymczasowy = start;
        while(tymczasowy.klucz!=klucz){
            tymczasowy = tymczasowy.nastepny;
        }
        return tymczasowy;
    }
    
    public boolean czy_istnieje(int start,int koniec,int xy){
        boolean wynik = true; // czy nie działa
        stworzenie(start);
        int startx;
        int xdo; //bo czemu nie 
        int ile= 1;
        graf[start].status = 2;
        while(ile>0){
            startx = get_min();
            zdejmij_min();
            if (graf[startx].DG != -1){
                xdo = startx-x;
                if (graf[xdo].status==0){
                    graf[xdo].status = 2;
                    na_koniec(new Lista(xdo));
                    ile++;
                }
            }
            if (graf[startx].DP != -1){
                xdo = startx+1;
                if (graf[xdo].status==0){
                    graf[xdo].status = 2;
                    na_koniec(new Lista(xdo));
                    ile++;
                }
            }
            if (graf[startx].DD != -1){
                xdo = startx+x;
                if (graf[xdo].status==0){
                    graf[xdo].status = 2;
                    na_koniec(new Lista(xdo));
                    ile++;
                }
            }
            if (graf[startx].DL != -1){
                xdo = startx-1;
                if (graf[xdo].status==0){
                    graf[xdo].status = 2;
                    na_koniec(new Lista(xdo));
                    ile++;
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
    }

    
    
    
}
