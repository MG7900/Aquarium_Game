public class Aquarium_Game {


    //tag game

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
    public boolean isAlive;

    public Aquarium_Game(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =1;
        dy =0;
        width = 60;
        height = 60;
        isAlive = true;
}
