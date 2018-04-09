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
  public int[] indegree;
  public boolean isValid;

  @SuppressWarnings({"rawtypes", "unchecked"})
  public EdgeWeightedDirectedGraph(Scanner scanner){
    this.V = scanner.nextInt();
    this.E = scanner.nextInt();
    this.isValid = true;

    this.indegree = new int[this.V];
    if(this.V >= 3) {
      int edges = this.E;
      adj = (Bag<DirectedEdge>[])new Bag[this.V];

      for(int i = 0; i < V; i++)
        adj[i] = new Bag<DirectedEdge>();

      for(int i = 0; i < edges; i++){
        int v = scanner.nextInt();
        int w = scanner.nextInt();
        double weight = scanner.nextDouble();
        if(v >= 0 && w >= 0 && weight >=0)
          addEdge(new DirectedEdge(v, w, weight));
        else
          this.isValid = false;
      }
      validateGraph();
    }
    else 
      this.isValid = false;
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
    indegree[w]++;
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


  public void validateGraph(){

    if(!this.isValid) return;
    boolean graphValid = true;

    for(int v=0; v<this.V; v++){
      if(indegree[v] < 1){
        graphValid = false;
        break;
      }
    }
    this.isValid = graphValid;

  }
}
