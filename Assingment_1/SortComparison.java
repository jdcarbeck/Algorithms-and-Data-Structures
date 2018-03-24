// TODO: Fix merge sort and selectionSort
// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
*  @version HT 2018
*/
import java.util.Arrays;

class SortComparison {
    static double[] emptyArray = new double[0];
    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double[] a)
    {
        if(a.length == 0) return a;
        double temp;
        for (int i = 0; i < a.length; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (a[j] < a[j-1])
                {
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
        return a;
    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double[] a){
        quickSort(a, 0, a.length-1);
        return a;
    }//end quicksort
    static void quickSort (double[] a, int lo, int hi)
    {
        if(a == null || a.length == 0) return;
        if(lo >= hi) return;

        int mid = lo + (hi - lo) / 2;
        double pivot = a[mid];
     
        int i = lo, j = hi;
        while (i <= j) {
            while (a[i] < pivot) {
                i++;
            }
     
            while (a[j] > pivot) {
                j--;
            }
     
            if (i <= j) {
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }
        if (lo < j)
            quickSort(a, lo, j);
     
        if (hi > i)
            quickSort(a, i, hi);
    }


    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] mergeSort (double[] a)
    {
        if(a.length > 1)
        {
            int mid = a.length/2;
            double[] x = Arrays.copyOfRange(a, 0, mid);
            double[] y = Arrays.copyOfRange(a, mid, a.length);
            mergeSort(x);
            mergeSort(y);
            merge(a,x,y);
        }
        return a;
    }//end mergesort
    static double [] merge(double[] a, double[] x, double[] y)
    {
        int N = a.length;
        int xLength = x.length;
        int yLength = y.length;
        int i, xIndex, yIndex;
        i = xIndex = yIndex = 0;
        while(i < N)
        {
            if((xIndex < xLength) && (yIndex < yLength))
            {
                if(x[xIndex] < y[yIndex])
                {
                    a[i] = x[xIndex];
                    xIndex++;
                    i++;    
                }
                else
                {
                    a[i] = y[yIndex];
                    yIndex++;
                    i++;
                }
            }
            else
            {
                if(xIndex >= xLength)
                {
                    while(yIndex < yLength)
                    {
                        a[i] = y[yIndex];
                        i++;
                        yIndex++;
                    }
                }
                if(yIndex >= yLength)
                {
                    while(xIndex < xLength)
                    {
                        a[i] = x[xIndex];
                        i++;
                        xIndex++;
                    }
                }
            }
        }
        return a;
    }

    /**
     * Sorts an array of doubles using Shell Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] shellSort (double[] a)
    {
        int len = a.length;
        int h = 1;
        while(h < (len/3)) h = 3*h + 1; //finds the largest possible h
        while(h >= 1)
        {
            for(int i = h; i < len; i++)
            {
                for(int j = i; j >= h && (a[j] < a[j-h]); j -=h)
                {
                    double temp = a[j];
                    a[j] = a[j-h];
                    a[j-h] = temp;
                }
            }
            h = h/3;
        }
        return a;
    }//end shellsort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double[] a)
    {
        int size = a.length;
        for(int i = 0; i < size; i++)
        {
            int minIndex = i;
            for(int j = i+1; j < size; j++)
                if(a[j] < a[minIndex]) minIndex = j;
            double temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
        return a;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Bubble Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] bubbleSort (double[] a)
    {
        double temp;
        int size = a.length;
        for(int i = 0; i < size; i++)
        {
            for(int j = 1; j < (size - i); j++)
            {
                if(a[j-1] > a[j])
                {
                    temp = a[j -1];
                    a[j -1] = a[j];
                    a[j] = temp;
                }
            }
        }
	    return a;
    }//end bubblesort
}//end class

