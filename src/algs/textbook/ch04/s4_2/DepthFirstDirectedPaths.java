/*
 * 有向图
 * 从s到v是否有一条路径
 */

package algs.textbook.ch04.s4_2;

import com.wangsg.algs.In;
import com.wangsg.algs.Stack;
import com.wangsg.algs.StdOut;

public class DepthFirstDirectedPaths {
	private int edgeTo[];
	private boolean[] marked;
	private static int s;
	
	public DepthFirstDirectedPaths(Digraph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G,s);
	}
	
	private void dfs(Digraph G, int v){
		marked[v] = true;
		for(int w:G.adj(v)){
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(G,w);
			}
		}
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x]){
			path.push(x);
		}
		path.push(s);
		return path;
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public static void main(String[] args) {
		Digraph G =new Digraph(new In(args[0]));
		DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, Integer.parseInt(args[1]));
		for(int v = 0; v < G.V(); v++){
			StdOut.print(s+" to " +v +":");
			if(dfs.hasPathTo(v)){
				
				for(int w:dfs.pathTo(v)){
					StdOut.print(w+" ");
				}
				StdOut.println();
			}
			else StdOut.println("Not connected");
		}

	}

}
