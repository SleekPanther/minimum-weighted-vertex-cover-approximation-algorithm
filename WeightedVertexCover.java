import java.util.*;

public class WeightedVertexCover{
	private int vertexCount;
	private LinkedList<Integer>[] graph;	//undirected graph adjacency list

	public WeightedVertexCover(int vertexCount){
		this.vertexCount = vertexCount;
		graph = new LinkedList[vertexCount];
		for (int i=0; i<vertexCount; ++i){
			graph[i] = new LinkedList<Integer>();
		}
	}

	//Undirected graph, so adds link to 1st vertex in the 2nd's list & link to the 2nd in the 1st's list (represented twice)
	public void addEdge(int v, int w)	{
		graph[v].add(w);
		graph[w].add(v);
	}

	public void printVertexCover(int[] originalWeights){
		//Copy weights to preserve original weights when printing at the end
		int[] remainingWeights = new int[originalWeights.length];
		for(int i=0 ;i<remainingWeights.length; i++){
			remainingWeights[i]=originalWeights[i];
		}
		
		System.out.println(Arrays.toString(graph));
	
		// Initialize all vertices as un-visited.
		boolean visited[] = new boolean[vertexCount];
		for (int i=0; i<vertexCount; i++){
			visited[i] = false;
		}
		
		LinkedList<Integer> weightedvertexCover = new LinkedList<Integer>(); 

		Iterator<Integer> i;

		// Consider all edges one by one
		for (int u=0; u<vertexCount; u++){
			// An edge is only picked when both visited[u] and visited[v] are false
			//if (visited[u] == false){
			if(remainingWeights[u]>0){
				// Go through all adjacents of u and pick the first not yet visited vertex (We are basically picking an edge (u, v) from remaining edges.
				i = graph[u].iterator();
				while (i.hasNext()){
					int v = i.next();
					//if (visited[v] == false){
					if(remainingWeights[v]>0){
						// Add the vertices (u, v) to the result set. We make the vertex u and v visited so that all edges from/to them would be ignored
						visited[v] = true;
						visited[u] = true;
						int smallerWeight = Math.min(remainingWeights[u], remainingWeights[v]);
						remainingWeights[u] -= smallerWeight;
						remainingWeights[v] -= smallerWeight;
						System.out.println("Chose Edge "+u+"-->"+v+"  (Edge paid weight="+smallerWeight+")");
						break;
					}
				}
			}
		}

		int vertexCoverWeight=0;
		System.out.println("\nVertices in the Vertex Cover");
		for (int j=0; j<vertexCount; j++){
			if (visited[j]){
				vertexCoverWeight+=originalWeights[j];
				System.out.print(j+", ");
			}
		}
		
		System.out.println("\nTotal Weight of Vertex Cover="+vertexCoverWeight);
	}

	public static void main(String args[])	{
		WeightedVertexCover g = new WeightedVertexCover(4);
//		g.addEdge(0,2);
//		g.addEdge(2,3);
//		g.addEdge(0,1);
//		g.addEdge(0,3);
//		g.addEdge(1,2);
//		
//		int[] weights = {2,4,9,2};
//		g.printVertexCover(weights);
		
		
		// g = new WeightedVertexCover(4);
		// g.addEdge(0,1);
		// g.addEdge(0,2);
		// g.addEdge(0,3);
		// g.addEdge(1,3);
		// g.addEdge(3,2);
		
		// int[] weights = {4,3,3,5};
		// g.printVertexCover(weights);

		g = new WeightedVertexCover(6);
		g.addEdge(0,5);
		g.addEdge(1,2);
		g.addEdge(2,4);
		g.addEdge(2,3);
		g.addEdge(0,1);
		g.addEdge(4,5);
		g.addEdge(1,3);
		
		int[] weights = {2,3,4,5,7,9};
		g.printVertexCover(weights);
		//optimal = 0,2,5 = 2+4+9=16
	}
}