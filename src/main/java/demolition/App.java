package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

    public static final int WIDTH = 480, HEIGHT = 480;

    public static final int FPS = 60;

    private PImage sprite;
    private SolidWall solidWall;
    private BrokenWall brokenWall;
    private EmptyTile emptyTile;
    private GoalTile goalTile;

    private Player bombMan;
    private Enemy yellowEnemy ;
    private Enemy redEnemy;

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
        gameMap.tick(this);
        /*this.rect(-1, -1, WIDTH + 2, HEIGHT + 2);

        this.solidWall.tick();
        this.solidWall.drawQuad(this, 0, 0);

        this.bombMan.tick(0);
        this.bombMan.drawChar(this);

        this.redEnemy.tick(0);
        this.redEnemy.drawChar(this);

        this.yellowEnemy.tick(0);
        this.yellowEnemy.drawChar(this);*/
    }

    public void keyPressed(){
        /*if (key == CODED){
            if (keyCode == UP){
                System.out.println("UP pressed");
                this.bombMan.moveY(-6);
            }
            if (keyCode == DOWN){
                System.out.println("DOWN pressed");
                this.bombMan.moveY(6);
            }
            if (keyCode == LEFT){
                System.out.println("LEFT pressed");
                this.bombMan.moveX(-6);
            }
            if (keyCode == RIGHT){
                System.out.println("RIGHT pressed");
                this.bombMan.moveX(6);
            }
        }*/
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
