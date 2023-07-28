import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
    }

    @Test
    public void nullNameExceptionCorrectMessage() {
        try {
            new Horse(null, 1, 2);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t", "\n\n\n\n"})
    public void blankNameException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t", "\n\n\n\n"})
    public void blankNameExceptionCorrectMessage(String name) {
        try {
            new Horse(name, 1, 2);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void negativeSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 2));
    }

    @Test
    public void negativeSpeedExceptionCorrectMessage() {
        try {
            new Horse("name", -1, 2);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -2));
    }

    @Test
    public void negativeDistanceExceptionCorrectMessage() {
        try {
            new Horse("name", 1, -2);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getName() {
        Horse horse = new Horse("name", 1, 1);

        assertEquals("name", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("name", 1, 2);

        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("name", 1, 2);

        assertEquals(2, horse.getDistance());

        Horse horse1 = new Horse("name", 1);

        assertEquals(0, horse1.getDistance());
    }

    @Test
    public void methodMoveUseGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("name", 1, 2).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3, 0.4, 777.777})
    public void move(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 7, 2);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(2 + 7 * random, horse.getDistance());
        }
    }
}
