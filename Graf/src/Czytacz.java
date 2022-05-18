import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import algorytmy.Graf;

public class Czytacz  {
    private Scanner skan;
    private Scanner skanlini;
    
    public boolean dodaj_plik(String File_name){
        Scanner skan;
        try {
            skan = new Scanner(new File(File_name));
            
        } catch (FileNotFoundException e) {
            return false;
        }
        this.skan = skan;
        return true;
    }
    public int czytaj_int(){
        return skan.nextInt();
    }
    
    public void wypelnij(Graf graf,int x,int y){
        int i;
        int xy =x*y;
        int polaczenie;
        String c ="ds";
        Double waga;
        skan.nextLine();
        for (i=0;i<xy;i++){
            if (!skan.hasNextLine()){
                break;
            }
            skanlini = new Scanner(skan.nextLine());
            while (skanlini.hasNextInt()){
                polaczenie = skanlini.nextInt();
                c = skanlini.next();
                if (c==":"){
                    System.out.println("Niewłaściwy format pliku");
                    return ;
                }
                waga = Double.parseDouble(c.substring(1));
                if(waga < 0 ){
                    System.out.println("Waga drogi nie możebyć ujemna a ma wartośc "+waga);
                    return;
                }
                if(!graf.dodaj_droge(i, polaczenie, waga)){
                    System.out.println("Niewłaściwe połączeie wieszchołków");
                    return;
                }
                
            }
            if (skanlini.hasNext()){
                System.out.println("Niewłaściwy format pliku 2");
                return;
            }
            
        }
    }
}
