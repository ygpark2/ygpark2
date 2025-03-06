package ds.graph;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
  private Map<Vertex, List<Vertex>> adjVertices;

  // standard constructor, getters, setters

  void addVertex(String label) {
    adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
  }

  void removeVertex(String label) {
    Vertex v = new Vertex(label);
    adjVertices.values().stream().forEach(e -> e.remove(v));
    adjVertices.remove(new Vertex(label));
  }

  void addEdge(String label1, String label2) {
    Vertex v1 = new Vertex(label1);
    Vertex v2 = new Vertex(label2);
    adjVertices.get(v1).add(v2);
    adjVertices.get(v2).add(v1);
  }

  void removeEdge(String label1, String label2) {
    Vertex v1 = new Vertex(label1);
    Vertex v2 = new Vertex(label2);
    List<Vertex> eV1 = adjVertices.get(v1);
    List<Vertex> eV2 = adjVertices.get(v2);
    if (eV1 != null)
      eV1.remove(v2);
    if (eV2 != null)
      eV2.remove(v1);
  }

  Graph createGraph() {
    Graph graph = new Graph();
    graph.addVertex("Bob");
    graph.addVertex("Alice");
    graph.addVertex("Mark");
    graph.addVertex("Rob");
    graph.addVertex("Maria");
    graph.addEdge("Bob", "Alice");
    graph.addEdge("Bob", "Rob");
    graph.addEdge("Alice", "Mark");
    graph.addEdge("Rob", "Mark");
    graph.addEdge("Alice", "Maria");
    graph.addEdge("Rob", "Maria");
    return graph;
  }

  List<Vertex> getAdjVertices(String label) {
    return adjVertices.get(new Vertex(label));
  }

  Set<String> depthFirstTraversal(Graph graph, String root) {
    Set<String> visited = new LinkedHashSet<>();
    Stack<String> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      String vertex = stack.pop();
      if (!visited.contains(vertex)) {
        visited.add(vertex);
        for (Vertex v : graph.getAdjVertices(vertex)) {
          stack.push(v.label);
        }
      }
    }
    return visited;
  }

  Set<String> breadthFirstTraversal(Graph graph, String root) {
    Set<String> visited = new LinkedHashSet<>();
    Queue<String> queue = new LinkedList<>();
    queue.add(root);
    visited.add(root);
    while (!queue.isEmpty()) {
      String vertex = queue.poll();
      for (Vertex v : graph.getAdjVertices(vertex)) {
        if (!visited.contains(v.label)) {
          visited.add(v.label);
          queue.add(v.label);
        }
      }
    }
    return visited;
  }
}
