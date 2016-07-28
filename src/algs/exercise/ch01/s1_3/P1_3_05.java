package algs.exercise.ch01.s1_3;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

import algs.textbook.ch01.s1_3.*;

public class P1_3_05 {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		int N = StdIn.readInt();
		while(N > 0){
			stack.push(N % 2);
			N = N/2;
		}
		for(int a:stack)
			StdOut.print(a);

	}

}
