package demolition;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

class RedEnemy extends Enemy {

    private static final String SPRITE_PATH = "src/main/resources/red_enemy/red_down1.png";

    public RedEnemy(int x, int y, PApplet pApplet) {
        super(x, y);
        if (pApplet != null) {
            this.sprite = pApplet.loadImage(SPRITE_PATH);
        }
    }

    public void doRandomTurn(){
//        Random rand = new Random();
//        int directionInt = rand.nextInt(4);
//        directionInt += 1;
//
//        System.out.println(directionInt);
//
//        if (directionInt == 1){
//            direction = "UP";
//        } else if (directionInt == 2) {
//            direction = "DOWN";
//        } else if (directionInt == 3) {
//            direction = "LEFT";
//        } else if (directionInt == 4) {
//            direction = "RIGHT";
//        }

    }

    @Override
    public Direction takeTurn(Direction direction){  // random turn
        Random rand = new Random();
        int order = (direction.ordinal() + rand.nextInt(4)) % 4;
        return Direction.values()[order];
    };

    public static void draw(PApplet pApplet, float x, float y) {
        PImage sprite = pApplet.loadImage("src/main/resources/red_enemy/red_down1.png");
        pApplet.image(sprite, x, y);
    }

}
