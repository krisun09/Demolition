package demolition;

import processing.core.PApplet;
import processing.core.PImage;

class Player extends Character {

    public Player(int x, int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.life = 4;
        this.sprite = sprite;
        this.yVel = 0;
        this.xVel = 0;
    }

    public void tick(int defaultV) {
        // refreshes

        if (this.life > 0) {

            // TODO: check facing side and thus move to the direction
            this.x += this.xVel;
            if (this.xVel != 0) {
                xVel = defaultV;
            }

            this.y += this.yVel;
            if (this.yVel != 0) {
                yVel = defaultV;
            }
        }
    }

    public void collision(){
        this.life --;
        if (life == 0){
            // game over
        }
        else {
            // restart level
        }
    }

    public String[] moveX(String[] map, int speed) {
        if (this.ground <= this.x && this.x <= this.ceiling) {
            this.xVel = speed;
        }
        return map;
    }

    public void moveY(int speed) {
        if (this.ground <= this.y && this.y <= this.ceiling) {
            this.yVel = speed;
        }
    }

    public static void draw(PApplet pApplet, float x, float y) {
        PImage sprite = pApplet.loadImage("src/main/resources/player/player1.png");
        pApplet.image(sprite, x, y);
    }

}
