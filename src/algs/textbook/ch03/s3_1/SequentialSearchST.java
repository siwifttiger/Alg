package algs.textbook.ch03.s3_1;

import com.wangsg.algs.Queue;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class SequentialSearchST<Key , Value> {
	private Node first;
	private int N;
	
	
	public SequentialSearchST(){
		first = null;
		N = 0;
	}
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key key, Value value, Node next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	public Value get(Key key){
		for(Node x =first; x != null; x=x.next){
			if(x.key.equals(key)){
				return x.value;
			}
		}
		return null;
	}
	
	public void put(Key key, Value value){
		if(value == null){
			delete(key);
			return;
		}
		//if has key
		for(Node x = first; x != null; x=x.next){
			if(x.key.equals(key))
				x.value = value;
			return;
		}
		first = new Node(key,value,first); //put at head
		N++;
	}
	
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	public void delete(Key key){
		Node x = first;
		Node x1 = first.next;
		while(!x.key.equals(key) && !x1.key.equals(key) && x1 != null){
			x1 = x1.next;
			x = x.next;
			if(x1 == null) return;
		}
		
		if(x.key.equals(key)){
			first = first.next;
			x = null;
			N--;
			return;
		}
		x.next = x1.next;
		x1 = null;
		N--;
		return;
		
	}
	
	public int size(){
		return N;
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(Node x = first; x != null; x=x.next){
			queue.enqueue(x.key);
		}
		return queue;
	}
	
	public static void main(String[] args){
		int minlen = StdIn.readInt();
		SequentialSearchST<String,Integer> st = new SequentialSearchST<String,Integer>();
		while(!StdIn.isEmpty()){
			String word = StdIn.readString();
			if(word.length() < minlen) continue;
			if(!st.contains(word))  st.put(word, 1);
			else                    st.put(word, st.get(word)+1);
		}
		
		String max = " ";
		st.put(max, 0);
		for(String word:st.keys()){
			if(st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println(max + " " + st.get(max));
	}
	

}
