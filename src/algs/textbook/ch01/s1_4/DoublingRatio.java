package algs.textbook.ch01.s1_4;

import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;

public class DoublingRatio {

	//this function can be changed for testing different algorithms
	public static double timeTrial(int N){
		int MAX = 1000000;
		int[] a = new int[N];
		for(int i = 0; i < N; i++){
			a[i] = StdRandom.uniform(-MAX, MAX);
		}
		Stopwatch timer = new Stopwatch();
		int count = ThreeSum.count(a);
		double time = timer.elapseTime();
		return time;
	}
	public static void main(String[] args) {
		double prev = timeTrial(125);
		for(int N = 250; true; N += N){
			double time = timeTrial(N);
			StdOut.printf("%6d %7.1f ", N, time);
			StdOut.printf("%5.1f\n", time/prev);
			prev = time;
		}

	}

}
