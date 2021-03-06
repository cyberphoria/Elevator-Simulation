package systemwide;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * DirectionTest ensures that Direction's search method functions correctly.
 *
 * @author Liam Tripp
 */
public class DirectionTest {

    @Test
    public void testGetDirection() {
        for (Direction direction : Direction.values()) {
            String directionName = direction.getName();
            Direction foundDirection = Direction.getDirection(directionName);
            Assertions.assertNotNull(foundDirection);
            Assertions.assertEquals(directionName, foundDirection.getName());
        }
    }

    @Test
    public void testGetDirectionWithLowerCaseAndWhiteSpace() {
        for (Direction direction : Direction.values()) {
            String directionName = " " + direction.getName().toLowerCase() + " ";
            Direction foundDirection = Direction.getDirection(directionName);
            Assertions.assertNotNull(foundDirection);
        }
    }
}
