package demolition;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

class RedEnemy extends Enemy {

    String direction = "UP";

    public RedEnemy(int x, int y, PImage sprite) {
        super(x, y, sprite);

    }

    public void doRandomTurn(){
        Random rand = new Random();
        int directionInt = rand.nextInt(4);
        directionInt += 1;

        System.out.println(directionInt);

        if (directionInt == 1){
            direction = "UP";
        } else if (directionInt == 2) {
            direction = "DOWN";
        } else if (directionInt == 3) {
            direction = "LEFT";
        } else if (directionInt == 4) {
            direction = "RIGHT";
        }


    }

    public static void draw(PApplet pApplet, float x, float y) {
        PImage sprite = pApplet.loadImage("src/main/resources/red_enemy/red_down1.png");
        pApplet.image(sprite, x, y);
    }

}
