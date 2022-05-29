package graf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import graf.algorytmy.*;

public class Testy {
    @Test
    public void Test_graf_wielkość(){
        Graf graf = new Graf();
        graf.dodaj_graf(5, 5);
        assertEquals(25, graf.dlugosc_grafu());
    }
    @Test
    public void Test_graf_obliczania_wagi_drogi_po_wieszchołkach(){
        Graf graf = new Graf();
        graf.dodaj_graf(4, 4);
        int pozycja1 = 5;
        int pozycja2 = 9;
        int pozycja3 = 10;
        int pozycja4 = 6;
        int waga = 7;
        graf.ustaw_wage(pozycja2, pozycja1, waga);
        graf.ustaw_wage(pozycja3, pozycja2, waga+1);
        graf.ustaw_wage(pozycja4, pozycja3, waga+2);
        graf.ustaw_wage(pozycja1, pozycja4, waga+3);
        assertTrue(waga*4+1+2+3== graf.dostan_waga(pozycja1));
    }
    @Test
    public void Test_graf_dodawania_zlych_drog(){
        Graf graf = new Graf();
        graf.dodaj_graf(3, 3);
        assertEquals(false, graf.dodaj_droge(0, -1, 1));
        assertEquals(false, graf.dodaj_droge(2, -4,1));
        assertEquals(false, graf.dodaj_droge(6, 5,1));
        assertEquals(false, graf.dodaj_droge(8, 11, 1));
        assertEquals(false, graf.dodaj_droge(0, 8,1));
        assertEquals(false, graf.dodaj_droge(-1, 0, 1));
        assertEquals(false, graf.dodaj_droge(9, 8, 1));
        assertEquals(false, graf.dodaj_droge(6, 6, 1));
        assertEquals(false, graf.dodaj_droge(0, 1, -2));
    }
    @Test
    public void Test_graf_dodawania_dobrych_drog(){
        Graf graf = new Graf();
        graf.dodaj_graf(2, 2);
        assertEquals(true, graf.dodaj_droge(0, 1, 1));
        assertEquals(true, graf.dodaj_droge(1, 0, 1));
        assertEquals(true, graf.dodaj_droge(0, 2, 1));
        assertEquals(true, graf.dodaj_droge(2, 0, 1));
        assertEquals(true, graf.dodaj_droge(2, 3, 1));
        assertEquals(true, graf.dodaj_droge(3, 2, 1));
        assertEquals(true, graf.dodaj_droge(3, 1, 1));
        assertEquals(true, graf.dodaj_droge(1, 3, 1));
    }
    @Test
    public void Test_graf_ogolna_zgodnosc(){
        Graf graf = new Graf();
        graf.dodaj_graf(6, 6);
        assertEquals(6, graf.dostan_wymiar_x());
        assertEquals(6, graf.dostan_wymiar_y());
        graf.dodaj_droge(7, 8, 15.8721);
        assertTrue(15.8721==graf.dostan_droge(7,3));
        graf.ustaw_status(23, 7);
        assertEquals(7, graf.dostan_status(23));
        graf.ustaw_droge_z(11, 12);
        assertEquals(12, graf.dostan_droge_z(11));
        graf.ustaw_wage(4, 3, 17.324);
        //assertEquals(17.324, graf.dostan_waga(4));
        assertTrue(17.324==graf.dostan_waga(4));
    }
    @Test
    public void Test_Algorytmu_przechodzenia_BFS(){
        Algorytm_przechodzenia algorytm = new BFS();
        Graf graf = new Graf();
        graf.dodaj_graf(4, 4);
        for(int i = 0;i <4;i++){
            for(int j = 0;j<4;j++ ){
                graf.dodaj_droge( i+j*4, i+1+j*4,i+1+j*4);
                graf.dodaj_droge( i+j*4,i-1+j*4, i+1+j*4);
                graf.dodaj_droge( i+j*4, i+4+j*4,i+1+j*4);
                graf.dodaj_droge( i+j*4,i-4+j*4, i+1+j*4);
            }
        }
        algorytm.dodaj_dane(graf);
        algorytm.rozwiarz(0);
        assertTrue(1==graf.dostan_waga(1));
        assertTrue(6==graf.dostan_waga(15));
        //assertEquals(1, graf.dostan_waga(1));
        //assertEquals(6, graf.dostan_waga(15));
    }
    @Test
    public void Test_Dijkstry_Fibonacci_(){
        Dijkstra algorytm = new Fibonacci();
        Graf graf = new Graf();
        graf.dodaj_graf(4, 4);
        for(int i = 0;i <4;i++){
            for(int j = 0;j<4;j++ ){
                graf.dodaj_droge( i+j*4, i+1+j*4,i+1+j*4);
                graf.dodaj_droge( i+j*4,i-1+j*4, i+1+j*4);
                graf.dodaj_droge( i+j*4, i+4+j*4,i+1+j*4);
                graf.dodaj_droge( i+j*4,i-4+j*4, i+1+j*4);
            }
        }
        algorytm.dodaj_dane(graf);
        algorytm.rozwiarz(0);
        assertTrue(1==graf.dostan_waga(1));
        assertTrue(30==graf.dostan_waga(15));
        //assertEquals(1, graf.dostan_waga(1));
        //assertEquals(30, graf.dostan_waga(15));

    }@Test
    public void Test_Dijkstry_Kolejki_(){
        Dijkstra algorytm = new Kolejka();
        Graf graf = new Graf();
        graf.dodaj_graf(4, 4);
        for(int i = 0;i <4;i++){
            for(int j = 0;j<4;j++ ){
                graf.dodaj_droge( i+j*4, i+1+j*4,i+1+j*4);
                graf.dodaj_droge( i+j*4,i-1+j*4, i+1+j*4);
                graf.dodaj_droge( i+j*4, i+4+j*4,i+1+j*4);
                graf.dodaj_droge( i+j*4,i-4+j*4, i+1+j*4);
            }
        }
        algorytm.dodaj_dane(graf);
        algorytm.rozwiarz(0);
        assertTrue(1==graf.dostan_waga(1));
        assertTrue(30==graf.dostan_waga(15));
        //assertEquals(1, graf.dostan_waga(1));
        //assertEquals(30, graf.dostan_waga(15));
    }
    @Test
    public void Test_Pisarz_(){
        Graf graf = new Graf();
        graf.dodaj_graf(100, 100);
        Pisarz pisz = new Pisarz();
        pisz.napisz(10.234, 3.718, graf);
        for(int i = 0;i < graf.dlugosc_grafu();i++){
            if(i%100!=0){
                assertEquals(true, graf.dostan_droge(i, 1)>=3.718&&graf.dostan_droge(i, 1)<=10.234);
            }
            if(i-100>0){
                assertEquals(true, graf.dostan_droge(i, 2)>=3.718&&graf.dostan_droge(i, 2)<=10.234);
            }
            if(i%100!=99){
                assertEquals(true, graf.dostan_droge(i, 3)>=3.718&&graf.dostan_droge(i, 3)<=10.234);
            }
            if(i+100<10000){
                assertEquals(true, graf.dostan_droge(i, 4)>=3.718&&graf.dostan_droge(i, 4)<=10.234);
            }
            
        }
    }
    
}