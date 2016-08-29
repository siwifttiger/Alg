package algs.textbook.ch04.s4_1;

import com.wangsg.algs.Bag;
import com.wangsg.algs.In;

public class Graph {
	private final int V;       //定点数量
	private int E;             //边的数量
	private Bag<Integer> [] adj;
	public Graph(int V){
		this.V = V;
		adj = (Bag<Integer>[])new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	
	public Graph(In in){
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v,w);
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	//添加边，无向图
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	//返回该顶点的邻接表
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public String toString(){
		String s = V + " vertices, " + E + " edges\n";
		for(int v = 0; v < V; v++){
			s += v+": ";
			for(int w : this.adj(v)){
				s += w + " ";
				s+="\n";
			}
		}
		return s;
	}
}
