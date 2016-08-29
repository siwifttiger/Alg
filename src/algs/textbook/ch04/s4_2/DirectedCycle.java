package algs.textbook.ch04.s4_2;

import com.wangsg.algs.In;
import com.wangsg.algs.Stack;
import com.wangsg.algs.StdOut;

public class DirectedCycle {
	private int edgeTo[];
	private Stack<Integer> cycle;
	private boolean[] marked;
	private boolean[] onStack;          //记录递归调用栈上的所有定点，调用结束就释放
	
	public DirectedCycle(Digraph G){
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		/*for(int v = 0; v < G.V(); v++){
			if(!marked[v]) dfs(G,v);
		}*/
	}
	
	private void dfs(Digraph G, int v){
		marked[v] = true;
		onStack[v] = true;
		for(int w:G.adj(v)){
			if(this.hasCycle()) return;
			else if(!marked[w]){
				edgeTo[w] = v;
				dfs(G,w);
			}
			else if(onStack[w]){
				cycle = new Stack<Integer>();
				for(int x  = v; x !=w; x = edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	
	
	public boolean hasCycle(){
		return cycle != null;
	}
	
	public void findCycle(Digraph G){
		for(int v = 0; v < G.V(); v++){
			if(!marked[v])
				dfs(G,v);
		}
	}
	

	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	
	
	public static void main(String[] args) {
		Digraph G =new Digraph(new In(args[0]));
		DirectedCycle dfs = new DirectedCycle(G);
		
		dfs.findCycle(G);
		if(dfs.hasCycle()){
			for(int w:dfs.cycle())
			StdOut.print(w+" ");
		}
	

	}

}
