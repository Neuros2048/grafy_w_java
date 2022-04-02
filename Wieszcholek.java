import java.util.Random;

public class Wieszcholek {
    int x ;
    public double waga;
    public double DL;
    public double DP;
    public double DG;
    public double DD;
        Wieszcholek(int x){
            this.x = x;
            this.waga = Math.abs(x-5000) ;
            this.DG = -1;
            this.DD = -1;
            this.DL = -1;
            this.DP = -1;

            

        }
    
}
