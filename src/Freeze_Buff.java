import java.awt.*;

public class Freeze_Buff {

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle hitbox;

    public Freeze_Buff(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 15;
        dy = 15;
        width = 60;
        height = 60;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, 55, 77);
    }

    public void move(){
        //ths freeze buff also wraps around the map
        if(ypos > 700){
            ypos = 0;
        }
        if(ypos < 0){
            ypos = 700;
        }

        if(xpos > 850){
            xpos = 0;
        }
        if(xpos < 0){
            xpos = 850;
        }
    }
}
