package demo;

public class MergeSort {
	
	//Cutoff value for insertion sort
	private final int cutOff = 7;

	public int[] sort(int a[]){
		
		int[] aux = new int [a.length];
		sort(a,aux,0,a.length-1);
		return a;
	}
	
	private void sort(int[] a, int[] aux, int lo, int hi){
		
		//Optimisation 1 : Switch to insertion sort for smaller sub arrays to reduce the overhead associated with merge sort for such conditions
		if((hi-lo) < cutOff){
			insertionSort(a, lo, hi);
			return;
		}
		
		int mid = lo + (hi-lo)/2;
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		
		//Optimisation 2 : Skip the merge, if the merged array is already sorted
		if(a[mid] < a[mid+1]){
			System.out.println("Already sorted array between "+lo+" and "+hi);
			return;
		}
		
		merge(a,aux,lo,mid,hi);
	}
	
	private void merge(int[] a, int[] aux, int lo, int mid, int hi){
		
		//Precondition to merge sort : Sub arrays should be sorted
		isSorted(a, lo, mid);
		isSorted(a, mid+1, hi);
		
		//Copying the original array to auxiliary array
		for(int k=lo;k<=hi;k++){
			aux[k] = a[k];
		}
		
		int i=lo, j=mid+1;

		for(int k=lo;k<=hi;k++){
			
			if(i > mid)
				a[k] = aux[j++];
			else if(j > hi)
				a[k] = aux[i++];
			else if(aux[j] < aux[i])
				a[k] = aux[j++];
			else
				a[k] = aux[i++];			
		}
		
		//Post-merge sorting check (Optional)
		isSorted(a, lo, hi);
		
	}
	
	private void insertionSort(int[] a, int lo, int hi){
		
		for(int i=lo+1; i<=hi; i++){
			for(int j=i ; j>lo && (a[j] < a[j-1]) ; j--){
				int temp = a[j-1];
				a[j-1] = a[j];
				a[j] = temp;
			}
		}
	}
	
	//Optional method for validating whether any sub-array or array is sorted or not
	private void isSorted(int[] a, int lo, int hi){
		
		for(int i=lo;i<hi;i++){			
			if(a[i+1] < a[i]){
				System.out.println("Sub array within index "+lo+" and "+hi+" are not sorted");
				System.exit(0);
			}
		}
	}
}
