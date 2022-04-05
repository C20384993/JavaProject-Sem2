package com.oop.eng;

import java.util.ArrayList;
import java.util.List;

/*
Author: Ciaran MacDermott, C20384993
Option 3, Search Engine. 
 */


public class Control {
	
	public static void main(String[] args)
	{
		//Create an object of the GUI class.
		SearchEngineGUI searchGUIObject = new SearchEngineGUI();
		
	}//end main	 
	
	
	//To sort the files by numberOfMatches, the Quicksort algorithm is used.
	//
	public static void sortBegin(List<List<Object>> passedFileResults)
	{
		
	    int listsLength = passedFileResults.size();
	    System.out.println("list length = "+listsLength);
	    System.out.println(passedFileResults.get(0));
	    System.out.println(passedFileResults.get(1));
	    System.out.println(passedFileResults.get(2));
	    System.out.println(passedFileResults.get(3));
	   // System.out.println(passedFileResults.get(4));
	     
	    //Begin the quick sort.
	    quickSortFunc(passedFileResults, 0, listsLength-1);
	    
	    System.out.println(passedFileResults.get(0));
	    System.out.println(passedFileResults.get(1));
	    System.out.println(passedFileResults.get(2));
	    System.out.println(passedFileResults.get(3));
	   // System.out.println(passedFileResults.get(4));
	    
	   // System.out.println("Sorted array: ");
	    printArray(passedFileResults, listsLength);
	}
	
	/* The main function that implements QuickSort
    arr[] --> Array to be sorted,
    low --> Starting index,
    high --> Ending index
	 */
	
	//quickSortFunc will recursively call itself to split the list down into smaller parts.
	//
	static void quickSortFunc(List<List<Object>> passedFileResults, int startPosition, int endPosition)
	{
		if (startPosition < endPosition)
		{
  			// pi is partitioning index, arr[p]
			// is now at right place
			int splitPosition = partition(passedFileResults, startPosition, endPosition);
			System.out.println("splitPosition = "+splitPosition);

			// Separately sort elements before
			// partition and after partition
			quickSortFunc(passedFileResults, startPosition, splitPosition-1);
			quickSortFunc(passedFileResults, splitPosition + 1, endPosition);
		}//end if
		
	}//end quickSortFunc
	
	
	/* This function takes last element as pivot, places
	   the pivot element at its correct position in sorted
	   array, and places all smaller (smaller than pivot)
	   to left of pivot and all greater elements to right
	   of pivot */
	static int partition(List<List<Object>> passedFileResults, int startPosition, int endPosition)
	{
	     
		System.out.println("endPosition = "+endPosition);
		System.out.println("startPosition = "+startPosition);
		System.out.println(passedFileResults.get(endPosition).get(1));
	    // pivot
		//The second .get is 1 because this is the index in the inner list where numOfMatches is stored.
	    Integer pivot = (Integer) passedFileResults.get(endPosition).get(1);
	     
	    // Index of smaller element and
	    // indicates the right position
	    // of pivot found so far
	    int iter = (startPosition-1);
	 
	    for(int j = startPosition; j <= endPosition-1; j++)
	    {
	         
	        // If current element is smaller
	        // than the pivot
	    	System.out.println("VALUE HERE********* -1? = "+Integer.compare((int) passedFileResults.get(j).get(1),pivot));
	    	System.out.println("fileResult j1 < pivot");
	    	System.out.println("j = "+j);
	    	System.out.println("j1 = "+passedFileResults.get(j).get(1));
	    	System.out.println("pivot = "+pivot.intValue());
	    	//jn <= pivot
	        if (Integer.compare((int) passedFileResults.get(j).get(1),pivot) == -1)
	        {
	             
	            // Increment index of
	            // smaller element
	        	System.out.println("LOOK AT MEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
	        	System.out.println("iter++");
	            iter++;
	            swapFunc(passedFileResults, iter, j);
	        }
	    }
	    swapFunc(passedFileResults, iter+1, endPosition);
	    return (iter + 1);
	    
	}//end partition
	
	
	
	// A utility function to swap two elements
	//Identify file by matches number, but swap file position and matches. ****************************
	static void swapFunc(List<List<Object>> passedFileResults, int iter, int j)
	{
		System.out.println("j = "+j);
		System.out.println("iter = "+iter);
		
	    Integer temp = (Integer) passedFileResults.get(iter).get(1);
	    
	    System.out.println("temp = iter 1 = "+temp.intValue());
	    
	    System.out.println("iter 1 = "+passedFileResults.get(iter).get(1));
	    System.out.println("j 1 = "+passedFileResults.get(j).get(1));
	    
	    passedFileResults.get(iter).get(1).equals(passedFileResults.get(j).get(1));
	    
	    System.out.println("iter 1 now = "+passedFileResults.get(iter).get(1));
	    
	    passedFileResults.get(j).get(1).equals(temp);
	    
	    System.out.println("j 1 now = "+passedFileResults.get(j).get(1));
	    
	    System.out.println(passedFileResults.get(0));
	    System.out.println(passedFileResults.get(1));
	    System.out.println(passedFileResults.get(2));
	    System.out.println(passedFileResults.get(3));
	    
	}
	 
	 
	 
	// Function to print an array
	static void printArray(List<List<Object>> passedFileResults, int size)
	{
		System.out.println("::::::Print array:::::");
	    for(int i = 0; i < size; i++)
	        System.out.print(passedFileResults.get(i).get(1) + " ");
	         
	    System.out.println();
	}
	
}//end Control class


