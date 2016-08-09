package algs.textbook.ch02.s2_1;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;

import algs.textbook.ch01.s1_4.Stopwatch;

public class SortCompare {

	public static double time(String alg, Comparable[] a){
		Stopwatch timer = new Stopwatch();
		if(alg.equals("Insertion")) Insertion.sort(a);
		if(alg.equals("Selection")) Selection.sort(a);
		if(alg.equals("Shell"))     Shell.sort(a);
		return timer.elapseTime();
	}
	
	public static double timeRandomInput(String alg,int N, int T){
		double total = 0.0;
		double time0;
		Double[] test = new Double[N];
		for(int i = 0; i < T; i++){
			for(int j = 0; j < N; j++)
				test[j] = StdRandom.uniform();
			time0 = time(alg,test);
			total += time0;
			StdOut.println(time0 + " " + total);
		}
		StdOut.println(total);
		return total;
	}
	
	public static void main(String[] args) {
		String alg1 = StdIn.readString();
		String alg2 = StdIn.readString();
		int N = StdIn.readInt();
		int T = StdIn.readInt();
		double t1 = timeRandomInput(alg1,N,T);
		double t2 = timeRandomInput(alg2,N,T);
		//StdOut.println(t1);
		StdOut.printf("For %d random Doubles\n   %s is",N,alg1);
		StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
		

	}

}
