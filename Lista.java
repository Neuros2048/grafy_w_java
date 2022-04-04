public class Lista {
    private static Lista start;
    private static Lista koniec;
    private static int ile;
    private static Wieszcholek[] graf;
    private Lista nastepny;
    private int klucz;
    Lista(int klucz){
        this.klucz = klucz;
        nastepny = null;
    }
    public void stworzenie(Wieszcholek[] graf){ // zawsze urzyć na początek
        start = this;
        koniec = this;
        Lista.graf = graf;
        ile = 1;
    }
    public int weź_klucz(){
        return klucz;
    }
    public Lista co_nastepne(){
        return nastepny;
    }
    public void na_koniec(){
        ile++;
        if (start==null){
            start = this;
            koniec = this;
            return;
        }
        koniec.nastepny =this;
        koniec = this;
    } 
    public int zabierz_start(){
        ile--;
        int klucz = start.klucz;
        start = start.nastepny;
        return klucz;
    }
    public void wtasuj(){
        ile++;
        if (start==null){
            start = this;
            return;
        }
        if (graf[start.klucz].waga>graf[this.klucz].waga){
            this.nastepny = start;
            start = this;
            return;
        }
        Lista tymczasowa = start;
        Lista porzedni;
        while(tymczasowa.nastepny!=null){
            porzedni = tymczasowa;
            tymczasowa = tymczasowa.nastepny;
            if (graf[tymczasowa.klucz].waga>graf[this.klucz].waga){
                porzedni.nastepny=this;
                this.nastepny= tymczasowa;
                return;
            }

        }
        tymczasowa.nastepny = this;

        return;
    }
    private Lista znajdz_mnie(){
        Lista tymczasowy = start;
        while(tymczasowy.nastepny!=this){
            tymczasowy = tymczasowy.nastepny;
        }
        return tymczasowy;
    }
    public void przetasuj(){
        if (start==this){
            return;
        }
        if (graf[start.klucz].waga>graf[this.klucz].waga){
            this.znajdz_mnie().nastepny = this.nastepny;
            this.nastepny = start;
            start = this;
            return;
        }
        Lista tymczasowy = start;
        Lista poprzedni;
        while(tymczasowy.nastepny!=this){
            poprzedni = tymczasowy;
            tymczasowy = tymczasowy.nastepny;
            if(graf[tymczasowy.klucz].waga>graf[this.klucz].waga){
                this.znajdz_mnie().nastepny = this.nastepny;
                this.nastepny = tymczasowy;
                poprzedni.nastepny = this;
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
    public Lista co_start(){
        return start;
    }
    public Lista co_koniec(){
        return koniec;
    }
    public int ile_elemntow(){
        return ile;
    }
    
}
