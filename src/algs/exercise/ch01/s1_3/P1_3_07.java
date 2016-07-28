package algs.exercise.ch01.s1_3;

import java.util.Iterator;

import com.wangsg.algs.In;
import com.wangsg.algs.StdOut;

public class P1_3_07<Item> implements Iterable<Item> {
	private Node first;
	private int size;
	
	private class Node{
		Item elements;
		Node next;
	}
	public P1_3_07() {
		first = null;
		size = 0;
	}
	
	public void push(Item item){
		Node oldfirst = first;
		// allocate new memory for new node;
		first = new Node();
		first.elements = item;
		first.next = oldfirst;
		size++;
	}
	
	public Item pop(){
		Item t = first.elements;
		first = first.next;
		size--;
		return t;
	}
	
	public Item peek(){
		return first.elements;
	}
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ReverseArray();
	}
	
	private class ReverseArray implements Iterator<Item>{
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item it = current.elements;
			current = current.next;
			return it;
			
		}
		
	}

	public static void main(String[] args) {
		P1_3_07<String> s;
		s = new P1_3_07<String>();
		String[] items = In.readStrings("http://algs4.cs.princeton.edu/13stacks/tobe.txt");
		for(int i = 0; i < items.length; i++){
			String a = items[i];
			if(!a.equals("-"))
				s.push(a);
			else if(!s.isEmpty()) StdOut.print(s.pop() + " ");
		}
		StdOut.println("(" + s.size() + " left on stack)");
		for(String a:s){
			StdOut.println(a);
		}
	}
}

