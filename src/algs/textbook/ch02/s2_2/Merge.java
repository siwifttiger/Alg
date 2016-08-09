package algs.textbook.ch02.s2_2;

import com.wangsg.algs.Insertion;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;
import com.wangsg.algs.Stopwatch;

public class Merge {
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a){
		aux = new Comparable[a.length];
		sort(a,0,a.length - 1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		/*if(hi-lo <= 10){
			insertionSort(a,lo,hi);
			return;
		}*/
		int mid = lo + (hi - lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,hi);
		merge(a,lo,mid,hi);
	}
	
	public static void merge(Comparable[] a, int lo, int mid, int hi){
		if(less(a[mid], a[mid+1])) return;
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		for(int k = lo; k <= hi; k++){
			if(i > mid)                  a[k] = aux[j++];    //left is used all
			else if(j > hi)              a[k] = aux[i++];    //right is used all;
			else if(less(aux[j],aux[i])) a[k] = aux[j++];
			else                         a[k] = aux[i++];
		}
	}
	
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
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
	public static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static void insertionSort(Comparable[] sub, int src, int dest){
		for(int i = src; i <= dest; i++){
			for(int j = i; j > src && less(sub[j],sub[j-1]); j--){
				exch(sub,j,j-1);
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
			//show(test);
			total += time;
		StdOut.printf("%.6f\n", total);

	}

}
