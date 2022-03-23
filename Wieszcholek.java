import java.util.Random;

public class Wieszcholek {
    int x ;
    double waga;
    double DL;
    double DP;
    double DG;
    double DD;
        Wieszcholek(int x){
            this.x = x;
            this.waga = Math.abs(x-5000) ;

            if (x>=8000){
                this.DG = -1;
                this.DD = -1;
                this.DL = -1;
                this.DP = -1;

            }else {
                this.DG = new Random().nextInt(10);
                this.DG = new Random().nextInt(10);
                this.DL = new Random().nextInt(10);
                this.DP = new Random().nextInt(10);
            }

        }
}
