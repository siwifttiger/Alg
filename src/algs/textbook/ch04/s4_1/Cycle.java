/*
 * �������ͼ�Ƿ��л�
 * ���ܼ���Ի�
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
			  * u��v�ĸ��ڵ㣬w��v���ӽڵ�
			  * ���w�ڷ���v֮ǰ�Ѿ������ʹ��ˣ�
			  * ����w����u��˵����������v��·���У�������w
			  * ˵������һ��w-v��·����v-wҲ����һ��·��
			  * �����л�
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
