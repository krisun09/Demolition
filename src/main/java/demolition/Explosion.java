package demolition;

import processing.core.PApplet;
import processing.core.PImage;

import static demolition.GameMap.CELL_SIZE;

public class Explosion {
    private static final String SPRITE_CENTER_PATH = "src/main/resources/explosion/centre.png";
    private static final String SPRITE_HORIZONTAL_PATH = "src/main/resources/explosion/horizontal.png";
    private static final String SPRITE_VERTICAL_PATH = "src/main/resources/explosion/vertical.png";

    private int x;
    private int y;
    private PImage centerSprite;
    private PImage horizontalSprite;
    private PImage verticalSprite;
    private int ticks = (int) (0.5 * App.FPS);

    public Explosion(int x, int y, PApplet pApplet) {
        this.x = x;
        this.y = y;
        if (pApplet != null) {
            this.centerSprite = pApplet.loadImage(SPRITE_CENTER_PATH);
            this.horizontalSprite = pApplet.loadImage(SPRITE_HORIZONTAL_PATH);
            this.verticalSprite = pApplet.loadImage(SPRITE_VERTICAL_PATH);
        }
    }

    public int getTicks() {
        return ticks;
    }

    public void tick() {
        ticks--;
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

    public void draw(PApplet pApplet, char[][] map) {
        pApplet.image(centerSprite, x * CELL_SIZE, y * CELL_SIZE);
        if (map[y][x - 1] == ' ' || map[y][x - 1] == 'B') {
            pApplet.image(horizontalSprite, (x - 1) * CELL_SIZE, y * CELL_SIZE);
        }
        if (map[y][x - 1] == ' ' && x > 1 && (map[y][x - 2] == ' ' || map[y][x - 2] == 'B')) {
            pApplet.image(horizontalSprite, (x - 2) * CELL_SIZE, y * CELL_SIZE);
        }
        if (map[y][x + 1] == ' ' || map[y][x + 1] == 'B') {
            pApplet.image(horizontalSprite, (x + 1) * CELL_SIZE, y * CELL_SIZE);

        }
        if (map[y][x + 1] == ' ' && (map[y][x + 2] == ' ' || map[y][x + 2] == 'B')) {
            pApplet.image(horizontalSprite, (x + 2) * CELL_SIZE, y * CELL_SIZE);
        }
        if (map[y - 1][x] == ' ' || map[y - 1][x] == 'B') {
            pApplet.image(verticalSprite, x * CELL_SIZE, (y - 1) * CELL_SIZE);

        }
        if (map[y - 1][x] == ' ' && y > 3 && (map[y - 2][x] == ' ' || map[y - 2][x] == 'B')) {
            pApplet.image(verticalSprite, x * CELL_SIZE, (y - 2) * CELL_SIZE);
        }
        if (map[y + 1][x] == ' ' || map[y + 1][x] == 'B') {
            pApplet.image(verticalSprite, x * CELL_SIZE, (y + 1) * CELL_SIZE);
        }
        if (map[y + 1][x] == ' ' && (map[y + 2][x] == ' ' || map[y + 2][x] == 'B')) {
            pApplet.image(verticalSprite, x * CELL_SIZE, (y + 2) * CELL_SIZE);
        }
    }

}
