package algs.exercise.ch01.s1_1;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class P1_1_14 {

	public static void main(String[] args) {
		int n = StdIn.readInt();
		StdOut.println(lg(n));

	}
	
	/**
	 * @param n
	 * @return
	 */
	public static int lg(int n){
		int t = 2;
		int count = 1;
		while(t <= n){
			t = t* t;
			count++;
		}
		return count;
		
		
	}

}
