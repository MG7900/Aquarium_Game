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
    public boolean isAlive;
    public Rectangle hitbox;

    public Tagger_2(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 10;
        dy = 10;
        width = 60;
        height = 60;
        isAlive = true;
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
    }
}
