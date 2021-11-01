package demolition;

import processing.core.PApplet;

public class App extends PApplet {

    public static final int WIDTH = 480, HEIGHT = 480;
    public static final int FPS = 60;

    private GameMap gameMap;

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);
        AppConfig appConfig = new AppConfig("config.json", this);
        gameMap = new GameMap(appConfig.getLevels().get(0), appConfig.getLives(), this);
        background(243, 112, 33);
        gameMap.drawMap(this);
    }

    public void draw() {

        /**
         * draws the basic game map: tiles, time, lives
         */

        gameMap.tick(this);
    }

    public void keyReleased() {
        if (this.key == CODED){
            gameMap.movePlayer(this);
        }
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
