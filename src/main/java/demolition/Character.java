package demolition;

import processing.core.PApplet;
import processing.core.PImage;

abstract class Character {
    int x;
    int y;
    int life;

    // TODO: limit the characters within the frame
    int ground = 10;
    int ceiling = 470;

    int yVel;
    int xVel;

    PImage sprite;

    public void drawChar(PApplet app){
        if (this.life > 0) {
            app.image(this.sprite, this.x, this.y); // TODO: replace with left facing image of the player
        }
    }

    public abstract String[] moveX(String[] map, int direction);

    public abstract void moveY(int speed);

}




