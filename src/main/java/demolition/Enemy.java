package demolition;

import processing.core.PApplet;

abstract class Enemy extends Character {

    private boolean killed = false;

    public Enemy (int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = Direction.Right;
    }

    /**
     * checks the next tile the enemy is to be moved onto and moves the enemy
     * if movable: move;
     * if not movable: take turn (different enemy has different methods of turning)
     * @param map: char[][] map
     */
    public void move(char[][] map) {
        System.out.println("i'm at coordinates x " + x + " y " + y);
        switch (direction) {
            case Right:
                char nextCell = map[y][x+1];
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    x++;
                }
                else {
                    System.out.println("cant go " + direction.name());
                    direction = takeTurn(direction);
                    System.out.println("trying to go " + direction.name());
                    move(map);
                }
                break;
            case Up:
                nextCell = map[y-1][x];
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    y--;
                }
                else {
                    System.out.println("cant go " + direction.name());
                    direction = takeTurn(direction);
                    System.out.println("trying to go " + direction.name());
                    move(map);
                }
                break;
            case Left:
                nextCell = map[y][x-1];
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    x--;
                }
                else {
                    System.out.println("cant go " + direction.name());
                    direction = takeTurn(direction);
                    System.out.println("trying to go " + direction.name());
                    move(map);
                }
                break;
            case Down:
                nextCell = map[y+1][x];
                if (nextCell == ' ' || nextCell == 'Y' || nextCell == 'P' || nextCell == 'R') {
                    y++;
                }
                else {
                    System.out.println("cant go " + direction.name());
                    direction = takeTurn(direction);
                    System.out.println("trying to go " + direction.name());
                    move(map);
                }
                break;
        }
    }

    public abstract Direction takeTurn(Direction direction);

    @Override
    public abstract void changeSprite(PApplet pApplet);

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }
}
