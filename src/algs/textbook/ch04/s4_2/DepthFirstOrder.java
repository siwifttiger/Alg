package algs.textbook.ch04.s4_2;

import com.wangsg.algs.Queue;
import com.wangsg.algs.Stack;

public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	
	public DepthFirstOrder(Digraph G){
		marked = new boolean[G.V()];
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		
		for(int v = 0; v < G.V(); v++){
			if(!marked[v]) dfs(G,v);
		}
	}
	
	private void dfs(Digraph G, int v){
		pre.enqueue(v);
		marked[v] = true;
		for(int w:G.adj(v)){
			if(!marked[w]){
				dfs(G,w);
			}
		}
		post.enqueue(v);
		reversePost.push(v);
	}
	
	public Iterable<Integer> pre(){
		return pre;
	}
	
	public Iterable<Integer> post(){
		return post;
	}
	
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
}