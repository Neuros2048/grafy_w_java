//import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.plaf.DimensionUIResource;

import javafx.scene.image.Image;


public class Widok { //extends JPanel{
    
    int x;
    int y;
    int rozmiar;
    int skala;// odwortność, jak rośnie to maleje wszytko
    int Dx;
    int Dy;
    double max; //max całkowitej drogi
    double maxw; // max pojedycznej drogi
    double minw; // min pojedynczej drogi
    BufferedImage obrazek;
    Graf graf;
    Widok(int x,int y,Graf graf,int Dx,int Dy,int rozmiar){
        this.graf = graf;
        this.x =x;
        this.y = y;
        this.rozmiar = rozmiar;
        skala = 1;
        
        this.Dx = Dx  ;
        this.Dy = Dy;
        this.obrazek = new BufferedImage(this.Dx,this.Dy,BufferedImage.TYPE_INT_RGB );
        paint();
    }
    public BufferedImage pomalowane(){
        return obrazek;
    }
    private void najwieksze(){
        int i;
        max = graf.dostan_waga(0) ;
        maxw = graf.dostan_droge(0, 1);
        minw = 0;
        for (i=0;i<x*y;i++){
            if(graf.dostan_droge(i, 1)!= -1){
                minw = graf.dostan_droge(i, 1);
                break;
            }
            if(graf.dostan_droge(i, 2)!= -1){
                minw = graf.dostan_droge(i, 2);
                break;
            }
            if(graf.dostan_droge(i, 3)!= -1){
                minw = graf.dostan_droge(i, 3);
                break;
            }
            if(graf.dostan_droge(i, 4)!= -1){
                minw = graf.dostan_droge(i, 4);
                break;
            }
        }
        for (i=0;i<x*y;i++){
            if (max < graf.dostan_waga(i)){
                max = graf.dostan_waga(i);
            }
            for(int od_1_do_4 = 1;od_1_do_4<5;od_1_do_4++ ){
                if(maxw < graf.dostan_droge(i,od_1_do_4)){
                    maxw = graf.dostan_droge(i,od_1_do_4);
                }
                if(graf.dostan_droge(i,od_1_do_4) != -1 && minw > graf.dostan_droge(i,od_1_do_4)){
                    minw = graf.dostan_droge(i,od_1_do_4);
                }
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
    public void paint(){  //sama sie urzywa
        najwieksze();
        Graphics2D g2D = pomalowane().createGraphics();
        int i,ii;
        g2D.setColor(Color.black);
        g2D.fillRect(0, 0, Dx, Dy);
        g2D.scale((double)Dx/rozmiar/x/3*2, (double)Dy/rozmiar/y/3*2);
        int pozycja_x;
        int pozycja_y;
        int iixi;
        g2D.setStroke(new BasicStroke(1));
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiar/8 + ii*rozmiar/2*3;
                pozycja_y = rozmiar/8 + i*rozmiar/2*3;

                iixi = ii+i*x;
                if (graf.dostan_droge(iixi,  3) != -1){
                    g2D.setColor(tecza(graf.dostan_droge(iixi, 3 ),graf.dostan_droge(iixi+1, 1 ) ));   
                    g2D.drawLine(pozycja_x + rozmiar, pozycja_y + rozmiar/2 , pozycja_x + rozmiar/2*3 , pozycja_y+rozmiar/2);
                }
                if (graf.dostan_droge(iixi,4  ) != -1){ 
                    g2D.setColor(tecza(graf.dostan_droge(iixi, 4 ),graf.dostan_droge(ii+(i+1)*x,2)));
                    g2D.drawLine(pozycja_x + rozmiar/2, pozycja_y + rozmiar, pozycja_x + rozmiar/2 , pozycja_y+rozmiar/2*3);
                }
                if (graf.dostan_droge(iixi,  2)==-1&&graf.dostan_droge(iixi,3  )==-1&&graf.dostan_droge(iixi, 4 )==-1&&graf.dostan_droge(iixi, 1 )==-1){
                    //tak na oko to szybciej powinno dziłać
                }else {
                    g2D.setColor(tecza(graf.dostan_waga(iixi)));
                    g2D.fillOval(pozycja_x,pozycja_y, rozmiar, rozmiar);
                }
                
            }
        }
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(Color.white);
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiar/8 + ii*rozmiar/2*3;
                pozycja_y = rozmiar/8 + i*rozmiar/2*3;
                if (graf.dostan_status(ii+i*x)==3){
                    if (graf.dostan_droge_z(ii+i*x)== ii+i*x -1 ){   
                        g2D.drawLine(pozycja_x+rozmiar/2, pozycja_y + rozmiar/2 , pozycja_x - rozmiar, pozycja_y+rozmiar/2);
                    }else if (graf.dostan_droge_z(ii+i*x)== ii+i*x +1 ){   
                        g2D.drawLine(pozycja_x+rozmiar/2, pozycja_y + rozmiar/2 , pozycja_x + rozmiar*2 , pozycja_y+rozmiar/2);
                    }else if (graf.dostan_droge_z(ii+i*x)== ii+(i-1)*x){  
                        g2D.drawLine(pozycja_x + rozmiar/2, pozycja_y + rozmiar/2, pozycja_x + rozmiar/2 , pozycja_y - rozmiar);
                    }else if (graf.dostan_droge_z(ii+i*x)== ii+(i+1)*x ){   
                        g2D.drawLine(pozycja_x + rozmiar/2, pozycja_y + rozmiar/2, pozycja_x + rozmiar/2 , pozycja_y + rozmiar*2);
                    }
                    
                }
                
                
            }
        }
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiar/8 + ii*rozmiar/2*3;
                pozycja_y = rozmiar/8 + i*rozmiar/2*3;
                
            }
        }
    }
    public void pomaluj_wynik(){
        Graphics2D g2D = pomalowane().createGraphics();
        int i,ii;
        g2D.scale((double)Dx/rozmiar/x/3*2, (double)Dy/rozmiar/y/3*2);
        int pozycja_x;
        int pozycja_y;
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(Color.white);
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiar/8 + ii*rozmiar/2*3;
                pozycja_y = rozmiar/8 + i*rozmiar/2*3;
                if (graf.dostan_status(ii+i*x)==3){
                    if (graf.dostan_droge_z(ii+i*x)== ii+i*x -1 ){   
                        g2D.drawLine(pozycja_x+rozmiar/2, pozycja_y + rozmiar/2 , pozycja_x - rozmiar, pozycja_y+rozmiar/2);
                    }else if (graf.dostan_droge_z(ii+i*x)== ii+i*x +1 ){   
                        g2D.drawLine(pozycja_x+rozmiar/2, pozycja_y + rozmiar/2 , pozycja_x + rozmiar*2 , pozycja_y+rozmiar/2);
                    }else if (graf.dostan_droge_z(ii+i*x)== ii+(i-1)*x){  
                        g2D.drawLine(pozycja_x + rozmiar/2, pozycja_y + rozmiar/2, pozycja_x + rozmiar/2 , pozycja_y - rozmiar);
                    }else if (graf.dostan_droge_z(ii+i*x)== ii+(i+1)*x ){   
                        g2D.drawLine(pozycja_x + rozmiar/2, pozycja_y + rozmiar/2, pozycja_x + rozmiar/2 , pozycja_y + rozmiar*2);
                    }
                }
                
                
            }
        }
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                pozycja_x = rozmiar/8 + ii*rozmiar/2*3;
                pozycja_y = rozmiar/8 + i*rozmiar/2*3;
                
            }
        }

    }
}
