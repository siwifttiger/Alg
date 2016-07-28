package algs.textbook.ch01.s1_3;

import com.wangsg.algs.In;
import com.wangsg.algs.StdOut;

public class FixedCapacityStack<Item> {

	private Item[] a;
	private int size;
	
	public FixedCapacityStack(int cap){
		a = (Item[]) new Object[cap];
	}
	
	public void push(Item item){
		a[size++] = item;
	}
	
	public Item pop(){
		return a[--size];
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	
	
	public static void main(String[] args) {
		FixedCapacityStack<String> s;
		s = new FixedCapacityStack<String>(100);
		String[] items = In.readStrings("http://algs4.cs.princeton.edu/13stacks/tobe.txt");
		for(int i = 0; i < items.length; i++){
			String a = items[i];
			if(!a.equals("-"))
				s.push(a);
			else if(!s.isEmpty()) StdOut.print(s.pop() + " ");
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}

}
