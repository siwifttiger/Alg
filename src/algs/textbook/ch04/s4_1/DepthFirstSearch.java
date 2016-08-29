/*
 * �����s��ʼ���ҵ����к����s��ͨ�Ķ���
 * ������ȱ���
 */

package algs.textbook.ch04.s4_1;

public class DepthFirstSearch {
	private boolean[] marked;
	private int count;
	
	public DepthFirstSearch(Graph g, int s){
		marked = new boolean[g.V()];
		dfs(g,s);
	}
	
	public void dfs(Graph g,int s){
		marked[s] = true;
		count++;
		for(int w: g.adj(s)){
			if(!mark(w))
				dfs(g,w);
		}
	}
	
	public boolean mark(int v){
		return marked[v];
	}
	
	public int count(){
		return count;
	}
	
	
}
