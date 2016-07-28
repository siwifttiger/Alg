package algs.textbook.ch01.s1_3;

import com.wangsg.algs.In;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class FixedCapacityStackOfStrings {
	private String[] a;
	private int size;
	
	public FixedCapacityStackOfStrings(int cap){
		a = new String[cap];
	}
	
	public void push(String item){
		a[size++] = item;
	}
	
	public String pop(){
		return a[--size];
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	

	public static void main(String[] args) {
		FixedCapacityStackOfStrings s;
		s = new FixedCapacityStackOfStrings(100);
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
