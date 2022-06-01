package graf;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import graf.algorytmy.Graf;

public class Pisarz {
    public Pisarz (){
    }/**
    *Pisze graf do pliku
     */
    public boolean napisz(int x, int y,double max,double min,String nazwa){
        FileWriter pioro;
        try {
            pioro = new FileWriter(nazwa);
        } catch (IOException e) {
            return false;
        }
        int i,ii;
        Random losowa =new Random();
        try {
        pioro.write(String.valueOf(y)+" "+String.valueOf(x)+"\n");
        for (i = 0; i < y; i++) {
			for (ii = 0; ii < x; ii++) {
				if ((ii + 1) < x){
                    pioro.write(String.valueOf(ii + i * x + 1)+" :"+String.valueOf(losowa.nextDouble()*(max-min)+min)+"   ");
                }
				if ((i + 1) < y){
                    pioro.write(String.valueOf(ii + i * x + x)+" :"+String.valueOf(losowa.nextDouble()*(max-min)+min)+"   ");
                }
				if ((ii - 1) > -1){
                    pioro.write(String.valueOf(ii + i * x - 1)+" :"+String.valueOf(losowa.nextDouble()*(max-min)+min)+"   ");
                }
				if ((i - 1) > -1){
                    pioro.write(String.valueOf(ii + i * x - x)+" :"+String.valueOf(losowa.nextDouble()*(max-min)+min)+"   ");
                }
                
                    pioro.write("\n");
                
			}
		}
        pioro.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public void napisz(double max,double min,Graf graf){
        int x = graf.dostan_wymiar_x();
        int y = graf.dostan_wymiar_y();
        int i,ii;
        Random losowa =new Random();
        for (i = 0; i < y; i++) {
			for (ii = 0; ii < x; ii++) {
				if ((ii + 1) < x){
                    graf.dodaj_droge(ii + i * x, ii + i * x + 1, losowa.nextDouble()*(max-min)+min);
                }
				if ((i + 1) < y){
                    graf.dodaj_droge(ii + i * x, ii + i * x + x, losowa.nextDouble()*(max-min)+min);
                }
				if ((ii - 1) > -1){
                    graf.dodaj_droge(ii + i * x, ii + i * x - 1, losowa.nextDouble()*(max-min)+min);
                }
				if ((i - 1) > -1){
                    graf.dodaj_droge(ii + i * x, ii + i * x - x, losowa.nextDouble()*(max-min)+min);
                }
			}
		}
    }
}
