
public class Fibonacci {
    private Wieszcholek[] graf;
    private int x; //długość wiersza do ruchu po tabeli
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
        final int klucz;
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
        this.tymczasowa = new punkt[dlugosc];//new punkt[1+Math.getExponent(Math.ceil(Math.log10(dlugosc)/Math.log10(2)))];
        for (int i = 0; i< this.tymczasowa.length;i++){
            this.tymczasowa[i] = null;
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
    }/*
    private void doda_min(punkt nowy){ //new_min
        if (this.new_min==null){
            this.new_min = nowy;
            nowy.brat2 = null;
            return;
        }
        
        
        nowy.brat2 = this.new_min.brat2;
        this.new_min.brat2 = nowy;
        
        return;
    }
    private void usun_min(punkt nowy){  //new_min
        if (new_min ==nowy){
            new_min =new_min.brat2;
            return;
        }
        punkt tmp = new_min;
        while(tmp.brat2 !=nowy){
            tmp = tmp.brat2;
        }
        tmp.brat2 = nowy.brat2;

    }*/
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
            dziecko.brat1 = rodzic.dziecko.brat1;
            dziecko.brat2 =rodzic.dziecko;
            rodzic.dziecko.brat1.brat2 = dziecko;
            rodzic.dziecko.brat1 = dziecko; 
        }
    }
    private void dodaj_tymczasowa(punkt tymczas){
        //System.out.println("start");
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
        //System.out.println("koniec");
        
    }
    private void nowy_min(){
        for (int i =0;i<tymczasowa.length;i++) {
            if (tymczasowa[i]!=null){
                //System.out.println("dodaje jako nowy min");
                dodaj(tymczasowa[i]);
                tymczasowa[i]=null;
            }
        }
    }
    private void zdejmij_min(){
        
        if(this.min.ile !=0){
            //System.out.println(this.min.dziecko.brat1.klucz+" "+this.min.dziecko.klucz+" "+this.min.dziecko.brat2.klucz);
            this.next = min.dziecko;
            if(this.min.brat1 == this.min){
                this.min = this.next;
            }else{
                this.min.brat1.brat2 = this.next;
                this.min.brat2.brat1 = this.next.brat1;
                this.next.brat1.brat2 = this.min.brat2;
                this.next.brat1 = this.min.brat1;
            }
        }
        else{
            if (this.min.brat1 == this.min){
                this.min = null;
                return;
            }else{
                //System.out.println("czy to jak");
                this.next = this.min.brat1;
                this.next.brat2 = this.min.brat2;
                this.next.brat2.brat1 = this.next;
            }
        }
        this.min = this.next;
        punkt tmp;
        
        do {
            //System.out.println(this.next.brat1.klucz+" "+this.next.klucz+" "+this.next.brat2.klucz);
            //System.out.println(this.min.klucz+" "+this.next.klucz);
            //System.out.println("pentla do whiel");
            tmp = this.next;
            tmp.rodzic =null;
            this.next = this.next.brat2;
            dodaj_tymczasowa(tmp);
        } while (this.min !=this.next) ;
        this.min = null;
        nowy_min();


    }
    private void usun_punkt(punkt nowy){
        //System.out.println("usuwa punkt");
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
        //System.out.println("tak");
        if (nowy==this.min){
            return;
        }
        else if (nowy.rodzic == null ){
            if(graf[this.min.klucz].waga>graf[nowy.klucz].waga){
                //System.out.println("jest");
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
    public void rozwiarz(int poczontek){
        this.min = this.punkty[poczontek];
        int wpentli = 1;
        int xdo;
        int startx;
        while(wpentli>0){
            //System.out.println("poczatel");
            //wypisz_min();
            startx = this.min.klucz;
            zdejmij_min();
            //wypisz_min();
            //System.out.println("koniec");
            graf[startx].status = 2;
            if (graf[startx].DG != -1){
                xdo = startx-x;
                if (graf[xdo].status==0){
                    graf[xdo].x = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DG;
                    dodaj(punkty[xdo]);
                    wpentli++;
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DG){
                    graf[xdo].x = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DG;
                    zmien_wage(punkty[xdo]);
                }
            }
            if (graf[startx].DP != -1){
                xdo = startx+1;
                if (graf[xdo].status==0){
                    graf[xdo].x = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DP;
                    dodaj(punkty[xdo]);
                    wpentli++;
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DP){
                    graf[xdo].x = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DP;
                    zmien_wage(punkty[xdo]);
                }
            }
            if (graf[startx].DD != -1){
                xdo = startx+x;
                if (graf[xdo].status==0){
                    graf[xdo].x = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DD;
                    dodaj(punkty[xdo]);
                    wpentli++;
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DD){
                    graf[xdo].x = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DD;
                    zmien_wage(punkty[xdo]);
                }
            }
            if (graf[startx].DL != -1){
                xdo = startx-1;
                if (graf[xdo].status==0){
                    graf[xdo].x = startx;
                    graf[xdo].status = 1;
                    graf[xdo].waga = graf[startx].waga + graf[startx].DL;
                    dodaj(punkty[xdo]);
                    wpentli++;
                }else if(graf[xdo].status==1&&graf[xdo].waga>graf[startx].waga+graf[startx].DL){
                    graf[xdo].x = startx;
                    graf[xdo].waga = graf[startx].waga+graf[startx].DL;
                    zmien_wage(punkty[xdo]);
                }
            }
            wpentli--;
        }
    }
    private String wypisz_dzieci(punkt nowy){
        String wyraz = "brak dzieci";
        for (int i= 0;i<nowy.ile;i++){
            wyraz = String.valueOf( nowy.ile);
        }
        return wyraz;
    }
    private void wypisz_min(){
        int awaria=0;
        if (this.min!=null){
            punkt tmp = this.min;
            do{
                System.out.print("("+tmp.brat1.klucz+" "+tmp.klucz+" "+tmp.brat2.klucz+" dzeicko "+wypisz_dzieci(tmp)+")  ");
                tmp =tmp.brat2;
                awaria++;
                if (awaria>5){
                    System.out.println("awaria");
                    break;
                }
            }while(tmp!=this.min);
            System.out.println();
        }else{
            System.out.println("nic");
        }
    }
}
