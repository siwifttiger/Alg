package algs.textbook.ch01.s1_3;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
	private Node first;
	private int N;
	
	private class Node{
		Item elements;
		Node next;
	}
	
	public void add(Item item){
		Node oldfirst = first;
		first = new Node();
		first.elements = item;
		oldfirst.next = first;
		N++;		
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
			cur =  cur.next;
			return item;
		}
		
	}

}
