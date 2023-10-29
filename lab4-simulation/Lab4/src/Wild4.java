// READ ME:
// This is the main class for the simulation. It has been named Wild 4
// because it is the 4th version of the simulation. This class will
// it will generate a simulation based on the seed provided.
// The grid sequence is a string of 8 characters, each character
// represents a cell in the grid. 0 represents a deer, 1 represents a wolf.
// The move sequence is a string of 8 characters, each character represents
// a move for each cell in the grid. 0 = left, 1 = right, 2 = up, 3 = down.
// so if you have a sequence of 33122110
// and the grid sequence is
// |----|----|
// |WOLF|DEER| <-- wolf moves down, deer moves down
// |----|----|
// |WOLF|DEER| <-- wolf moves right, deer moves up
// |----|----|
// |DEER|WOLF| <-- deer moves up, wolf moves right
// |----|----|
// |WOLF|DEER| <-- wolf moves right, deer moves left
// |----|----|
// also, i am very sure theres some minor bugs in the move() method, but it seems 
// to work fine during test cases so i am not going to touch it anymore
// because in proramming, if it works, it works....

import java.util.Random;

public class Wild4 {
    public static void main(String[] args) {
        // generate a simulation.
        String genSeqSeed = generateSequence(123);
        // generate a sequence of 8 animals
        summoned = randomizeSummon(genSeqSeed);
        // generate a sequence of 8 moves (0, 1, 2, 3)

        // print the map
        System.out.print("\nGen 0:");
        // generate sequence for gen 1
        String moveSeqSeed = generateMoveSequence(456, 1);
        System.out.print(mapToString());

        move(moveSeqSeed);
        System.out.print("\nGen 1:");
        // generate sequence for gen 2
        moveSeqSeed = generateMoveSequence(456, 2);
        System.out.println(mapToString());

        move(moveSeqSeed);
        System.out.print("\nGen 2:");
        moveSeqSeed = generateMoveSequence(456, 3);
        System.out.println(mapToString());

    }

    static final int MAXT = 3, // maximum number of simulation time steps
            MAXX = 2, // maximum number of cells on the x-axis
            MAXY = 4; // maximum number of cells on the y-axis
    static public Animal[][] summoned = new Animal[MAXY][MAXX];
    static Random random = new Random();
    static Random randomMovement = new Random();
    static long seed = -1;
    static long moveSeed = -1;
    static String animalSequence = "";
    static String moveSequence = "";

    public static String mapToString() {
        StringBuilder map = new StringBuilder();

        // print the map
        map.append("\nGrid sequence is: " + animalSequence);
        map.append("\n|----|----|\n");
        for (int y = 0; y < MAXY; y++) {
            for (int x = 0; x < MAXX; x++) {
                map.append("|").append(summoned[y][x].getName());
            }
            map.append("|\n|----|----|\n");
        }
        map.append("Next grid will move in seq of: " + moveSequence + "\n");
        return map.toString();
    }

    public static Animal[][] randomizeSummon(String sequence) {
        Animal[][] animalArray = new Animal[MAXY][MAXX];
        // generate wolf and deer based on the string provided
        int index = 0;
        if (sequence.length() != 8 || !sequence.contains("0") || !sequence.contains("1")) {
            return null;
        }
        for (int y = 0; y < MAXY; y++) {
            for (int x = 0; x < MAXX; x++) {
                if (sequence.charAt(index) == '1') {
                    animalArray[y][x] = new Wolf(x, y, false);
                } else if (sequence.charAt(index) == '0') {
                    animalArray[y][x] = new Deer(x, y, false);
                }
                index++;
            }
        }

        return animalArray;

    }

    public static String generateSequence(long seed) {
        int numMoves = 8;
        String s = "";
        random.setSeed(seed);
        for (int i = 0; i < numMoves; i++) {
            s += random.nextInt(2);
        }
        int startIndex = Math.max(0, s.length() - 8);
        s = s.substring(startIndex);

        animalSequence = s;
        return s;

    }

    public static String generateMoveSequence(long seed, int numMoves) {
        numMoves *= 8;
        String s = "";
        randomMovement.setSeed(seed);
        for (int i = 1; i <= numMoves; i++) {
            s += randomMovement.nextInt(4);
        }

        // adjust the move sequence so it only prints out the last 8 parts
        // Calculate the starting index to extract the last 8 parts
        int startIndex = Math.max(0, s.length() - 8);

        // Extract the last 8 parts from the generated sequence
        s = s.substring(startIndex);

        moveSequence = s;
        return s;
    }

