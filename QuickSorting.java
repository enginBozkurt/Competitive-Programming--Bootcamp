package com.sort;

public class QuickSorting {
	
	static void QuickSort(int[] arr, int low, int high) {
		
		if(low > high) return;  //if the searching area is exceed
		
		int mid = low + (high-low)/2;
		int pivot = arr[mid];  //pivot is the middle element
		
		int i=low;
		int j= high;
		
		while(i<=j) {
			
			while(arr[i] < pivot)  //first half sub-array less than pivot
				i++;
			while(arr[j] > pivot) //second half sub-array greater than pivot
				j--;
			if(i <= j) {   //otherwise swap the elements
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
		
		if(low < j) // sort algorithm is recursively applied to the left part
			QuickSort(arr, low, j);
		if(high > i)  // sort algorithm is recursively applied to the right part
			QuickSort(arr, i, high);
	
	}
	
	public static void main(String[] args) {
		
		int[] arr = {1 , 50, 30 ,10 ,80, 60};
		
		System.out.println("Before sort");
		
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + "\t");
		
		QuickSort(arr, 0, arr.length-1);   
		
		System.out.println("\nAfter sort");
		
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + "\t");

	}
}