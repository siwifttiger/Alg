package algs.textbook.ch04.s4_4;

import java.io.File;

import com.wangsg.algs.In;
import com.wangsg.algs.IndexMinPQ;
import com.wangsg.algs.Stack;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

import algs.textbook.ch04.s4_1.Graph;

public class DijkstraSP {
	private DirectedEdge[] edgeTo;   //edgeTo[v]表示链接v和其父节点的边
	private double[] distTo;
	private IndexMinPQ<Double> edges;
	
	
	public DijkstraSP(EdgeWeightedDigraph G,int s){
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		for(int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		edges = new IndexMinPQ<Double>(G.V());
		distTo[s] = 0.0;
		edges.insert(s,0.0);
		while(!edges.isEmpty()){
			relax(G,edges.delMin());
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v){
		for(DirectedEdge e : G.adj(v)){
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(edges.contains(w))   edges.change(w, distTo[w]);
				else                    edges.insert(w, distTo[w]);
			}
		}
	}
	
	public double distTo(int v){
		return distTo[v];
	}
	
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);
		return path;
	}
	
	public static void main(String args[]){
		EdgeWeightedDigraph G;
		
		G = new EdgeWeightedDigraph (new In("E:"+
				File.separator+"workspace"
				+File.separator+"AlgorithmPractices"+File.separator
				+"src"+File.separator
				+"algs"+File.separator
				+"textbook"+File.separator
				+"ch04"+File.separator
				+"s4_4"+File.separator
				+"tinyEWG.txt"));;
		int s = StdIn.readInt();
		DijkstraSP sp = new DijkstraSP(G, s);
		for(int t = 0; t < G.V(); t++){
			StdOut.print(s + " to " + t);
			StdOut.printf(" (%4.2f): ", sp.distTo(t));
			if(sp.hasPathTo(t)){
				for(DirectedEdge e:sp.pathTo(t)){
					StdOut.print(e + " ");
				}
			}
			StdOut.println();
		}
	}
}
