package algs.textbook.ch04.s4_2;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class Toplogic {
	private Iterable<Integer> order;
	
	public Toplogic(Digraph G){
		DirectedCycle cycleFinder = new DirectedCycle(G);
		if(!cycleFinder.hasCycle()){
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public boolean isDAG(){
		return order != null;
	}
	
	public Iterable<Integer> order(){
		return order;
	}
	
	   public static void main(String[] args) {
	        String filename  = args[0];
	        String delimiter = args[1];
	        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
	        Toplogic tpg = new Toplogic(sg.graph());
	        for(int v:tpg.order()){
	        	StdOut.println(sg.name(v));
	        }
	      
	    }


}
