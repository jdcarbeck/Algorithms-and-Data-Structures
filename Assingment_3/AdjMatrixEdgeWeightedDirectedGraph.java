import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjMatrixEdgeWeightedDirectedGraph {
  //verticies
  private int V = 0;
  //edges
  private int E = 0;
  //adjacency table
  private DirectedEdge[][] edgeFromTo;
  private boolean isValid;

  public AdjMatrixEdgeWeightedDirectedGraph(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    this.V = scanner.nextInt();
    this.E = scanner.nextInt();
    this.isValid = true;

    int edges = this.E;
    this.edgeFromTo = new DirectedEdge[this.V][this.V];

    for(int i=0; i < edges; i++){
    	int v = scanner.nextInt();
    	int w = scanner.nextInt();
    	double weight = scanner.nextDouble();

    	if(v >= 0 && w >=0){
    		if(v == w)
    			addEdge(new DirectedEdge(v, w, Math.abs(weight)));
    		else
    			addEdge(new DirectedEdge(v, w, weight));
    	}
    	else
    		this.isValid = false;
  	}
  }
  public boolean isValid(){
  	return this.isValid;
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
    if(edgeFromTo[v][w] == null){
    	E++;
    	edgeFromTo[v][w] = e;
    }
  }

  //returns edges incident to vertex
  public Iterable<DirectedEdge> edgeFromTo(int v){
    return new AdjIterator(v);
  }

  private class AdjIterator implements Iterator<DirectedEdge>, Iterable<DirectedEdge> {
  	private int v;
  	private int w = 0;

  	public AdjIterator(int v){
  		this.v = v;
  	}

  	public Iterator<DirectedEdge> iterator(){
  		return this;
  	}

  	public boolean hasNext(){
  		while(w < V) {
  			if(edgeFromTo[v][w] != null) return true;
  			w++;
  		}
  		return false;
  	}

  	public DirectedEdge next(){
  		if(!hasNext())
  			throw new NoSuchElementException();
  		return edgeFromTo[v][w++];
  	}
  }
}
