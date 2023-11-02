import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class GraphTest {
	List<Edge> edges = new ArrayList<>();
	Graph socialNetwork;

	@Test
	void testIsConnected() {
		edges = Arrays.asList(new Edge(0, 1), new Edge(0, 2));
		socialNetwork = new Graph(edges);
		assertTrue(socialNetwork.isConnected(socialNetwork.graph[0], 
				socialNetwork.graph[1]));
		assertFalse(socialNetwork.isConnected(socialNetwork.graph[1], 
				socialNetwork.graph[2]));
		
	}

}
