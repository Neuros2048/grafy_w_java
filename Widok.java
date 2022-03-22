import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Widok  extends JPanel{
    int x;
    int y;
    int rozmiarx;
    int rozmiary;
    int skala;// odwortność, jak rośnie to maleje wszytko
    int Dx;
    int Dy;
    double max;
    Wieszcholek[] graf;
    Widok(int x,int y,Wieszcholek[] graf){
        this.graf = graf;
        this.x =x;
        this.y = y;
        Dx = 1600;
        Dy = 1000;
        rozmiarx = 8;
        rozmiary = 8;
        skala = 1;
        max = x * y;
        this.setPreferredSize(new DimensionUIResource(Dx, Dy));
        skaluj();
    }
    private void skaluj() {
        int work = 1;
        while (work==1){
            work = 0;
            if (x*rozmiarx*3/2<Dx ){
                rozmiarx = rozmiarx*3/2;
                work = 1 ;
            }
            if (y*rozmiary*3/2<Dy ){
                rozmiary = rozmiary*3/2;
                work = 1;
            }
        }
    }
    private Color tecza(Double waga){
        int r=0,g=0,b=0;
        double d = 256/20; //magiczna stała na kolory XD
        double kolor = 100*waga/max;
        if (kolor<20){
            r = 255 - (int)Math.round(d*kolor);
            b = 255;
        }else if (kolor<40){
            g = (int)Math.round(d*(kolor-20));
            b = 255;
        }else if (kolor <60){
            g =255;
            b = 255 - (int)Math.round(d*(kolor-40));
        }else if (kolor <80){
            r = (int)Math.round(d*(kolor-60));
            g = 255;
        }else {
            r = 255;
            g = 255 - (int)Math.round(d*(kolor-80));
        }
        return new Color(r,g,b);
    }
    public void paint(Graphics g){  //sama sie urzywa
        int i,ii;
        
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                g.setColor(tecza(graf[ii+i*x].waga));
                g.fillRect(ii*rozmiarx, i*rozmiary, rozmiarx, rozmiary);
            }
        }
        g.setColor(new Color(0,0,0));
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                g.fillOval(rozmiarx/4 + ii*rozmiarx,rozmiary/4 + i*rozmiary, rozmiarx/2, rozmiary/2);
            }
        }
        
        
        Graphics2D g2D = (Graphics2D) g;

    }
}
