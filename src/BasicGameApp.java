//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Image forestPic;
    public Image Tagger1Pic;
    public Image Tagger2Pic;
    public Image RunnerPic;
    public Image FreezePic;
    public Image SpeedPic;
    public Image BeginningPic;
    public Image CapturedPic;
    public Image EscapingPic;
    public Image GameOverPic;

    //time counter
    public long startTime = System.currentTimeMillis();

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    public Tagger_1 tag1;
    public Tagger_2 tag2;
    public Runner runner;
    public Freeze_Buff freezeBuff;
    public Speed_Buff speedBuff;
    public Life life;
    //public Slow_Buff slowBuff;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {
        int randx = (int) (Math.random() * 700);
        int randy = (int) (Math.random() * 1000);
        setUpGraphics();


        //variable and objects
        //create (construct) the objects needed for the game and load up


        forestPic = Toolkit.getDefaultToolkit().getImage("Forest.jpg");
        Tagger1Pic = Toolkit.getDefaultToolkit().getImage("Tagger_1.jpg");
        Tagger2Pic = Toolkit.getDefaultToolkit().getImage("Tagger_2.png");
        RunnerPic = Toolkit.getDefaultToolkit().getImage("Frodo.jpg");
        FreezePic = Toolkit.getDefaultToolkit().getImage("Freeze.png");
        SpeedPic = Toolkit.getDefaultToolkit().getImage("Speed.png");
        //scenes below to be played
        BeginningPic = Toolkit.getDefaultToolkit().getImage("Beginning.png");
//        CapturedPic = Toolkit.getDefaultToolkit().getImage("First Capture.png");
//        EscapingPic = Toolkit.getDefaultToolkit().getImage("Escaping.png");
        GameOverPic = Toolkit.getDefaultToolkit().getImage("Game Over.png");

        tag1 = new Tagger_1((int) (Math.random() * 700), (int) (Math.random() * 500));
        tag1.dy = (int) (Math.random() * 5) - 3;

        tag2 = new Tagger_2((int) (Math.random() * 600), (int) (Math.random() * 500));
        tag2.dx = (int) (Math.random() * 5) - 3;
        tag2.dy = (int) (Math.random() * 5) - 3;

        runner = new Runner((int) (Math.random() * 800), (int) (Math.random() * 700));
        runner.dx = (int) (Math.random() * 5) - 2;
        runner.dy = -5;


        freezeBuff = new Freeze_Buff((int) (Math.random() * 800), (int) (Math.random() * 700));
        freezeBuff.dx = 5;
        freezeBuff.dy = (int) (Math.random() * 5) - 3;


        life = new Life();

        speedBuff = new Speed_Buff((int) (Math.random() * 800), (int) (Math.random() * 700));
        speedBuff.dx = (int) (Math.random() * 5) - 3;
        speedBuff.dy = 5;

    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //starts the counter outside of loop to avoid infinite delay
        long y = System.currentTimeMillis();
        //for the moment we will loop things forever.
        while (true) {

            long elapsedy = System.currentTimeMillis() - y;
            render();

            if (elapsedy > 3000) {

                moveThings();  //move all the game objects
                pause(20); // sleep for 10 ms
            }
        }
    }


    public void moveThings() {
        //calls the move( ) code in the objects
        tag1.move();
        tag2.move();
        catching();
        taggers_crashing();
        runner.move();

        //Below are the buffs
        freezeBuff.move();
        speedBuff.move();
        getting_FreezeBuff();
        getting_SpeedBuff();

    }

    //below makes sure that the taggers would bounce off eachother
    public void taggers_crashing() {
        if (tag1.hitbox.intersects(tag2.hitbox)) {
            System.out.println("Taggers crashing!");
            System.out.println("Tag1: " + tag1.xpos + tag1.ypos);
            System.out.println("Tag2: " + tag2.xpos + tag2.ypos);


            //center the xpos and ypos of the objects intp the center
            int x = Math.abs(tag1.xpos - tag2.xpos);
            int y = Math.abs(tag1.ypos - tag2.ypos);

            //the taggers will bounce off of each other based on the direction they interact
            if (x > y) {
                //here EXPLANATION
                tag1.dx = -tag1.dx;
                tag2.dx = -tag2.dx;
            } else if (x < y) {
                //here EXPLANATION
                tag1.dy = -tag1.dy;
                tag2.dy = -tag2.dy;
            }
        }
    }

    //when the nazguls(taggers) catches frodo(the escaping main characters)
    public void catching() {
        if (tag1.hitbox.intersects(runner.hitbox) || tag2.hitbox.intersects(runner.hitbox)) {
            System.out.println("Caught Frodo!");
            //add some consequences e.g. a heart disapperas etc;
            life.lost_life = life.lost_life + 1;
            //add animation for lost life lol in render
        }
    }

    //    //Below are the Buff effects and their interactions
