package algs.textbook.ch02.s2_2;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;
import com.wangsg.algs.Stopwatch;

public class MergeBU {
	private static Comparable[] aux;
	
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	public static void merge(Comparable[] a, int lo, int mid, int hi){
		if(less(a[mid], a[mid + 1])) return;
		int i = lo,j = mid+1;
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		for(int k = lo; k <= hi; k++){
			if(j > hi) a[k] = aux[i++];
			else if(i > mid) a[k] = aux[j++];
			else if(less(aux[j],aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
	
	public static void show(Comparable[] a){
		for(int i = 0; i < a.length; i++)
			StdOut.println(a[i]);
	}
	
	public static void sort(Comparable[] a){
		int N = a.length;
		aux = new Comparable[N];
		for(int sz = 1; sz<= N -1; sz += sz){
			for(int lo = 0; lo < N - sz; lo += sz+sz){
				merge(a,lo,lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
			}
		}
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
			show(test);
			total += time;
		StdOut.printf("%.6f\n", total);

	}

}
