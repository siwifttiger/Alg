package algs.textbook.ch04.s4_2;


import java.util.TreeMap;

import com.wangsg.algs.In;
import com.wangsg.algs.StdIn;

import edu.princeton.cs.algs4.StdOut;

public class SymbolDigraph {
	private TreeMap<String,Integer> st;
	private String[] keys;
	private int[] id;
	private Digraph G;
	
	//文件名和分隔符
	public SymbolDigraph(String stream, String sp){
		st = new TreeMap<String,Integer>();
		In in = new In(stream);
		while(in.hasNextLine()){
			String[] a= in.readLine().split(sp);
			for(int i = 0; i < a.length; i++){
				if(!st.containsKey(a[i]))
					st.put(a[i], st.size());
			}
		}
		keys = new String[st.size()];
		for(String name:st.keySet()){
			keys[st.get(name)] = name;
		}
		
		G = new Digraph(st.size());
		in = new In(stream);
		while(in.hasNextLine()){
			String[] a = in.readLine().split(sp);
			int v = st.get(a[0]);
			for(int i = 0; i < a.length; i++){
				G.addEdge(v, st.get(a[i]));
			}
		}
	}
	
	public boolean contains(String s){
		return st.containsKey(s);
	}
	
	public int indexOf(String s){
		return st.get(s);
	}
	
	public String name(int v){
		return keys[v];
	}
	
	public Digraph graph(){
		return G;
	}
	
	   public static void main(String[] args) {
	        String filename  = args[0];
	        String delimiter = args[1];
	        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
	        Digraph graph = sg.graph();
	        while (StdIn.hasNextLine()) {
	            String source = StdIn.readLine();
	            if (sg.contains(source)) {
	                int s = sg.indexOf(source);
	                for (int v : graph.adj(s)) {
	                    StdOut.println("   " + sg.name(v));
	                }
	            }
	            else {
	                StdOut.println("input not contain '" + source + "'");
	            }
	        }
	    }
}
