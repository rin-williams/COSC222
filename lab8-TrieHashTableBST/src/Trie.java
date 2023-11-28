
// code started from: https://www.geeksforgeeks.org/trie-insert-and-search/ ----------------------------------------------
// under license: https://creativecommons.org/licenses/by-sa/4.0/
// which states that "You are free to: Share — copy and redistribute the material in any medium or format
// modified, removed static keywords.
public class Trie {
    // Alphabet size (# of symbols)
    // modified to accept symbols etc
    private static final int ALPHABET_SIZE = 128;

    // trie node
    private class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    // modified to add and search for special characters
    public void insert(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level);
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isEndOfWord = true;
    }

    public boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level);

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        return (pCrawl.isEndOfWord);
    }

    // modified, removed main function
}
// Code end ----------------------------------------------