package algs.textbook.ch02.s2_1;

import com.wangsg.algs.In;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;
import com.wangsg.algs.Stopwatch;

public class Insertion {
	public static void sort(Comparable[] a){
		for(int i = 1; i < a.length; i++){
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
				exch(a,j,j-1);
			}
		}
	}
	
	public static boolean less(Comparable v,Comparable w){
		return v.compareTo(w) < 0;
	}
	
	public static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static void show(Comparable[] a){
		for(int i = 0; i < a.length; i++)
			StdOut.println(a[i]);
	}
	
	public static boolean isSorted(Comparable[] a){
		for(int i = 1; i < a.length; i++){
			if(less(a[i],a[i - 1])) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		double total = 0.0;
		int N = StdIn.readInt();
		Double[] test = new Double[N];
			for(int j = 0; j < N; j++)
				test[j] = StdRandom.uniform();
			Stopwatch timer = new Stopwatch();
			sort(test);
			double time = timer.elapsedTime();
			total += time;
			
		
	
		StdOut.printf("%.6f\n", total);

	}

}
