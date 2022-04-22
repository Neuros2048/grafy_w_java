import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.DimensionUIResource;


public class Widok  extends JPanel{
    int x;
    int y;
    int rozmiarx;
    int rozmiary;
    int skala;// odwortność, jak rośnie to maleje wszytko
    int Dx;
    int Dy;
    double max; //max całkowitej drogi
    double maxw; // max pojedycznej drogi
    double minw; // min pojedynczej drogi
    Wieszcholek[] graf;
    Widok(int x,int y,Wieszcholek[] graf){
        this.graf = graf;
        this.x =x;
        this.y = y;
        rozmiarx = 8;
        rozmiary = 8;
        skala = 1;
        najwieksze(graf);
        Dimension Wymiary_ekranu = Toolkit.getDefaultToolkit().getScreenSize(); // Bierze wymiary ekranu.
        Dy = Wymiary_ekranu.height/10 *9;
        Dx =  Wymiary_ekranu.width/10 * 9;
        this.setPreferredSize(new DimensionUIResource(Dx, Dy));
        
    }
    private void najwieksze(Wieszcholek[] graf){
        int i;
        max = graf[0].waga;
        maxw = graf[0].DD;
        minw = 0;
        for (i=0;i<x*y;i++){
            if(graf[i].DD!= -1){
                minw = graf[i].DD;
                break;
            }
            if(graf[i].DP!= -1){
                minw = graf[i].DP;
                break;
            }
            if(graf[i].DL!= -1){
                minw = graf[i].DL;
                break;
            }
            if(graf[i].DG!= -1){
                minw = graf[i].DG;
                break;
            }
        }
        for (i=0;i<x*y;i++){
            if (max < graf[i].waga){
                max = graf[i].waga;
            }
            if(maxw < graf[i].DD){
                maxw = graf[i].DD;
            }
            if(maxw < graf[i].DP){
                maxw = graf[i].DP;
            }
            if(maxw < graf[i].DL){
                maxw = graf[i].DL;
            }
            if(maxw < graf[i].DG){
                maxw = graf[i].DG;
            }
            if(graf[i].DD != -1 && minw > graf[i].DD){
                minw = graf[i].DD;
            }
            if(graf[i].DP != -1 && minw > graf[i].DP){
                minw = graf[i].DP;
            }
            if(graf[i].DL != -1 && minw > graf[i].DL){
                minw = graf[i].DL;
            }
            if(graf[i].DG != -1 && minw > graf[i].DG){
                minw = graf[i].DG;
            }
        }


    }
    private Color tecza(double waga){
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
    private Color tecza(double waga1,double waga2){
        int r=0,g=0,b=0;
        double d = 256/20; //magiczna stała na kolory XD
        double kolor = 100.0*((waga1+waga2)/2-minw)/(maxw-minw) ;
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
            if ((int)Math.round(d*(kolor-80))>255){
            }else{
                g = 255 - (int)Math.round(d*(kolor-80));
            }
        }
        return new Color(r,g,b);
    }
    public void paint(Graphics g){  //sama sie urzywa
        int i,ii;
        Graphics2D g2D = (Graphics2D) g; // więcej popcji graficznych
        g.setColor(Color.black);
        g.fillRect(0, 0, Dx, Dy);
        g2D.scale((double)Dx/rozmiarx/x/3*2, (double)Dy/rozmiary/y/3*2);
        int pozycja_x;
        int pozycja_y;
        int iixi;
        g2D.setStroke(new BasicStroke(1));
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiarx/8 + ii*rozmiarx/2*3;
                pozycja_y = rozmiary/8 + i*rozmiary/2*3;

                iixi = ii+i*x;
                if (graf[iixi].DP != -1){
                    g2D.setColor(tecza(graf[iixi].DP,graf[iixi+1].DL));   
                    g2D.drawLine(pozycja_x + rozmiarx, pozycja_y + rozmiary/2 , pozycja_x + rozmiarx/2*3 , pozycja_y+rozmiary/2);
                }
                if (graf[iixi].DD != -1){ 
                    g2D.setColor(tecza(graf[iixi].DD,graf[ii+(i+1)*x].DG));
                    g2D.drawLine(pozycja_x + rozmiarx/2, pozycja_y + rozmiary, pozycja_x + rozmiarx/2 , pozycja_y+rozmiary/2*3);
                }
                if (graf[iixi].DG==-1&&graf[iixi].DP==-1&&graf[iixi].DD==-1&&graf[iixi].DL==-1){
                    //tak na oko to szybciej powinno dziłać
                }else {
                    g2D.setColor(tecza(graf[iixi].waga));
                    g2D.fillOval(pozycja_x,pozycja_y, rozmiarx, rozmiary);
                }
                
            }
        }
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(Color.white);
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiarx/8 + ii*rozmiarx/2*3;
                pozycja_y = rozmiary/8 + i*rozmiary/2*3;
                if (graf[ii+i*x].status==3){
                    if (graf[ii+i*x].z== ii+i*x -1 ){   
                        g2D.drawLine(pozycja_x+rozmiarx/2, pozycja_y + rozmiary/2 , pozycja_x - rozmiarx, pozycja_y+rozmiary/2);
                    }else if (graf[ii+i*x].z== ii+i*x +1 ){   
                        g2D.drawLine(pozycja_x+rozmiarx/2, pozycja_y + rozmiary/2 , pozycja_x + rozmiarx*2 , pozycja_y+rozmiary/2);
                    }else if (graf[ii+i*x].z== ii+(i-1)*x){  
                        g2D.drawLine(pozycja_x + rozmiarx/2, pozycja_y + rozmiary/2, pozycja_x + rozmiarx/2 , pozycja_y - rozmiary);
                    }else if (graf[ii+i*x].z== ii+(i+1)*x ){   
                        g2D.drawLine(pozycja_x + rozmiarx/2, pozycja_y + rozmiary/2, pozycja_x + rozmiarx/2 , pozycja_y + rozmiary*2);
                    }
                }
                
                
            }
        }
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiarx/8 + ii*rozmiarx/2*3;
                pozycja_y = rozmiary/8 + i*rozmiary/2*3;
                
            }
        }
    }
}
