package algorytmy;
public class Kolejka extends Dijkstra{

    private Lista start;
    //private Lista koniec;
    private class Lista {
        private Lista nastepny;
        private int klucz;
        Lista(int klucz){
            this.klucz = klucz;
            nastepny = null;
        }
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
        if ( graf.dostan_waga(start.klucz)> graf.dostan_waga(nowy.klucz)){
            nowy.nastepny = start;
            start = nowy;
            return;
        }
        Lista tymczasowa = start;
        Lista porzedni;
        while(tymczasowa.nastepny!=null){
            porzedni = tymczasowa;
            tymczasowa = tymczasowa.nastepny;
            if ( graf.dostan_waga(tymczasowa.klucz)> graf.dostan_waga(nowy.klucz) ){
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
        //this.koniec = this.start;
        return;
    }/*
    private void na_koniec(Lista nowy){
        if (start==null){
            start = nowy;
            koniec = nowy;
            return;
        }
        koniec.nastepny =nowy;
        koniec = nowy;
    }*/
    private Lista znajdz_poprzednika(Lista ja){
        Lista tymczasowy = start;
        while(tymczasowy.nastepny!=ja){
            tymczasowy = tymczasowy.nastepny;
        }
        return tymczasowy;
    }
    private void przetasuj(Lista zmieniony){
        if (start==zmieniony){
            return;
        }
        if (  graf.dostan_waga(start.klucz)> graf.dostan_waga(zmieniony.klucz) ){
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
            if( graf.dostan_waga(tymczasowy.klucz)> graf.dostan_waga(zmieniony.klucz) ){
                znajdz_poprzednika(zmieniony).nastepny = zmieniony.nastepny;
                zmieniony.nastepny = tymczasowy;
                poprzedni.nastepny = zmieniony;
                return;
            }
            
        }
        
    }
    private Lista znajc_klucz(int klucz){
        Lista tymczasowy = start;
        while(tymczasowy.klucz!=klucz){
            tymczasowy = tymczasowy.nastepny;
        }
        return tymczasowy;
    }
    
    
    
}
