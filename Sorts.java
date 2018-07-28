//----------------------------------------------------------------------------
// Test harness used to run sorting algorithms
// Edited by Salek Khan 
//----------------------------------------------------------------------------

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Sorts
{
    public static final int SIZE = 50;          // Size of array to be sorted
    private static int[] values = new int[SIZE];  // Values to be sorted

    private static void initValues()
    // Initializes the values array with random integers from 0 to 99
    {
        Random rand = new Random(560);
        for (int index =0; index < SIZE; index++)
            values[index] = Math.abs(rand.nextInt()) % 100;
    }

    public static boolean isSorted()
    // Determine whether the array values are sorted
    {
        boolean sorted = true;
        for (int i = 0; i < values.length - 1; i++)
            if (values[i] > values[i + 1])
                sorted = false;
        return sorted;   
    }

    public static void swap(int index1, int index2)
    // Swaps the integers at locations index1 and index2 of array values
    // Precondition:  index1 and index2 are less than size
    {
        int temp = values[index1];
        values[index1] = values[index2];
        values[index2] = temp;
    }

    public static void printValues()
    // Prints all the values integers
    {
        int value;
        DecimalFormat fmt = new DecimalFormat("00");
        System.out.println("The values in the array are:");
        for (int index = 0; index < SIZE; index++)
        {
            value = values[index];
            if (((index + 1) % 10) == 0)
                System.out.println(fmt.format(value));
            else
                System.out.print(fmt.format(value) + " ");
        }
        System.out.println();
    }

    public static int minIndex(int startIndex, int endIndex)
    {
        int indexOfMin = startIndex;
        int minVal = values[startIndex];
        for (int index = startIndex; index <= endIndex; index++)
        {
            if (values[index] < minVal)
            {
                minVal = values[index];
                indexOfMin = index;
            }   
        }
        return indexOfMin;
    }

    public static void selectionSort()
    {
        for(int i = 0; i < values.length; i++)
        {
            swap(i, minIndex(i, values.length-1));
        }
    }

    public static void insertionSort()
    {
        for (int i = 1; i < values.length; i++)
        {
            insertItem (0,i);
        }
    }

    public static void insertItem(int startIndex, int endIndex)
    {
        boolean finished = false;
        int current = endIndex;
        boolean moreToSearch = true;
        while(moreToSearch && !finished)
        {
            if(values[current] < values[current - 1])
            {
                swap(current, current - 1);
                current--;
                moreToSearch = current != startIndex;
            }
            else 
            {
                finished =  true;
            }          
        }
    }

    public static void mergeSort(int first, int last)
    {
        if(first < last)
        {
            int middle = first + (last - first) / 2;
            mergeSort(first, middle);
            mergeSort(middle + 1, last);
            merge(first, middle, middle + 1, last);
        }
    }

    public static void merge(int leftFirst, int leftLast, int rightFirst, int rightLast)
    {
        int[] tempArray = new int[SIZE];
        int saveFirst = leftFirst;
        int index = leftFirst;
        while(rightFirst <= rightLast && leftFirst <= leftLast)
        {
            if(values[leftFirst] < values[rightFirst])
            {
                tempArray[index] = values[leftFirst];
                leftFirst++;
            }
            else
            {
                tempArray[index] = values[rightFirst];
                rightFirst++;
            }
            index++;
        }
        for(int j = rightFirst; j <= rightLast; j++)
        {
            tempArray[index] = values[j];
            index++;
        }
        for(int i = leftFirst; i <= leftLast; i++)
        {
            tempArray[index] = values[i];
            index++;
        }
        for(int k = saveFirst; k <= rightLast; k++)
        {
            values[k] = tempArray[k];
        }
    }

    public static void quickSort(int from, int to)
    {
        if(from < to)
        {
            int p = split(from, to);
            quickSort(from, p - 1);
            quickSort(p + 1, to);
        }
    }

    public static int split(int from, int to)
    {
        int pivot = values[from];
        int first = from;
        int last = to;
        while(first < last)
        {
            first++;
            while(first < SIZE && values[first] < pivot)
            {
                first++;
            }
            while(values[last] > pivot)
            {
                last--;
            }
            if(first < last)
            {
                swap(first,last);
            }
        }
        swap(from, last);
        return last;
    }

    public static void main(String[] args) throws IOException
    // Tests the other methods of the Sorts class
    {
        initValues();
        printValues();

        insertionSort();
        selectionSort();
        printValues();
        System.out.println("values is sorted: " + isSorted());
        System.out.println();

        bubbleSort();
        printValues();
        System.out.println("values is sorted: " + isSorted());
        System.out.println(); 

        mergeSort(0, SIZE - 1);
        printValues();
        System.out.println("values is sorted: " + isSorted());
        System.out.println();

        quickSort(0, SIZE-1);
        printValues();
        System.out.println("The sorted values are: " + isSorted());
        System.out.println();  
    }   
}
