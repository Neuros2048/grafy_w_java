package graf.algorytmy;

public class Graf{
    private Wierzcholek [] wierzcholki;
    private int x,y; 
    private class Wierzcholek {
        public int z ;
        public int status;
        public double waga;
        public double DL;
        public double DP;
        public double DG;
        public double DD;
            Wierzcholek(){
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
        wierzcholki = null;
        x =0;
        y =0;
    }
    public void dodaj_graf(int x,int y ){
        this.x = x;
        this.y = y;
        int i;
        this.wierzcholki = new Wierzcholek[x*y];
        for (i=0;i<x*y;i++){
            wierzcholki[i] = new Wierzcholek();
        }
    }
    public int dlugosc_grafu(){
        return wierzcholki.length;
    }
    public void okresl_scieszke(int szukane){
        while(szukane != -2){
            wierzcholki[szukane].status = 3;
            szukane = wierzcholki[szukane].z; 
        }
    }
    public int dostan_status(int i){
        return wierzcholki[i].status;
    }
    public void ustaw_status(int i,int status){
        wierzcholki[i].status = status;
    }
    /**
     * 
     * @param i
     * @param od_1_do_4 1-DL 2-DG 3-DP 4-DD
     * @return -1 nieporawna wartość drugiego argumentu
     */
    public double dostan_droge(int i,int od_1_do_4){
        if (od_1_do_4 == 1){
            return wierzcholki[i].DL;
        }else if(od_1_do_4==2){
            return  wierzcholki[i].DG ;
        }else if (od_1_do_4 == 3){
            return wierzcholki[i].DP;
        }else if (od_1_do_4 == 4){
            return wierzcholki[i].DD ;
        }else{
            return -1;
        }
    }/**
    *@return true-udalo się false-nieprawidłowe parametry i lub iz
    */
    public boolean dodaj_droge(int i,int iz, double waga ){
        if(i<0||iz<0||i>x*y-1||iz>x*y-1){
            return false;
        }
        if(waga <0){
            return false;
        }
        if (iz == i-1 && i%x != 0 ){
            wierzcholki[i].DL = waga;
        }else if(iz == i+1 && i%x != x-1 ){
            wierzcholki[i].DP = waga;
        }else if (dlugosc_grafu() > iz && iz == i+x){
            wierzcholki[i].DD = waga;
        }else if (iz >= 0 && iz == i -x){
            wierzcholki[i].DG = waga;
        }else{
            return false;
        }
        return true;
    }
    public void ustaw_wage(int i ,int iz,double waga){
        ustaw_droge_z(i, iz);
        wierzcholki[i].waga = waga + wierzcholki[iz].waga; 
    }
    public double dostan_waga(int i){
        return wierzcholki[i].waga;
    }
    public void ustaw_droge_z(int i,int iz){
        wierzcholki[i].z = iz;
    }
    public int dostan_droge_z(int i){
        return wierzcholki[i].z;
    }
    public void zeruj_status(){
        for (int i = 0 ; i< wierzcholki.length ;i++){
            wierzcholki[i].status = 0;
        }
    }
    public void zeruj_dane(){
        for (int i = 0 ; i< wierzcholki.length ;i++){
            wierzcholki[i].status = 0;
            wierzcholki[i].z = -2;
            wierzcholki[i].waga = 0;
        }
    }
    public int dostan_wymiar_x(){
        return this.x;
    }
    public int dostan_wymiar_y(){
        return this.y;
    }
}