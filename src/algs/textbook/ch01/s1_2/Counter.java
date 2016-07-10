/*********************************************************************
 * public class Counter
 * 
 * 
******************************************************************** */
package algs.textbook.ch01.s1_2;

import com.wangsg.algs.StdOut;

public class Counter {
	private  final String name;
	private int count;
	
	/**
	 * 
	 * @param id
	 */
	public Counter(String id){
		this.name = id;
	}
	
	/**
	 * 
	 */
	public void increment(){
		++count;
	}
	
	/**
	 * 
	 * @return
	 */
	public int tally(){
		return count;
	}
	
	/**
	 * @return
	 */
	public String toString(){
		return (name + " " + count);
	}

	public static void main(String[] args) {
		Counter head = new Counter("head");
		Counter tail = new Counter("tail");
		
		head.increment();
		head.increment();
		tail.increment();
		StdOut.println(head + "\n" + tail);
		StdOut.println(head.tally() + tail.tally());

	}

}
