/*
 * 加权无向图的最小生成树算法
 */

package algs.textbook.ch04.s4_3;

import com.wangsg.algs.In;
import com.wangsg.algs.MinPQ;
import com.wangsg.algs.Queue;

import edu.princeton.cs.algs4.StdOut;

public class LazyPrimMST {
	private boolean[] marked;                //用来标记顶点是否已经被访问过（是否已经在树中）
	private Queue<Edge> mst;              //记录最小生成树的边
	private MinPQ<Edge> pq;                 //横切边（包括失效的边）
	
	public LazyPrimMST(EdgeWeightedGraph G){
		marked = new boolean[G.V()];
		pq = new MinPQ<Edge>();
		mst = new Queue<Edge>();
		visit(G,0);
		while(!pq.isEmpty()){
			Edge e = pq.delMin();            //选取出权重最小的边
			int v = e.either();
			int w = e.other(v);
			if(marked[v] && marked[w]) continue;         //对失效边暂时不做处理
			mst.enqueue(e);                              //将边添加到树中
			if(!marked[v])             visit(G,v);       //将顶点添加到树中，继续寻找横向边
			if(!marked[w])             visit(G,w);
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v){
		marked[v] = true;
		for(Edge e:G.adj(v)){
			if(!marked[e.other(v)]) pq.insert(e);
		}
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight(){
		double sum = 0.0;
		for(Edge e:mst){
			sum += e.weight();
		}
		return sum;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		LazyPrimMST mst = new LazyPrimMST(G);
		for(Edge e:mst.edges())
			StdOut.println(e.toString());
		StdOut.println(mst.weight());
	}

}
