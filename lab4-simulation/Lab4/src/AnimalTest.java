import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    public void testGetName() {
        Animal animal = new Animal();
        animal.name = "Lion";
        Assertions.assertEquals("Lion", animal.getName());
    }

    @Test
    public void testGetX() {
        Animal animal = new Animal();
        animal.x = 5;
        Assertions.assertEquals(5, animal.getX());
    }

    @Test
    public void testGetY() {
        Animal animal = new Animal();
        animal.y = 10;
        Assertions.assertEquals(10, animal.getY());
    }

    @Test
    public void testSetX() {
        Animal animal = new Animal();
        animal.setX(3);
        Assertions.assertEquals(3, animal.x);
    }

    @Test
    public void testSetY() {
        Animal animal = new Animal();
        animal.setY(7);
        Assertions.assertEquals(7, animal.y);
    }

    @Test
    public void testGetHasMoved() {
        Animal animal = new Animal();
        animal.hasMoved = true;
        Assertions.assertTrue(animal.getHasMoved());
    }

    @Test
    public void testSetHasMoved() {
        Animal animal = new Animal();
        animal.setHasMoved(true);
        Assertions.assertTrue(animal.hasMoved);
    }
}