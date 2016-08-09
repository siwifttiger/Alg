package algs.textbook.ch02.s2_4;

public class MaxPQ<Key extends Comparable<Key>> {
	
	private Key[] pq;
	private int N;
	
	
	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	private void swim(int k){
		while(k > 1 && less(k/2,k)){
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
			if(j < N && less(j,j+1)) j++;
			if(!less(k,j)) break;
			exch(k,j);
			k = j;
		}
	}
	
	public MaxPQ() {
		N = 0;
		pq = (Key[])new Comparable[N+1];
	}
	
	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN+1];
		N=0;
	}
	
	public MaxPQ(Key[] a){
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
	
	public  Key max(){
		Key max = pq[1];
		return max;
	}
	
	public  Key delMax(){
		Key max = pq[1];
		exch(1,N--);
		
		pq[N+1] = null;  //hui shou dui xiang
		skin(1);
		if((N > 0) && (N == ((pq.length - 1) / 4))) resize(pq.length/2);
		return max;
	}
	
	public  boolean isEmpty(){
		return N==0;
	}
	
	public  int size(){
		return N;
	}

}
