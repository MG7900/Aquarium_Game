import java.awt.*;

public class Tagger_2 {
    //this would be the one warping around the map

    public String name;
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;

    public Rectangle hitbox;

    public Tagger_2(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 10;
        dy = 10;
        width = 110;
        height = 110;
        hitbox = new Rectangle(xpos, ypos, 120, 120);
    }

    public void move(){
        //this tagger bounces around the map
        if(xpos < 0 || xpos > 950){
            dx = -dx;
        }

        if(ypos < 0 || ypos > 620){
            dy = -dy;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, 80, 80);
    }
}
