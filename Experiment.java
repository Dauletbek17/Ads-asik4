import java.util.Random;

public class Experiment {


    public long[] runTraversals(Graph g) {
        long startBfs = System.nanoTime();
        g.bfs(0);
        long endBfs = System.nanoTime();

        long startDfs = System.nanoTime();
        g.dfs(0);
        long endDfs = System.nanoTime();

        return new long[]{endBfs - startBfs, endDfs - startDfs};
    }


    public Graph buildRandomGraph(int n, int edges) {
        Graph g = new Graph();
        for (int i = 0; i < n; i++) {
            g.addVertex(new Vertex(i));
        }
        Random rand = new Random(42);
        int added = 0;
        while (added < edges) {
            int from = rand.nextInt(n);
            int to = rand.nextInt(n);
            if (from != to) {
                g.addEdge(from, to);
                added++;
            }
        }
        return g;
    }


    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};

        System.out.println("\n========== EXPERIMENT RESULTS ==========");
        System.out.printf("%-12s %-15s %-15s%n", "Size", "BFS (ns)", "DFS (ns)");
        System.out.println("----------------------------------------");

        for (int size : sizes) {

            Graph g = buildRandomGraph(size, size * 2);
            long[] times = runTraversals(g);
            System.out.printf("%-12d %-15d %-15d%n", size, times[0], times[1]);
        }
        System.out.println("========================================\n");
    }

    public void printResults() {
        System.out.println("Experiment finished.");
        System.out.println("See the table above for BFS vs DFS execution times.");
    }
}