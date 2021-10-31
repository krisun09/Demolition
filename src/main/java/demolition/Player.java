package demolition;

import processing.core.PApplet;
import processing.core.PImage;

class Player extends Character {

    private static final String SPRITE_PATH = "src/main/resources/player/player1.png";

    public Player(int x, int y, PApplet pApplet) {
        this.x = x;
        this.y = y;
        this.sprite = pApplet.loadImage(SPRITE_PATH);
    }

    public void collision() {
    }

    public void keyPressed() {
        /*if (key == CODED){
            if (keyCode == UP){
                System.out.println("UP pressed");
                this.bombMan.moveY(-6);
            }
            if (keyCode == DOWN){
                System.out.println("DOWN pressed");
                this.bombMan.moveY(6);
            }
            if (keyCode == LEFT){
                System.out.println("LEFT pressed");
                this.bombMan.moveX(-6);
            }
            if (keyCode == RIGHT){
                System.out.println("RIGHT pressed");
                this.bombMan.moveX(6);
            }
        }*/
    }

    public static void draw(PApplet pApplet, float x, float y) {
        PImage sprite = pApplet.loadImage(SPRITE_PATH);
        pApplet.image(sprite, x, y);
    }

}
