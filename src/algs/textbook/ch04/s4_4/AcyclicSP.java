/*
 * 加权无环图的最短路径算法
 */

package algs.textbook.ch04.s4_4;

import java.io.File;

import com.wangsg.algs.In;
import com.wangsg.algs.Stack;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

import algs.textbook.ch04.s4_2.Toplogic;

public class AcyclicSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	public AcyclicSP(EdgeWeightedDigraph G, int s){
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		
		for(int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] =0.0;
		//一定是无环图才行
		Toplogic top = new Toplogic(G);
		for(int v:top.order()){
			relax(G,v);
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v){
		for(DirectedEdge e:G.adj(v)){
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()){
				edgeTo[w] = e;
				distTo[w] = distTo[v] + e.weight();
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
		for(DirectedEdge e = edgeTo[v]; e!= null; e = edgeTo[e.from()]){
			path.push(e);
		}
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
				+"tinyEWDAG.txt"));;
		int s = StdIn.readInt();
		AcyclicSP sp = new AcyclicSP(G, s);
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
