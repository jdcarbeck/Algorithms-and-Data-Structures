import java.util.Stack;

public class DijkstraSP {
  private double[] distTo;
  private DirectedEdge[] edgeTo;
  private IndexMinPQ<Double> priorityQueue;

  public DijkstraSP(EdgeWeightedDirectedGraph G, int source){
    distTo = new double[G.V()];
    edgeTo = new DirectedEdge[G.V()];

    for(int v = 0; v < G.V(); v++)
      distTo[v] = Double.POSITIVE_INFINITY;

    distTo[source] = 0.0;
    priorityQueue = new IndexMinPQ<Double>(G.V());
    priorityQueue.insert(source, distTo[source]);

    while(!priorityQueue.isEmpty()){
      int v = priorityQueue.delMin();
      for(DirectedEdge e : G.adj(v))
        relax(e);
    }
  }

  private void relax(DirectedEdge e){
    int v = e.from();
    int w = e.to();

    if(distTo[w] > distTo[v] + e.weight()){
      distTo[w] = distTo[v] + e.weight();
      edgeTo[w] = e;

      if(priorityQueue.contains(w))
        priorityQueue.decreaseKey(w, distTo[w]);
      else
        priorityQueue.insert(w, distTo[w]);
    }
  }

  public double distTo(int v){
    return distTo[v];
  }

  public boolean hasPathTo(int v){
    return distTo[v] < Double.POSITIVE_INFINITY;
  }

  public Iterable<DirectedEdge> pathTo(int v){
    if(!hasPathTo(v))
      return null;
    Stack<DirectedEdge> path = new Stack<DirectedEdge>();
    for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
      path.push(e);
    return path;
  }
  }