//
    public void getting_FreezeBuff() {
        if (runner.hitbox.intersects(freezeBuff.hitbox)) {
            System.out.println("Frodo gets Freeze Buff");

            //set a counter as a substitute for a timer
            long u = System.currentTimeMillis();
            long elapsedu = System.currentTimeMillis() - u;

            if (elapsedu < 3000) {
                runner.dx = 0;
                runner.dy = 0;
            }

        }
        if (tag1.hitbox.intersects(freezeBuff.hitbox)) {
            System.out.println("Tagger 1 gets Freeze Buff");
            long m = System.currentTimeMillis();
            long elapsedm = System.currentTimeMillis() - m;

            if (elapsedm < 3000) {
                tag1.dx = 0;
                tag1.dy = 0;
            }


        }
        if (tag2.hitbox.intersects(freezeBuff.hitbox)) {
            System.out.println("Tagger 2 gets Freeze Buff");
            long j = System.currentTimeMillis();
            long elapsedj = System.currentTimeMillis() - j;

            if (elapsedj < 3000) {
                tag2.dx = 0;
                tag2.dy = 0;
            }


        }
    }

    //
    public void getting_SpeedBuff() {
        if (runner.hitbox.intersects(speedBuff.hitbox)) {
            System.out.println("Frodo Speed Buffed");
            long d = System.currentTimeMillis();
            long elapsedd = System.currentTimeMillis() - d;

            if (elapsedd < 3000) {
                runner.dx = runner.dx + 5;
                runner.dy = runner.dy + 3;
            }
        }
        if (tag1.hitbox.intersects(speedBuff.hitbox)) {
            System.out.println("Tagger 2 Speed Buffed");
            long g = System.currentTimeMillis();
            long elapsedg = System.currentTimeMillis() - g;

            if (elapsedg < 3000) {
                tag1.dx = tag1.dx + 3;
                tag1.dy = tag1.dy + 4;
            }


        }
        if (tag2.hitbox.intersects(speedBuff.hitbox)) {
            System.out.println("Tagger 1 Speed Buffed");
            long h = System.currentTimeMillis();
            long elapsedh = System.currentTimeMillis() - h;

            if (elapsedh < 3000) {
                tag2.dx = tag2.dx + 3;
                tag2.dy = tag2.dy + 3;
            }

        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

        //about a few seconds to show beginning game background
        long elapsedz = System.currentTimeMillis() - startTime;

        if (elapsedz < 3000) {
            g.drawImage(BeginningPic, 0, 0, 1000, 800, null);
        } else {
            g.drawImage(forestPic, 0, 0, 1000, 800, null);
        }
            if (life.lost_life == 0) {
                g.drawImage(RunnerPic, runner.xpos, runner.ypos, runner.width, runner.height, null);
                g.drawRect(runner.hitbox.x, runner.hitbox.y, runner.hitbox.width, runner.hitbox.height);

                //draw the image of the taggers and the forest backgrounds
                g.drawImage(Tagger1Pic, tag1.xpos, tag1.ypos, tag1.width, tag1.height, null);
                g.drawRect(tag1.hitbox.x, tag1.hitbox.y, tag1.hitbox.width, tag1.hitbox.height);

                g.drawImage(Tagger2Pic, tag2.xpos, tag2.ypos, tag2.width, tag2.height, null);
                g.drawRect(tag2.hitbox.x, tag2.hitbox.y, tag2.hitbox.width, tag2.hitbox.height);

                //current issue, no response when characters interact with the freeze buff 2026/2/11
                g.drawImage(FreezePic, freezeBuff.xpos, freezeBuff.ypos, freezeBuff.width, freezeBuff.height, null);
                g.drawRect(freezeBuff.hitbox.x, freezeBuff.hitbox.y, freezeBuff.hitbox.width, freezeBuff.hitbox.height);

                g.drawImage(SpeedPic, speedBuff.xpos, speedBuff.ypos, speedBuff.width, speedBuff.height, null);
                g.drawRect(speedBuff.hitbox.x, speedBuff.hitbox.y, speedBuff.hitbox.width, speedBuff.hitbox.height);

            } else {
                //clear everything and draw game over
                g.clearRect(0, 0, WIDTH, HEIGHT);
                g.drawImage(GameOverPic, 0, 0, WIDTH, HEIGHT, null);
            }
            g.dispose();
            bufferStrategy.show();
        }

}

