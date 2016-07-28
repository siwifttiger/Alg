package algs.exercise.ch01.s1_3;

import com.wangsg.algs.In;
import com.wangsg.algs.StdOut;

import algs.textbook.ch01.s1_3.FixedCapacityStackOfStrings;

public class P1_3_01 {
	private String[] a;
	private int size;
	
	public P1_3_01(int cap){
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
	
	public boolean isFull(){
		return size == a.length;
	}
	

	public static void main(String[] args) {
		P1_3_01 s;
		s = new P1_3_01(100);
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
