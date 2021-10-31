package demolition;

import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestRedEnemy {

    @Test
    public void testTakeTurn() {
        RedEnemy redEnemy = new RedEnemy(3, 5, null);
        assertNotEquals(Direction.Right, redEnemy.takeTurn(Direction.Right));
        assertNotEquals(Direction.Up, redEnemy.takeTurn(Direction.Up));
    }
}
