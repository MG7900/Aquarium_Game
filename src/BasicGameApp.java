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


    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    public Tagger_1 tag1;
    public Tagger_2 tag2;
    public Runner runner;
    public Freeze_Buff freezeBuff;
    public Speed_Buff speedBuff;
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
        int randx = (int) (Math.random() * 10);
        int randy = (int) (Math.random() * 10);
        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up

        forestPic = Toolkit.getDefaultToolkit().getImage("Forest.jpg");
        Tagger1Pic = Toolkit.getDefaultToolkit().getImage("Tagger_1.jpg");
        Tagger2Pic = Toolkit.getDefaultToolkit().getImage("Tagger_2.jpg");
        RunnerPic = Toolkit.getDefaultToolkit().getImage("Frodo.jpg");
        FreezePic = Toolkit.getDefaultToolkit().getImage("Freeze.png");
        SpeedPic = Toolkit.getDefaultToolkit().getImage("Speed.png");

        tag1 = new Tagger_1(10, 100);
        tag1.dx = 10;
        tag1.dy = 50;

        tag2 = new Tagger_2(100, 10);
        tag2.dx = -10;
        tag2.dy = -10;

        runner = new Runner(500, 400);

        freezeBuff = new Freeze_Buff(100, 100);
        //add stuff!!

        speedBuff = new Speed_Buff(200,200);

        //slow Buff

    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }


    public void moveThings() {
        //calls the move( ) code in the objects
        tag1.move();
        tag2.move();
        runner.move();

        //why is this showing me errors?
        freezeBuff.move();
        speedBuff.move();
//slow Buff
        getting_FreezeBuff();
        getting_SpeedBuff();

    }

    //when the nazguls(taggers) catches frodo(the escaping main characters)
    public void catching() {
        if (tag1.hitbox.intersects(runner.hitbox) || tag2.hitbox.intersects(runner.hitbox)) {
            System.out.println("Caught Frodo!");


        }
    }

    //Below are the Buff effects and their interactions

    public void getting_FreezeBuff(){
        if (runner.hitbox.intersects(freezeBuff.hitbox)) {
            System.out.println("Frodo gets Freeze Buff");
            runner.dx = runner.dx-10;
            runner.dy = runner.dy-10;
            freezeBuff.isAvailable = false;

        }
        if (tag1.hitbox.intersects(freezeBuff.hitbox)) {
            System.out.println("Tagger 1 gets Freeze Buff");
            //for 5 seconds

            freezeBuff.isAvailable = false;
        }
        if (tag2.hitbox.intersects(freezeBuff.hitbox)) {
            System.out.println("Tagger 2 gets Freeze Buff");
            freezeBuff.isAvailable = false;

        }
    }

    public void getting_SpeedBuff(){
        if (runner.hitbox.intersects(speedBuff.hitbox)) {
            System.out.println("Frodo Speed Buffed");
            runner.dx = runner.dx + 10;
            speedBuff.isAvailable = false;

        }
        if (tag1.hitbox.intersects(speedBuff.hitbox)) {
            System.out.println("Tagger 2 Speed Buffed");
            //for 5 seconds
            tag1.dx = tag1.dx + 10;
            tag1.dy = tag1.dy + 10;
            speedBuff.isAvailable = false;

        }
        if (tag2.hitbox.intersects(speedBuff.hitbox)) {
            System.out.println("Tagger 1 Speed Buffed");
            tag2.dx = tag2.dx + 10;
            tag2.dy = tag2.dy + 10;
            speedBuff.isAvailable = false;
        }
    }

        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause ( int time ){
            //sleep
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }

        //Graphics setup method
        private void setUpGraphics () {
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
        private void render () {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);
            //draw backgroup first of all
            g.drawImage(forestPic, 0, 0, 1000, 800, null);

            g.drawImage(RunnerPic, 100, 100, tag1.width, tag1.height, null);
            //draw the image of the taggers and the forest backgrounds
            g.drawImage(Tagger1Pic, 100, 400, tag1.width, tag1.height, null);
            g.drawImage(Tagger2Pic, 500, 400, tag1.width, tag1.height, null);
            g.drawImage(FreezePic, 250, 250, freezeBuff.width, freezeBuff.height, null);
            g.drawImage(FreezePic, 250, 250, speedBuff.width, speedBuff.height, null);
            //speed buff draw image
            //slow buff draw image

            g.dispose();

            bufferStrategy.show();
        }
    }