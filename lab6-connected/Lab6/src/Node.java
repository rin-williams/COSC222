// this is the vertex aka the people

import java.util.Hashtable;

public class Node {
	int index;
	Hashtable<Integer, Integer> connections = new Hashtable<>();

	Node(int index, int size) {
		this.index = index;
		for (int i = 0; i < size; i++) {
			connections.put(i, 0);
		}
	}

	void connectWith(Node u) {
		this.connections.put(u.index, 1);
		u.connections.put(this.index, 1);
	}
	

	boolean isConnected(Node u) {
		// this node is connected if and only if:
		return (this.connections.get(u.index) == 1 && u.connections.get(this.index) == 1);
	}
}
