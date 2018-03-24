import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;    
import java.lang.Exception;
import java.io.File;

/**    
 *    ------------------------------------------------------------------------------------------------
 *    |                      |  insert   |   quick   |   merge   |   shell   | selection |   bubble  |
 *    ------------------------------------------------------------------------------------------------
 *    | 10 random            | 0.00217ms | 0.00250ms | 0.01256ms | 0.00130ms | 0.00229ms | 0.00068ms |
 *    ------------------------------------------------------------------------------------------------
 *    | 100 random           | 0.14485ms | 0.02600ms | 0.07048ms | 0.01489ms | 0.14027ms | 0.05613ms |
 *    ------------------------------------------------------------------------------------------------
 *    | 1000 random          | 3.43790ms | 0.07907ms | 0.29362ms | 0.21265ms | 3.98588ms | 0.10771ms |
 *    ------------------------------------------------------------------------------------------------
 *    | 1000 few unique      | 0.51417ms | 0.04207ms | 0.10392ms | 0.00520ms | 0.30474ms | 0.14211ms |
 *    ------------------------------------------------------------------------------------------------
 *    | 1000 nearly ordered  | 0.63027ms | 0.03154ms | 0.07335ms | 0.00552ms | 0.30733ms | 0.10317ms |
 *    ------------------------------------------------------------------------------------------------
 *    | 1000 reverse ordered | 1.24414ms | 0.01207ms | 0.17180ms | 0.02909ms | 0.89933ms | 0.08375ms |
 *    ------------------------------------------------------------------------------------------------
 *    | 1000 sorted          | 0.06904ms | 0.02440ms | 0.25463ms | 0.07019ms | 0.29916ms | 0.13930ms |
 *    ------------------------------------------------------------------------------------------------
 * 
 *
 *  1. Which of the sorting algorithms does the order of input have an impact on? Why?
 *  2. Which algorithm has the biggest difference between the best and worst performance, based on the type of input, for the input of size 1000? Why?
 *  3. Which algorithm has the best/worst scalability, ie, the difference in performance time based on the input size? Please consider only input files
 *      with random order for this answer.
 *  4. Which algorithm is the fastest for each of the 7 input files
 *
 *  1. All algoritms run times are impacted by size the algoritm with the greatest impact is insertion sort as it has a order of growth of N^2.
 *  2. Insertion sort has the largest notable between best and worst preformance. Its preformances ranges from best of N to worst of N^2. This
 *     is seen in the time for 1000 random and 1000 revered around ~3ms, which is much longer than the time for the sorted 1000 array 0.06ms. 
 *     The times for those runs are simlar to selection with a runtime of N^2 but when the the array is sorted it has a very quick time. 
 *  3. The best algoritms for scalibilty is quicksort and merge sort. Depending on if you need the array to be stable (mergesort) or in place (quicksort)
 *     these algoritms provide runtimes of NlogN. The worst is insertion sort with a runtime of N^2
 *  4. 10 Random: All sorts are very close almost negligible (bubble technically)
 *     100 Random: quicksort
 *     1000 Random: quicksort/shell
 *     1000 Nearly Sorted: shell
 *     1000 Reverse: quick/shell
 *     1000 Sorted: insertion/shell
 *
 *
 */

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author John Carbeck 16309095
 *  @version HT 2018
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }
    @Test
    public void testInsertionSort()
    {
        double[] empty = {};
        String expected = "[]"; 
        double[] aSorted = {1, 2, 3.2, 7, 9.6};
        double[] a = {7, 1, 9.6, 2, 3.2};     
        assertEquals("Insertionsort empty: ", expected, Arrays.toString(SortComparison.insertionSort(empty)));
        assertEquals("Insertionsort: ", Arrays.toString(aSorted), Arrays.toString(SortComparison.insertionSort(a)));   
    }
    
    @Test
    public void testBubbleSort()
    {
        double[] empty = {};
        String emptyString = "[]";  
        double[] aSorted = {1, 2, 3.2, 7, 9.6};
        double[] a = {7, 1, 9.6, 2, 3.2};   
        assertEquals("Bubblesort empty: ", emptyString, Arrays.toString(SortComparison.bubbleSort(empty)));
        assertEquals("Bubblesort: ", Arrays.toString(aSorted), Arrays.toString(SortComparison.bubbleSort(a)));
    }

    
    @Test
    public void testSelectionSort()
    {
        double[] empty = {};
        String emptyString = "[]";     
        double[] aSorted = {1, 2, 3.2, 7, 9.6};
        double[] a = {7, 1, 9.6, 2, 3.2};
        assertEquals("Selection empty: ", emptyString, Arrays.toString(SortComparison.selectionSort(empty)));
        assertEquals("Selection: ", Arrays.toString(aSorted), Arrays.toString(SortComparison.selectionSort(a)));
        
    }
    
    @Test
    public void testShellSort()
    {
        double[] empty = {};
        String emptyString = "[]";      
        double[] aSorted = {1, 2, 3.2, 7, 9.6};
        double[] a = {7, 1, 9.6, 2, 3.2};
        SortComparison.shellSort(a);
        assertEquals("Shell empty:", emptyString, Arrays.toString(SortComparison.shellSort(empty)));
        assertEquals("Shell: ", Arrays.toString(aSorted), Arrays.toString(SortComparison.shellSort(a)));

        aSorted = convertToDoubleArray(new File("numbersSorted1000.txt"));
        a = SortComparison.shellSort(convertToDoubleArray(new File("numbers1000.txt")));
        assertEquals("Shell 1000: ", Arrays.toString(aSorted), Arrays.toString(a));
    }
    
    @Test
    public void testMergeSort()
    {
        double[] empty = {};
        String emptyString = "[]";
        double[] aSorted = {1, 2, 3.2, 7, 9.6};
        double[] a = {7, 1, 9.6, 2, 3.2};
        SortComparison.mergeSort(a);
        assertEquals("Mergesort: ", Arrays.toString(aSorted), Arrays.toString(SortComparison.mergeSort(a)));
    }
    
    @Test
    public void testQuickSort()
    {
        double[] empty = {};
        String emptyString = "[]"; 
        double[] one = {1.0};
        double[] oneSorted = {1.0};
        double[] aSorted = {1, 2, 3.2, 7, 9.6};  
        double[] a = {7, 1, 9.6, 2, 3.2};
        assertEquals("Quick empty: ", emptyString, Arrays.toString(SortComparison.quickSort(empty)));
        assertEquals("Quick 1:", Arrays.toString(oneSorted), Arrays.toString(SortComparison.quickSort(one)));
        a = SortComparison.quickSort(a);
        assertEquals("Quick:", Arrays.toString(aSorted), Arrays.toString(a));
        a = convertToDoubleArray(new File("numbers1000.txt"));
        aSorted = convertToDoubleArray(new File("numbersSorted1000.txt"));
        a = SortComparison.quickSort(a);
        assertEquals("Quick 1000: ", Arrays.toString(aSorted), Arrays.toString(a));
        
    }

    public static double[] convertToDoubleArray(File file)
    {
        try
        {
             Scanner inFile = new Scanner(file); 

             ArrayList<Double> dataIn = new ArrayList<Double>();

             while(inFile.hasNext())
             {
                dataIn.add(inFile.nextDouble());
             }

             double[] data = new double[dataIn.size()]; 

             for(int i=0; i < dataIn.size(); i++)
             {
                 data[i] = dataIn.get(i).doubleValue();   
             }
             return data;
         }
         catch(Exception e)
         {
            return null;
         }     
    }


