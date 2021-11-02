package demolition;

import processing.core.PApplet;
import processing.core.PImage;

class BrokenWall extends ElementsInMap {

    /**
     * draws the broken wall
     */
    public static void draw(PApplet pApplet, float x, float y) {
        PImage sprite = pApplet.loadImage("src/main/resources/broken/broken.png");
        pApplet.image(sprite, x, y);
    }
}
