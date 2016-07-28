package algs.textbook.ch01.s1_5;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

import algs.textbook.ch01.s1_4.Stopwatch;

public class WeightedCompressQuickUnionUF {
	
	private int[] id;
	private int[] size;
	private int count;
	public WeightedCompressQuickUnionUF(int N){
		count = N;
		id = new int[N];
		size = new int[N];
		for(int i = 0; i < N; i++){
			id[i] = i;
			size[i] = 1;
		}
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public int find(int p){
		int temp = p;
		while(p != id[p]){
			p = id[p];
		}
		while(temp != id[p]) {
			int templd = id[temp];
			id[temp] = id[p];
			temp = templd;
			
		}
		return p;
	}
	
	public void union(int p, int q){
		int i = find(p);
		int j = find(q);
		if(i == j) return;
		if(size[i] > size[j]){
			id[j] = i;
			size[i] += size[j];
		}
		else{
			id[i] = j;
			size[j] += size[i];
		}
		count--;
	}
	
	public static void main(String[] args) {
		int N = StdIn.readInt();
		WeightedCompressQuickUnionUF uf = new WeightedCompressQuickUnionUF(N);
		Stopwatch timer = new Stopwatch();
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(uf.connected(p, q)) continue;
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		double time = timer.elapseTime();
		StdOut.println(uf.count() + " compponents, time: " + time );
	}

}
