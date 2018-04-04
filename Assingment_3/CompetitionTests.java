import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.File;
import java.lang.UnsupportedOperationException;

import org.junit.Test;

public class CompetitionTests {

  @Test
  public void testDijkstraConstructor() throws FileNotFoundException{
    CompetitionDijkstra comp = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);
    assertEquals("Time: ", 38, comp.timeRequiredforCompetition());
  }

  @Test
  public void testFWConstructor() throws FileNotFoundException{
    CompetitionFloydWarshall comp = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);
    assertEquals("Time: ", 38, comp.timeRequiredforCompetition());
    boolean negCycle = false;
    try{
      comp = new CompetitionFloydWarshall("negCycle.txt", 1, 1, 1);
    }catch(UnsupportedOperationException e){negCycle = true;}
    assertTrue("NegCycle", negCycle);
    comp = new CompetitionFloydWarshall("invalidEWD.txt", 0, 0, 0);
    assertFalse("Valid", comp.isValidGraph);
  }

  @Test
  public void testAdjMatrixConstructor() throws FileNotFoundException{
    AdjMatrixEdgeWeightedDirectedGraph adjA =
      new AdjMatrixEdgeWeightedDirectedGraph(new File("tinyEWD.txt"));
    AdjMatrixEdgeWeightedDirectedGraph adjB =
      new AdjMatrixEdgeWeightedDirectedGraph(new File("negCycle.txt"));

    assertEquals("E: ", 30, adjA.E());
  }

  @Test
  public void testEdgeWeigthedDirectedCycle() throws FileNotFoundException{
    EdgeWeightedDirectedGraph g = new EdgeWeightedDirectedGraph(new File("tinyEWD.txt"));
    EdgeWeightedDirectedCycle c = new EdgeWeightedDirectedCycle(g);
    assertTrue("Cycle", c.hasCycle());
  }

  @Test
  public void testMisc() throws FileNotFoundException{
    EdgeWeightedDirectedGraph g = new EdgeWeightedDirectedGraph(new File("tinyEWD.txt"));
    assertEquals("E", 30, g.E());

    DijkstraSP d = new DijkstraSP(g, 0);
    assertTrue("Path", d.hasPathTo(4));
    Iterable<DirectedEdge> path = d.pathTo(4);
    assertNull("Null", d.pathTo(3));
    
    Iterable<DirectedEdge> edges = g.edges();
  }
}
