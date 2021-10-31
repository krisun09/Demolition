package demolition;

import processing.core.PApplet;
import processing.core.PImage;

import static demolition.GameMap.CELL_SIZE;

abstract class Character {
    int x;
    int y;
    Direction direction;
    PImage sprite;

    public void drawChar(PApplet app) {
        // TODO: replace with left facing image of the player
        app.image(sprite, x * CELL_SIZE, y * CELL_SIZE * 0.93F);
    }

}




