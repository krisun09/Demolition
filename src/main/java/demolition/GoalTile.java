package demolition;

import processing.core.PApplet;
import processing.core.PImage;

class GoalTile extends ElementsInMap {
    public static void draw(PApplet pApplet, float x, float y) {
        PImage sprite = pApplet.loadImage("src/main/resources/goal/goal.png");
        pApplet.image(sprite, x, y);
    }
}
