package algs.textbook.ch01.s1_5;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class QuickUnionUF {
	private int count;
	private int[] id;
	
	public QuickUnionUF(int N){
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++)
			id[i] = i;
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public int find(int p){
		while(p != id[p]) p = id[p];
		return p;
	}
	
	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) return;
		id[pRoot] = qRoot;
		count--;
	}
	public static void main(String[] args) {
		int N = StdIn.readInt();
		QuickUnionUF qUF = new QuickUnionUF(N);
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(qUF.connected(p,q)) continue;
			qUF.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(qUF.count() + " components");
	}

}
