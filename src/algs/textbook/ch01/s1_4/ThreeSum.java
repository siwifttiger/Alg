package algs.textbook.ch01.s1_4;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class ThreeSum {

	public static int count(int [] a){
		int N = a.length;
		int cnt = 0;
		for(int i = 0; i < N; i++){
			for(int j = i+1; j< N; j++){
				for(int k = j + 1; k < N; k++){
					if(a[i] + a[j] + a[k] == 0)
						cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		int[] a = StdIn.readAllInts();
		StdOut.println(count(a));

	}

}
