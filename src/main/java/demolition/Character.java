package demolition;

import processing.core.PApplet;
import processing.core.PImage;

import static demolition.GameMap.CELL_SIZE;

abstract class Character {
    int x;
    int y;
    Direction direction;
    PImage sprite;
    int ticks = 12;
    int spriteNum = 1;

    public abstract void changeSprite(PApplet pApplet);

    /**
     * draws the animation of the character
     */
    public void drawChar(PApplet pApplet) {
        ticks--;
        if (ticks == 0) {
            changeSprite(pApplet);
            spriteNum = (spriteNum + 1) % 4;
            if (spriteNum == 0) {
                spriteNum = 4;
            }
            ticks = 12;
        }
        pApplet.image(sprite, x * CELL_SIZE, y * CELL_SIZE * 0.93F);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}




