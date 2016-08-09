package algs.textbook.ch03.s3_1;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	Key[] keys;
	Value[] vals;
	private int N;
	final private static int CAPACITY =2;
	public BinarySearchST(){
		this(CAPACITY);
	}

	public BinarySearchST(int capacity){
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[])new Object[capacity];
		N=0;
	}
	
	private void resize(int len){
		Key[] k = (Key[]) new Comparable[len];
		Value[] v = (Value[]) new Object[len];
		for(int i = 0; i < N; i++){
			k[i] = keys[i];
			v[i] = vals[i];
		}
		keys = k;
		vals = v;
	}

	public boolean isEmpty(){
		return size() == 0;
	}
	
	public int size(){
		return N;
	}
	
	public Value get(Key key){
		if(isEmpty()) return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) return vals[i];
		else return null;
	}
	
	public void put(Key key, Value val){
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0){
			vals[i] = val;
			return;
		}
		if(N == keys.length) resize(2*N);
		for(int j = N; j > i; j--){
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i]= val;
		N++;
	}
	public boolean contains(Key key){
		return get(key) != null;
	}
	public int rank(Key key){
		int lo = 0;
		int hi = N - 1;
		while(hi >= lo){
			int mid = (lo + hi) /2;
			if(keys[mid].compareTo(key) == 0) return mid;
			else if(keys[mid].compareTo(key) < 0) lo = mid + 1;
			else hi = mid - 1;
		}
		return lo;
	}

	public void delete(Key key){
		int i = rank(key);
		//如果找不到就直接return
		if(i < N && keys[i].compareTo(key) != 0) return;
		//如果能找到就直接删除
		
		for(int j = i; j<N-1; j++){
			keys[j] = keys[j+1];
			vals[i] = vals[j+1];
		}
		N--;
		keys[N] = null;
		vals[N] = null;

	}

	public Key min(){
		return keys[0];
	}

	public Key max(){
		return keys[N-1];
	}
	
	//大于等于key的最小键
	public Key ceiling(Key key){
		int i = rank(key);
		return keys[i];
	}
	//小于等于key的最大键
	public Key floor(Key key){
		int i = rank(key);
		if(i <N && keys[i].compareTo(key) == 0) return keys[i];
		return keys[i-1];
	}

	//排名为k的键
	public Key select(int k){
		if(k < 0 || k > N) return null;
		return keys[k];	
	}

	public void deleteMin(){
		delete(min());
	}
	
	public void deleteMax(){
		delete(max());
	}

	public int size(Key lo, Key hi){
		if(lo.compareTo(hi) > 0) return 0;
		if(contains(hi)) return rank(hi) - rank(lo) + 1;
		else             return rank(hi) - rank(lo);	
	}

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new NullPointerException("first argument to size() is null"); 
        if (hi == null) throw new NullPointerException("second argument to size() is null"); 

        Queue<Key> queue = new Queue<Key>(); 
        // if (lo == null && hi == null) return queue;
        if (lo == null) throw new NullPointerException("lo is null in keys()");
        if (hi == null) throw new NullPointerException("hi is null in keys()");
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) 
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue; 
    }

	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	
	public static void main(String[] args){
		BinarySearchST<String,Integer> bst = new BinarySearchST<String,Integer>();
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
