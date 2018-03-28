import java.io.File;
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;
/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    Each contestant walks at a given estimated speed.
 *    The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

  private class DirectedEdge implements Comparable<DirectedEdge>{
    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
      if(v < 0) throw new IllegalArgumentException("vertex index must be a natural number");
      if(w < 0) throw new IllegalArgumentException("vertex index must be a natural number");
      if(Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
      this.v = v;
      this.w = w;
      this.weight = weight;
    }

    public double weight(){
      return weight;
    }

    public int from(){
      return v;
    }

    public int to(){
      return w;
    }
  }

  private class EdgeWeightedDirectedGraph {

    private class Bag<item> implements Iterable<Item> {
      private Node<Item> first;
      private int n;

      private static class Node<Item> {
        private Item item;
        private Node<Item> next;
      }

      public Bag(){
        first = null;
        n = 0;
      }

      public isEmpty(){
        return first == null;
      }

      public void add(Item item){
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
      }
    }

    private static final String NEWLINE = System.getProperty("line.separator");
    //verticies
    private final int V;
    //edges
    private int E;
    //adjacency table
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDirectedGraph(File file) {
      Scanner scanner = new Scanner(file);
      int V = scanner.nextInt;
      int E = scanner.nextInt;
      if(E < 0) throw new IllegalArgumentException("Number of edges must be a natural number");
      for(int i = 0; i < E; i++){
        int v = scanner.nextInt();
        int w = scanner.nextInt();
        validateVertex(v);
        validateVertex(w);
        double weight = scanner.nextDouble();
        DirectedEdge e = new DirectedEdge(v, w, weight);
        addEdge(e);
      }
    }

    public int V() { return V };
    public int E() { return E };

    private void validateVertex(int v){
      if(v < 0 || v >= V)
        throw new IllegalArgumentException("vertex "+v+" is not betwen 0 and "+(V-1));
    }

    public void addEdge(DirectedEdge e){
      int v = e.either();
      int w = e.other();
      validateVertex(v);
      validateVertex(w);
      adj[v].add(e);
      adj[w].add(e);
      E++;
    }

    //returns edges incident to vertex
    public Iterable<DirectedEdge> adj(int v){
      validateVertex(v);
      return adj[v];
    }
  }

  private int sA;
  private int sB;
  private int sC;
  private EdgeWeightedDirectedGraph G;
  /**
  * @param filename: A filename containing the details of the city road network
  * @param sA, sB, sC: speeds for 3 contestants
  */
  CompetitionDijkstra (String filename, int sA, int sB, int sC){
    //implment a weighted directed graph
    this.G = new EdgeWeightedDirectedGraph(new File("./"+filesname));
    this.sA = sA;
    this.sB = sB;
    this.sC = Sc;
  }

  /**
  * @return int: minimum minutes that will pass before the three contestants can meet
  */
  public int timeRequiredforCompetition(){
    double[] distTo = new double[G.V()];
    DirectedEdge[] edgeTo = new DirectedEdge[G.V()];



    return -1;
  }
}
