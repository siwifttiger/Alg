/*
 * 检测无向图是否有环
 * 不能检测自环
 * 
 */

package algs.textbook.ch04.s4_1;

import java.io.File;
import java.util.Stack;

import com.wangsg.algs.In;

import edu.princeton.cs.algs4.StdOut;

public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	 public Cycle(Graph G){
		 marked = new boolean[G.V()];
		 edgeTo = new int[G.V()];
		 for(int s = 0; s < G.V(); s++){
			 if(!marked[s]){
				 dfs(G,s,s);
			 }
		 }
	 }
	 
	 public void dfs(Graph G, int v, int u){
		 marked[v] = true;
		 
		 for(int w:G.adj(v)){
			 if(cycle != null) return;
			 
			 if(!marked[w]){
				 edgeTo[w] = v;
				 dfs(G,w,v);
			 }
			 /*
			  * u是v的父节点，w是v的子节点
			  * 如果w在访问v之前已经被访问过了，
			  * 并且w不是u，说明在搜索到v的路径中，经过了w
			  * 说明存在一条w-v的路径，v-w也存在一条路径
			  * 所以有环
			  */
			 else if(w != u){
				 hasCycle = true;
				  cycle = new Stack<Integer>();
				 for(int x = v; x != w; x = edgeTo[x]){
					 cycle.push(x);
				 }
				 cycle.push(w);
				 cycle.push(v);
			 }
		 }
	 }
	 
	 public boolean isCycle(){
		 return hasCycle;
	 }
	 
	 public Iterable<Integer> cycle(){
		 return cycle;
	 }
	
	
	
	public static void main(String[] args) {
		Graph G = new Graph(new In("E:"+
				File.separator+"workspace"
				+File.separator+"AlgorithmPractices"+File.separator
				+"src"+File.separator
				+"algs"+File.separator
				+"textbook"+File.separator
				+"ch04"+File.separator
				+"s4_1"+File.separator
				+"tinyG.txt"));
		
		Cycle c = new Cycle(G);
		if(c.isCycle()) StdOut.println("Cycle");
		for(int w : c.cycle()){
			StdOut.println(w + " ");
		}

	}

}
