/*
 * 接受一个起点s作为参数，
 * 计算从s到与s连通的每个顶点的路径
 */

package algs.textbook.ch04.s4_1;

import java.io.File;
import java.nio.file.Path;

import com.wangsg.algs.In;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstPath {
	boolean[] marked;
	private int[] edgeTo;
	private static int s;            //起点
	
	public DepthFirstPath(Graph g, int s){
		this.s = s;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		dfs(g,s);
	}
	
	//深度优先遍历寻找路径
	public void dfs(Graph g, int v){
		marked[v] = true;
		for(int w : g.adj(v)){
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(g,w);
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
		int source = Integer.parseInt(args[0]);
		DepthFirstPath search = new DepthFirstPath(g, source);
		for(int v = 0; v < g.V();v++){
			StdOut.print(source + " to " + v + ": ");
			for(int x:search.pathTo(v)){
				if(x == s) StdOut.print(x);
				else StdOut.print("-"+x);
			}
			StdOut.println();
		}
	}
}
