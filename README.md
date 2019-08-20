# pbGraphs-java
pbGraphs is a graph library for Java.

## Requirements
Java 1.5+. By changing the use static imports to ordinary imports, it will work with Java 1.3+.

## Dependencies
There are no dependencies.

## Getting Started
It is easy to get started. Here are two alternatives:

 - Include the sources in your project.
 - Create a jar file and use it in your project.

## Avaialble in 12 different programming languages
pbGraphs-java has been implemented with [progsbase](https://www.progsbase.com), so the library is available for 12 different programming languages.

Try [all functions online](https://repo.progsbase.com/repoviewer/no.inductive.libraries/DirectedGraphs/0.1.14/) and see the implementations in different languages.

## Functions

### Graph Equality
```
boolean DirectedGraphsEqual(DirectedGraph a, DirectedGraph b)
```

Return true of the two directed graphs are equal.

### Graph Components
```
boolean GetGraphComponents(DirectedGraph g, NumberArrayReference componentMembership)
```

Places the list of strongly connected components in componentMembership. Returns false if graphs does not quality as undirected.

### Topological Sort
```
boolean TopologicalSort(DirectedGraph g, NumberArrayReference list)
```

Places the topological sort of the graph in `list`.

### Searches

* Depth-first Search
```
void DepthFirstSearch(DirectedGraph g, double start, NumberArrayReference list)
```

Places the depth-first sort ordering of the graph in `list`.

* Breadth-first Search
```
void BreadthFirstSearch(DirectedGraph g, double start, NumberArrayReference list)
```

Places the breadth-first sort ordering of the graph in `list`.

### Shortest Paths

* Dijkstra's Algorithm
```
void DijkstrasAlgorithm(DirectedGraph g, double src, NumberArrayReference dist, BooleanArrayReference distSet, NumberArrayReference prev)
```

Performs Dijkstra's algorithm on the graph `g` from `src`. Whether nodes are reachable is placed in `distSet`, the shortest distances in `dist`, and the previous node in the shortest paths in `prev`.

* Bellman-Ford Algorithm
```
boolean BellmanFordAlgorithm(DirectedGraph g, double src, NumberArrayReference dist, BooleanArrayReference distSet, NumberArrayReference prev)
```

Performs the Bellman-Ford algorithm on the graph `g` from `src`. Whether nodes are reachable is placed in `distSet`, the shortest distances in `dist`, and the previous node in the shortest paths in `prev`.


* Floyd-Warshall Algorithm
```
boolean FloydWarshallAlgorithm(DirectedGraph g, Distances distances)
```

Performs the Floyd-Warshall algorithm on the graph `g`. The shortest distances between each pair of nodes are placed in `distances`.

### Minimum Spanning Trees

* Prim's Algorithm
```
boolean PrimsAlgorithm(DirectedGraph g, Forest forest)
```

Performs the Prim's algorithm on the graph `g`. All minimum spanning trees of the graph are placed in `forest`. Returns false if graphs does not quality as undirected.


* Kruskal's Algorithm
```
boolean KruskalsAlgorithm(DirectedGraph g, Forest forest)
```

Performs the Kruskal's algorithm on the graph `g`. All minimum spanning trees of the graph are placed in `forest`. Returns false if graphs does not quality as undirected.

### Cycles

* Cycle Detection
```
boolean DirectedGraphContainsCycleDFS(DirectedGraph g)
```

Return true if there are cycles in the graph `g`.

* Cycle Counting
```
double DirectedGraphCountCyclesDFS(DirectedGraph g)
```

Return the number of cycles in the graph `g`.

* Get All Cyles
```
Cycle [] DirectedGraphGetCyclesDFS(DirectedGraph g)
```

Returns the list of cycles in the graph `g`.