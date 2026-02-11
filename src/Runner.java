import java.awt.*;

public class Runner {


    //TAG GAME
    //a forest background
    //make buffs that takes the form of boxes that floats around
    //freeze buff: Any character that comes into contact of it would freeze for a certain seconds
    //slow/speed up buff: Any character that comes into contact of it would have their dx and dy speed up or slow down
    //tagger: multiple characters that moves towards the main character: Movements TBD
    //main character: bounces around the forest background
    //heath bar of main character: has a certain amount of lives, getting tagged deducts

    public String name;
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isCaught;
    public int lives;
    public Rectangle hitbox;

    public Runner(int pXpos, int pYpos) {
        //this will be temporary following the LOTR storylines until I think of a better idea
        name = "Frodo";
        xpos = pXpos;
        ypos = pYpos;
        dx = 1;
        dy = 0;
        width = 110;
        height = 90;
        isCaught = false;
        lives = 3;
        //when the runner has been caught 3 times, the game ends

        hitbox = new Rectangle(xpos, ypos, 40, 80);
    }

    public void move(){
        //the runner shall also warp around the map
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
        hitbox = new Rectangle(xpos, ypos+20, 60, 70);
    }
}
