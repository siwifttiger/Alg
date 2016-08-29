package algs.textbook.ch03.s3_3;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value>{
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node{
		Key key;
		Value val;
		Node left, right;
		int N;
		boolean color;

		Node(Key key,Value val,int N,boolean color){
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	private Node root;

	private int size(Node x){
		if(x == null) return 0;
		return x. N;
	}
        
	public int size(){
		return size(root);
	}

	public boolean isEmpty(){
		return root == null;	
	}


	public boolean isRed(Node x){
		if(x == null) return false;
		return x.color == RED;
	}

	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;   //保留原来的颜色
		//指向h的链接变为红色
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left)+size(h.right) + 1;
		return x;
	}

	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	//改变左右链接的颜色并且将指向自己的链接改为红色
	private void flipColor(Node h){
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	public boolean contains(Key key){
		return get(key) != null;
	}

	public Value get(Key key){
	     return get(root,key);	
	}
	


	private Value get(Node x, Key key){
		while(x != null){
			int cmp = key.compareTo(x.key);
			if(cmp < 0) x=x.left;
			else if(cmp > 0) x=x.right;
			else return x.val;
		}
		return null;
	}

	//私有辅助函数，插入
	private Node put(Node h,Key key, Value val){
		//新节点和父节点用红链接相连
		if(h == null)
			return new Node(key,val,1,RED);
		int cmp = key.compareTo(h.key);
		if(cmp < 0) h.left = put(h.left, key,val);
		else if(cmp > 0) h.right = put(h.right, key, val);
		else h.val = val;
		
		//改变颜色
		if(isRed(h.right) && !isRed(h.left)) h=rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) h=rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) flipColor(h);
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}

	public void put(Key key, Value val){
		root = put(root,key,val);
		root.color = BLACK;
	}

	public Key min(){
		return min(root).key;
	}

	private Node min(Node x){
		if(x.left == null) return x;
		else	return min(x.left);
	}

	public Key max(){
		return max(root).key;
	}

	private Node max(Node x){
		if(x.right == null) return x;
		else return min(x.right);
	}

	private Node balance(Node h){
		if(isRed(h.right)) h=rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) h=rotateRight(h);
		if(isRed(h.left) && isRed(h.left)) flipColor(h);
		h.N = 1+size(h.left) + size(h.right);
		return h;
	}
	

	//私有辅助函数
	//
 	private Node moveRedLeft(Node h){
		flipColor(h);
		//如果右子节点不是2-节点
		//就进行旋转操作，
		//旋转之后左子节点会是一个3-节点
		if(isRed(h.right.left)){
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			//个人感觉这里可以不用进行颜色转换，
			//因为最后还是要进行平衡操作
			//早一步也没事，性能上可能更佳
			flipColor(h);
		}
		return h;
	}

	private Node deleteMin(Node h){
		if(h.left == null) return null;
		//这个判断是为了判断左子节点是否为2-节点
		if(!isRed(h.left) && !isRed(h.left.left))
			//如果是2-节点，就要将左子节点变为3-节点或者是4-节点
			h = moveRedLeft(h);	
		h.left = deleteMin(h.left);
		//最后平衡
		return balance(h);
	}

	public void deleteMin(){
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMin(root);
		if(!isEmpty()) root.color = BLACK;
	}


	//道理类似，不过左子节点的旋转只要一次就可以
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColor(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColor(h);
        }
        return h;
    }

    private Node deleteMax(Node h){
    	if(isRed(h.left))
		h = rotateRight(h);
	
	if(h.right == null)
		return null;
	//判断右子节点是否为2-节点
	if(!isRed(h.right) && !isRed(h.right.left))
		h = moveRedRight(h);

	h.right = deleteMax(h.right);
	//最后平衡
	return balance(h);	
    }

    public void deleteMax(){
    	if(!isRed(root.left) && !isRed(root.right))
		root.color = RED;
	
	root = deleteMax(root);
	if(!isEmpty()) root.color = BLACK;	
    }

	
  
   

    private Node delete(Node h, Key key){
    	if(key.compareTo(h.key) < 0) {
		if(!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);
		h.left = delete(h.left,key);
	}
	else{
		if(isRed(h.left))
			h = rotateRight(h);
		if(key.compareTo(h.key) == 0 && (h.right == null))
			return null;
		if(!isRed(h.right) && !isRed(h.right.left))
			h = moveRedRight(h);
		if(key.compareTo(h.key) == 0){
			Node x = min(h.right);
			h.key = x.key;
			h.val = x.val;
			h.right = deleteMin(h.right);
		}
		else h.right = delete(h.right,key);
	}
	return balance(h);
    }

    public void delete(Key key){
    	if(!contains(key)) return;

	if(!isRed(root.left) && !isRed(root.right))
		root.color = RED;
	root = delete(root,key);
	if(!isEmpty()) root.color = BLACK;
    }

	public int rank(Key key){
		return rank(root,key);
	}

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
	RedBlackBST<String,Integer> bst = new RedBlackBST<String,Integer>();
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

