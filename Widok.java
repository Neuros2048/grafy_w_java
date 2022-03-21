import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Widok  extends JPanel{
    int x;
    int y;
    Widok(int x,int y){
        this.x =x;
        this.y = y;
        this.setPreferredSize(new DimensionUIResource(500, 500));
    }
    public void paint(Graphics g){  //sama sie urzywa
        int i,ii;
        for (i =0 ;i<y;i++){
            for (ii = 0;ii<x;ii++){
                g.fillOval(10+ii*10, 10+i*10, 5, 5);
            }
        }
        
        
        Graphics2D g2D = (Graphics2D) g;

    }
}
