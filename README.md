 Assignment 4 - Graph Traversal and Representation System

Java implementation of a graph with BFS and DFS traversals. Graph is stored using adjacency list.

 A. Project Overview

This project implements a basic graph data structure and two main traversal algorithms: BFS and DFS. A graph is a collection of vertices (nodes) and edges (connections between them). Graphs are used to model many real things like road networks, social connections, dependencies between tasks etc.

A vertex is a node in the graph. It holds one element from the collection and has its own id. An edge is a connection between two vertices. In this project the graph is undirected, so an edge from A to B means also from B to A.

BFS (Breadth-First Search) visits all neighbors of the current vertex first, then neighbors of neighbors and so on. It works level by level. Queue is used here.

DFS (Depth-First Search) goes as deep as possible along one branch, then backtracks to the previous vertex and continues with the next branch. Stack is used here (or recursion, but I used iterative version with Stack).

 B. Class Descriptions

 Vertex
Represents one node in the graph. Has a private field `id`. Methods: constructor, getter, `toString()`.

 Edge
Represents a connection between two vertices. Stores `source` and `destination` vertices. Methods: constructor, getters, `toString()`.

 Graph
Main class. Stores the graph as `ArrayList<ArrayList<Integer>> adj`, where `adj[i]` is the list of vertices adjacent to vertex i. This is the adjacency list representation from Lecture 8.

Methods:
- `addVertex(Vertex v)` adds a new empty row to the adjacency list
- `addEdge(int from, int to)` adds an edge (to both lists, because the graph is undirected)
- `printGraph()` prints all adjacency lists
- `bfs(int start)` breadth-first traversal starting from given vertex
- `dfs(int start)` depth-first traversal starting from given vertex

 Experiment
Class for running performance tests. Generates random graphs of different sizes (10, 30, 100), runs BFS and DFS on them, measures execution time with `System.nanoTime()`.

 Adjacency list representation## C. Algorithm Descriptions

### BFS - Breadth-First Search

Steps:
1. Pick the start vertex, mark it visited and put into the queue.
2. Take a vertex from the front of the queue.
3. For each of its unvisited neighbors: mark as visited and push to the queue.
4. Repeat while the queue is not empty.

Use cases:
- shortest path in an unweighted graph (by number of edges)
- finding all vertices at distance k from start
- checking graph connectivity
- finding connected components

Time complexity: O(V + E), where V is number of vertices, E is number of edges. Each vertex and each edge is processed exactly once.

### DFS - Depth-First Search

Steps:
1. Pick an unvisited vertex, push it on the stack.
2. Pop a vertex from the stack. If it is not visited yet, mark it visited.
3. Push all its unvisited neighbors on the stack.
4. Repeat while the stack is not empty.
5. If there are still unvisited vertices, repeat from step 1.

Use cases:
- topological sort
- cycle detection
- finding strongly connected components
- backtracking problems (mazes, puzzles)

Time complexity: O(V + E), same reasons as BFS.

## D. Experimental Results

Tests were done on graphs of size 10, 30 and 100 vertices. Number of edges is about V*2 (sparse graph). Time was measured with `System.nanoTime()` from the start of BFS/DFS until the end. Start vertex is always 0.

### Execution time table

| Vertices | Edges | BFS (ns)  | DFS (ns)  |
|----------|-------|-----------|-----------|
| 10       | 20    | 247 300   | 216 500   |
| 30       | 60    | 607 800   | 697 900   |
| 100      | 200   | 1 846 500 | 2 139 800 |

These are real numbers from my run. Values change a bit between runs because of JIT compilation in JVM, garbage collector and CPU load from other processes.

### Observations

On small graphs (10 and 30 vertices) BFS and DFS show almost the same time, the difference is within measurement error. On 100 vertices DFS became a bit slower than BFS. The reason is that in the iterative DFS with stack the same vertex can be pushed to the stack several times through different neighbors. The visited check happens only when we pop it, so extra push/pop operations are still done.

Overall the difference between BFS and DFS is small, both run in O(V + E). Time grows roughly linearly with graph size, which matches the theoretical complexity.

## E. Screenshots

Screenshots of the program output are in `docs/screenshots/`:
- `graph_output.png` adjacency list output
- `bfs_output.png` BFS traversal order
- `dfs_output.png` DFS traversal order
- `performance.png` experiment results table

### Sample output ## F. Reflection

In this assignment I learned how to work with graphs as a data structure. Before that I only worked with arrays and lists, but here I had to figure out how to store connections between objects. The most interesting part was the difference between BFS and DFS: they both visit every vertex of the graph but in totally different order. BFS spreads from the start like circles on water, and DFS dives deep into one branch and goes back when it hits a dead end.

The main challenge was the DFS part. First I wanted to write it recursively, but then I switched to the iterative version with a stack because that was the way it was shown in the lecture. Another issue was that the time measurements were jumping between runs, and I figured out it was because of JVM warmup and JIT compilation. I also understood why adjacency list is better than adjacency matrix for sparse graphs: less memory and faster neighbor iteration.

## Repository Structure
Adjacency list is an array of lists. For each vertex i it stores a list of its neighbors. Compared to adjacency matrix it uses less memory for sparse graphs: O(V + E) instead of O(V^2).

Example from the lecture:
