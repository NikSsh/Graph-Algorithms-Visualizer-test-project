# Graph-Algorithms-Visualizer-test-project
Graph-Algorithms Visualizer test project on java, swing framework

## Demo

![graph-demo](https://user-images.githubusercontent.com/71446610/183302612-5f8fd4d4-2022-4e20-9c21-2bef68a45c22.gif)

## Stages

**Stage 1/7: Drawing circles**
Description
A graph is a pictorial representation of a set of objects. Object pairs in a graph are connected by links. The interconnected objects represented by points are called vertices, and the links that connect the vertices are edges.
In the first stage of the project, we will start by creating several vertices. A vertex can be represented by a labeled circle.

**Stage 2/7: Click to vertex**
Description
There is a problem that we faced in the previous stage. We could create vertices only by hardwiring them inside our code. We want our users to interact with our application. So, in this stage, the program will create vertices when users click on the screen.
When users click on the graph, prompt a dialog box JOptionPane that contains a TextBox, an Ok button, and a Cancel button.
Make sure that the Vertex ID contains only one character. Prompt a new dialog box if the input is not valid. And the center of each new vertex is created exactly in the position where users make a click.

**Stage 3/7: Connecting the dots**
Description
Every graph contains edges that connect two vertices. Edges can be of different types: directed and undirected. However, we will deal only with undirected edges in this project.
We want to give full control to our users, so our application should also have options — let users choose what they want to do, add vertices, edges, remove vertices or edges.
In the Add an Edge mode users can select a vertex when they click on a vertex. Now, if users again click on a different vertex, a dialog box will appear to input the edge weight. After valid input, it will create a new edge between the selected vertices. The first vertex will be the source, and the second vertex is the destination. The edge weight should also be displayed near the edge.

**Stage 4/7: Removing vertices & edges**
Description
Until now users haven't been able to make any changes to graphs. Making changes requires restarting the application. It is not that convenient, isn't it? In this stage, we resolve this issue. Let's add options to remove an edge and a vertex. We will also implement a "soft reset" feature that resets an entire graph.
To implement them, we would require two new menu options: Remove an Edge and Remove a Vertex. If you want to remove an edge, the program needs to remove the edge JComponent and the corresponding edge label (JLabel) from the JPanel graph. As for the vertex removal, a click on a vertex in the Remove a Vertex mode should remove the vertex JPanel together with all the edges.

**Stage 5/7: The graph traversal algorithm**
Description
In computer science, graph traversal (also known as graph search) is the process of checking and/or updating each vertex in a graph. A traversal can be classified by the order in which it visits the vertices. Graphs can be traversed in two ways:
Depth-first search (DFS) is an algorithm for traversing or searching tree/graph data structures. The algorithm starts at the root node and explores it as far as possible along each branch before backtracking. In the case of a graph, it can select any node.
Breadth-first search (BFS) algorithm traverses a graph by the breadth and uses a queue to get to the next vertex and to start a search when a dead end occurs in any iteration.

**Stage 6/7: The shortest path algorithm**
Description
In this stage, we will implement the shortest path functionality. It is a path between vertices in a graph that produces the least possible sum of the edge weights. We will work on one of the most popular pathfinding algorithms — Dijkstra's single-source shortest path algorithm.
Add the Dijkstra's Algorithm JMenuItem in the Algorithms menu and name it Dijkstra's Algorithm;
Keep the algorithms from the previous stage;
The algorithm should produce a list of <Vertex>=<Cost> pairs separated by a comma in the display JLabel . For example: A=1, B=2, C=3, D=4. This means that the shortest path of A, B, C, D from the selected source vertex is 1, 2, 3, 4 respectively.

**Stage 7/7: Minimum spanning tree**
Description
In the final stage of the project, we will take a look at two crucial concepts. The first one is the spanning tree notion. The graph theory determines the spanning tree T of the undirected graph G as a tree subgraph that includes all vertices of G. In general, a graph can have several spanning trees, but a non-connected graph cannot have spanning trees. If all edges of G are also the edges of the spanning tree T, then G is a tree identical to T — a tree has a unique spanning tree that is itself by nature.
Another interesting concept for discussion is a minimum spanning tree (MST). It is a subset of the edges of a connected edge-weighted undirected graph. It connects all graph vertices with the minimum possible total edge weight without cycles. In other words, it is a spanning tree whose edge weight sum is the least possible.
In this stage, we will work on Prim's algorithm for a minimum spanning tree.
