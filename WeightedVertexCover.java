import java.io.*;
import java.util.*;
 
class WeightedVertexCover {
	// private int V;
	// private LinkedList<Integer> adj[];

	// public WeightedVertexCover(int v) {
	// 	V = v;
	// 	adj = new LinkedList[v];
	// 	for (int i=0; i<v; ++i)
	// 		adj[i] = new LinkedList();
	// }

	//Function to add an edge into the graph
	// void addEdge(int v, int w) {
	// 	adj[v].add(w);  // Add w to v's list.
	// 	adj[w].add(v);  //Graph is undirected
	// }

	// The function to print vertex cover
	public void printVertexCover(int[][] graph, int[] originalWeights) {
		// Initialize all vertices as not visited.
		int vertexCount=graph.length;
		boolean visited[] = new boolean[vertexCount];
		for (int i=0; i<vertexCount; i++)
			visited[i] = false;

		// Consider all edges one by one
		for (int u=0; u<vertexCount; u++) {
			// An edge is only picked when both visited[u] and visited[v] are false
			if (visited[u] == false) {
				// Go through all adjacents of u and pick the first not yet visited vertex (We are basically picking an edge (u, v) from remaining edges.
				// i = adj[u].iterator();
				// while (i.hasNext()) {
				for(int j=0; j<graph[u].length; j++){
					// int v = i.next();
					int v = graph[u][0];
					if (visited[v] == false) {
						// Add the vertices (u, v) to the result set. We make the vertex u and v visited so that all edges from/to them would be ignored
						visited[v] = true;
						visited[u] = true;
						break;
					}
				}
			}
		}

		// Print the vertex cover
		for (int j=0; j<vertexCount; j++)
			if (visited[j])
				System.out.print(j+" ");
	}

	public static void main(String args[]) {
		// int[][] graph = {{1, 2, 3},
		// 				{0, 2},
		// 				{0, 1, 3},
		// 				{0, 2}
		// 			};
		int[][] graph = {{3, 2, 1},	//0
						{2, 3, 0},		//1
						{1, 3, 0},		//2
						{1, 2, 0},		//3
					};
		// int[][] graph = {{1, 2},
		// 				{0, 3},
		// 				{0},
		// 				{1, 4},
		// 				{3, 5},
		// 				{4, 6},
		// 				{5}
		// 			};
		int[] weights = {2, 4, 9, 2};
		WeightedVertexCover vertexCoverFinder = new WeightedVertexCover();
		vertexCoverFinder.printVertexCover(graph, weights);

		// WeightedVertexCover g = new WeightedVertexCover(7);
		// g.addEdge(0, 1);
		// g.addEdge(0, 2);
		// g.addEdge(1, 3);
		// g.addEdge(3, 4);
		// g.addEdge(4, 5);
		// g.addEdge(5, 6);

		// g.printVertexCover();
	}
}