package demolition;

import processing.core.PApplet;

class Player extends Character {

    private static final String SPRITE_PATH = "src/main/resources/player/player1.png";
    private App applet;

    public Player(int x, int y, App pApplet) {
        this.applet = pApplet;
        this.x = x;
        this.y = y;
        this.sprite = pApplet.loadImage(SPRITE_PATH);
        this.direction = Direction.Down;
    }

    public void move(char[][] map, Direction direction) {
        this.direction = direction;
        switch (direction) {
            case Right:
                char nextCell = map[y][x+1];
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    x += 1;
                } else if(nextCell == 'G') {
                    this.applet.getGameMap().goToNextLevel();
                } else {
                    System.out.println("cant go " + direction.name());
                }
                break;
            case Up:
                nextCell = map[y-1][x];
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    y--;
                } else if(nextCell == 'G') {
                    this.applet.getGameMap().goToNextLevel();
                } else {
                    System.out.println("cant go " + direction.name());
                }
                break;
            case Left:
                nextCell = map[y][x-1];
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    x--;
                } else if(nextCell == 'G') {
                    this.applet.getGameMap().goToNextLevel();
                } else {
                    System.out.println("cant go " + direction.name());
                }
                break;
            case Down:
                nextCell = map[y+1][x];
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    y++;
                } else if(nextCell == 'G') {
                    this.applet.getGameMap().goToNextLevel();
                } else {
                    System.out.println("cant go " + direction.name());
                }
                break;
        }
    }

    @Override
    public void changeSprite(PApplet pApplet) {
        switch (direction) {
            case Down:
                sprite = pApplet.loadImage( "src/main/resources/player/player" + spriteNum + ".png");
                break;
            case Up:
                sprite = pApplet.loadImage( "src/main/resources/player/player_up" + spriteNum + ".png");
                break;
            case Left:
                sprite = pApplet.loadImage( "src/main/resources/player/player_left" + spriteNum + ".png");
                break;
            case Right:
                sprite = pApplet.loadImage( "src/main/resources/player/player_right" + spriteNum + ".png");
                break;
        }
    }

}

