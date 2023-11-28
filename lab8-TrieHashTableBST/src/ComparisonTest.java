import java.io.IOException;
import java.net.MalformedURLException;

class ComparisonTest {

    public static void main(String[] args) throws MalformedURLException, IOException {
        Comparison c = new Comparison();
        System.out.println("Trie: " + c.trieTime() + "s");
        System.out.println("Red-black tree: " + c.rbTreeTime() + "s");
        System.out.println("Hash table: " + c.hashTime() + "s");
    }
}
