import java.util.*;

public class MinimumWeightedVertexCover{
	public static void findMinimumWeightedVertexCoverApprox(ArrayList<Edge> graph,  int[] weights){
		int[] remainingWeights = Arrays.copyOf(weights, weights.length);

		ArrayList<Integer> vertexCoverNodes = new ArrayList<Integer>();
		int totalWeight = 0;

		for(Edge edge : graph){
			int fromVertex = edge.fromVertex;
			int toVertex = edge.toVertex;
			if(remainingWeights[fromVertex]==0 || remainingWeights[toVertex]==0){		//skip edges where either vertex is tight
				// System.out.println("skipped "+edge);
				continue;
			}

			int smallerWeight = 0;
			if(remainingWeights[fromVertex] < remainingWeights[toVertex]){		//fromVertex is smaller
				smallerWeight = remainingWeights[fromVertex];
				edge.pricePaid = smallerWeight;
				remainingWeights[fromVertex] = 0;
				remainingWeights[toVertex] -= smallerWeight;
				totalWeight += smallerWeight;
				vertexCoverNodes.add(fromVertex);
			}
			else{		//toVertex is smaller or they're equal
				smallerWeight = remainingWeights[toVertex];
				edge.pricePaid = smallerWeight;
				remainingWeights[toVertex] = 0;
				remainingWeights[fromVertex] -= smallerWeight;
				totalWeight += smallerWeight;
				vertexCoverNodes.add(toVertex);
			}
			System.out.println("Chose edge "+edge);
		}

		System.out.println("\nVertex Cover Nodes: "+vertexCoverNodes);
		System.out.println("Total Weight: "+totalWeight);
	}

	

	public static void main(String args[]){
		System.out.println("Graph 1");
		int[] weights1 = {4,3,5,3};
		ArrayList<Edge> graph1 = new ArrayList<Edge>();
		graph1.add(new Edge(0,1));
		graph1.add(new Edge(0,3));
		graph1.add(new Edge(0,2));
		graph1.add(new Edge(2,3));
		graph1.add(new Edge(2,1));
		MinimumWeightedVertexCover.findMinimumWeightedVertexCoverApprox(graph1, weights1);

		System.out.println("\n\nGraph 4");
		int[] weights4 = {100, 1, 1, 1};
		ArrayList<Edge> graph4 = new ArrayList<Edge>();
		graph4.add(new Edge(0,1));
		graph4.add(new Edge(0,2));
		graph4.add(new Edge(0,3));
		MinimumWeightedVertexCover.findMinimumWeightedVertexCoverApprox(graph4, weights4);
	}
}

class Edge{
	public int fromVertex;
	public int toVertex;
	public int pricePaid = 0;

	public Edge(int fromVertex, int toVertex){
		this.fromVertex=fromVertex;
		this.toVertex=toVertex;
	}
	
	@Override
	public String toString(){
		return "("+fromVertex+", "+toVertex+") [Price paid="+pricePaid+"]";
	}
}