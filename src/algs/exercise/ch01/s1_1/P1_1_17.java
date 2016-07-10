/*
 * ������룬�����StackOverflowError
 */
package algs.exercise.ch01.s1_1;

public class P1_1_17 {

	public static void main(String[] args) {
		exR1(6);
	}
	
	public static String exR1(int n){
		String s = exR1(n-3) + n + exR1(n-2) + n;
		if(n <= 0) return "";
		return s;
	}

}
