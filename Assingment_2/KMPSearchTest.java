import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

import org.junit.Test;

public class KMPSearchTest {

  @Test
  public void testEmpty(){
    assertEquals("Empty text or pattern is invalid",-1,KMPSearch.searchFirst("",""));
    assertEquals("Empty text or pattern is invalid",0,KMPSearch.searchAll("",""));
    assertFalse("Empty text or pattern is invalid",KMPSearch.contains("",""));
  }

  @Test
  public void testDoesNotContain() {
  	String pat = "BBA";
  	String txt = "AABAACAADAABAABA";
  	assertEquals("Checking index for pattern: "+pat+" in txt: "+txt+"", -1, KMPSearch.searchFirst(txt,pat));
  	assertEquals("Checking occurances of pattern: "+pat+" in txt: "+txt+"", 0, KMPSearch.searchAll(txt,pat));
  	assertFalse("Checking if pattern: "+pat+" is in txt: "+txt+"", KMPSearch.contains(txt,pat));
  }

  @Test
  public void testContains() {
  	String pat = "AABA";
  	String txt = "AABAACAADAABAABA";
  	assertEquals("Checking index for pattern: "+pat+" in txt: "+txt+"", 0, KMPSearch.searchFirst(txt,pat));
  	assertEquals("Checking occurances of pattern: "+pat+" in txt: "+txt+"", 2, KMPSearch.searchAll(txt,pat));
  	assertFalse("Checking if pattern: "+pat+" is in txt: "+txt+"", KMPSearch.contains(txt,"BBA"));
  }

/*
  public static void main(String[] args) {
  	String fileContent = "";
  	try {
  		fileContent = new Scanner(new File("BUSES_SERVICE_0.json")).useDelimiter("\\Z").next();
  	}catch (IOException e) {e.printStackTrace();}
  	System.out.println("Numbers of vehicles: " + KMPSearch.searchAll(fileContent,"VehicleNo"));
  	System.out.println("Contains 16555: " + KMPSearch.contains(fileContent,"16555"));
  	System.out.println("First ocurrance of HAMPTON PARK at: " + KMPSearch.searchFirst(fileContent,"HAMPTON PARK"));
  	System.out.println("Contains \"VehicleNo\":\"9043409\": " + KMPSearch.contains(fileContent,"\"VehicleNo\":\"9043409\""));
  }*/
}
