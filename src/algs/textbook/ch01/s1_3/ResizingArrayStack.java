package algs.textbook.ch01.s1_3;

import java.util.Iterator;

import com.wangsg.algs.In;
import com.wangsg.algs.StdOut;

public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] a;
	private int N;
	
	public ResizingArrayStack(){
		a = (Item[]) new Object[2];
		N = 0;
	}
	
	public void resize(int max){
		Item[]temp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++){
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void push(Item item){
		if(a.length == N) resize(2*a.length);
		a[N++] = item;
	}
	
	public Item pop(){
		Item item = a[--N];
		if(N > 0 && N < (a.length / 4)) resize(a.length / 2);
		return item;
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	

	@Override
	public Iterator<Item> iterator() {
		return new ReverseArray();
	}
	
	private class ReverseArray implements Iterator<Item>{
		private int i = N;
		@Override
		public boolean hasNext() {
			
			return i > 0;
		}

		@Override
		public Item next() {
			
			return a[--i];
		}
		
	}
	public static void main(String[] args) {
		ResizingArrayStack<String> s;
		s = new ResizingArrayStack<String>();
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
