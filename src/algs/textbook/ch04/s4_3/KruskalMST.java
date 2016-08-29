/*
 * 前提是图示连通的
 */

package algs.textbook.ch04.s4_3;

import com.wangsg.algs.In;
import com.wangsg.algs.MinPQ;
import com.wangsg.algs.Queue;
import com.wangsg.algs.UF;

import edu.princeton.cs.algs4.StdOut;

public class KruskalMST {
	private MinPQ<Edge> pq;
	private UF uf;
	private Queue<Edge> mst;
	
	public KruskalMST(EdgeWeightedGraph G){
		pq = new MinPQ<Edge>(G.V());
		uf = new UF(G.V());
		mst = new Queue<Edge>();
		for(Edge e:G.edges()) pq.insert(e);
		while(!pq.isEmpty() && mst.size() < G.V()-1){
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if(uf.connected(v, w)) continue;
			uf.union(v, w);
			mst.enqueue(e);
		}
		
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight(){
		double sum = 0.0;
		for(Edge e:edges()){
			sum += e.weight();
		}
		return sum;
	}
	
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		KruskalMST mst = new KruskalMST(G);
		for(Edge e:mst.edges())
			StdOut.println(e.toString());
		StdOut.printf("%.5f\n", mst.weight());

	}

}
