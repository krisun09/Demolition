package demolition;

import processing.core.PApplet;

import java.util.List;

class Player extends Character {

    private static final String SPRITE_PATH = "src/main/resources/player/player1.png";

    public Player(int x, int y, PApplet pApplet) {
        this.x = x;
        this.y = y;
        this.sprite = pApplet.loadImage(SPRITE_PATH);
        this.direction = Direction.Down;
    }

    public void checkCollision(List<Enemy> enemyList, int x, int y) {
        for (Enemy enemy : enemyList) {
            if (x == enemy.x && y == enemy.y) {
                // life -= 1
            }
        }
    }

    public void keyReleased(PApplet pApplet, String[] map) {

        if (pApplet.keyCode == pApplet.UP){
            System.out.println("UP pressed");
            this.direction = Direction.Up;
            takeMove(map);
        }
        else if (pApplet.keyCode == pApplet.DOWN){
            System.out.println("DOWN pressed");
            this.direction = Direction.Down;
            takeMove(map);
        }
        else if (pApplet.keyCode == pApplet.LEFT){
            System.out.println("LEFT pressed");
            this.direction = Direction.Left;
            takeMove(map);
        }
        else if (pApplet.keyCode == pApplet.RIGHT){
            System.out.println("RIGHT pressed");
            this.direction = Direction.Right;
            takeMove(map);
        }

    }

    public void takeMove(String[] map) {

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

    @Override
    public void changeSprite(PApplet pApplet) {
        switch (direction) {
            case Down:
                spritePath = "src/main/resources/player/player" + spriteNum + ".png";
                sprite = pApplet.loadImage( "src/main/resources/player/player" + spriteNum + ".png");
                break;
            case Up:
                spritePath = "src/main/resources/player/player_up" + spriteNum + ".png";
                sprite = pApplet.loadImage( "src/main/resources/player/player_up" + spriteNum + ".png");
                break;
            case Left:
                spritePath = "src/main/resources/player/player_left" + spriteNum + ".png";
                sprite = pApplet.loadImage( "src/main/resources/player/player_left" + spriteNum + ".png");
                break;
            case Right:
                spritePath = "src/main/resources/player/player_right" + spriteNum + ".png";
                sprite = pApplet.loadImage( "src/main/resources/player/player_right" + spriteNum + ".png");
                break;
        }
    }


}

