import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Comparison {
    ArrayList<String> data = new ArrayList<String>();
    URL url;
    long startTime, endTime;

    public Comparison() throws IOException, MalformedURLException {
        // preprocessing
        url = new URL("https://github.com/dwyl/english-words/raw/master/words.txt");
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            data.add(line);

        }
        scanner.close();

    }

    public double trieTime() {
        Trie trie = new Trie();
        // preprocessing
        for (String word : data) {
            trie.insert(word);
        }
        startTime = System.currentTimeMillis();
        for (String word : data) {
            if (trie.search(word) == false) {
                System.out.println("Error: " + word + " not found, trie was implemented incorrectly.");
            }
        }
        endTime = System.currentTimeMillis();

        // convert to seconds, up to 3 decimal places
        double time = (endTime - startTime) / 1000.0;
        return time;
    }

    public double rbTreeTime() {
        RBTree rbt = new RBTree();
        // preprocessing
        for (String word : data) {
            rbt.insert(word);
        }
        startTime = System.currentTimeMillis();
        for (String word : data) {
            if (rbt.searchFor(word) == false) {
                System.out.println("Error: " + word + " not found, RBT was implemented incorrectly.");
            }
        }
        endTime = System.currentTimeMillis();

        // convert to seconds, up to 3 decimal places
        double time = (endTime - startTime) / 1000.0;
        return time;
    }

    public double hashTime() {
        HashTableString hash = new HashTableString();
        // preprocessing
        for (String word : data) {
            hash.insert(word);
        }
        startTime = System.currentTimeMillis();
        for (String word : data) {
            if (hash.search(word) == false) {
                System.out.println("Error: " + word + " not found, hash table was implemented incorrectly.");
            }
        }
        endTime = System.currentTimeMillis();

        // convert to seconds, up to 3 decimal places
        double time = (endTime - startTime) / 1000.0;
        return time;
    }
}
