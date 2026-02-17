import java.awt.*;

public class Speed_Buff {

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public Rectangle hitbox;

    public Speed_Buff(int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        dx = 15;
        dy = 15;
        width = 60;
        height = 60;
        hitbox = new Rectangle(xpos, ypos, 55, 77);
    }

    public void move(){
        //ths freeze buff also wraps around the map
        if(xpos < 0 || xpos > 950){
            dx = -dx;
        }

        if(ypos < 0 || ypos > 620){
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, 55, 60);
    }
}
