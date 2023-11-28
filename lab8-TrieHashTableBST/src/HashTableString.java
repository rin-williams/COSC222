import java.util.Hashtable;

public class HashTableString {
    private Hashtable<String, String> table;

    public HashTableString() {
        table = new Hashtable<String, String>();
    }

    // Method to add a word to the hash table
    public void insert(String word) {
        table.put(word, word);
    }

    // Method to search for a word in the hash table
    public boolean search(String word) {
        return table.containsKey(word);
    }
}
