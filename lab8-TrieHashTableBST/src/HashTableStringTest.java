import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HashTableStringTest {
    private HashTableString hashTable;

    @Before
    public void setUp() {
        hashTable = new HashTableString();
    }

    @Test
    public void testInsert() {
        hashTable.insert("apple");
        hashTable.insert("banana");
        hashTable.insert("cherry");

        assertTrue(hashTable.search("apple"));
        assertTrue(hashTable.search("banana"));
        assertTrue(hashTable.search("cherry"));
    }

    @Test
    public void testSearch() {
        hashTable.insert("apple");
        hashTable.insert("banana");
        hashTable.insert("cherry");

        assertTrue(hashTable.search("apple"));
        assertTrue(hashTable.search("banana"));
        assertTrue(hashTable.search("cherry"));

        assertFalse(hashTable.search("orange"));
        assertFalse(hashTable.search("grape"));
    }
}