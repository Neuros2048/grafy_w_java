import javax.swing.JFrame;

public class Obraz extends JFrame {
    Widok panel;
    Obraz(int x,int y){
        panel = new Widok(x,y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
