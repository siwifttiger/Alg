package algs.textbook.ch02.s2_3;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;
import com.wangsg.algs.Stopwatch;

public class Quick {
	
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	
	
	public static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int j = partion(a,lo,hi);
		sort(a,lo,j-1);
		sort(a,j+1,hi);
	}
	
	public static int partion(Comparable[] a, int lo, int hi){
		int i = lo;
		int j = hi +1;
		Comparable v = a[lo];
		while(true){
			while(less(v,a[--j])) if(j == lo) break;
			while(less(a[++i],v)) if(i == hi) break;
			if(i >= j) break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
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
			//show(test);
		

		StdOut.printf("%.6f seconds\n", total);

	}

}
