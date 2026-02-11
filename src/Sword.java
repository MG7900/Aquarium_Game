import java.awt.*;

public class Sword {

    //the idea is that when the runner picks up the sword, he can fight back against the pursuers and defeat them

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAvailable;
    public Rectangle hitbox;

    public Sword(int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        dx = 15;
        dy = 15;
        width = 40;
        height = 40;
        isAvailable = true;
        hitbox = new Rectangle(xpos, ypos, 55, 77);
    }




}
