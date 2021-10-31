package demolition;

import processing.core.PApplet;
import processing.core.PImage;

class Player extends Character {

    private static final String SPRITE_PATH = "src/main/resources/player/player1.png";

    public Player(int x, int y, PApplet pApplet){
        this.x = x;
        this.y = y;
        this.sprite = pApplet.loadImage(SPRITE_PATH);
    }

    public void tick(int defaultV) {
        // refreshes

            // TODO: check facing side and thus move to the direction

    }

    public void collision(){
    }

    public void moveX(String[] map, int speed) {

    }

    public void moveY(int speed) {

    }

    public static void draw(PApplet pApplet, float x, float y) {
        PImage sprite = pApplet.loadImage(SPRITE_PATH);
        pApplet.image(sprite, x, y);
    }

}
