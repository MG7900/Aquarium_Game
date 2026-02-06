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
        width = 60;
        height = 60;
        hitbox = new Rectangle(xpos, ypos, 55, 77);
    }

    public void move(){
        //this tagger bounces around the map
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
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}
