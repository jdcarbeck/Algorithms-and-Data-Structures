import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.NullPointerException;

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

  private EdgeWeightedDirectedGraph G;
  private int slowest;
  private double maxDist;
  /**
  * @param filename: A filename containing the details of the city road network
  * @param sA, sB, sC: speeds for 3 contestants
  */
  CompetitionDijkstra (String filename, int sA, int sB, int sC){
    //implment a weighted directed graph

    try{
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      this.G = new EdgeWeightedDirectedGraph(scanner);
      this.slowest = Math.min(Math.min(sA,sB),sC);
      this.maxDist = 0.0;
    }catch(FileNotFoundException | NullPointerException e){
      this.G = null;
      this.slowest = 0;
      this.maxDist = 0.0;
    }
    if(this.G != null && this.slowest > 0 && this.G.isValid) {
      for(int i = 0; i < G.V(); i++){
        DijkstraSP routedGraph = new DijkstraSP(G, i);
        for(int j = 0; j < G.V(); j++){
          if(routedGraph.hasPathTo(j)){
            if(this.maxDist < routedGraph.distTo(j))
              this.maxDist = routedGraph.distTo(j);
          }
        }
      }
    }
  }

  /**
  * @return int: minimum minutes that will pass before the three contestants can meet
  */
  public int timeRequiredforCompetition(){
    //run and find all the paths from each node saving the longest distance for each time
    if(this.G != null && this.maxDist != 0.0 && this.slowest != 0){
      double time = (1000*this.maxDist)/this.slowest;
      return (int) Math.ceil(time);
    }
    return -1;
  }
  /*
  public static void main(String[] args) throws FileNotFoundException{
    CompetitionDijkstra competition = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);
  }*/
}
