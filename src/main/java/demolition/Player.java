package demolition;

import processing.core.PApplet;
import processing.core.PImage;

class Player extends Character {

    private static final String SPRITE_PATH = "src/main/resources/player/player1.png";

    public Player(int x, int y, PApplet pApplet) {
        this.x = x;
        this.y = y;
        this.sprite = pApplet.loadImage(SPRITE_PATH);
    }

    public static void draw (PApplet pApplet,float x, float y){
        PImage sprite = pApplet.loadImage(SPRITE_PATH);
        pApplet.image(sprite, x, y);
    }

    public void collision() {
    }

    public void keyReleased(PApplet pApplet, String[] map) {

        if (pApplet.keyCode == pApplet.UP){
            System.out.println("UP pressed");
            takeMove(Direction.Up, map);
        }
        else if (pApplet.keyCode == pApplet.DOWN){
            System.out.println("DOWN pressed");
            takeMove(Direction.Down, map);
        }
        else if (pApplet.keyCode == pApplet.LEFT){
            System.out.println("LEFT pressed");
            takeMove(Direction.Left, map);
        }
        else if (pApplet.keyCode == pApplet.RIGHT){
            System.out.println("RIGHT pressed");
            takeMove(Direction.Right, map);
        }

    }

    public void takeMove(Direction direction, String[] map) {

        switch (direction) {
            case Right:
                char nextCell = map[y-2].charAt(x+1);
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    x += 1;
                }
                else {
                    System.out.println("cant go " + direction.name());
                }
                break;
            case Up:
                nextCell = map[y-3].charAt(x);
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    y--;
                }
                else {
                    System.out.println("cant go " + direction.name());
                }
                break;
            case Left:
                nextCell = map[y-2].charAt(x-1);
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    x--;
                }
                else {
                    System.out.println("cant go " + direction.name());
                }
                break;
            case Down:
                nextCell = map[y-1].charAt(x);
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    y++;
                }
                else {
                    System.out.println("cant go " + direction.name());
                }
                break;

        }


    }


}