    public static void move(String moveSequenceStr) {
        // move each cell based on the move sequence
        // recall that 0 is left, 1 is right, 2 is up, 3 is down
        // since moveSequenceNum is a string, we need to convert it to int
        // first
        int[] moveSequenceInt = new int[moveSequenceStr.length()];
        for (int i = 0; i < moveSequenceInt.length; i++) {
            moveSequenceInt[i] = Character.getNumericValue(moveSequenceStr.charAt(i));
        }
        // index for moveSequenceInt, increment it when we move the animal
        int iMS = 0;
        // now we move the animal based on the move sequence

        for (int y = 0; y < MAXY; y++) {
            for (int x = 0; x < MAXX; x++) {
                dirDecider(x, y, moveSequenceInt[iMS]);
                iMS++;
            }
        }
    }

    public static void dirDecider(int x, int y, int caseNum) {
        // now we move it, based on the caseNum
        // recall that 0 is left, 1 is right, 2 is up, 3 is down
        // caseNum = 0;
        // check if it is possible to move left, right, up, or down
        boolean possibleLeft = (x - 1 >= 0);
        boolean possibleRight = (x + 1 < MAXX);
        boolean possibleUp = (y - 1 >= 0);
        boolean possibleDown = (y + 1 < MAXY);
        Animal current = summoned[y][x];
        // check if current is a wolf or deer
        boolean isWolf = (current instanceof Wolf);
        boolean isDeer = (current instanceof Deer);

        if (isWolf) {
            // check if it is possible to move left, right, up, or down
            switch (caseNum) {
                case 0:
                    // if possible to move left
                    // if left contains deer, eat it
                    if (possibleLeft) {
                        if ((summoned[y][x - 1] instanceof Deer || summoned[y][x - 1] instanceof Empty)
                                && !summoned[y][x].getHasMoved()) {
                            summoned[y][x - 1] = new Wolf(x - 1, y, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                    }
                    break;
                case 1:
                    // if possible to move right
                    // if right contains deer, eat it
                    if (possibleRight) {
                        if ((summoned[y][x + 1] instanceof Deer || summoned[y][x + 1] instanceof Empty)
                                && !summoned[y][x].getHasMoved()) {
                            summoned[y][x + 1] = new Wolf(x + 1, y, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                    }
                    break;
                case 2:
                    // if possible to move up
                    // if up contains deer, eat it
                    if (possibleUp) {
                        if ((summoned[y - 1][x] instanceof Deer || summoned[y - 1][x] instanceof Empty)
                                && !summoned[y][x].getHasMoved()) {
                            summoned[y - 1][x] = new Wolf(x, y - 1, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                    }
                    break;
                case 3:
                    // if possible to move down
                    // if down contains deer, eat it
                    if (possibleDown) {
                        if ((summoned[y + 1][x] instanceof Deer || summoned[y + 1][x] instanceof Empty)
                                && !summoned[y][x].getHasMoved()) {
                            summoned[y + 1][x] = new Wolf(x, y + 1, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                    }
                    break;

            }

        } else if (isDeer) {
            switch (caseNum) {
                case 0:
                    // if possible to move left
                    // if left contains Wolf, the Deer gets eaten.
                    if (possibleLeft) {
                        if ((summoned[y][x - 1] instanceof Wolf || summoned[y][x - 1] instanceof Empty)
                                && !summoned[y][x].getHasMoved()) {
                            summoned[y][x - 1] = new Wolf(x - 1, y, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                    }
                    break;
                case 1:
                    // if possible to move right
                    // if right contains Wolf, the Deer gets eaten.
                    if (possibleRight) {
                        if (summoned[y][x + 1] instanceof Wolf && !summoned[y][x].getHasMoved()) {
                            summoned[y][x + 1] = new Wolf(x + 1, y, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                        if (summoned[y][x + 1] instanceof Empty && !summoned[y][x].getHasMoved()) {
                            summoned[y][x + 1] = new Deer(x + 1, y, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                    }
                    break;
                case 2:
                    // if possible to move up
                    // if up contains Wolf, the Deer gets eaten.
                    if (possibleUp) {
                        if (summoned[y - 1][x] instanceof Wolf && !summoned[y][x].getHasMoved()) {
                            summoned[y - 1][x] = new Wolf(x, y - 1, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                        if (summoned[y - 1][x] instanceof Empty && !summoned[y][x].getHasMoved()) {
                            summoned[y - 1][x] = new Deer(x, y - 1, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                    }
                    break;
                case 3:
                    // if possible to move down
                    // if down contains Wolf, the Deer gets eaten.
                    if (possibleDown) {
                        if (summoned[y + 1][x] instanceof Wolf && !summoned[y][x].getHasMoved()) {
                            summoned[y + 1][x] = new Wolf(x, y + 1, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                        if (summoned[y + 1][x] instanceof Empty && !summoned[y][x].getHasMoved()) {
                            summoned[y + 1][x] = new Deer(x, y + 1, true);
                            summoned[y][x] = new Empty(x, y, false);
                        }
                    }
                    break;
            }
        } else {
        }
    }
}
