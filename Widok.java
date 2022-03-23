import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.plaf.DimensionUIResource;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

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
        rozmiarx = 8;
        rozmiary = 8;
        skala = 1;
        max = x * y;
        Dimension Wymiary_ekranu = Toolkit.getDefaultToolkit().getScreenSize(); // Bierze wymiary ekranu.
        Dy = Wymiary_ekranu.height/10 *9;
        Dx =  Wymiary_ekranu.width/10 * 9;
        this.setPreferredSize(new DimensionUIResource(Dx, Dy));
        
        
        //skaluj();
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
            r = 150 - (int)Math.round(d*kolor)/2;
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
        Graphics2D g2D = (Graphics2D) g; // więcej popcji graficznych
        g.setColor(Color.black);
        g.fillRect(0, 0, Dx, Dy);
        //g2D.scale(1, 0.5);
        g2D.scale((double)Dx/rozmiarx/x/3*2, (double)Dy/rozmiary/y/3*2);
        System.out.println((double)Dx/rozmiarx/x/3*2 + (double)Dy/rozmiary/y/3*2);
        //g2D.scale(1, 1);
        //g.setColor(new Color(0,0,0));
        /*
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                g.setColor(tecza(graf[ii+i*x].waga));
                g.fillRect(ii*rozmiarx, i*rozmiary, rozmiarx, rozmiary);
            }
        }*/
        
        int pozycja_x;
        int pozycja_y;
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiarx/8 + ii*rozmiarx/2*3;
                pozycja_y = rozmiary/8 + i*rozmiary/2*3;

                g2D.setColor(Color.red);
                if (graf[ii+i*x].DP!= -1){   
                    g2D.drawLine(pozycja_x + rozmiarx, pozycja_y + rozmiary/2 , pozycja_x + rozmiarx/2*3 , pozycja_y+rozmiary/2);
                }
                if (graf[ii+i*x].DD!= -1){   
                    g2D.drawLine(pozycja_x + rozmiarx/2, pozycja_y + rozmiary, pozycja_x + rozmiarx/2 , pozycja_y+rozmiary/2*3);
                }

                

                g2D.setColor(tecza(graf[ii+i*x].waga));
                g2D.fillOval(pozycja_x,pozycja_y, rozmiarx, rozmiary);
                
            }
        }
        g2D.setColor(Color.white);
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiarx/8 + ii*rozmiarx/2*3;
                pozycja_y = rozmiary/8 + i*rozmiary/2*3;
                
                if (graf[ii+i*x].x== ii+i*x -1 ){   
                    g2D.drawLine(pozycja_x, pozycja_y + rozmiary/2 , pozycja_x - rozmiarx/2 , pozycja_y+rozmiary/2);
                }else if (graf[ii+i*x].x== ii+i*x +1 ){   
                    g2D.drawLine(pozycja_x+rozmiarx, pozycja_y + rozmiary/2 , pozycja_x + rozmiarx/2*3 , pozycja_y+rozmiary/2);
                }else if (graf[ii+i*x].x== ii+(i-1)*x){   
                    g2D.drawLine(pozycja_x + rozmiarx/2, pozycja_y, pozycja_x + rozmiarx/2 , pozycja_y - rozmiary/2);
                }else if (graf[ii+i*x].x== ii+(i+1)*x ){   
                    g2D.drawLine(pozycja_x + rozmiarx/2, pozycja_y + rozmiary, pozycja_x + rozmiarx/2 , pozycja_y + rozmiary/2*3);
                }
                
                
            }
        }
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiarx/8 + ii*rozmiarx/2*3;
                pozycja_y = rozmiary/8 + i*rozmiary/2*3;
                g2D.setColor(Color.white);
                
            }
        }
        
        System.out.println("end is now!");
        
        
        

    }
}
