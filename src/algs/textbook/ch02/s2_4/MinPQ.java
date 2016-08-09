package algs.textbook.ch02.s2_4;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;
import com.wangsg.algs.Stopwatch;

public class MinPQ<Key extends Comparable<Key>> {
	
	private Key[] pq;
	private int N;
	
	
	public static void sort(Comparable[] a){
		int N = a.length -1;
		for(int k = N/2; k >= 0; k--){
			sink(a,k,N);
		}
		
		while(N > 0){
			exch(a,0,N--);
			sink(a,0,N);
		}
		
	}
	
	private static void sink(Comparable[] a, int lo, int hi){
		while(2*lo <= hi){
			int j = 2*lo;
			if(j < hi && greater(a[j],a[j+1])) j++;
			if(!greater(a[lo],a[j])) break;
			exch(a,lo,j);
		    lo = j;
		}
	}
	
	private static boolean greater(Comparable v, Comparable w){
		return v.compareTo(w) > 0;
	}
	
	private boolean greater(int i, int j){
		return pq[i].compareTo(pq[j]) > 0;
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	private void swim(int k){
		while(k > 1 && greater(k/2,k)){
			exch(k/2,k);
			k = k/2;
		}
	}
	
	private void resize(int s){
		Key[] temp = (Key[]) new Comparable[s];
		for(int i = 1; i <= N; i++){
			temp[i] = pq[i];
		}
		pq = temp;
	}
	
	private void skin(int k){
		while(2*k <= N){
			//find the max one
			int j = 2*k;
			if(j < N && greater(j,j+1)) j++;
			if(!greater(k,j)) break;
			exch(k,j);
			k = j;
		}
	}
	
	public MinPQ() {
		N = 0;
		pq = (Key[])new Comparable[N+1];
	}
	
	public MinPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN+1];
		N=0;
	}
	
	public MinPQ(Key[] a){
		N = a.length;
		pq = (Key[]) new Comparable[a.length+1];
		for(int i = 0; i < a.length; i++){
			pq[i+1] = a[i];
		}
		for(int k = N/2; k >= 1; k--){
			skin(k);
		}
	}
	
	public void insert(Key v){
		if(N >= pq.length -1 ) resize(2*pq.length);
		pq[++N] = v;
		swim(N);
	}
	
	public  Key min(){
		Key min = pq[1];
		return min;
	}
	
	public  Key delMin(){
		Key min = pq[1];
		exch(1,N--);
		pq[N+1] = null;  //hui shou dui xiang
		skin(1);
		if((N > 0) && (N == ((pq.length - 1) / 4))) resize(pq.length/2);
		return min;
	}
	
	public  boolean isEmpty(){
		return N==0;
	}
	
	public  int size(){
		return N;
	}
	
	public static void show(Comparable[] a){
		for(int i = 0; i < a.length; i++){
			StdOut.println(i + " "+a[i]);
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
			total += time;
			show(test);
		

		StdOut.printf("%.6f seconds\n", total);

	}

}
