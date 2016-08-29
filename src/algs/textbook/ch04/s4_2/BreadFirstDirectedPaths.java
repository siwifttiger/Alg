/*
 * 广度优先遍历
 * 找最短路径
 */
package algs.textbook.ch04.s4_2;

import com.wangsg.algs.In;
import com.wangsg.algs.Queue;
import com.wangsg.algs.Stack;
import com.wangsg.algs.StdOut;

public class BreadFirstDirectedPaths {
	private int edgeTo[];
	private boolean[] marked;
	private static int s;
	
	public BreadFirstDirectedPaths(Digraph G, int s){
		this.s = s;
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		bfs(G,s);
	}
	
	private void bfs(Digraph G, int v){
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(v);
		marked[v] = true;
		while(!q.isEmpty()){
			int top = q.dequeue();
			for(int w:G.adj(top)){
				if(!marked[w]){
					marked[w] = true;
					edgeTo[w] = top;
					q.enqueue(w);
				}
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
		BreadFirstDirectedPaths dfs = new BreadFirstDirectedPaths(G, Integer.parseInt(args[1]));
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
