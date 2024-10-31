import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Graph {
    private int vertices;
    private List<List<Integer>> adjList;
    // Constructor to initialize the graph
    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    // Function to add an edge between two vertices
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    // Brute-force function to find the largest independent set
    public Set<Integer> findMaximalIndependentSet() {
        Set<Integer> maxSet = new HashSet<>();  // Stores the largest independent set
        // Iterate through all subsets of vertices
        for (int i = 0; i < (1 << vertices); i++) {  // 1 << vertices means 2^vertices
            Set<Integer> currentSet = new HashSet<>();  // Current subset of vertices
            boolean isIndependent = true;  // To check if the current subset is independent
            // Iterate through each vertex to see if it is in the current subset
            for (int v = 0; v < vertices; v++) {
                if ((i & (1 << v)) != 0) {  // Check if vertex 'v' is in the subset
                    // Check if this vertex forms an edge with any other vertex in the subset
                    for (int neighbor : currentSet) {
                        if (adjList.get(v).contains(neighbor)) {
                            isIndependent = false;  // If connected, it's not independent
                            break;
                        }
                    }
                    if (isIndependent) {
                        currentSet.add(v);  // Add vertex to the current independent set
                    } else {
                        break;  // No need to check further if the set is not independent
                    }
                }
            }

            // If the current subset is independent and larger than the maxSet, update maxSet
            if (isIndependent && currentSet.size() > maxSet.size()) {
                maxSet = new HashSet<>(currentSet);
            }
        }
        return maxSet;  // Return the largest independent set found
    }
}

public class BruteForce {
    public static void main(String[] args) {
        // Create a graph with 5 vertices (example graph)
        Graph graph = new Graph(5);

        // Add edges (example: A=0, B=1, C=2, D=3, E=4)
        graph.addEdge(0, 1); // A -- B
        graph.addEdge(0, 2); // A -- C
        graph.addEdge(1, 3); // B -- D
        graph.addEdge(2, 4); // C -- E

        // Find and print the Maximal Independent Set using brute-force
        Set<Integer> mis = graph.findMaximalIndependentSet();
        System.out.println("Maximal Independent Set (Brute Force): " + mis);
    }
}
