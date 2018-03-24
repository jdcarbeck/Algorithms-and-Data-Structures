import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Scanner;

public class TSTTest {

  @Test
  public void testEmpty(){
    TST<Long> trie = new TST<>();
    assertEquals("size of an empty trie should be 0",0, trie.size());
    assertFalse("searching an empty trie should return false",trie.contains(""));
    assertNull("getting from an empty trie should return null",trie.get(""));
  }

  @Test
  public void testSingleElement() {
  	TST<Integer> trie = new TST<>();
  	trie.put("A", 1);
  	assertEquals("Size of single element list should be: ", 1, trie.size());
		assertTrue("contains \"A\"", trie.contains("A"));
		assertFalse("contains \"B\"", trie.contains("B"));
		assertFalse("contains \"AB\"", trie.contains("AB"));
		assertEquals("get val of \"A\"", (Integer) 1,trie.get("A"));
		assertNull("get val of \"B\"", trie.get("B"));
		assertNull("get val of \"AB\"", trie.get("AB"));
  }

  @Test 
  public void testTwoElements() {
  	TST<Integer> trie = new TST<>();
  	trie.put("D", 4);
  	trie.put("E", 3);
  	assertEquals("Size of two element list should be: ", 2, trie.size());
		assertTrue("contains \"D\"", trie.contains("D"));
		assertTrue("contains \"E\"", trie.contains("E"));
		assertFalse("contains \"C\"", trie.contains("C"));
		assertFalse("contains \"DE\"", trie.contains("DE"));
		assertFalse("contains \"EF\"", trie.contains("EF"));
		assertEquals("get val of \"D\"", (Integer)4,trie.get("D"));
		assertNull("get val of \"C\"", trie.get("C"));
		assertNull("get val of \"DC\"", trie.get("DC"));
		assertNull("get val of \"EF\"", trie.get("EF"));
  }

  @Test
  public void testMultipleElements() {
  	TST<Integer> trie = new TST<>();
  	trie.put("A", 1);
  	trie.put("B", 2);
  	trie.put("C", 3);
  	trie.put("D", 4);
  	trie.put("E", 5);
  	trie.put("F", 6);
  	assertEquals("size of 6 element list should be: ", 6, trie.size());
  	assertTrue("contains \"A\"", trie.contains("A"));
  	assertTrue("contains \"B\"", trie.contains("B"));
  	assertTrue("contains \"C\"", trie.contains("C"));
  	assertTrue("contains \"D\"", trie.contains("D"));
  	assertFalse("contains \"G\"", trie.contains("G"));
  	assertFalse("contains \"AB\"", trie.contains("AB"));
  	assertFalse("contains \"BC\"", trie.contains("BC"));
  	assertFalse("contains \"CD\"", trie.contains("CD"));
  	assertEquals("get val of \"B\"", (Integer)2,trie.get("B"));
  	assertEquals("get val of \"A\"", (Integer)1,trie.get("A"));
  	assertEquals("get val of \"D\"", (Integer)4,trie.get("D"));
  	assertNull("get val of \"G\"", trie.get("G"));
		assertNull("get val of \"DE\"", trie.get("DE"));
		assertNull("get val of \"EF\"", trie.get("EF"));
  }
  /*
  public static void main(String[] args) {
  	
	  TST<Integer> busTrie = new TST<Integer>();
	  try{
	  	Reader reader = new FileReader("BUSES_SERVICE_0.json");
	  	JSONParser parser = new JSONParser();
	  	Object object = parser.parse(reader);
	  	JSONArray array = (JSONArray) object;
	  	int n = array.size();
	  	for(int i = 0; i < n; i++) {
	  		JSONObject next = (JSONObject) array.get(i);
	  		String dest = (String) next.get("Destination");
	  		if(busTrie.contains(dest))
	  			busTrie.put(dest, busTrie.get(dest) + 1);
	  		else {
	  			busTrie.put(dest, 1);
	  		}
	  	}
	  } catch (ParseException | IOException e) {}
	  LinkedList downDest = busTrie.keysWithPrefix("DOWN");
	  int total = 0;
	  for(int i = downDest.size(); i > 0; i--)
	  	total += busTrie.get((String)downDest.get(i-1));

	  System.out.println("1. Number of unique destinations: " + busTrie.size());
	  System.out.println("2. Bus going \"SOUTHSIDE\": " + busTrie.contains("SOUTHSIDE"));
	  System.out.println("3. Number of buses with a destinaion prefix \"DOWN\": " + total);

  	TST<String> bookTrie = new TST<String>();
  	try {
	  	Scanner scanner = new Scanner(new File("google-books-common-words.txt"));
	  	
	  	while(scanner.hasNext()) {
	  		String word = scanner.next();
	  		String occurance = scanner.next();
	  		bookTrie.put(word, occurance);
	  	}

	  	scanner.close();
	  } catch (FileNotFoundException e) {}

	  System.out.println("4. Number of words in file: " + bookTrie.size());
	  System.out.println("5. Frequency of the word \"ALGORITHM\": " + bookTrie.get("ALGORITHM"));
	  System.out.println("6. Contain \"EMOJI\": " + bookTrie.contains("EMOJI"));
	  System.out.println("7. Contain \"BLAH\": " + bookTrie.contains("BLAH"));
	  System.out.println("8. Number of words with prefix \"TEST\": " + bookTrie.keysWithPrefix("TEST").size());
	} */
}
