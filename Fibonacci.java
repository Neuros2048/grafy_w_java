
public class Fibonacci extends Dijkstra {
    private punkt punkty[];
    private punkt tymczasowa[];
    private punkt min;
    private class punkt {
        private punkt rodzic;
        private punkt brat1;
        private punkt brat2;
        private punkt dziecko;
        private boolean depresja;
        private int ile;
        private final int klucz;
        punkt(int klucz){
            this.klucz = klucz;
            this.brat1 = this;
            this.brat2 = this;
            this.ile = 0;
            this.depresja = false;
            this.rodzic = null;
        }
    }
    private void dodaj(punkt nowy){
        if (this.min==null){
            this.min = nowy;
            nowy.brat1 =nowy;
            nowy.brat2 =nowy;
            return;
        }
        nowy.brat1 = this.min.brat1;
        nowy.brat2 = this.min;
        nowy.brat1.brat2 = nowy;
        this.min.brat1 = nowy;
        if (this.graf[this.min.klucz].waga>this.graf[nowy.klucz].waga){
            this.min = nowy;
        }
        return;
    }
    private void dodaj_dziecko(punkt rodzic,punkt dziecko){
        if (rodzic.ile==0){
            rodzic.ile++;
            rodzic.dziecko = dziecko;
            dziecko.rodzic = rodzic;
            dziecko.brat1=dziecko;
            dziecko.brat2 =dziecko;
        }
        else{
            rodzic.ile++;
            dziecko.rodzic = rodzic;
            dziecko.brat1 = rodzic.dziecko.brat1;
            dziecko.brat2 =rodzic.dziecko;
            rodzic.dziecko.brat1.brat2 = dziecko;
            rodzic.dziecko.brat1 = dziecko; 
        }
    }
    private void dodaj_tymczasowa(punkt tymczas){
        int w_tablicy = tymczas.ile;
        if (tymczasowa[w_tablicy]==null){
            tymczasowa[w_tablicy] = tymczas;
        }else{
            if ( graf[tymczasowa[w_tablicy].klucz].waga>graf[tymczas.klucz].waga ){
                dodaj_dziecko(tymczas, tymczasowa[w_tablicy]);
                tymczasowa[w_tablicy]=null;
                dodaj_tymczasowa(tymczas);
            }else{
                dodaj_dziecko(tymczasowa[w_tablicy],tymczas);
                tymczas =tymczasowa[w_tablicy];
                
                tymczasowa[w_tablicy]=null;
                dodaj_tymczasowa(tymczas);
            }
        }
        
    }
    private void nowy_min(){
        for (int i =0;i<tymczasowa.length;i++) {
            if (tymczasowa[i]!=null){
                dodaj(tymczasowa[i]);
                tymczasowa[i]=null;
            }
        }
    }
    private void usun_punkt(punkt nowy){
        nowy.rodzic.ile--;
            if (nowy.rodzic.rodzic!=null){
                if (nowy.rodzic.depresja==true){
                    nowy.rodzic.depresja=false;
                    usun_punkt(nowy.rodzic);
                }else{
                    nowy.rodzic.depresja=true;
                }
            }
        if (nowy.rodzic.dziecko==nowy){
            if(nowy.rodzic.ile==0){
                nowy.rodzic.dziecko =null;
            }else{
                nowy.brat1.brat2=nowy.brat2;
                nowy.brat2.brat1 =nowy.brat1;
                nowy.rodzic.dziecko = nowy.brat1;
            }
        }
        else{
            
            nowy.brat1.brat2=nowy.brat2;
            nowy.brat2.brat1 =nowy.brat1;
            nowy.rodzic = null;
            
        }
        dodaj(nowy);
    }
    private void zmien_wage(punkt nowy){
        if (nowy==this.min){
            return;
        }
        else if (nowy.rodzic == null ){
            if(graf[this.min.klucz].waga>graf[nowy.klucz].waga){
                nowy.brat1.brat2 =nowy.brat2;
                nowy.brat2.brat1 = nowy.brat1;
                nowy.brat1 = this.min.brat1;
                nowy.brat2 = this.min;
                nowy.brat1.brat2 = nowy;
                this.min.brat1 = nowy;
                this.min = nowy;
            }
        }
        else if(graf[nowy.rodzic.klucz].waga>graf[nowy.klucz].waga){

            usun_punkt(nowy);
        }
    }
    @Override
    protected void dodaj_dane(Wieszcholek[] graf, int x) {
        this.graf = graf;
        this.x = x;
        int dlugosc = graf.length;
        this.punkty = new punkt[dlugosc];
        for(int i =0;i< dlugosc;i++){
            this.punkty[i] = new punkt(i);
        }
        this.tymczasowa = new punkt[1+Math.getExponent(dlugosc)];
        for (int i = 0; i< this.tymczasowa.length;i++){
            this.tymczasowa[i] = null;
        }
    }
    @Override
    protected int get_min() {
        return this.min.klucz;
    }
    @Override
    protected void zdejmij_min() {
        punkt next;
        if(this.min.ile !=0){
            next = min.dziecko;
            if(this.min.brat1 == this.min){
                this.min = next;
            }else{
                this.min.brat1.brat2 = next;
                this.min.brat2.brat1 = next.brat1;
                next.brat1.brat2 = this.min.brat2;
                next.brat1 = this.min.brat1;
            }
        }
        else{
            if (this.min.brat1 == this.min){
                this.min = null;
                return;
            }else{
                next = this.min.brat1;
                next.brat2 = this.min.brat2;
                next.brat2.brat1 = next;
            }
        }
        this.min = next;
        punkt tmp;
        
        do {
            tmp = next;
            tmp.rodzic =null;
            next = next.brat2;
            dodaj_tymczasowa(tmp);
        } while (this.min !=next) ;
        this.min = null;
        nowy_min();

    }
    @Override
    protected void zmien_wage(int xdo) {
        zmien_wage(this.punkty[xdo]);
        
    }
    @Override
    protected void dodaj(int xdo) {
        dodaj(this.punkty[xdo]);
    }
    @Override
    protected void stworzenie(int poczontek) {
        this.min = this.punkty[poczontek];
    }
    
}
