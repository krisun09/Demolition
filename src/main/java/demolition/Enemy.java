package demolition;

abstract class Enemy extends Character {

    public Enemy (int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = Direction.Right;
    }

    public void move(String[] map) {
        System.out.println("i'm at coordinates x " + x + " y " + y);
        switch (direction) {
            case Right:
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
            case Up:
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
            case Left:
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
            case Down:
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
    }

    public abstract Direction takeTurn(Direction direction);

}
