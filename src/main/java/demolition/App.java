package demolition;

import processing.core.PApplet;

import java.awt.event.KeyEvent;

public class App extends PApplet {

    public static final int WIDTH = 480, HEIGHT = 480;
    public static final int FPS = 60;

    private GameMap gameMap;

    private AppConfig appConfig;

    public void settings() {
        size(WIDTH, HEIGHT);
    }


    /**
     * sets up frameRate, reads map.txt, create a new gameMap object and call draw map method.
     */
    public void setup() {
        frameRate(FPS);
        appConfig = new AppConfig("config.json", this);
        gameMap = new GameMap(appConfig.getLevels().get(0), appConfig.getLives(), this);
        background(243, 112, 33);
        gameMap.drawMap();
    }

    /**
     * draws the basic game map: tiles, time, lives
     */
    public void draw() {
        gameMap.tick();
    }

    public void keyReleased() {
        if (this.key == CODED) {
            switch (this.keyCode) {
                case UP:
                    System.out.println("UP pressed");
                    gameMap.movePlayer(Direction.Up);
                    break;
                case DOWN:
                    System.out.println("DOWN pressed");
                    gameMap.movePlayer(Direction.Down);
                    break;
                case LEFT:
                    System.out.println("LEFT pressed");
                    gameMap.movePlayer(Direction.Left);
                    break;
                case RIGHT:
                    System.out.println("RIGHT pressed");
                    gameMap.movePlayer(Direction.Right);
                    break;
            }
        } else if(this.keyCode == KeyEvent.VK_SPACE) {
            gameMap.placeBomb();
        }
    }

    /**
     * @return gameMap
     */
    public GameMap getGameMap() {
        return gameMap;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
