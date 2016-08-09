/*
 * It actually makes the index to the binary heap
 */

package algs.textbook.ch02.s2_4;

import com.wangsg.algs.StdOut;

public class IndexMinPQ <Key extends Comparable<Key>>{
	private int N;             //numbers of PQ;
	private int pq[];          //index  of binary heap, start with 1;
	private int qp[];          //inverse of pq;  qp[pq[i]] = pq[qp[i]] = i;
	private Key[] keys;        //elements with priority
	
	public IndexMinPQ(int maxN){
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		keys = (Key[])new Comparable[maxN + 1];
		for(int i = 1; i <= maxN; i++) qp[i] = -1;
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public boolean contains(int k){
		return qp[k] == -1;
	}
	
	public void insert(int k, Key key){
		N++;
		qp[k] = N;
		pq[N] = k;
		keys[k] = key;
		swim(N);
	}
	
	public int minIndex(){
		return pq[1];
	}
	
	public Key min(){
		return keys[pq[1]];
	}
	
	public int delMin(){
		int indexOfMin = pq[1];
		exch(1,N--);
		sink(1);
		keys[pq[N+1]] = null;
		return indexOfMin;
	}
	
	public void change(int k, Key key){
		keys[k] = key;
		swim(qp[k]);
		sink(qp[k]);
	}
	
	public void delete(int k){
		int index = qp[k];
		exch(index,N--);
		swim(index);
		sink(index);
		keys[k] = null;
		qp[k] = -1;
	}
	
	public void ShowIndex(){
		for(int i = 1; i <= N; i++ ){
			StdOut.println("pq:"+ i+" "+pq[i]);
		}
		for(int i = 0; i < N; i++){
			StdOut.println("qp:" + i + " "+ qp[i]);
		}
		
	}
	
	public void showKeys(){
		for(int i = 0; i < N; i++){
			StdOut.println("keys" + i + " "+keys[pq[i+1]]);
		}
	}
	
	private boolean greater(int i, int j){
		return keys[pq[i]].compareTo(keys[pq[j]])>0;
	}
	
	private void exch(int i, int j){
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	
	private void swim(int k){
		while(k > 1 && greater(k/2,k)){
			exch(k/2,k);
			k = k/2;
		}
	}
	
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && greater(j,j+1)) j++;
			if(!greater(k,j)) break;
			exch(k,j);
			k = j;
		}
	}
	
	public static void main(String[] args){
		IndexMinPQ<String> iPQ= new IndexMinPQ<String>(7);
		iPQ.insert(0, "G");
		iPQ.insert(1, "F");
		iPQ.insert(2, "E");
		iPQ.insert(3, "A");
		iPQ.insert(4, "B");
		iPQ.insert(5, "D");
		iPQ.insert(6, "C");
		iPQ.ShowIndex();
		iPQ.showKeys();
	}
}
