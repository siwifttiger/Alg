/*
 * 关键路径
 */

package algs.textbook.ch04.s4_4;

import java.io.File;

import com.wangsg.algs.In;
import com.wangsg.algs.StdIn;

import edu.princeton.cs.algs4.StdOut;

public class CMP {
	
	
	public EdgeWeightedDigraph createNetwork(In in){
		
		int N = in.readInt();
		int s = 2*N, t = 2*N+1;
		//StdOut.println(N);
		String temp1 = in.readLine();
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N + 2);
		for(int i = 0; i < N; i++){
			String temp = in.readLine();
			//StdOut.println(temp);
			String[] a = temp.split(" ");
			double duration = Double.parseDouble(a[0]);
			//i为任务开始的起点，i+N为任务结束的终点，权重为持续时间
			G.addEdge(new DirectedEdge(i, i+N, duration));
			//添加一条从s到任务起点的边，权重为0
			G.addEdge(new DirectedEdge(s, i, 0.0));
			//添加一条从任务结束终点到t的边，权重为0
			G.addEdge(new DirectedEdge(i+N, t, 0.0));
			for(int j = 1 ; j < a.length; j++){
				int successor = Integer.parseInt(a[j]);
				//任务之间设置优先级
				G.addEdge(new DirectedEdge(i+N, successor, 0.0));
			}
		}
		return G;
		
	}
	

	public static void main(String[] args) {
		In in = new In("E:"+
				File.separator+"workspace"
				+File.separator+"AlgorithmPractices"+File.separator
				+"src"+File.separator
				+"algs"+File.separator
				+"textbook"+File.separator
				+"ch04"+File.separator
				+"s4_4"+File.separator
				+"jobsPC.txt");
		CMP cmp = new CMP();
		EdgeWeightedDigraph G = cmp.createNetwork(in);
		AcyclicLP lp = new AcyclicLP(G, G.V()-2);
        // print results
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < (G.V()-2) / 2; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i+(G.V()-2)/2));
        }
        StdOut.printf("Finish time: %7.1f\n", lp.distTo(G.V()-1));
		
	}

}
