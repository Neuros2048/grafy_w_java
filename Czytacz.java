import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Czytacz  {
    Scanner skan;
    Czytacz(File plik) {
        try {
        skan = new Scanner(plik);
        } catch(FileNotFoundException e){
            System.out.println("Nie moge otworzyć pliku");
        } 
    }
    public int czytaj_int(){
        return skan.nextInt();
    }
}
