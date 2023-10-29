import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.function.IntUnaryOperator;

public class MainTest {
    public static void main(String[] args) throws IOException {
        Hashing h = new Hashing();

        int[] aValues = new int[] { 10, 33, 37, 39, 41 };

        ArrayList<Integer> maxColValArr = new ArrayList<>();
        ArrayList<Integer> totalColValArr = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<String>();

        File file = new File("Lab5/src/words.txt");
        long time = -1;
        long timeafter = -1;

        // ------------------- read in the file -------------------//
        try {
            // open and read
            time = System.currentTimeMillis();
            FileReader readReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(readReader);
            String line = "";
            // get the time right now
            while (((line = bufferedReader.readLine()) != null)) {
                strings.add(line);
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        timeafter = System.currentTimeMillis();
        System.out.println("\nNumber of words: " + strings.size() + " words read in " + (timeafter - time) + "ms\n");

        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to compute using hashPolynomial? (y/n): ");
        String input = sc.nextLine();

        // ------------------- compute using hashPolynomial -------------------//
        if (input.contains("y")) {
            System.out.print(
                    "Computing using hashPolynomial: [.............................................]");
            // record time
            time = System.currentTimeMillis();
            for (int a : aValues) {
                PriorityQueue<Long> pq = new PriorityQueue<>();
                for (String s : strings) {
                    pq.add(h.hashPolynomial(s, a));
                }

                long previous = pq.poll();
                int count = 0;
                int totalColVal = 0;
                int maxColVal = 0;
                boolean hasCountedThisCollision = false;
                boolean distinctCounted = false;
                while (!pq.isEmpty()) {

                    long current = pq.poll();
                    // if the previous and current are the same, then we have a collision
                    if (previous == current) {
                        // count totalColVal based on unique collisions
                        if (!hasCountedThisCollision && !distinctCounted) {
                            totalColVal++;
                            distinctCounted = true;
                        }
                        hasCountedThisCollision = true;
                        count++;
                        // get the max collision value by using Math.max
                        maxColVal = Math.max(maxColVal, count);
                    } else {
                        // reset the count when we have a new collision
                        count = 0;
                        hasCountedThisCollision = false;
                        distinctCounted = false;
                    }
                    previous = current;
                    if (pq.size() % 200000 == 0) {
                        System.out.print("\b\b\b\b");
                        System.out.print("<]   ");
                        System.out.print("\b\b\b\b");
                    }

                }
                maxColValArr.add(maxColVal);
                totalColValArr.add(totalColVal);

            }

            // ------------------- print out the table -------------------//
            StringBuilder sb = new StringBuilder();
            sb.append("\n\n|   a values    |");

            // calculate the length of the digits displayed
            IntUnaryOperator tColDigits = new IntUnaryOperator() {
                @Override
                public int applyAsInt(int i) {
                    return Integer.toString(totalColValArr.get(i)).length();
                }
            };
            IntUnaryOperator mColDigits = new IntUnaryOperator() {
                @Override
                public int applyAsInt(int i) {
                    return Integer.toString(maxColValArr.get(i)).length();
                }
            };
            IntUnaryOperator aValDigits = new IntUnaryOperator() {
                @Override
                public int applyAsInt(int i) {
                    return Integer.toString(aValues[i]).length();
                }
            };
            int digits = 0;
            for (int i = 0; i < 5; i++) {
                digits = Math.max(digits, tColDigits.applyAsInt(i));
                digits = Math.max(digits, mColDigits.applyAsInt(i));
                digits = Math.max(digits, aValDigits.applyAsInt(i));
            }

            // print out the aVals with the spaces
            for (int i = 0; i < aValues.length; i++) {
                sb.append(" ");
                sb.append(aValues[i]);
                // print spaces with aValDigits.get(i) length - digits
                for (int j = 0; j < digits - aValDigits.applyAsInt(i); j++) {
                    sb.append(" ");
                }
                sb.append(" ");
                sb.append("|");
            }

            // print out another line before total collision
            int sbLength = sb.length() - 4;
            sb.append("\n|");
            for (int i = 0; i < sbLength; i++) {
                sb.append("-");
            }
            sb.append("|");

            // print out total collision
            sb.append("\n|total collision|");

            for (int i = 0; i < totalColValArr.size(); i++) {
                sb.append(" ");
                sb.append(totalColValArr.get(i));
                // print space with totalcolvararr.get(i) length - digits
                for (int j = 0; j < digits - tColDigits.applyAsInt(i); j++) {
                    sb.append(" ");
                }
                sb.append(" ");
                sb.append("|");
            }

            // print out another line before max collision
            sb.append("\n|");
            for (int i = 0; i < sbLength; i++) {
                sb.append("-");
            }
            sb.append("|");

            // print out max collision
            sb.append("\n| max collision |");
            for (int i = 0; i < maxColValArr.size(); i++) {
                sb.append(" ");
                sb.append(maxColValArr.get(i));
                // print space with maxcolvararr.get(i) length - digits
                for (int j = 0; j < digits - mColDigits.applyAsInt(i); j++) {
                    sb.append(" ");
                }
                sb.append(" ");
                sb.append("|");
            }

            String s = sb.toString();

            timeafter = System.currentTimeMillis();
            System.out.println(s);
            System.out.println("computed in: " + (timeafter - time) + "ms");
        } else {
            System.out.println("Skipped computing using hashPolynomial.");
        }

        System.out.print("\nDo you want to compute using compressionMAD? (y/n): ");
        input = sc.nextLine();
        sc.close();

        // ------------------- compute using compressionMAD -------------------//
        if (input.contains("y")) {
            System.out
                    .print("\nComputing using compressionMAD: [......................................................]");
            time = System.currentTimeMillis();
            // α=10,000, 100,000, and 200,000; and β=50,000, and 150,000
            int[] alphaVals = new int[] { 10000, 100000, 200000 };
            int[] betaVals = new int[] { 50000, 150000 };
            ArrayList<String> alpxbeta = new ArrayList<>();
            maxColValArr.clear();
            totalColValArr.clear();

            PriorityQueue<Long> pqFor41 = new PriorityQueue<>();
            for (String str : strings) {
                pqFor41.add(h.hashPolynomial(str, 41));
            }

            h.setnBuckets(pqFor41.size());
            int nPrime = h.findNextPrime(h.getnBuckets());
            h.setNextPrime(nPrime);

            for (int alpha : alphaVals) {
                for (int beta : betaVals) {
                    alpxbeta.add(alpha + "x" + beta);
                    h.setAlphaVal(alpha);
                    h.setBetaVal(beta);

                    PriorityQueue<Long> pq = new PriorityQueue<>();
                    for (long i : pqFor41) {
                        pq.add(h.compressionMAD(i));
                    }

                    long previous = pq.poll();
                    int count = 0;
                    int totalColVal = 0;
                    int maxColVal = 0;
                    boolean hasCountedThisCollision = false;
                    boolean distinctCounted = false;

                    while (!pq.isEmpty()) {
                        long current = pq.poll();
                        // if the previous and current are the same, then we have a collision
                        if (previous == current) {
                            // count totalColVal based on unique collisions
                            if (!hasCountedThisCollision && !distinctCounted) {
                                totalColVal++;
                                distinctCounted = true;
                            }
                            hasCountedThisCollision = true;
                            count++;
                            // get the max collision value by using Math.max
                            maxColVal = Math.max(maxColVal, count);
                        } else {
                            // reset the count when we have a new collision
                            count = 0;
                            hasCountedThisCollision = false;
                            distinctCounted = false;
                        }
                        previous = current;
                        if (pq.size() % 200000 == 0) {
                            System.out.print("\b\b\b\b");
                            System.out.print("<]   ");
                            System.out.print("\b\b\b\b");
                        }
                    }
                    maxColValArr.add(maxColVal);
                    totalColValArr.add(totalColVal);
                }
            }

            // ------------------- print out the table -------------------//
            StringBuilder sb = new StringBuilder();
            sb.append("\n\n|  alpha x beta |");

            // calculate the length of the digits displayed
            IntUnaryOperator tColDigits = new IntUnaryOperator() {
                @Override
                public int applyAsInt(int i) {
                    return Integer.toString(totalColValArr.get(i)).length();
                }
            };

            IntUnaryOperator mColDigits = new IntUnaryOperator() {
                @Override
                public int applyAsInt(int i) {
                    return Integer.toString(maxColValArr.get(i)).length();
                }
            };

            int digits = 0;
            for (int i = 0; i < 6; i++) {
                digits = Math.max(digits, tColDigits.applyAsInt(i));
                digits = Math.max(digits, mColDigits.applyAsInt(i));
                digits = Math.max(digits, alpxbeta.get(i).length());
            }

            // print out the alpxbeta with the spaces
            for (int i = 0; i < alpxbeta.size(); i++) {
                sb.append(" ");
                sb.append(alpxbeta.get(i));
                sb.append(" ");
                sb.append("|");
            }

            // print out another line before total collision
            int sbLength = sb.length() - 4;
            sb.append("\n|");
            for (int i = 0; i < sbLength; i++) {
                sb.append("-");
            }
            sb.append("|");

            // print out total collision
            sb.append("\n|total collision|");

            for (int i = 0; i < totalColValArr.size(); i++) {
                int size = alpxbeta.get(i).length() - Integer.toString(totalColValArr.get(i)).length();
                sb.append(" ");
                // print with totalcolvararr.get(i) length - digits
                sb.append(totalColValArr.get(i));
                for (int j = 0; j < size; j++) {
                    sb.append(" ");
                }
                sb.append(" ");
                sb.append("|");
            }

            // print out another line before max collision

            sb.append("\n|");
            for (int i = 0; i < sbLength; i++) {
                sb.append("-");
            }
            sb.append("|");

            // print out max collision
            sb.append("\n| max collision |");
            for (int i = 0; i < maxColValArr.size(); i++) {
                sb.append(" ");
                // print 0 with maxcolvararr.get(i) length - digits
                sb.append(maxColValArr.get(i));
                int size = alpxbeta.get(i).length() - Integer.toString(maxColValArr.get(i)).length();
                for (int j = 0; j < size; j++) {
                    sb.append(" ");
                }
                sb.append(" ");
                sb.append("|");
            }
            String s = sb.toString();
            System.out.println(s);
            timeafter = System.currentTimeMillis();
            System.out.println("computed in: " + (timeafter - time) + "ms");
        }
    }
}
