import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void nullArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void nullArgumentExceptionCorrectMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void blankArgumentException() {
        final List<Horse> horses = List.of();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    public void blankArgumentExceptionCorrectMessage() {
        final List<Horse> horses = List.of();
        try {
            new Hippodrome(horses);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinner() {
       Horse horse = new Horse("horse", 1, 1);
       Horse horse1 = new Horse("horse1", 1, 2);
       Horse horse2 = new Horse("horse2", 1, 32);
       Horse horse3 = new Horse("horse3", 1, 4);
       Horse horse4 = new Horse("horse4", 1, 5);

       Hippodrome hippodrome = new Hippodrome(List.of(horse, horse1, horse2, horse3, horse4));
       assertSame(horse2, hippodrome.getWinner());
    }

}
