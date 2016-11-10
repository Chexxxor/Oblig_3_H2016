package task2;

import java.util.*;

public class UnweightedGraph<V> extends AbstractGraph<V> {
	/** Construct an empty graph */
	public UnweightedGraph() {
	}

	/** Construct a graph from vertices and edges stored in arrays */
	public UnweightedGraph(V[] vertices, int[][] edges) {
		super(vertices, edges);
	}
	/** Construct a graph from vertices and edges stored in List */
	public UnweightedGraph(List<V> vertices, List<Edge> edges) {
		super(vertices, edges);
	}
	/** Construct a graph for integer vertices 0, 1, 2 and edge list */
	public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}
	/** Construct a graph from integer vertices 0, 1, and edge array */
	public UnweightedGraph(int[][] edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}
	
	public List<Integer> getPath(int u, int v){
		return (List<Integer>) bfs(u).getPath(v);
	}
	
	//@Override
	public Tree dfs(int v){
		Deque<Integer> stack = new ArrayDeque<Integer>();
		List<Integer> searchOrder = new ArrayList<>();
		stack.push(v);
		searchOrder.add(v);

		boolean[] isVisited = new boolean[vertices.size()];
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1; // Initialize parent[i] to -1
		
		while(!stack.isEmpty()){
			int current = stack.peek();
			isVisited[current] = true;

			boolean allVisited = true;
			for (Edge e : neighbors.get(current)) {
				if (!isVisited[e.v]) {
					allVisited = false;
					searchOrder.add(e.v);
					parent[e.v] = current;
					stack.push(e.v);
					break;
				}
			}
			
			if(allVisited)
				stack.pop();
		}
		
		return new Tree(v, parent, searchOrder);
	}
}