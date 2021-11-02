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
    public void changeSprite(PApplet pApplet) {
        switch (direction) {
            case Down:
                sprite = pApplet.loadImage("src/main/resources/yellow_enemy/yellow_down" + spriteNum + ".png");
                break;
            case Up:
                sprite = pApplet.loadImage("src/main/resources/yellow_enemy/yellow_up" + spriteNum + ".png");
                break;
            case Left:
                sprite = pApplet.loadImage("src/main/resources/yellow_enemy/yellow_left" + spriteNum + ".png");
                break;
            case Right:
                sprite = pApplet.loadImage("src/main/resources/yellow_enemy/yellow_right" + spriteNum + ".png");
                break;
        }
    }

    @Override
    public Direction takeTurn(Direction direction) {
        int order = (direction.ordinal() + 1) % 4;
        return Direction.values()[order];
    }
}
