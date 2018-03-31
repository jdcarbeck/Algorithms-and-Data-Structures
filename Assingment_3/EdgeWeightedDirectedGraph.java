import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EdgeWeightedDirectedGraph {
  //verticies
  private int V = 0;
  //edges
  private int E = 0;
  //adjacency table
  private Bag<DirectedEdge>[] adj;

  public EdgeWeightedDirectedGraph(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    this.V = scanner.nextInt();
    this.E = scanner.nextInt();

    int edges = this.E;
    adj = (Bag<DirectedEdge>[])new Bag[this.V];

    for(int i = 0; i < V; i++)
      adj[i] = new Bag<DirectedEdge>();

    for(int i = 0; i < edges; i++){
      int v = scanner.nextInt();
      int w = scanner.nextInt();
      double weight = scanner.nextDouble();
      DirectedEdge e = new DirectedEdge(v, w, weight);
      addEdge(e);
    }
  }

  public int V(){
    return V;
  }
  public int E(){
    return E;
  }

  public void addEdge(DirectedEdge e){
    int v = e.from();
    int w = e.to();
    this.adj[v].add(e);
    E++;
  }

  //returns edges incident to vertex
  public Iterable<DirectedEdge> adj(int v){
    return adj[v];
  }

  public Iterable<DirectedEdge> edges(){
    Bag<DirectedEdge> list = new Bag<DirectedEdge>();
    for(int v = 0; v < V; v++){
      for(DirectedEdge e : adj(v))
        list.add(e);
    }
    return list;
  }
}