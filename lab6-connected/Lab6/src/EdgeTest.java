
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EdgeTest {

	@Test
	void testEdgeToString() {
		Edge edge = new Edge(0, 1);
		assertEquals(edge.toString(), edge.u + "-" + edge.v);
	}

	@Test
	void testEdgeIsEquals() {
		Edge edge1 = new Edge(0, 1);
		Edge edge2 = new Edge(0, 1);
		assertTrue(edge1.isEqual(edge2));
		assertTrue(edge2.isEqual(edge1));
		// test for not equals
		Edge edge3 = new Edge(1, 2);
		assertFalse(edge3.isEqual(edge1));
		assertFalse(edge1.isEqual(edge3));
	}
}
