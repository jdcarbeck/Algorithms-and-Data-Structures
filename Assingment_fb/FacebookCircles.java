/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author John Carbeck
 *
 * @version 19/12/17
 */
import java.util.ArrayList;

public class FacebookCircles {

  public int[] id;
  private int numberOfFacebookUsers;
  private QuickUnion facebookData;

  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  public FacebookCircles(int numberOfFacebookUsers) {
    facebookData = new QuickUnion(numberOfFacebookUsers);
  }

  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends( int user1, int user2 ) {
   facebookData.union(user1, user2);
  }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() {
    return facebookData.count();
  }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
    int largestCircle = 0;
    for(int i = 0; i < facebookData.sz.length; i++) {
      if(largestCircle < facebookData.sz[i]) largestCircle = facebookData.sz[i];
    }
    return largestCircle;
  }

  /**
   * @return the size of the median circle in the data already loaded.
   */
  public int sizeOfAverageCircle() {
    int sumOfCircleSizes = 0;
    int numberOfCircles = numberOfCircles();
    for(int i = 0; i < facebookData.sz.length; i++) {
      if(facebookData.id[i] == i) sumOfCircleSizes += facebookData.sz[i];
    }
    return (sumOfCircleSizes/numberOfCircles);
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
    int smallestCircle = sizeOfLargestCircle();
    for(int i = 0; i < facebookData.sz.length; i++) {
      if(facebookData.id[i] == i) {
        if(smallestCircle > facebookData.sz[i]) smallestCircle = facebookData.sz[i];
      }
    } 
    return smallestCircle;
  }
}