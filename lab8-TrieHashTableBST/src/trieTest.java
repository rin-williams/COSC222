import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class trieTest {

    @Test
    void testInsertAndSearch() {
        Trie trie = new Trie();

        // Insert words into the trie
        trie.insert("apple");
        trie.insert("banana");
        trie.insert("cat");
        trie.insert("apple!");

        // Search for existing words
        assertTrue(trie.search("apple"));
        assertTrue(trie.search("banana"));
        assertTrue(trie.search("cat"));

        // Search for non-existing words
        assertFalse(trie.search("dog"));
        assertFalse(trie.search("car"));

        // search for words with special characters
        assertTrue(trie.search("apple!"));
        assertFalse(trie.search("banana!"));
    }
}