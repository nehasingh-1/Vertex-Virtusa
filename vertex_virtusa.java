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

    // Function to find a maximal independent set using a greedy approach
    public Set<Integer> findMaximalIndependentSet() {
        Set<Integer> mis = new HashSet<>();   // Stores the maximal independent set
        boolean[] selected = new boolean[vertices]; // Tracks if a vertex is selected

        // Iterate through each vertex
        for (int v = 0; v < vertices; v++) {
            // If the vertex is not selected and none of its neighbors are selected
            if (!selected[v]) {
                mis.add(v);   // Add the vertex to the independent set
                selected[v] = true;   // Mark it as selected

                // Mark all adjacent vertices as selected
                for (int neighbor : adjList.get(v)) {
                    selected[neighbor] = true;
                }
            }
        }
        return mis;
    }
}

public class MaximalIndependentSet {
    public static void main(String[] args) {
        // Create a graph with 5 vertices (example graph)
        Graph graph = new Graph(5);

        // Add edges (example: A=0, B=1, C=2, D=3, E=4)
        graph.addEdge(0, 1); // A -- B
        graph.addEdge(0, 2); // A -- C
        graph.addEdge(1, 3); // B -- D
        graph.addEdge(2, 4); // C -- E

        // Find and print the Maximal Independent Set
        Set<Integer> mis = graph.findMaximalIndependentSet();
        System.out.println("Maximal Independent Set (Greedy Approach): " + mis);
    }
}
