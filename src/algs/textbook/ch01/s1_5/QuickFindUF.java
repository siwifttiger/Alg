package algs.textbook.ch01.s1_5;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class QuickFindUF {
	private int[] id;
	private int count;
	
	public QuickFindUF(int N){
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++){
			id[i] = i;
		}
	}
	
	public int count(){
		return count;
	}
	
	public int find(int p){
		return id[p];
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public void union(int p, int q){
		int pID = find(p);
		int qID = find(q);
		if(pID == qID) return;
		for(int i = 0; i < id.length; i++){
			if(id[i] == pID) id[i] = qID;
		}
		count--;
	}
	
	public static void main(String[] args) {
		int N = StdIn.readInt();
		QuickFindUF qUF = new QuickFindUF(N);
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(qUF.connected(p, q)) continue;
			qUF.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(qUF.count() + "components");
		

	}

}
