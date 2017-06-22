# Minimum Weighted Vertex Cover - Pricing Method (Approximation Algorithm)
Approximation Algorithm for the NP-Complete problem of finding a **vertex cover of minimum weight** in a graph with weighted vertices. Guarantees an answers at most **2 times** the optimal minimum weighted vertex cover (2-approximation algorithm, [see references for the proof](#References)).

## Problem Statement
- Given a weighted graph with each vertex weighted > 0
- Find a vertex cover **S ⊆ V** (each edge has at least 1 edge in **S**)
- And has to **minimum total weight** (when adding weights of the vertices in the vertex cover)
#### Graph1
<img src="images/graph1.png-" width="400">

#### Graph1 Optimal Minimum Weighted Vertex Cover
<img src="images/graph1-optimal-vertex-cover.png-" width="400">

<h4>Vertex Cover = 0, 2 <br>
Total Weight = 4+5 = 9
</h4>

#### Graph1 Algorithm's Weighted Vertex Cover (sub-optimal)
<img src="images/graph1-algorithm-vertex-cover.png-" width="400">

<h4>Vertex Cover = 0, 1, 2, 3 <br>
Total Weight = 4+3+5+3 = 15
</h4>

The algorithm does not find the optimal solution, but the answer given is **15**, which is less than **twice the optimal value** which would be **2 * 9 = 18**  


## Algorithm Solution
The problem is **NP-Complete** and this does not guarantee an optimal solution, but it's a polynomial-time 2-approximation algorithm  
The answer found is at most **2 times** the weight of the optimal Minimum Weighted Vertex Cover

### Pricing Strategy (Fairness)
- Each edge ***e=(i,j)*** must pay a price > 0 to vertices **i** and **j**
- **Fairness: ** an edge cannot pay more than the remaining weight of the vertex
- **Tight: ** a vertex is tight when it has no remaining weight

## Input Graphs

## References
- [Approximation Algorithms - Kevin Wayne](https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pdf/11ApproximationAlgorithms.pdf#page=25)
- [Vertex Cover Problem - GeeksForGeeks](http://www.geeksforgeeks.org/vertex-cover-problem-set-1-introduction-approximate-algorithm-2/) adapted method for finding a simple vertex cover
