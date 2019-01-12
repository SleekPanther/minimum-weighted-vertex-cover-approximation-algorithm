import java.util.*;

public class MinimumWeightedVertexCover{
	public static void findMinimumWeightedVertexCoverApprox(ArrayList<Edge> graph, int[] weights){
		//Build String array of integer vertex names if no string names are provided
		String[] integerNames = new String[weights.length];
		for(int i=0; i<weights.length; i++){
			integerNames[i] = i+"";
		}
		findMinimumWeightedVertexCoverApprox(graph, weights, integerNames);
	}

	public static void findMinimumWeightedVertexCoverApprox(ArrayList<Edge> graph, int[] weights, String[] vertexNames){
		int[] remainingWeights = Arrays.copyOf(weights, weights.length);

		ArrayList<String> vertexCoverNodes = new ArrayList<String>();
		int totalWeight = 0;

		for(Edge edge : graph){
			int fromVertex = edge.fromVertex;
			int toVertex = edge.toVertex;
			if(remainingWeights[fromVertex]==0 || remainingWeights[toVertex]==0){		//skip edges if either vertex is already tight
				continue;
			}

			if(remainingWeights[fromVertex] < remainingWeights[toVertex]){		//fromVertex weight is smaller
				int smallerWeight = remainingWeights[fromVertex];
				edge.pricePaid = smallerWeight;
				remainingWeights[fromVertex] = 0;	//1 vertex becomes tight (greedy)
				remainingWeights[toVertex] -= smallerWeight;
				totalWeight += weights[fromVertex];
				vertexCoverNodes.add(vertexNames[fromVertex]);
			}
			else{		//toVertex weight is smaller or they're equal
				int smallerWeight = remainingWeights[toVertex];
				edge.pricePaid = smallerWeight;
				remainingWeights[toVertex] = 0;		//1 vertex becomes tight (greedy)
				remainingWeights[fromVertex] -= smallerWeight;
				totalWeight += weights[toVertex];
				vertexCoverNodes.add(vertexNames[toVertex]);
			}
			System.out.println("Chose Edge "+edge);
		}

		System.out.println("\nVertex Cover: "+vertexCoverNodes);
		System.out.println("Total Weight: "+totalWeight);
	}

	

	public static void main(String args[]){
		System.out.println("Graph 1");
		int[] weights1 = {4,3,5,3};
		String[] vertexNames1 = {"a", "b", "c", "d"};
		ArrayList<Edge> graph1 = new ArrayList<Edge>();
		graph1.add(new Edge(0,1, vertexNames1));
		graph1.add(new Edge(0,3, vertexNames1));
		graph1.add(new Edge(0,2, vertexNames1));
		graph1.add(new Edge(2,3, vertexNames1));
		graph1.add(new Edge(2,1, vertexNames1));
		MinimumWeightedVertexCover.findMinimumWeightedVertexCoverApprox(graph1, weights1, vertexNames1);
		//Optimal = a, c (Total weight = 4+5 = 9)

		System.out.println("\n\nGraph 2");
		int[] weights2 = {2,4,9,2};
		String[] vertexNames2 = {"a", "b", "c", "d"};
		ArrayList<Edge> graph2 = new ArrayList<Edge>();
		graph2.add(new Edge(0,2, vertexNames2));
		graph2.add(new Edge(0,1, vertexNames2));
		graph2.add(new Edge(0,3, vertexNames2));
		graph2.add(new Edge(2,3, vertexNames2));
		graph2.add(new Edge(1,2, vertexNames2));
		MinimumWeightedVertexCover.findMinimumWeightedVertexCoverApprox(graph2, weights2, vertexNames2);
		//Optimal Found!

		System.out.println("\n\nGraph 3");
		int[] weights3 = {2,3,4,5,7,9};
		String[] vertexNames3 = {"a", "b", "c", "d", "e", "f"};
		ArrayList<Edge> graph3 = new ArrayList<Edge>();
		graph3.add(new Edge(0,5, vertexNames3));
		graph3.add(new Edge(1,2, vertexNames3));
		graph3.add(new Edge(2,4, vertexNames3));
		graph3.add(new Edge(2,3, vertexNames3));
		graph3.add(new Edge(0,1, vertexNames3));
		graph3.add(new Edge(4,5, vertexNames3));
		graph3.add(new Edge(1,5, vertexNames3));
		MinimumWeightedVertexCover.findMinimumWeightedVertexCoverApprox(graph3, weights3, vertexNames3);
		//Optimal = a, c, f (Total weight = 2+4+9 = 16)
		

		System.out.println("\n\nGraph 4 (without vertex names)");
		ArrayList<Edge> graph4WithoutNames = new ArrayList<Edge>();
		int[] weights4WithoutNames = {10, 1, 1, 1};
		graph4WithoutNames.add(new Edge(0,1));
		graph4WithoutNames.add(new Edge(0,2));
		graph4WithoutNames.add(new Edge(0,3));
		MinimumWeightedVertexCover.findMinimumWeightedVertexCoverApprox(graph4WithoutNames, weights4WithoutNames);
		// Optimal Found!

		System.out.println("\n\nGraph 4");
		ArrayList<Edge> graph4 = new ArrayList<Edge>();
		int[] weights4 = {10, 1, 1, 1};
		String[] vertexNames4 = {"a", "b", "c", "d"};
		graph4.add(new Edge(0,1, vertexNames4));
		graph4.add(new Edge(0,2, vertexNames4));
		graph4.add(new Edge(0,3, vertexNames4));
		MinimumWeightedVertexCover.findMinimumWeightedVertexCoverApprox(graph4, weights4, vertexNames4);
		// Optimal Found!
	}
}

class Edge{
	public int fromVertex;
	public int toVertex;
	public int pricePaid = 0;

	private String[] vertexNames;

	public Edge(int fromVertex, int toVertex){
		this.fromVertex=fromVertex;
		this.toVertex=toVertex;
	}

	public Edge(int fromVertex, int toVertex, String[] vertexNames){
		this.fromVertex=fromVertex;
		this.toVertex=toVertex;
		this.vertexNames=vertexNames;
	}
	
	@Override
	public String toString(){
		if(vertexNames !=null){
			return "("+vertexNames[fromVertex]+", "+vertexNames[toVertex]+") [Price paid="+pricePaid+"]";
		}
		return toStringAsInt();
	}

	public String toStringAsInt(){
		return "("+fromVertex+", "+toVertex+") [Price paid="+pricePaid+"]";
	}
}