package demolition;

import processing.core.PApplet;
import processing.core.PImage;

import static demolition.GameMap.CELL_SIZE;

public class Bomb {

    private static final String SPRITE_PATH = "src/main/resources/bomb/bomb.png";

    private int x;
    private int y;
    private PImage sprite;
    private int ticks = 2 * App.FPS;
    private int spriteTicks = (int) (0.25 * App.FPS);
    private int spriteNum = 1;

    public Bomb(int x, int y, PApplet pApplet) {
        this.x = x;
        this.y = y;
        if (pApplet != null) {
            this.sprite = pApplet.loadImage(SPRITE_PATH);
        }
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

    public int getTicks() {
        return ticks;
    }

    public void tick() {
        ticks--;
        spriteTicks--;
    }

    /**
     * draws the animation for the bomb
     */
    public void draw(PApplet pApplet) {
        if (spriteTicks == 0) {
            sprite = pApplet.loadImage("src/main/resources/bomb/bomb" + spriteNum + ".png");
            spriteNum = (spriteNum + 1) % 8;
            if (spriteNum == 0) {
                spriteNum = 8;
            }
            spriteTicks = (int) (0.25 * App.FPS);
        }
        pApplet.image(sprite, x * CELL_SIZE, y * CELL_SIZE);
    }
}
