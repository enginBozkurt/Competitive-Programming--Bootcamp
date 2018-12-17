package com.sort;


public class MergeSort  {
	
	 public static void main(String[] args) {
			
		 
			int[] arr={1,50,30,10,60,80};
			System.out.println("Before sorting");
			for(int i=0;i<arr.length;i++)
				System.out.print(arr[i]+ "\t");
			
			
			MergeSort.sort(arr);
			System.out.println("\nAfter sorting");
			for(int i=0;i< arr.length;i++)
				System.out.print( arr[i]+ "\t");
		}
	
	// stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
	private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		
		// copy to aux[]
		for(int k=lo; k<=hi; k++)
			aux[k] = a[k];
		
		int i=lo;
		int j=mid+1;
		
		 // merge back to a[]
		for(int k=lo; k<=hi; k++) {
			
		if(i > mid) 	a[k] = aux[j++];
		else if(j > hi) a[k] = aux[i++];
		else if(aux[j] < aux[i]) a[k] = aux[j++];
		else a[k] = aux[i++];
		}			
	}
	
	// mergesort a[lo..hi] using auxiliary array aux[lo..hi]
	public static void sort(int[] a, int[] aux, int lo, int hi) {
		
		if(hi <=lo) return;
		int mid = lo + (hi - lo)/2;
		
		sort(a, aux, lo, mid);    //sort first half
		sort(a, aux, mid+1, hi);  //sort second half
		
		// merge sorted arrays
		merge(a, aux, lo, mid, hi);
		
	}
	
	
	//Rearranges the array in ascending order, using the natural order
	public static void sort(int[] a) {
		int[] aux = new int[a.length]; 		// create aux array
		sort(a, aux, 0, a.length-1);
	}
		
}
