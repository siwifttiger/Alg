package algs.textbook.ch02.s2_3;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;
import com.wangsg.algs.Stopwatch;

public class Quick3Way {
	
	
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);	
		sort(a,0,a.length-1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi){
		if(lo >= hi) return;
		int lt = lo,i = lo+1, gt = hi;
		Comparable v = a[lo];
		while(gt >= i){
			int cmp = a[i].compareTo(v);
			if(cmp > 0) exch(a,i,gt--);
			else if(cmp < 0) exch(a,lt++,i++);
			else i++;
		}
		sort(a,lo,lt-1);
		sort(a,gt+1,hi);
	}
	
	
	public static boolean less(Comparable v,Comparable w){
		return v.compareTo(w) < 0;
	}
	
	public static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
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
