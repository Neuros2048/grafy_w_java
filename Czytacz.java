import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Czytacz  {
    Scanner skan;
    Scanner skanlini;
    FileReader czytaj;
    Czytacz(File plik) {
        try {
        skan = new Scanner(plik);
        czytaj = new FileReader(plik);
        } catch(FileNotFoundException e){
            System.out.println("Nie moge otworzyć pliku");
        } 
    }
    public int czytaj_int(){
        return skan.nextInt();
    }

    public void wypelnij(Wieszcholek[] graf,int x,int y){
        int i,j;
        int xy =x*y;
        int polaczenie = 32;
        String c ="ds";
        String Linia;
        char liter;
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
                if (polaczenie == i-1){
                    graf[i].DL = waga;
                }else if(polaczenie==i+1){
                    graf[i].DP = waga;
                }else if (polaczenie == i+x){
                    graf[i].DD = waga;
                }else if (polaczenie == i -x){
                    graf[i].DG = waga;
                }else{
                    System.out.println("Nie właścoiwe połaczenmoie");
                }
                
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
