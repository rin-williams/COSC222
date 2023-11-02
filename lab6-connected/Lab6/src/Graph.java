import java.util.List;

public class Graph {
	Node[] graph;

	boolean isConnected(Node u, Node v) {
		return u.isConnected(v);
	}

	Graph(List<Edge> Edges) {
		// we make a graph of nodes since each nodes has its own hashtable, so finding
		// the nodes at index i
		// will be O(1), and getting the connections will also be O(1).
		// data structure used is adjacency matrix with the implementation of hashtable.
		// while query time is O(1), This implementation ultimately sacifices
		// space complexity to O(V^2) where V is the number of vertices.

		// iterate through the edges first to find the max index, then make the graph
		// since each nodes has a hashtable with the size of how many vertices there
		// are.
		int maxIndex = -1;
		for (Edge edge : Edges) {
			maxIndex = Math.max(maxIndex, Math.max(edge.u, edge.v));
		}
		// add one to account for the size-index error.
		int vertexSize = maxIndex + 1;
		graph = new Node[vertexSize];
		// first make a graph of Nodes first
		for (int i = 0; i < vertexSize; i++) {
			graph[i] = new Node(i, vertexSize);
		}
		// then set the nodes based on what the edge says,
		for (Edge edge : Edges) {
			Node u = graph[edge.u];
			Node v = graph[edge.v];
			u.connectWith(v);
		}
	}
}