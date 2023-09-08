import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MathTest {
    @Test
    void testAddStatic() {
        int num1 = 3;
        int num2 = 2;
        int total = 5;
        int sum = 0;
        sum = Math.add(num1, num2);
        assertEquals(sum, total);
    }

    @SuppressWarnings("static-access")

    @Test
    void testAdd() {
        int num1 = 3;
        int num2 = 2;
        int total = 5;
        int sum = 0;
        Math m = new Math();
        sum = m.add(num1, num2);
        assertEquals(sum, total);
    }

}