import java.awt.*;

//adjust for slow buff

public class Freeze_Buff {

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public static boolean isAvailable;
    public Rectangle hitbox;

    public Freeze_Buff(int pXpos, int pYpos) {
        //the freeze buff should randomize anywhere on the map at the start and after each time it is used

        //using the randx and randy now in BGA to randomize position? Do I still need the pXpos and pYpos?
        xpos = pXpos;
        ypos = pYpos;
        dx = (int) (Math.random() * 1)+1;;
        dy = (int) (Math.random() * 10)+1;
        width = 60;
        height = 60;
        isAvailable = true;
        hitbox = new Rectangle(xpos, ypos, 60, 60);
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
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}
