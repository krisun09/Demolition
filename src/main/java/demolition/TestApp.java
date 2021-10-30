package demolition;

import processing.core.PApplet;

public class TestApp extends PApplet {

    public static void main(String[] args) {
        PApplet.main("demolition.TestApp");
    }

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        AppConfig appConfig = new AppConfig("config.json", this);
        GameMap gameMap = new GameMap(appConfig.getLevels().get(0), appConfig.getLives(), this);
        background(192, 64, 0);
        stroke(255);
    }

    public void draw() {
        line(150, 25, mouseX, mouseY);

    }

    public void mousePressed() {
        background(192, 64, 0);
    }
}
