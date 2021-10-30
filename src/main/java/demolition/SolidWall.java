package demolition;

import processing.core.PApplet;
import processing.core.PImage;

class SolidWall extends ElementsInMap {
    public static void draw(PApplet pApplet, float x, float y) {
       PImage sprite = pApplet.loadImage("src/main/resources/wall/solid.png");
        pApplet.image(sprite, x, y);
    }
}
