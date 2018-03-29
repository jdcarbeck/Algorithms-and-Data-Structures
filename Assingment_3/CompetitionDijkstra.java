import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Stack;

import java.util.Iterator;
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

  private class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
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

  private class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    private class Node<Item> {
      private Item item;
      private Node<Item> next;
    }

    public Bag(){
      first = null;
      n = 0;
    }

    public boolean isEmpty(){
      return first == null;
    }

    public int size(){
      return n;
    }

    public void add(Item item){
      Node<Item> oldfirst = first;
      first = new Node<Item>();
      first.item = item;
      first.next = oldfirst;
      n++;
    }

    public Iterator<Item> iterator() {
      return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
      private Node<Item> current;
      public ListIterator(Node<Item> first){
        current = first;
      }
      public boolean hasNext(){
        return current != null;
      }
      public Item next(){
        if(!hasNext())
          throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
      }
    }
  }

  private class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer>{
    private int maxN;
    private int n;

    private int[] pq;
    private int[] qp;

    private Key[] keys;

    public IndexMinPQ(int maxN){
      this.maxN = maxN;
      n = 0;
      keys = (Key[]) new Comparable[maxN + 1];
      pq = new int[maxN + 1];
      qp = new int[maxN + 1];

      for(int i = 0; i <= maxN; i++)
        qp[i] = -1;
    }

    public boolean isEmpty(){
      return n == 0;
    }

    public boolean contains(int i){
      return qp[i] != -1;
    }

    public int size(){
      return n;
    }

    public void insert(int i, Key key){
      n++;
      qp[i] = n;
      pq[n] = i;
      keys[i] = key;
      swim(n);
    }

    public int delMin(){
      int min = pq[1];
      exch(1, n--);
      sink(1);
      assert min == pq[n + 1];
      qp[min] = -1;
      keys[min] = null;
      pq[n + 1] = -1;
      return min;
    }

    public void decreaseKey(int i, Key key){
      keys[i] = key;
      swim(qp[i]);
    }

    private boolean greater(int i, int j){
      return keys[pq[i]].compareTo(keys[pq[j]])  > 0;
    }

    private void exch(int i, int j){
      int swap = pq[i];
      pq[i] = pq[j];
      pq[j] = swap;
      qp[pq[i]] = i;
      qp[pq[j]] = j;
    }

    private void swim(int k){
      while(k > 1 && greater(k/2, k)){
        exch(k, k/2);
        k = k/2;
      }
    }

    private void sink(int k){
      while(2 * k <= n){
        int j = 2 * k;
        if(j < n && greater(j, j+1))
          j++;
        if(!greater(k, j))
          break;
        exch(k, j);
        k = j;
      }
    }

    public Iterator<Integer> iterator(){
      return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer>{
      private IndexMinPQ<Key> copy;

      public HeapIterator(){
        copy = new IndexMinPQ<Key>(pq.length - 1);
        for(int i = 1; i <= n; i++)
          copy.insert(pq[i], keys[pq[i]]);
      }

      public boolean hasNext(){
        return !copy.isEmpty();
      }

      public Integer next(){
        if(!hasNext())
          throw new NoSuchElementException();
        return copy.delMin();
      }
    }
  }

  private class EdgeWeightedDirectedGraph {

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

  private class DijkstraSP {
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

  private static EdgeWeightedDirectedGraph G;
  /**
  * @param filename: A filename containing the details of the city road network
  * @param sA, sB, sC: speeds for 3 contestants
  */
  CompetitionDijkstra (String filename, int sA, int sB, int sC) throws FileNotFoundException {
    //implment a weighted directed graph

    this.G = new EdgeWeightedDirectedGraph(new File(filename));

    int slowest = Math.min(Math.min(sA,sB),sC);
    double maxDist = 0.0;

    for(int i = 0; i < G.V(); i++){
      DijkstraSP routedGraph = new DijkstraSP(G, i);
      for(int j = 0; j < G.V(); j++){
        if(routedGraph.hasPathTo(j)){
          if(maxDist < routedGraph.distTo(j))
            maxDist = routedGraph.distTo(j);
        }
      }
    }
    int time = timeRequiredForCompetition(maxDist, slowest);
    System.out.println("Time required for show: " + time + "min");
  }

  /**
  * @return int: minimum minutes that will pass before the three contestants can meet
  */
  public int timeRequiredForCompetition(double dist, int speed){
    //run and find all the paths from each node saving the longest distance for each time
    double time = (1000*dist)/speed;

    return (int) Math.ceil(time);
  }

  public static void main(String[] args) throws FileNotFoundException{
    CompetitionDijkstra competition = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);
  }
}
