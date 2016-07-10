package algs.exercise.ch01.s1_1;

import java.util.Arrays;

import com.wangsg.algs.In;

public class P1_1_28 {

	
	public static void main(String[] args) {
		String filename = new String("/home/cygnus/workspace/AlgorithmPractices/src/com/wangsg/ch01/tinyW.txt");
		int[] whiteList = In.readInts(filename);
		Arrays.sort(whiteList);
		int[] arr = rmduplicate(whiteList);
		System.out.println(Arrays.toString(arr));

	}
	
	public static int[] rmduplicate(int[] arr){
		int[] temp = new int[arr.length];
		int k = 0;
		temp[0] = arr[0];
		for(int i = 1; i < arr.length; i++){
			if(temp[k] != arr[i]){
				k++;
				temp[k] = arr[i];
				
			}
		}
		int[] results = new int[k+1];
		for(int i = 0; i <= k; i++){
			results[i] = temp[i];
		}
		return results;
	}

}
