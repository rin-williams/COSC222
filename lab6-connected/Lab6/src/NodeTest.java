import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NodeTest {

	@Test
	public void testConnectWith() {
		Node n1 = new Node(0, 2);
		Node n2 = new Node(1, 2);
		
		n1.connectWith(n2);
		assertTrue(n1.isConnected(n2));
		
		// test for not connected
		Node n3 = new Node(0, 2);
		assertFalse(n3.isConnected(n2));
	}

}
