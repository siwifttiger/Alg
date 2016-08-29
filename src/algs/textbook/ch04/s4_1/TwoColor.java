package algs.textbook.ch04.s4_1;

import java.io.File;

import com.wangsg.algs.In;

import edu.princeton.cs.algs4.StdOut;

public class TwoColor {
	private boolean[] colored;
	private boolean[] marked;
	private boolean isTwoColorble = true;
	
	
	public TwoColor(Graph G){
		marked = new boolean[G.V()];
		colored = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++){
			if(!marked[s]){
				dfs(G,s);
			}
		}
	}
	
	private void dfs(Graph G, int s){
		marked[s] = true;
		for(int w:G.adj(s)){
			if(!marked[w]){
				colored[w] = !colored[s];
				dfs(G,w);
			}
			else if(colored[w] == colored[s]) isTwoColorble = false;
		}
	}
	
	public boolean isTwoColor(){
		return isTwoColorble;
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
		
		TwoColor t = new TwoColor(G);
		if(t.isTwoColor()) StdOut.print("Two\n");

	}

}
