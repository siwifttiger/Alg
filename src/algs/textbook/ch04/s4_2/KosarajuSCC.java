/*
 * ����ǿ��ͨ����
 * �㷨˼�룬����һ������ͼG�������G�ķ���ͼGR,�����GR�������
 * ���������������˳���G����������ȱ���������ÿһ��DFS������һ��ǿ��ͨ����
 * �걸�Ե�֤���ԣ�ÿ����sǿ��ͨ�Ķ���v���ܹ���dfs(G,s)���ʵ�
 * ��ȷ�Ե�֤����ͨ��dfs(G,s)���ʵ��Ķ���v����sǿ��ͨ
 * ��Ϊ��G��dfs��˳���ǰ���GR������������ʵ�
 * ��G���ȷ��ʵ�s��ζ����GR��dfs(G,v)��Ȼ��dfs(G,s)Ҫ�Ƚ���
 * ����ΪGR�д���һ����v��s��·������������������GR�д���һ����s��v��·����
 * ����s��v��ǿ��ͨ��
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
