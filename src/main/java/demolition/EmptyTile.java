package demolition;

import processing.core.PApplet;
import processing.core.PImage;

class EmptyTile extends ElementsInMap {

    /**
     * draws the empty tile
     */
    public static void draw(PApplet pApplet, float x, float y) {
        PImage sprite = pApplet.loadImage("src/main/resources/empty/empty.png");
        pApplet.image(sprite, x, y);
    }
}
