import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import algorytmy.Graf;


public class Czytacz  {
    Scanner skan;
    Scanner skanlini;
    FileReader czytaj;
    
    public boolean dodaj_plik(String File_name){
        Scanner skan;
        try {
            skan = new Scanner(new File(File_name));
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
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
            
        }
    }
}
