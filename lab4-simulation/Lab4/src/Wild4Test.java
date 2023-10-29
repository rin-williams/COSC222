import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

public class Wild4Test {

    @Test
    public void testMoveLeftWolfAndDeerAndEmpty() {
        // make a summoned
        Animal[][] summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false),
                        new Empty(0, 2, false), new Empty(0, 3, false) },
                { new Deer(1, 0, false), new Wolf(1, 1, false),
                        new Empty(1, 2, false), new Empty(1, 3, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false),
                        new Empty(2, 2, false), new Empty(2, 3, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;

        // now move the wolf
        Wild4.dirDecider(1, 1, 0);
        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][1] instanceof Empty);
        assertTrue(Wild4.summoned[1][0] instanceof Wolf);

        // now move the deer
        // first reset it
        summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false),
                        new Empty(0, 2, false), new Empty(0, 3, false) },
                { new Wolf(1, 0, false), new Deer(1, 1, false),
                        new Empty(1, 2, false), new Empty(1, 3, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false),
                        new Empty(2, 2, false), new Empty(2, 3, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;

        // now move the deer
        Wild4.dirDecider(1, 1, 0);
        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][1] instanceof Empty);
        assertTrue(Wild4.summoned[1][0] instanceof Wolf);

        // now move the empty
        // first reset it
        summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false),
                        new Empty(0, 2, false), new Empty(0, 3, false) },
                { new Wolf(1, 0, false), new Empty(1, 1, false),
                        new Empty(1, 2, false), new Empty(1, 3, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false),
                        new Empty(2, 2, false), new Empty(2, 3, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;

        // now move the empty
        Wild4.dirDecider(1, 1, 0);
        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][1] instanceof Empty);
        assertTrue(Wild4.summoned[1][0] instanceof Wolf);

    }

    @Test
    public void testMoveRightWolfAndDeerAndEmpty() {
        // make a summoned
        Animal[][] summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false) },
                { new Deer(1, 0, false), new Wolf(1, 1, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the deer
        Wild4.dirDecider(0, 1, 1);
        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[1][1] instanceof Wolf);

        // now move the wolf
        // first reset it
        summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false) },
                { new Wolf(1, 0, false), new Deer(1, 1, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the wolf
        Wild4.dirDecider(0, 1, 1);
        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[1][1] instanceof Wolf);

        // now move the empty
        // first reset it
        summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false) },
                { new Empty(1, 0, false), new Wolf(1, 1, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the empty
        Wild4.dirDecider(0, 1, 1);
        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[1][1] instanceof Wolf);

    }

    @Test
    public void testMoveUpWolfAndDeerAndEmpty() {
        // make a summoned
        Animal[][] summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false) },
                { new Wolf(1, 0, false), new Empty(1, 1, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the wolf
        Wild4.dirDecider(0, 1, 2);
        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[0][0] instanceof Wolf);

        // now move the deer
        // first reset it
        summoned = new Animal[][] {
                { new Wolf(0, 0, false), new Empty(0, 1, false) },
                { new Deer(1, 0, false), new Empty(1, 1, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the deer
        Wild4.dirDecider(0, 1, 2);

        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[0][0] instanceof Wolf);

        // now move the empty
        // first reset it
        summoned = new Animal[][] {
                { new Wolf(0, 0, false), new Empty(0, 1, false) },
                { new Empty(1, 0, false), new Empty(1, 1, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the empty
        Wild4.dirDecider(0, 1, 2);

        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[0][0] instanceof Wolf);

    }

    @Test
    public void testMoveDownWolfAndDeerAndEmpty() {
        // make a summoned
        Animal[][] summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false) },
                { new Wolf(1, 0, false), new Empty(1, 1, false) },
                { new Deer(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the wolf
        Wild4.dirDecider(0, 1, 3);

        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[2][0] instanceof Wolf);

        // now move the deer
        // first reset it
        summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false) },
                { new Deer(1, 0, false), new Empty(1, 1, false) },
                { new Wolf(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the deer
        Wild4.dirDecider(0, 1, 3);

        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[2][0] instanceof Wolf);

        // now move the empty
        // first reset it
        summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false) },
                { new Empty(1, 0, false), new Empty(1, 1, false) },
                { new Wolf(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;
        // now move the empty
        Wild4.dirDecider(0, 1, 3);

        // assert check both cells have moved
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[2][0] instanceof Wolf);

    }

    @Test
    public void testMapToString() {
        // make a summoned
        Animal[][] summoned = new Animal[][] {
                { new Empty(0, 0, false), new Empty(0, 1, false) },
                { new Deer(1, 0, false), new Wolf(1, 1, false) },
                { new Empty(2, 0, false), new Empty(2, 1, false) },
                { new Empty(3, 0, false), new Empty(3, 1, false) }
        };
        Wild4.summoned = summoned;

        String expected = "\nGrid sequence is: \n" +
                "|----|----|\n" +
                "|    |    |\n" +
                "|----|----|\n" +
                "|DEER|WOLF|\n" +
                "|----|----|\n" +
                "|    |    |\n" +
                "|----|----|\n" +
                "|    |    |\n" +
                "|----|----|\n" +
                "Next grid will move in seq of: \n";
        assertEquals(expected, Wild4.mapToString());
    }

    @Test
    public void testGenerateSequence() {
        Random rand = new Random(1234);
        String s = "";
        for (int i = 0; i < 8; i++) {
            s += rand.nextInt(2);
        }
        String sequence = Wild4.generateSequence(1234);
        assertEquals(8, sequence.length());
        assertTrue(sequence.matches(s));

    }

    @Test
    public void testGenerateMoveSequence() {
        Random rand = new Random(12345);
        String s = "";
        for (int i = 0; i < 24; i++) {
            s += rand.nextInt(4);
        }
        // get the first 8
        String first8 = s.substring(0, 8);
        // now test
        String moveSequence = Wild4.generateMoveSequence(12345, 1);
        assertEquals(8, moveSequence.length());
        assertTrue(moveSequence.matches(first8));

        // get the next 8
        String next8 = s.substring(8, 16);
        // now test
        moveSequence = Wild4.generateMoveSequence(12345, 2);
        assertEquals(8, moveSequence.length());
        assertTrue(moveSequence.matches(next8));

        // get the next 8
        String next16 = s.substring(16, 24);
        // now test
        moveSequence = Wild4.generateMoveSequence(12345, 3);
        assertEquals(8, moveSequence.length());
        assertTrue(moveSequence.matches(next16));

    }

    @Test
    public void testGenerateMoveSequenceWithZeroMoves() {
        String moveSequence = Wild4.generateMoveSequence(12345, 0);
        assertEquals("", moveSequence);
    }

    @Test
    public void testGenerateMoveSequenceWithNegativeMoves() {
        String moveSequence = Wild4.generateMoveSequence(12345, -5);
        assertEquals("", moveSequence);
    }

    @Test
    public void testRandomizedSummon() {
        String sequence = "10101010";
        Animal[][] actual = Wild4.randomizeSummon(sequence);
        Animal[][] expected = new Animal[][] {
                { new Wolf(0, 0, false), new Deer(0, 1, false) },
                { new Wolf(1, 0, false), new Deer(1, 1, false) },
                { new Wolf(2, 0, false), new Deer(2, 1, false) },
                { new Wolf(3, 0, false), new Deer(3, 1, false) }
        };
        Arrays.deepEquals(expected, actual);

    }

    @Test
    public void testMove() {
        // recall that 0 is left, 1 is right, 2 is up, 3 is down
        String moveSeqStr = "11111111";
        // we make thm all move right.
        Animal[][] summoned = Wild4.randomizeSummon("10101010");
        Wild4.summoned = summoned;
        Wild4.move(moveSeqStr);
        // now the wolf should all be corned up on the right side of the board.
        // assert
        assertTrue(Wild4.summoned[0][0] instanceof Empty);
        assertTrue(Wild4.summoned[0][1] instanceof Wolf);
        assertTrue(Wild4.summoned[1][0] instanceof Empty);
        assertTrue(Wild4.summoned[1][1] instanceof Wolf);
        assertTrue(Wild4.summoned[2][0] instanceof Empty);
        assertTrue(Wild4.summoned[2][1] instanceof Wolf);
        assertTrue(Wild4.summoned[3][0] instanceof Empty);
        assertTrue(Wild4.summoned[3][1] instanceof Wolf);

    }

    public static void main(String[] args) {
        Wild4Test test = new Wild4Test();
        test.testRandomizedSummon();
    }
}
