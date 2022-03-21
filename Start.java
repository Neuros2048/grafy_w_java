
public class Start {
    public static void main(String[] args) {
        int x = 21;
        int y = 37;
        Wieszcholek[] graf = new Wieszcholek[x*y];
        int i;
        for (i=0;i<x*y;i++){
            graf[i] = new Wieszcholek(i);
        }
        new Obraz(x,y);

    }
}