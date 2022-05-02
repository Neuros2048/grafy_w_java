public abstract class BFS {
    Graf graf;
    int x;
    protected abstract void dodaj_dane(Graf graf,int x ); //może do zamiany na konstruktor
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
            
            
            wpentli--;
        }
    }    
}
