
public class KMPSearch {

  /*
   * Bus Service Questions:
   *
   * 1. How many total vehicles is there information on?
   *    987
   *
   * 2. Does the file contain information about the vehicle number 16555?
   *    Yes
   *
   * 3. Locate the first record about a bus heading to HAMPTON PARK
   *    index: 19774
   *
   * 4. Does the file contain information about the vehicle number 9043409?
   *    No
   */

   //Code based off implementation of in Algorithms, Rober Sedgewick and Kevin Wayne

  private static class DFA {
     
    private int[][] dfa;
    private int m;
    private int radix = 256;

    public DFA() {}

    public DFA(String pat) {
      this.m = pat.length();
      this.dfa = new int[this.radix][this.m];
      this.dfa[pat.charAt(0)][0] = 1;

      for(int x = 0, j = 1; j < this.m; j++) {
        for(int c = 0; c < radix; c++)
          this.dfa[c][j] = this.dfa[c][x];
        this.dfa[pat.charAt(j)][j] = j + 1;
        x = this.dfa[pat.charAt(j)][x];
      }
    }

    public int get(int i, int j) {
      if(i < radix && j < m)
        return this.dfa[i][j];
      return -1;
    }
  }

  public static DFA dfa = new DFA();

   /*
    * The method checks whether a pattern pat occurs at least once in String txt.
    *
    */
  public static boolean contains(String txt, String pat) {
    if(searchFirst(txt, pat) != -1) 
      return true;
    return false;
  }

  /*
   * The method returns the index of the first ocurrence of a pattern pat in String txt.
   * It should return -1 if the pat is not present
   */
  public static int searchFirst(String txt, String pat) {
    if(txt.length() == 0 || pat.length() == 0)
      return -1;
    
    dfa = new DFA(pat);
    
    int m = pat.length();
    int n = txt.length();
    int j;
    int i;

    for(i = 0, j = 0; i < n && j < m; i++) {
      j = dfa.get(txt.charAt(i), j);
    }

    if(j == m)
      return i - m; //index of found

    return -1;
  }

  /*
   * The method returns the number of non-overlapping occurences of a pattern pat in String txt.
   */
  public static int searchAll(String txt, String pat) {
    if(txt.length() == 0 || pat.length() == 0)
      return 0;
    
    dfa = new DFA(pat);
    
    String tmp = txt;
    int occurances = 0;
    int index;
    boolean finished = false;
   
   while(!finished) {
      index = searchFirst(tmp, pat);
      
      if(index != -1) {
        occurances++;
        tmp = tmp.substring(index + pat.length());
      }
      else finished = true;
    }

    return occurances;
  }
}
