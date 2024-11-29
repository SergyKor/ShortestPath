import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt(); // Number of test cases

        for (int t = 0; t < testCases; t++) {
            int numCities = scanner.nextInt(); // Number of cities
            Map<String, Integer> cityIndexMap = new HashMap<>(); // Maps city names to indices
            List<List<int[]>> graph = new ArrayList<>(); // Adjacency list representation of the graph

            for (int i = 0; i < numCities; i++) {
                String cityName = scanner.next(); // City name
                cityIndexMap.put(cityName, i); // Assign index to city
                int numNeighbors = scanner.nextInt(); // Number of neighboring cities
                List<int[]> edges = new ArrayList<>(); // List of edges from this city

                for (int j = 0; j < numNeighbors; j++) {
                    int neighborIndex = scanner.nextInt() - 1; // Neighbor city index (1-based to 0-based)
                    int cost = scanner.nextInt(); // Transportation cost
                    edges.add(new int[]{neighborIndex, cost}); // Add edge
                }

                graph.add(edges); // Add edges for the city
            }

            int numPaths = scanner.nextInt(); // Number of paths to find
            for (int i = 0; i < numPaths; i++) {
                String startCity = scanner.next(); // Source city
                String endCity = scanner.next(); // Destination city
                int startIndex = cityIndexMap.get(startCity); // Get source index
                int endIndex = cityIndexMap.get(endCity); // Get destination index

                // Dijkstra's algorithm to find the shortest path from startIndex to endIndex
                int[] distances = new int[numCities]; // Distance from start to each city
                Arrays.fill(distances, Integer.MAX_VALUE); // Initialize distances with maximum value
                distances[startIndex] = 0; // Distance to start city is 0

                PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Min-heap priority queue
                pq.add(new int[]{startIndex, 0}); // Add the start city to the queue

                while (!pq.isEmpty()) {
                    int[] current = pq.poll(); // Get the city with the shortest distance
                    int currentNode = current[0]; // Current city index
                    int currentCost = current[1]; // Current cost

                    if (currentCost > distances[currentNode]) continue; // Skip if already visited with a shorter distance

                    for (int[] edge : graph.get(currentNode)) { // Iterate through all neighbors
                        int neighbor = edge[0]; // Neighbor city index
                        int newCost = currentCost + edge[1]; // Cost to reach the neighbor

                        if (newCost < distances[neighbor]) { // If a shorter path is found
                            distances[neighbor] = newCost; // Update the distance
                            pq.add(new int[]{neighbor, newCost}); // Add neighbor to the queue
                        }
                    }
                }

                System.out.println(distances[endIndex]); // Output the shortest distance to the destination city
            }

            if (t < testCases - 1) {
                scanner.nextLine(); // Skip empty line between test cases
            }
        }
    }
}