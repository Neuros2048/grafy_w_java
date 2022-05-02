import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import algorytmy.Graf;


public class Czytacz  {
    Scanner skan;
    Scanner skanlini;
    FileReader czytaj;
    Czytacz(Scanner skan) {
        this.skan = skan;

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
                //System.out.println(polaczenie+"ds"+i);
                graf.dodaj_droge(i, polaczenie, waga);
                
            }/*
            //Linia =skan.nextLine();
            //polaczenie = Linia.
            for(j=0;j<4;j++){
               // if (skan.)
            }
            liter = 's';
            if (skan.hasNextInt()){
                polaczenie = skan.nextInt();
                if (skan.hasNext()){
                    System.out.println("dsadasdasda");
                }
                c = skan.next();
                
                
                //c = skan.nextLine();
                
            }
            System.out.println(polaczenie);
            System.out.printf("%s\n",c.charAt(0));
            System.out.printf("%c\n",liter);
            waga = Double.parseDouble(c.substring(1));
            System.out.println(waga);
            System.out.println(c.substring(1));*/
            
        }
    }
}
