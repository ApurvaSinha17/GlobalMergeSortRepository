package demo;

import java.util.Random;

public class MainClass {

	public static void main(String[] args) {
		
		int arrayLength = Integer.parseInt(args[0]);
		int [] a = new int[arrayLength];
		Random random = new Random();
		for(int i=0;i<arrayLength;i++){
			a[i] = random.nextInt(100);
		}
		
		System.out.println("Original Array");
		for(int i=0;i<arrayLength;i++){
			System.out.println(a[i]);
		}

		MergeSort mergeSort = new MergeSort();
		a = mergeSort.sort(a);
		
		System.out.println("Sorted array");
		for(int i=0;i<arrayLength;i++){
			System.out.println(a[i]);
		}
	}

}
