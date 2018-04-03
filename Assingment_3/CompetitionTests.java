import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.File;

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor(){
      try{
        CompetitionDijkstra comp = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);
      } catch(Exception e){}
    }

   @Test
   public void testFWConstructor() {
     try{
      CompetitionFloydWarshall comp = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);
     }catch(Exception e){}
   }

    //TODO - more tests

}