// ----------------------------------------------------------
/**
 *  Main Method.
 *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
 */
   /* public static void main(String[] args)
    {
        
        File[] files = { 
            new File("numbers10.txt"),
            new File("numbers100.txt"),
            new File("numbers1000.txt"),
            new File("numbers1000Duplicates.txt"),
            new File("numbersNearlyOrdered1000.txt"),
            new File("numbersReverse1000.txt"),
            new File("numbersSorted1000.txt"),
        };

        double insertionSortTime, quickSortTime, mergeSortTime, 
               shellSortTime, selectionSortTime, bubbleSortTime;
        double insertionSortSum, quickSortSum, mergeSortSum,
               shellSortSum, selectionSortSum, bubbleSortSum;

        int occurances = 3;

        for(int i = 0; i < files.length; i++)
        {
            double[] data = convertToDoubleArray(files[i]);
            double[] dataArray = data;

            insertionSortSum = quickSortSum = mergeSortSum =
                shellSortSum = selectionSortSum = bubbleSortSum = 0;

            for(int j = 0; j < occurances; j++)
            {
                SortComparison.insertionSort(dataArray);
                dataArray = data;
                SortComparison.quickSort(dataArray);
                dataArray = data;
                SortComparison.mergeSort(dataArray);
                dataArray = data;
                SortComparison.shellSort(dataArray);
                dataArray = data;
                SortComparison.selectionSort(dataArray);
                dataArray = data;
                SortComparison.bubbleSort(dataArray);
                dataArray = data;
                
                insertionSortTime = System.nanoTime();
                SortComparison.insertionSort(dataArray);
                insertionSortTime = (System.nanoTime() - insertionSortTime)/1000000;
                insertionSortSum += insertionSortTime;

                dataArray = data;
                quickSortTime = System.nanoTime();
                SortComparison.quickSort(dataArray);
                quickSortTime = (System.nanoTime() - quickSortTime)/1000000;
                quickSortSum += quickSortTime;

                dataArray = data;
                mergeSortTime = System.nanoTime();
                SortComparison.mergeSort(dataArray);
                mergeSortTime = (System.nanoTime() - mergeSortTime)/1000000;
                mergeSortSum += mergeSortTime;

                dataArray = data;
                shellSortTime = System.nanoTime();
                SortComparison.shellSort(dataArray);
                shellSortTime = (System.nanoTime() - shellSortTime)/1000000;
                shellSortSum += shellSortTime;

                dataArray = data;
                selectionSortTime = System.nanoTime();
                SortComparison.selectionSort(dataArray);
                selectionSortTime = (System.nanoTime() - selectionSortTime)/1000000;
                selectionSortSum += selectionSortTime;

                dataArray = data;
                bubbleSortTime = System.nanoTime();
                SortComparison.bubbleSort(dataArray);
                bubbleSortTime = (System.nanoTime() - bubbleSortTime)/1000000;
                bubbleSortSum =+ bubbleSortTime;

            }

            insertionSortTime = insertionSortSum/occurances;
            quickSortTime = quickSortSum/occurances;
            mergeSortTime = mergeSortSum/occurances;
            shellSortTime = shellSortSum/occurances;
            selectionSortTime = selectionSortSum/occurances;
            bubbleSortTime = bubbleSortSum/occurances;

            System.out.println(" | " + insertionSortTime + 
                " | " + quickSortTime + " | " + mergeSortTime + " | " + shellSortTime + 
                " | " + selectionSortTime + " | " + bubbleSortTime);
        }
    }
    */
}   

