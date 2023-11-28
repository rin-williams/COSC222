import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RBTreeTest {
    @Test
    void testInsertAndSearch() {
        RBTree rbt = new RBTree();

        // Insert words into the trie
        rbt.insert("apple");
        rbt.insert("banana");
        rbt.insert("cat");
        rbt.insert("apple!");

        // Search for existing words
        assertTrue(rbt.search("apple").val.equals("apple"));
        assertTrue(rbt.search("banana").val.equals("banana"));
        assertTrue(rbt.search("cat").val.equals("cat"));

        // Search for non-existing words
        // since the word is not in the tree, the search method should return the
        // last node that was visited during the search, which is the node with value
        // "cat"
        assertFalse(rbt.search("dog").val.equals("dog"));
        assertTrue(rbt.search("car").val.equals("cat"));

        // search for words with special characters
        assertTrue(rbt.search("apple!").val.equals("apple!"));
        // since the word "banana!" is not in the tree, the search method should return
        // the last node that was visited during the search, which is the node with
        // value "cat"
        assertTrue(rbt.search("banana!").val.equals("cat"));

        // test for searchFor()
        assertTrue(rbt.searchFor("apple"));
        assertTrue(rbt.searchFor("apple!"));
        assertFalse(rbt.searchFor("dog"));

    }
}
