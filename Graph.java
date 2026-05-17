import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    private ArrayList<ArrayList<Integer>> adj;
    private int numVertices;

    public Graph() {
        this.adj = new ArrayList<>();
        this.numVertices = 0;
    }


    public void addVertex(Vertex v) {
        adj.add(new ArrayList<>());
        numVertices++;
    }

    // ненаправленное ребро - добавляем в оба списка
    public void addEdge(int from, int to) {
        if (from >= numVertices || to >= numVertices || from < 0 || to < 0) {
            System.out.println("Vertex doesn't exist!");
            return;
        }
        // чтобы не было дубликатов
        if (!adj.get(from).contains(to)) {
            adj.get(from).add(to);
        }
        if (!adj.get(to).contains(from)) {
            adj.get(to).add(from);
        }
    }

    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print("adj[" + i + "] = ");
            System.out.println(adj.get(i));
        }
    }


    public void bfs(int start) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS order: ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");


            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }


    public void dfs(int start) {
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        System.out.print("DFS order: ");
        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;
                System.out.print(current + " ");


                for (int neighbor : adj.get(current)) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    public int getNumVertices() {
        return numVertices;
    }
}