/*
 * 利用深度优先遍历来检测图中的连通分量
 */
package algs.textbook.ch04.s4_1;

import java.io.File;

import com.wangsg.algs.In;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class CC {
	private boolean[] marked;
	private int[] id;      //索引数组
	private int count;    //连通分量数目
	private int[] size;   //size[count] = 第count个连通分量的顶点个数
	
	
	public CC(Graph g){
		marked = new boolean[g.V()];
		id = new int[g.V()];
		size = new int[g.V()];
		for(int v = 0; v < g.V(); v++){
			if(!marked[v]){
				dfs(g,v);
				count++;
			}
		}
	}
	
	private void dfs(Graph g, int s){
		marked[s] = true;
		id[s] = count;
		size[count]++;
		for(int w:g.adj(s)){
			if(!marked[w]){
				marked[w] = true;
				dfs(g,w);
			}
		}
	}
	
	public int id(int v){
		return id[v];
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int v, int w){
		return id[v] == id[w];
	}
	
	public int size(int count){
		return size[count];
	}
	
	public static void main(String[] args){
		In in = new In("E:"+
				File.separator+"workspace"
				+File.separator+"AlgorithmPractices"+File.separator
				+"src"+File.separator
				+"algs"+File.separator
				+"textbook"+File.separator
				+"ch04"+File.separator
				+"s4_1"+File.separator
				+"tinyG.txt");
		Graph G = new Graph(in);
		CC cc = new CC(G);
		int m = cc.count();
		StdOut.println(m + "components");
		
		Queue<Integer>[] components = (Queue<Integer>[])new Queue[m];
		for(int i = 0; i < m; i++){
			components[i] = new Queue<Integer>();
		}
		for(int v = 0; v < G.V(); v++){
			components[cc.id(v)].enqueue(v);
		}
		
		for(int i = 0; i < m; i++){
			for(int v:components[i]){
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}
	
}
