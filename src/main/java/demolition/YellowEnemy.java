package demolition;

import processing.core.PApplet;

class YellowEnemy extends Enemy {

    private static final String SPRITE_PATH = "src/main/resources/yellow_enemy/yellow_down1.png";

    public YellowEnemy(int x, int y, PApplet pApplet) {
        super(x, y);
        if (pApplet != null) {
            this.sprite = pApplet.loadImage(SPRITE_PATH);
        }
    }

    @Override
    public Direction takeTurn(Direction direction) {
        int order = (direction.ordinal() + 1) % 4;
        return Direction.values()[order];
    }
}
