package algs.exercise.ch01.s1_1;

import com.wangsg.algs.*;

public class P1_1_18{
	public static void main(String args[]){
		StdOut.println(mystery(2,25));
		StdOut.println(mystery(3,11));	
		StdOut.println(mystery1(2,25));
		StdOut.println(mystery1(3,11));	
	}
	
	public static int mystery(int a, int b){
		if(b==0) return 0;
		if(b % 2 == 0) return mystery(a+a, b/2);
		return mystery(a+a, b/2) + a;
	}

	public static int mystery1(int a, int b){
		if(b==0) return 1;
		if(b % 2 == 0) return mystery1(a*a, b/2);
		return mystery1(a*a, b/2) * a;
	}
}
