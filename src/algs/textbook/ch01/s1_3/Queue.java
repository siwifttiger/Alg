package algs.textbook.ch01.s1_3;

import java.util.Iterator;

import com.wangsg.algs.In;
import com.wangsg.algs.StdOut;

public class Queue<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;
	
	private class Node{
		Item elements;
		Node next;
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public void equeue(Item item){
		Node oldlast = last;
		last = new Node();
		last.elements = item;
		last.next = null;
		if(isEmpty()) first = last;
		else oldlast.next = last;
		N++;
	}
	
	public Item dequeue(){
		Item item = first.elements;
		first = first.next;
		if(isEmpty()) last = null;
		N--;
		return item;
	}

	
	
	
	
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node cur = first;
		@Override
		public boolean hasNext() {

			return cur != null;
		}

		@Override
		public Item next() {
			Item item = cur.elements;
			cur = cur.next;
			return item;
		}
		
	}

	
	
	public static void main(String[] args) {
		Queue<String> s;
		s = new Queue<String>();
		String[] items = In.readStrings("http://algs4.cs.princeton.edu/13stacks/tobe.txt");
		for(int i = 0; i < items.length; i++){
			String a = items[i];
			if(!a.equals("-"))
				s.equeue(a);
			else if(!s.isEmpty()) StdOut.print(s.dequeue() + " ");
		}
		StdOut.println("(" + s.size() + " left on queue)");
		for(String a:s){
			StdOut.println(a);
		}
	}
	
}
