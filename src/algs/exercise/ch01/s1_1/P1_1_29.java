package algs.exercise.ch01.s1_1;

import java.util.Arrays;

import com.wangsg.algs.In;

public class P1_1_29 {

	public static void main(String[] args) {
		int []whiteList = In.readInts("http://algs4.cs.princeton.edu/11model/tinyT.txt");
		Arrays.sort(whiteList);
		System.out.println(rank(77,whiteList)+ "\n" + count(77,whiteList));
		
	}
	/**
	 * @param key the key to be searched
	 * @param arr the sorted array
	 * @return mid key is in the array and index is mid
	 * @return -1 key is not in the array
	 */
	public  static int search(int key, int[] arr){
		int lo = 0;
		int hi = arr.length-1;
		
		while(lo <= hi){
			int mid = (lo + hi) /2;
			if(arr[mid] > key) hi = mid -1;
			else if(arr[mid] < key) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	/**
	 * @param key the compared key
	 * @param arr the sorted array
	 * @return the numbers of elements' that are less than the key
	 */
	
	public static int rank(int key, int[] arr){
		int lo = 0;
		int hi = arr.length - 1;
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			if(arr[mid] > key){
				hi = mid - 1;
			}
			else if(arr[mid] < key){
				lo = mid + 1;
			}
			else{
				while(mid > 0 && arr[mid] == arr[mid - 1])
					--mid;
				return mid;
			}
			
			
		}
		return lo;
	}
	

	/**
	 * @param key compared key
	 * @param arr  the sorted array
	 * @return the numbers of elements that are equal the key
	 */
	public static int count(int key, int[] arr){
		int lo = 0;
		int hi = arr.length - 1;
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			if(arr[mid] > key){
				hi = mid - 1;
			}
			else if(arr[mid] < key){
				lo = mid + 1;
			}
			else{
				int count = 1;
				while(mid > 0 && arr[mid] == arr[mid - 1]){
					--mid;
					++count;
				}
				
				return count;
			}
			
			
		}
		return 0;	
	}
	
	

}
