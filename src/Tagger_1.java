import java.awt.*;

public class Tagger_1 {
    //this would be the one bouncing around
    public String name;
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public Rectangle hitbox;

    public Tagger_1(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 1;
        dy = 0;
        width = 90;
        height = 90;
        hitbox = new Rectangle(xpos, ypos, 90, 90);
    }

    public void move(){
        //this tagger warps around the map


        if(xpos < 0 || xpos > 950){
            dx = -dx;
        }

        if(ypos < 0 || ypos > 620){
            dy = -dy;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos+15, ypos, 60, 80);
    }




}
