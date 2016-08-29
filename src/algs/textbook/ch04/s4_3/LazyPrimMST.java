/*
 * ��Ȩ����ͼ����С�������㷨
 */

package algs.textbook.ch04.s4_3;

import com.wangsg.algs.In;
import com.wangsg.algs.MinPQ;
import com.wangsg.algs.Queue;

import edu.princeton.cs.algs4.StdOut;

public class LazyPrimMST {
	private boolean[] marked;                //������Ƕ����Ƿ��Ѿ������ʹ����Ƿ��Ѿ������У�
	private Queue<Edge> mst;              //��¼��С�������ı�
	private MinPQ<Edge> pq;                 //���бߣ�����ʧЧ�ıߣ�
	
	public LazyPrimMST(EdgeWeightedGraph G){
		marked = new boolean[G.V()];
		pq = new MinPQ<Edge>();
		mst = new Queue<Edge>();
		visit(G,0);
		while(!pq.isEmpty()){
			Edge e = pq.delMin();            //ѡȡ��Ȩ����С�ı�
			int v = e.either();
			int w = e.other(v);
			if(marked[v] && marked[w]) continue;         //��ʧЧ����ʱ��������
			mst.enqueue(e);                              //������ӵ�����
			if(!marked[v])             visit(G,v);       //��������ӵ����У�����Ѱ�Һ����
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
