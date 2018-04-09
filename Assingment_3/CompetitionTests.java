import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.File;

import java.util.Scanner;

import org.junit.Test;

public class CompetitionTests {

  @Test
  public void testDijkstraConstructor() throws FileNotFoundException{

    //test valid graph
    CompetitionDijkstra comp = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);
    //test a valid graph time
    assertEquals("Time: ", 38, comp.timeRequiredforCompetition());

    //test a negCycle graph
    comp = new CompetitionDijkstra("negCycle.txt", 50, 75, 100);
    //test a negCycle time
    assertEquals("Time: ", -1, comp.timeRequiredforCompetition());

    //test a invalid graph
    comp = new CompetitionDijkstra("NotValidFile.txt", 0, 0, 0);
    //test a invalid graph time
    assertEquals("Time: ", -1, comp.timeRequiredforCompetition());

    //test a empty file
    comp = new CompetitionDijkstra("", -1, 2, 3);
    assertEquals("Time: ", -1, comp.timeRequiredforCompetition());
  }

  @Test
  public void testFWConstructor() throws FileNotFoundException{
    //test vliad graph
    CompetitionFloydWarshall comp = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);
    //test valid graph time
    assertEquals("Time: ", 38, comp.timeRequiredforCompetition());

    //test negCycle graph
    comp = new CompetitionFloydWarshall("negCycle.txt", 1, 1, 1);
    assertTrue("NegCycle", comp.shortestPath.hasNegativeCycle());
    //test negCycle graph time
    assertEquals("Time: ", -1, comp.timeRequiredforCompetition());

    //test invalid file
    comp = new CompetitionFloydWarshall("NotValidFile.txt", 0, 0, 0);
    assertFalse("Valid", comp.isValidGraph);
    //test invalid file time
    assertEquals("Time: ", -1, comp.timeRequiredforCompetition());

    //test empty file
    comp = new CompetitionFloydWarshall("", -2, -1, -1);
    //test empty file time
    assertEquals("Time: ", -1, comp.timeRequiredforCompetition());
  }

  @Test
  public void testAdjMatrixConstructor() throws FileNotFoundException{
    //test valid graph
    Scanner scanner = new Scanner(new File("tinyEWD.txt"));
    AdjMatrixEdgeWeightedDirectedGraph adjA =
      new AdjMatrixEdgeWeightedDirectedGraph(scanner);

    assertEquals("E: ", 30, adjA.E());

    //test graph with negitive cycle
    scanner = new Scanner(new File("negCycle.txt"));
    AdjMatrixEdgeWeightedDirectedGraph adjB =
      new AdjMatrixEdgeWeightedDirectedGraph(scanner);
    assertEquals("E: ", 28, adjB.E());

    //test empty file path
    scanner = new Scanner(new File("NotValidFile.txt"));
    AdjMatrixEdgeWeightedDirectedGraph adjC =
      new AdjMatrixEdgeWeightedDirectedGraph(scanner);
    assertFalse("invalid graph", adjC.isValid());

    scanner.close();
  }

  @Test
  public void testEdgeWeigthedDirectedCycle() throws FileNotFoundException{
    Scanner scanner = new Scanner(new File("tinyEWD.txt"));
    EdgeWeightedDirectedGraph g = new EdgeWeightedDirectedGraph(scanner);
    EdgeWeightedDirectedCycle c = new EdgeWeightedDirectedCycle(g);
    scanner.close();
  }

  @Test
  public void testMisc() throws FileNotFoundException{
    Scanner scanner = new Scanner(new File("tinyEWD.txt"));
    EdgeWeightedDirectedGraph g = new EdgeWeightedDirectedGraph(scanner);
    assertEquals("E", 30, g.E());

    DijkstraSP d = new DijkstraSP(g, 0);
    assertTrue("Path", d.hasPathTo(4));
    Iterable<DirectedEdge> path = d.pathTo(4);
    Iterable<DirectedEdge> edges = g.edges();
    scanner.close();
  }
}
