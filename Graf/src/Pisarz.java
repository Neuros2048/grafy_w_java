
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import algorytmy.Graf;

public class Pisarz {
    Pisarz (){
    }/**
    *Pisze graf do pliku
     */
    public void napisz(int x, int y,double max,double min,String nazwa){
        FileWriter pioro;
        try {
            pioro = new FileWriter(nazwa);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        int i,ii;
        Random losowa =new Random();
        try {
        for (i = 0; i < y; i++) {
			for (ii = 0; ii < x; ii++) {
				if ((ii + 1) < x){
                    pioro.write(String.valueOf(ii + i * x + 1)+" :"+String.valueOf(losowa.nextDouble()*max+min*max+min)+"   ");
                }
				if ((i + 1) < y){
                    pioro.write(String.valueOf(ii + i * x + x)+" :"+String.valueOf(losowa.nextDouble()*max+min)+"   ");
                }
				if ((ii - 1) > -1){
                    pioro.write(String.valueOf(ii + i * x - 1)+" :"+String.valueOf(losowa.nextDouble()*max+min)+"   ");
                }
				if ((i - 1) > -1){
                    pioro.write(String.valueOf(ii + i * x - x)+" :"+String.valueOf(losowa.nextDouble()*max+min)+"   ");
                }
                
                    pioro.write("\n");
                
			}
		}
        pioro.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void napisz(double max,double min,Graf graf){
        int x = graf.dostan_wymiar_x();
        int y = graf.dostan_wymiar_y();
        int i,ii;
        Random losowa =new Random();
        for (i = 0; i < y; i++) {
			for (ii = 0; ii < x; ii++) {
				if ((ii + 1) < x){
                    graf.dodaj_droge(ii + i * x, ii + i * x + 1, losowa.nextDouble()*max+min);
                }
				if ((i + 1) < y){
                    graf.dodaj_droge(ii + i * x, ii + i * x + x, losowa.nextDouble()*max+min);
                }
				if ((ii - 1) > -1){
                    graf.dodaj_droge(ii + i * x, ii + i * x - 1, losowa.nextDouble()*max+min);
                }
				if ((i - 1) > -1){
                    graf.dodaj_droge(ii + i * x, ii + i * x - x, losowa.nextDouble()*max+min);
                }
			}
		}
    }
}
