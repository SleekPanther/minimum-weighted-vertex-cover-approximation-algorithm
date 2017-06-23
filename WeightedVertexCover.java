import java.util.*;

public class WeightedVertexCover{
	private int vertexCount;
	private LinkedList<Integer>[] graph;	//undirected graph adjacency list

	public WeightedVertexCover(int vertexCount){
		this.vertexCount = vertexCount;
		graph = new LinkedList[vertexCount];
		for(int i=0; i<vertexCount; ++i){
			graph[i] = new LinkedList<Integer>();
		}
	}

	//Undirected graph, so adds link to 1st vertex in the 2nd's list & link to the 2nd in the 1st's list (represented twice)
	public void addEdge(int v, int w){
		graph[v].add(w);
		graph[w].add(v);
	}

	public void printWeightedVertexCover(int[] originalWeights){
		//Copy weights to preserve original weights when printing at the end
		int[] remainingWeights = new int[originalWeights.length];
		for(int i=0 ;i<remainingWeights.length; i++){
			remainingWeights[i]=originalWeights[i];
		}
	
		// Initialize all vertices as un-visited.
		boolean visited[] = new boolean[vertexCount];
		for (int i=0; i<vertexCount; i++){
			visited[i] = false;
		}
		
		Iterator<Integer> iterator;

		// Consider all edges one by one
		for (int u=0; u<vertexCount; u++){
			// Pick edge if both vertices have remaining weight > 0
			if(remainingWeights[u]>0){
				// Go through all adjacents of u and pick the first with remaining weight > 0 (Basically picking an edge (u, v) from remaining edges)
				iterator = graph[u].iterator();
				while (iterator.hasNext()){
					int v = iterator.next();
					if(remainingWeights[v]>0){
						// Visit both edges to say they're included in the vertex cover
						visited[v] = true;
						visited[u] = true;
						//Update the remaining weights by maxing out the one with the smaller remaining weight
						int smallerWeight = Math.min(remainingWeights[u], remainingWeights[v]);
						remainingWeights[u] -= smallerWeight;
						remainingWeights[v] -= smallerWeight;
						System.out.println("Chose Edge "+u+"-->"+v+"  (Edge paid weight="+smallerWeight+")");
						break;		//stop looking for adjacent vertices once we find an edge
					}
				}
			}
		}

		//Find which nodes have been visited & print vertex cover
		ArrayList<ArrayList<Integer>> vertexCover = new ArrayList<ArrayList<Integer>>();
		System.out.print("\nVertices in the Vertex Cover:  ");
		for (int j=0; j<vertexCount; j++){
			if (visited[j]){
				vertexCover.add(new ArrayList<Integer>(Arrays.asList(j, originalWeights[j])));
			}
		}
		//Add the 1st vertex
		System.out.print(+vertexCover.get(0).get(0));
		int vertexCoverWeight=vertexCover.get(0).get(1);
		String vertexCoverWeightsMath = vertexCover.get(0).get(1) + "";
		//Now add remaining vertices to avoid trailing commas & + signs
		for(int i=1; i<vertexCover.size(); i++){
			System.out.print(", "+vertexCover.get(i).get(0));	//vertex id
			int vertexWeight = vertexCover.get(i).get(1);
			vertexCoverWeightsMath = vertexCoverWeightsMath + "+" + vertexWeight;
			vertexCoverWeight += vertexWeight;	//vertex weight
		}
		System.out.println("\nTotal Weight of Vertex Cover: "+vertexCoverWeightsMath+" = "+vertexCoverWeight);
	}

	public static void main(String args[]){
		System.out.println("Graph 1");
		int vertexCount = 4;
		WeightedVertexCover graph1 = new WeightedVertexCover(vertexCount);
		graph1.addEdge(0,1);
		graph1.addEdge(0,2);
		graph1.addEdge(0,3);
		graph1.addEdge(1,2);
		graph1.addEdge(3,2);
		
		int[] weights = {4,3,3,5};
		graph1.printWeightedVertexCover(weights);
		//Optimal = 0, 2 (Total weight = 4+5 = 9)


		System.out.println("\n\nGraph 2");
		vertexCount = 4;
		WeightedVertexCover graph2 = new WeightedVertexCover(vertexCount);
		graph2.addEdge(0,2);
		graph2.addEdge(2,3);
		graph2.addEdge(0,1);
		graph2.addEdge(0,3);
		graph2.addEdge(1,2);
		
		weights = new int[]{2,4,9,2};
		graph2.printWeightedVertexCover(weights);
		//Optimal = 0, 1, 3 (Total weight = 2+4+2 = 8)
		
		
		System.out.println("\n\nGraph 3");
		vertexCount = 6;
		WeightedVertexCover graph3 = new WeightedVertexCover(vertexCount);
		graph3.addEdge(0,5);
		graph3.addEdge(1,2);
		graph3.addEdge(2,4);
		graph3.addEdge(2,3);
		graph3.addEdge(0,1);
		graph3.addEdge(4,5);
		graph3.addEdge(1,5);
		
		weights = new int[]{2,3,4,5,7,9};
		graph3.printWeightedVertexCover(weights);
		//Optimal = 0,2,5 (Total weight = 2+4+9 = 16)
	}
}