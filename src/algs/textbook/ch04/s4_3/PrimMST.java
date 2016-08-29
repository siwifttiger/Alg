package algs.textbook.ch04.s4_3;

import com.wangsg.algs.In;
import com.wangsg.algs.IndexMinPQ;
import com.wangsg.algs.Queue;

import edu.princeton.cs.algs4.StdOut;

public class PrimMST {
	private Edge[] edgeTo;         // 距离树最短的边 edgeTo[v]表示v距离树最短的边
	private double[] distTo;       // distTo[w] = edgeTo[w].weight()
	private boolean[] marked;      // v在树中则为true；
	private IndexMinPQ<Double> pq; // 有效的横切边,索引为顶点v，索引关联的是v距离树的最小权重
	
	public PrimMST(EdgeWeightedGraph G){
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for(int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[0] = 0.0;
		pq.insert(0, distTo[0]);
		while(!pq.isEmpty()){
			visit(G,pq.delMin());                   //将最近的点添加到树中
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v){
		marked[v] = true;
		for(Edge e: G.adj(v)){
			int w = e.other(v);
			if(marked[w]) continue;
			if(e.weight() < distTo[w]){
				edgeTo[w] = e;
				distTo[w] = e.weight();
				//如果之前添加过含有w顶点的横向边，需要改变索引相关联的权重
				if(pq.contains(w)){
					pq.change(w, distTo[w]);
				}
				else{
					pq.insert(w,distTo[w]);
				}
			}
		}
	}
	
	public Iterable<Edge> edges(){
		Queue<Edge> mst = new Queue<Edge>();
		for(int v = 0; v < edgeTo.length; v++){
			Edge e = edgeTo[v];
			if(e != null)
				mst.enqueue(e);
		}
		return mst;
	}
	
	public double weight(){
		double sum = 0.0;
		for(Edge e: edges()){
			sum += e.weight();
		}
		return sum;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		PrimMST mst = new PrimMST(G);
		for(Edge e:mst.edges())
			StdOut.println(e.toString());
		StdOut.printf("%.5f\n", mst.weight());
	}

}
