/*  
 *用二叉搜索树实现的符号表
 * 
 */

package algs.textbook.ch03.s3_2;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

import algs.textbook.ch03.s3_1.BinarySearchST;

import com.wangsg.algs.Queue;

public class BST<Key extends Comparable<Key>,Value>{
	private class Node{
		Key key;
		Value value;
		Node left,right;
		int N;     //以该节点为根节点的子树所有节点数量
		public Node(Key key,Value val,  int num){
			this.key = key;
			this.value = val;
			this.N = num;
	}
}
	private Node root;

	//大小
	public int size(){
		return size(root);
	}
	//私有辅助函数
	private int size(Node x){
		if(x==null) return 0;
		else        return x.N;
	}

	//查找
	public Value get(Key key){
		return get(root,key);
	}

	//私有辅助函数
	private Value get(Node x, Key key)
{
	if(x == null) return null;
	int cmp = key.compareTo(x.key);
	if(cmp < 0) return get(x.left,key);
	else if(cmp > 0) return get(x.right,key);
	else return x.value;
}

	//插入
	public void put(Key key,Value val)
{
	root = put(root,key,val);
}
	
	//私有辅助函数
	//返回插入后的根节点
	private Node put(Node x, Key key, Value val)
{
	if(x == null) return new Node(key,val,1);
	int cmp = key.compareTo(x.key);
	if(cmp < 0)  x.left=put(x.left,key,val);
	else if(cmp > 0)  x.right=put(x.right,key,val);
	else x.value = val;
	x.N = size(x.left) + size(x.right) + 1;
	return x;
}


	//删除
	public void delete(Key key){
		root = delete(root,key);
	}
	
	//私有构造函数，返回删除后的根节点
	private Node delete(Node x, Key key){
		if(x == null)  return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return delete(x.left,key);
		else if(cmp > 0) return delete(x.right,key);
		else{
			if(x.left == null) return x.right; //只有一个右节点的情况
			if(x.right == null) return x.left; //只有左节点的情况
			Node t = x;
			x = min(t.right);
			//重新找到左右子树
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left)+size(x.right)+1;
		return x;
	} 

	//包含
	public boolean contains(Key key){
		return get(key) != null;
	}

	//是否为空
	public boolean isEmpty(){
		return size()==0;
	}


	//最小
	public Key min(){
		return min(root).key;
	}
	
	//私有辅助函数
	private Node min(Node x){
		if(x.left == null) return x;
		return min(x.left);
	}

	
	//最大
	public Key max(){
		return max(root).key;	
	}

	
	//私有辅助函数
	private Node max(Node x){
		if(x.right == null) return x;
		return max(x.right);
	}
	//删除最小
	public void deleteMin(){
		root = deleteMin(root);
	}
	
	//私有辅助函数,返回删除后的根节点
	private Node deleteMin(Node x){
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left)+size(x.right)+1;
		return x;
	}
	//删除最大
	public void deleteMax(){
		root = deleteMax(root);
	}
	
	//私有辅助函数 
	private Node deleteMax(Node x){
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left)+size(x.right)+1;
		return x;
	}
	//向上去整
	public Key ceiling(Key key){
		Node x = ceiling(root,key);
		if(x == null) return null;
		return x.key;
	}

	//私有辅助函数
	private Node ceiling(Node x, Key key)
{
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		if(cmp > 0) return ceiling(x.right,key);
		Node t = ceiling(x.left,key);
		if(t != null) return t;
		else return x;
}
	//向下取整
	public Key floor(Key key){
		Node x = floor(root,key);
		if(x == null) return null;
		return x.key;
	}
	
	//私有辅助函数
	private Node floor(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		//如果相等，直接返回
		if(cmp == 0) return x;
		//如果要找的值小于当前节点的值
		//就在这个节点的左子树中查找
		if(cmp < 0) return floor(x.left,key);
		//如果要找的值大于当前节点
		//先在右子树中查找
		//如果能找到就返回
		//找不到就说明右子树中的所有
		//值都大于要查找的键
		//所以就返回当前节点
		Node t = floor(x.right,key);
		if(t != null) return t;
		else return x;
	}
	//排名
	public int rank(Key key){
		return rank(root,key);
	}

	//私有辅助函数
	private int rank(Node x, Key key){
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return rank(x.left,key);
		else if(cmp > 0) return 1+size(x.left)+rank(x.right,key);
		else return size(x.left);
	}


	//选择排名为k的键值
	public Key select(int k){
		return select(root,k);
	}

	//私有辅助函数
	private Key select(Node x, int k){
		if(x == null) return null;
		int t = size(x.left);
		if( t < k) return select(x.left,k);
		else if(t > k) return select(x.right, k-t-1);
		else return x.key;
	}

	//lo到hi之间的大小
	public int size(Key lo, Key hi)
	{
		
        	if (lo.compareTo(hi) > 0) return 0;
        	if (contains(hi)) return rank(hi) - rank(lo) + 1;
        	else              return rank(hi) - rank(lo);
	}

	//迭代器
	public Iterable<Key> keys(){
		return keys(min(), max());	
	}

	//迭代器
	public Iterable<Key> keys(Key lo,Key hi)
	{
		Queue<Key> q = new Queue<Key>();
		keys(root,q,lo,hi);
		return q;
	}
	
	private void keys(Node x, Queue<Key> q, Key lo, Key hi){
		if(x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0) keys(x.left,q,lo,hi);
		if(cmplo <= 0 && cmphi >=0) q.enqueue(x.key);
		if(cmphi > 0) keys(x.right,q,lo,hi);
	}

	public static void main(String[] args){
		BST<String,Integer> bst = new BST<String,Integer>();
		int minlen = Integer.parseInt(args[0]);
		while(!StdIn.isEmpty()){
			String word = StdIn.readString();
			if(!bst.contains(word)) bst.put(word,1);
			else bst.put(word,bst.get(word) + 1);
		}
		String max = " ";
		bst.put(max,0);
		for(String t : bst.keys()){
			if(bst.get(t) > bst.get(max))
				max = t;
		}
		StdOut.println(max + " " + bst.get(max));
	}


}
