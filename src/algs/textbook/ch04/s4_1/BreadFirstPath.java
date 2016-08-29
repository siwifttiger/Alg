/*
 * 利用广度优先遍历
 * 从某一个起点出发
 * 找出所有与该起点连通的顶点的
 * 最短路径
 */

package algs.textbook.ch04.s4_1;
import java.io.File;
import com.wangsg.algs.In;
import com.wangsg.algs.StdOut;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

public class BreadFirstPath {
	private boolean[] marked;
	private static int s;
	private int[] edgeTo;
	
	
	public BreadFirstPath(Graph g, int s){
		this.s = s;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		bfs(g,s);
	}
	
	public void bfs(Graph g, int s){
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		marked[s] = true;
		while(!q.isEmpty()){
			int v = q.dequeue();
			for(int w:g.adj(v)){
				if(!marked[w]){
					marked[w] = true;
					edgeTo[w] = v;
					q.enqueue(w);
				}
			}
		}
	} 
	
	public boolean hasPathTo(int v){
		return marked[v];
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
	
	public int pathLen(int v){
		if(!hasPathTo(v)) return 0;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x]){
			
			path.push(x);
		}
		path.push(s);
		return path.size()-1;
		
	}
	
	public static void main(String[] args){

		Graph g = new Graph(new In("E:"+
				File.separator+"workspace"
				+File.separator+"AlgorithmPractices"+File.separator
				+"src"+File.separator
				+"algs"+File.separator
				+"textbook"+File.separator
				+"ch04"+File.separator
				+"s4_1"+File.separator
				+"tinyCG.txt"));
		int source = StdIn.readInt();
		BreadFirstPath search = new BreadFirstPath(g, source);
		
		for(int v = 0; v < g.V();v++){
			StdOut.print(source + " to " + v + ": ");
			if(search.pathLen(v) != 0) StdOut.println("path length is " + search.pathLen(v));
			for(int x:search.pathTo(v)){
				if(x == s) StdOut.print(x);
				else StdOut.print("-"+x);
			}
			StdOut.println();
		}
	}
}
