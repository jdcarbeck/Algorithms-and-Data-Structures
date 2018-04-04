import java.io.File;
import java.io.FileNotFoundException;
/* * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
  public AdjMatrixEdgeWeightedDirectedGraph G;
  public int slowest;
  public double maxDistance;
  public boolean isValidGraph;
  /**
   * @param filename: A filename containing the details of the city road network
   * @param sA, sB, sC: speeds for 3 contestants
   */
  CompetitionFloydWarshall (String filename, int sA, int sB, int sC) throws FileNotFoundException{
    this.G = new AdjMatrixEdgeWeightedDirectedGraph(new File(filename));
    FloydWarshall shortestPath = new FloydWarshall(this.G);
    this.maxDistance = 0.0;
    this.slowest = Math.min(Math.min(sA,sB),sC);
    int vertices = this.G.V();
    if(this.G.isValid()){
      this.isValidGraph = true;
      for(int v = 0; v < vertices; v++){
        for(int w = 0; w < vertices; w++){
          if(shortestPath.hasPath(v,w)){
            if(this.maxDistance < shortestPath.dist(v,w))
              this.maxDistance = shortestPath.dist(v,w);
          }
        }
      }
    }
    else
      this.isValidGraph = false;
  }


  /**
   * @return int: minimum minutes that will pass before the three contestants can meet
   */
  public int timeRequiredforCompetition(){
    return (int) Math.ceil((1000*this.maxDistance)/this.slowest);
  }
  /*
  public static void main(String[] args) throws FileNotFoundException{
   CompetitionFloydWarshall comp = new CompetitionFloydWarshall("tinyEWD.txt", 50 ,75, 100);
  }*/
}
