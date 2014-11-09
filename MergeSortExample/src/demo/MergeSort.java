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
		
		//Optimization 1 : Switch to insertion sort for smaller sub arrays to reduce the overhead associated with merge sort for such conditions
		if((hi-lo) < cutOff){
			insertionSort(a, lo, hi);
			return;
		}
		
		int mid = lo + (hi-lo)/2;
		System.out.println("Debug: lo= "+lo);
		System.out.println("Debug: hi= "+hi);
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		
		//Optimization 2 : Skip the merge, if the merged array is already sorted
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
		
		//Debug: Array before merge
		System.out.println("Array before merge");
		for(int k=lo;k<=hi;k++){
			System.out.println(a[k]);
		}
		
		int i=lo, j=mid+1;
		
		System.out.println("Debug: Merge: lo: "+lo);
		System.out.println("Debug: Merge: mid: "+mid);
		System.out.println("Debug: Merge: hi: "+hi);
		
		//Bug present
		for(int k=lo;k<=hi;k++){
			
			if(i > mid){
				System.out.println("Debug: "+i+" is greater than "+mid);
				a[k] = aux[j];
				j++;
			}
			else if(j > hi){
				System.out.println("Debug: "+j+" is greater than "+hi);
				a[k] = aux[i];
				i++;
			}
			//Fixed Bug: Use auxiliary array instead of the array where we are placing sorted values;
			//i.e. one array for comparison and one array for storing sorted values
			else if(aux[j] < aux[i]){
				System.out.println("Debug: "+a[j]+" is less than "+a[i]);
				a[k] = aux[j];
				j++;
			}
			else{
				System.out.println("Debug: "+a[i]+" is less than "+a[j]);
				a[k] = aux[i];
				i++;
			}				
		}
		
		//Debug: Array after merge
				System.out.println("Array after merge");
				for(int k=lo;k<=hi;k++){
					System.out.println(a[k]);
				}
		
		//Postmerge sorting check (Optional)
		isSorted(a, lo, hi);
		
	}
	
	private void insertionSort(int[] a, int lo, int hi){
		
		System.out.println("Doing insertion sort of array between indices "+lo+" and "+hi);
		for(int i=lo+1; i<=hi; i++){
			//Fixed Bug: Remember to keep j>lo instead of j>0
			for(int j=i ; j>lo && (a[j] < a[j-1]) ; j--){
				int temp = a[j-1];
				a[j-1] = a[j];
				a[j] = temp;
			}
		}
		
		//Debug: After Insertion Sort
		System.out.println("Array after insertion sort");
		for(int i=lo;i<=hi;i++)
			System.out.println(a[i]);
	}
	
	//Optional method for debug purpose - Start
	private void isSorted(int[] a, int lo, int hi){
		
		for(int i=lo;i<hi;i++){			
			if(a[i+1] < a[i]){
				System.out.println("Sub array within index "+lo+" and "+hi+" are not sorted");
				System.out.println(a[i+1]);
				System.exit(0);
			}
		}
	}
	//Optional method for debug purpose - End
}
