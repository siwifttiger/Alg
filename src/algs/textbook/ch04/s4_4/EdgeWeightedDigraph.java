package algs.textbook.ch04.s4_4;

import com.wangsg.algs.Bag;
import com.wangsg.algs.In;

public class EdgeWeightedDigraph {
	private final int V;
	private int E;
	private Bag<DirectedEdge>[] adj;
	private int[] indegree;
	
	public EdgeWeightedDigraph(int V){
		this.V = V;
		adj = (Bag[])new Bag[V];
		indegree = new int[V];
		for(int v = 0; v < V; v++){
			adj[v] = new Bag<DirectedEdge>();
		}
	}
	
	public EdgeWeightedDigraph(In in){
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			DirectedEdge edge = new DirectedEdge(v, w, weight);
			addEdge(edge);
		}
	}
	
	public void addEdge(DirectedEdge e){
		int v = e.from();
		int w = e.to();
		adj[v].add(e);
		indegree[w]++;
		E++;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for(int v = 0; v < V; v++){
			for(DirectedEdge e: adj[v]){
				bag.add(e);
			}
			
		}
		return bag;
	}
	
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		for(int v = 0; v < V; v++){
			for(DirectedEdge e:adj[v]){
				stringBuilder.append(e+ " ");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
