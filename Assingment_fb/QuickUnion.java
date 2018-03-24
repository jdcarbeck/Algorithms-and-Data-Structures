/******************************************************************************
 *  From Algorithms by Robert Sedgewick and Kevin Wayne
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  Weighted quick-union with path compression.
 *
 ******************************************************************************/

public class QuickUnion {
  public int[] id; // parent link (site indexed)
  public int[] sz;  // size of component for roots (site indexed)
  public int count;  // number of components

  /**
   * Constructor of a empty union find data structure with N elements
   * labeled 0 - (N-1) 
   * expects N to be larger than 0
   * @param N the number of elements to represented
   */
  public QuickUnion(int N) {
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++) id[i] = i;
    sz = new int[N];
    for (int i = 0; i < N; i++) sz[i] = 1;
  }

  /**
   * count returns the number of elements in the data structure
   */
  public int count() {  
    return count; 
  }

  /**
   * find reutrns the id for the element stored in p connnection that is compressed
   * @param p the int representing a connection
   * @return the id for the element stored in specified connection
   */
  private int find(int p) {  
    while (p != id[p]) {
      id[p] = id[id[p]];
      p = id[p];
    }
    return p; 
  }

  /**
   * union merges the connection specified by p with the connection specified
   * by q
   * @param p the id of a connection
   * @param q the id of a connection
   */
  public void union(int p, int q)
  {
    int i = find(p);
    int j = find(q);
    if (i == j) return;
    
    if   (sz[i] < sz[j]) { 
      id[i] = j; 
      sz[j] += sz[i]; 
    }
    else {
      id[j] = i;
      sz[i] += sz[j];
    }

    count--; 
  }
}