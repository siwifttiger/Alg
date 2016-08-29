package algs.textbook.ch04.s4_1;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class DegreesOfSeparation {

	public static void main(String[] args) {
		SymbolGraph sg = new SymbolGraph(args[0], args[1]);
		Graph G = sg.graph();
		String source = args[2];
		if(!sg.contains(source))
			StdOut.println(source + " is not in the database");
		int s = sg.indexOf(source);
		BreadFirstPath bfs = new BreadFirstPath(G, s);
		while(StdIn.hasNextLine()){
			String sink = StdIn.readLine();
			if(sg.contains(sink)){
				int v = sg.indexOf(sink);
				if(bfs.hasPathTo(v)){
					for(int w:bfs.pathTo(v)){
						StdOut.println("	"+sg.name(w));
					}
					StdOut.println("	The Bacon number of "+sink +" is " +bfs.pathLen(v)/2);
				}
				else{
					StdOut.println("Not connected");
				}
			}
			else StdOut.println("Not in the database");
		}

	}

}
