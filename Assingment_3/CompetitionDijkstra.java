import java.io.File;
import java.io.FileNotFoundException;

import java.util.NoSuchElementException;


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
