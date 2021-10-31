package demolition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestYellowEnemy {

    @Test
    public void testTakeTurn() {
        YellowEnemy yellowEnemy = new YellowEnemy(3, 5, null);
        assertEquals(Direction.Down, yellowEnemy.takeTurn(Direction.Right));
        assertEquals(Direction.Left, yellowEnemy.takeTurn(Direction.Down));
        assertEquals(Direction.Up, yellowEnemy.takeTurn(Direction.Left));
        assertEquals(Direction.Right, yellowEnemy.takeTurn(Direction.Up));
    }
}
