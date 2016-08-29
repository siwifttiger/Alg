/*
 * 有向图API
 */
package algs.textbook.ch04.s4_2;
import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Stack;

import com.wangsg.algs.Bag;
import com.wangsg.algs.In;
import com.wangsg.algs.StdOut;

public class Digraph {
	private static final String NEWLINE = System.getProperty("line separator");
	
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	private int[] indegree;
	
	public Digraph(int V){
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = (Bag<Integer>[]) new Bag[V];
		for(int i = 0; i<V; i++){
			adj[i] = new Bag<Integer>();
		}
	}
	
    public Digraph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
            indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w); 
            }
        }
        catch (NoSuchElementException e) {
            throw new InputMismatchException("Invalid input format in Digraph constructor");
        }
    }
	
	//拷贝构造函数
	public Digraph(Digraph Dig){
		this(Dig.V());
		this.E = Dig.E();
		for(int i = 0; i < V; i++){
			this.indegree[i] = Dig.indegree(i);
		}
		for(int v = 0; v < V; v++){
			Stack<Integer> reverse = new Stack<Integer>();
			for(int w:Dig.adj(v)){
				reverse.push(w);
			}
			for(int w:reverse){
				this.adj[v].add(w);
			}
		}
		
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public int indegree(int v){
		return indegree[v];
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);
		indegree[w]++;
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public Digraph reverse(){
		Digraph R = new Digraph(V);
		for(int v = 0; v < V; v++){
			for(int w:adj(v)){
				R.addEdge(w,v);
			}
		}
		return R;
	}
	
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    
    public static void main(String[] args){
    	In in  = new In(args[0]);
    	Digraph dig = new Digraph(in);
    	StdOut.println(dig);
    }
	
}
