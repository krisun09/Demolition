package demolition;

import java.util.Objects;

abstract class Enemy extends Character {

    public Enemy (int x, int y){
        this.x = x;
        this.y = y;
        this.direction = Direction.Right;
    }

    public void move(String[] map) {
        System.out.println("i'm at coordinates x " + x + " y " + y);
        switch (direction) {
            case Right:  // moving right
                char nextCell = map[y-2].charAt(x+1);
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
            case Up:  // moving up
                nextCell = map[y-3].charAt(x);
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
            case Left:  // moving left
                nextCell = map[y-2].charAt(x-1);
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
            case Down:  // moving down
                nextCell = map[y-1].charAt(x);
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


        // we know we hit the wall
        // change direction
    }

    public void tick(int defaultV){
        // refreshes

    }

    public void moveX(String[] map, int speed) {
        if (speed > 0) {
            String[] newMap = map[x].split("");
            // wall check
            if (Objects.equals(newMap[y], " W") || Objects.equals(newMap[y], "B")) {
                takeTurn(this.direction);
            }
            else {
                newMap[y] = " ";
                this.y ++;
                newMap[y] = "R";
                map[x] = String.join("", newMap);
            }

        }
        else if (speed < 0) {
            this.x --;
        }
    }

    public void moveY(int speed) {
        boolean positive;

    }

    public abstract Direction takeTurn(Direction direction);

}
