package algorytmy;
import java.beans.BeanProperty;

public class Graf{
    private Wieszcholek [] wieszcholki;
    private int x,y; 
    public class Wieszcholek {
        public int z ;
        public int status;
        public double waga;
        public double DL;
        public double DP;
        public double DG;
        public double DD;
            Wieszcholek(){
                this.z = -2;
                this.status = 0;
                this.waga = 0 ;
                this.DG = -1;
                this.DD = -1;
                this.DL = -1;
                this.DP = -1;
            }
        
    }
    public Graf(){
        wieszcholki = null;
        x =0;
        y =0;
    }
    public void dodaj_graf(int x,int y ){
        this.x = x;
        this.y = y;
        int i;
        this.wieszcholki = new Wieszcholek[x*y];
        for (i=0;i<x*y;i++){
            wieszcholki[i] = new Wieszcholek();
        }
    }
    public int dlugosc_grafu(){
        return wieszcholki.length;
    }
    public void okresl_scieszke(int szukane){
        while(szukane != -2){
            wieszcholki[szukane].status = 3;
            szukane = wieszcholki[szukane].z; 
        }
    }
    public int dostan_status(int i){
        return wieszcholki[i].status;
    }
    public void ustaw_status(int i,int status){
        wieszcholki[i].status = status;
    }
    /**
     * 
     * @param i
     * @param od_1_do_4 1-DL 2-DG 3-DP 4-DD
     * @return
     */
    public double dostan_droge(int i,int od_1_do_4){
        if (od_1_do_4 == 1){
            return wieszcholki[i].DL;
        }else if(od_1_do_4==2){
            return  wieszcholki[i].DG ;
        }else if (od_1_do_4 == 3){
            return wieszcholki[i].DP;
        }else if (od_1_do_4 == 4){
            return wieszcholki[i].DD ;
        }else{
            return -1;
        }
    }
    public boolean dodaj_droge(int i,int iz, double waga ){
        if (iz == i-1){
            wieszcholki[i].DL = waga;
        }else if(iz==i+1){
            wieszcholki[i].DP = waga;
        }else if (iz == i+x){
            wieszcholki[i].DD = waga;
        }else if (iz == i -x){
            wieszcholki[i].DG = waga;
        }else{
            return false;
        }
        return true;
    }
    public void ustaw_wage(int i ,int iz,double waga){
        ustaw_droge_z(i, iz);
        wieszcholki[i].waga = waga + wieszcholki[iz].waga; 
    }
    public double dostan_waga(int i){
        return wieszcholki[i].waga;
    }
    public void ustaw_droge_z(int i,int iz){
        wieszcholki[i].z = iz;
    }
    public int dostan_droge_z(int i){
        return wieszcholki[i].z;
    }
    public void zeruj_status(){
        for (int i = 0 ; i< wieszcholki.length ;i++){
            wieszcholki[i].status = 0;
        }
    }
    public void zeruj_dane(){
        for (int i = 0 ; i< wieszcholki.length ;i++){
            wieszcholki[i].status = 0;
            wieszcholki[i].z = -2;
            wieszcholki[i].waga = 0;
        }
    }
}