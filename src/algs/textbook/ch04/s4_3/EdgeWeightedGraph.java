/*
 * 加权无向图的实现
 */
package algs.textbook.ch04.s4_3;

import com.wangsg.algs.Bag;
import com.wangsg.algs.In;

public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private Bag<Edge> adj[];     //存放顶点的关联边
	
	public EdgeWeightedGraph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[])new Bag[V];
		for(int i = 0; i < V; i++){
			adj[i] = new Bag<Edge>();
		}
	}
	
	public EdgeWeightedGraph(In in){
		this(in.readInt());
		int E = in.readInt();
		int v, w;
		double weight;
		for(int i = 0; i < E; i++){
			v = in.readInt();
			w = in.readInt();
			weight =in.readDouble();
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}
	
	public void addEdge(Edge e){
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public Iterable<Edge> edges(){
		Bag<Edge> list = new Bag<Edge>();
		for(int v = 0; v < V; v++){
			for(Edge e:adj(v)){
				if(e.other(v) > v)
					list.add(e);
			}
		}
		return list;
	}
}
