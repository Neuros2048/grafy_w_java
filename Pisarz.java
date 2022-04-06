import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Pisarz {
    FileWriter pioro;
    Pisarz (String nazwa){
        try {
            pioro = new FileWriter(nazwa);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void napisz(int x, int y){
        int i,ii;
        Random losowa =new Random();
        try {
        for (i = 0; i < y; i++) {
			for (ii = 0; ii < x; ii++) {
				if ((ii + 1) < x){
                    pioro.write(String.valueOf(ii + i * x + 1)+" :"+String.valueOf(losowa.nextDouble())+"   ");
                }
				if ((i + 1) < y){
                    pioro.write(String.valueOf(ii + i * x + x)+" :"+String.valueOf(losowa.nextDouble())+"   ");
                }
				if ((ii - 1) > -1){
                    pioro.write(String.valueOf(ii + i * x - 1)+" :"+String.valueOf(losowa.nextDouble())+"   ");
                }
				if ((i - 1) > -1){
                    pioro.write(String.valueOf(ii + i * x - x)+" :"+String.valueOf(losowa.nextDouble())+"   ");
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
}
