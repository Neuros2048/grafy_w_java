
public class Fibonacci {
    private Wieszcholek[] graf;
    private int x;
    private punkt punkty[];
    private punkt tymczasowa[];
    private punkt min;
    private punkt next;
    private class punkt {
        punkt rodzic;
        punkt brat1;
        punkt brat2;
        punkt dziecko;
        boolean depresja;
        int ile;
        int klucz;
        punkt(int klucz){
            this.klucz = klucz;
            this.brat1 = this;
            this.brat2 = this;
            this.ile = 0;
            this.depresja = false;
            this.rodzic = null;
        }
    }
    Fibonacci(Wieszcholek[] graf,int x){
        this.graf = graf;
        this.x = x;
        int dlugosc = graf.length;
        this.punkty = new punkt[dlugosc];
        for(int i =0;i< dlugosc;i++){
            this.punkty[i] = new punkt(i);
        }
        this.tymczasowa = new punkt[Math.getExponent(Math.ceil(Math.log10(dlugosc)/Math.log10(2)))];
        for (int i = 0; i< this.tymczasowa.length;i++){
            this.tymczasowa[i] = null;
        }
    }
    private void dodaj(punkt nowy){
        if (this.min==null){
            this.min = nowy;
            return;
        }
        nowy.brat1 = this.min.brat1;
        nowy.brat2 = this.min;
        nowy.brat1.brat2 = nowy;
        min.brat1 = nowy;
        if (this.graf[this.min.klucz].waga>this.graf[nowy.klucz].waga){
            this.min = nowy;
        }
        
    }
    private void zdejmij_min(){
        this.next = min.dziecko;
        if(this.min.ile !=0){
            if(this.min.brat1 == this.min){
                this.min = this.next;
                this.min.rodzic = null;
            }else{
                this.min.brat1.brat2 = this.next;
                this.min.brat2.brat1 = this.next.brat1;
                this.next.brat1.brat2 = this.min.brat2;
                this.next.brat1 = this.min.brat1;
            }
        }
    }
    public void rozwiarz(int poczontek){
        this.min = this.punkty[poczontek];
        int wpentli = 1;
        while(wpentli>0){
            wpentli--;
        }
    }
}
