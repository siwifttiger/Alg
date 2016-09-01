package algs.textbook.ch04.s4_4;


import com.wangsg.algs.Queue;
import com.wangsg.algs.Stack;

public class BellmanFord {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private boolean[] onQ;
	private int cost;                                  //relax的调用次数
	private Queue<Integer> queue;
	private Iterable<DirectedEdge> cycle;             //判断图中是否有负权重环
	
	public BellmanFord(EdgeWeightedDigraph g, int s){
		distTo = new double[g.V()];
		edgeTo = new DirectedEdge[g.V()];
		onQ = new boolean[g.V()];
		queue = new Queue<Integer>();
		for(int v = 0; v < g.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		queue.enqueue(s);
		while(!hasNegativeCycle() && !queue.isEmpty()){
			int top = queue.dequeue();
			onQ[top] = false;
			relax(g,top);
		}
	}
	
	
	private void relax(EdgeWeightedDigraph G, int v){
		for(DirectedEdge e:G.adj(v)){
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()){
				edgeTo[w] = e;
				distTo[w] = distTo[v] + e.weight();
				if(!onQ[w]){
					queue.enqueue(w);
					onQ[w] = true;
				}
			}
			
		}
		//如果relax函数调用次数已经超过了顶点的数量，要考虑是否有负权重环
		if(cost++ % G.V() == 0){
			findNegativeCycle();
		}
	}
	
 
    public boolean hasNegativeCycle() {
        return cycle != null;
    }

 
    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    // by finding a cycle in predecessor graph
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }


    public double distTo(int v) {
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo[v];
    }

  

	
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);
		return path;
	}
	
}
