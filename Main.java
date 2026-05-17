public class Main {
    public static void main(String[] args) {


        System.out.println("===== SMALL GRAPH (10 vertices) =====");
        Graph small = new Graph();
        for (int i = 0; i < 10; i++) {
            small.addVertex(new Vertex(i));
        }

        small.addEdge(0, 1);
        small.addEdge(0, 2);
        small.addEdge(0, 3);
        small.addEdge(0, 4);
        small.addEdge(1, 5);
        small.addEdge(1, 6);
        small.addEdge(2, 7);
        small.addEdge(3, 8);
        small.addEdge(4, 9);
        small.addEdge(5, 6);

        small.printGraph();
        System.out.println();
        small.bfs(0);
        small.dfs(0);


        Experiment exp = new Experiment();
        exp.runMultipleTests();
        exp.printResults();
    }
}
