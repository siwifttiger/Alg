/*
 * 计算强连通分量
 * 算法思想，对于一幅有向图G，先求出G的反向图GR,再求出GR的逆后序
 * 根据求出的逆后序的顺序对G进行深度优先遍历，这样每一个DFS树就是一个强连通分量
 * 完备性的证明略，每个和s强连通的顶点v都能够被dfs(G,s)访问到
 * 正确性的证明，通过dfs(G,s)访问到的顶点v都和s强连通
 * 因为在G中dfs的顺序是按照GR的逆后序来访问的
 * 在G中先访问到s意味着在GR中dfs(G,v)必然比dfs(G,s)要先结束
 * 又因为GR中存在一条从v到s的路径，所以上述表明在GR中存在一条从s到v的路径，
 * 所以s和v是强连通的
 */

package algs.textbook.ch04.s4_2;

import com.wangsg.algs.In;
import com.wangsg.algs.StdOut;

import edu.princeton.cs.algs4.Queue;

public class KosarajuSCC {
	private boolean[] marked;
	private int[]   id;
	private int count;
	
    public KosarajuSCC(Digraph G) {

        // compute reverse postorder of reverse graph
        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());

        // run DFS on G, using reverse postorder to guide calculation
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int v : dfs.reversePost()) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }

        // check that id[] gives strong components
      
    }

    // DFS on graph G
    private void dfs(Digraph G, int v) { 
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }
	
	
	public boolean scc(int v, int w){
		return id[v] == id[w];
	}
	
	public int count(){
		return count;
	}
	
	public int id(int v){
		return id[v];
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		KosarajuSCC cc = new KosarajuSCC(G);
		int m = cc.count();
		StdOut.println(m+" strong components");
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
