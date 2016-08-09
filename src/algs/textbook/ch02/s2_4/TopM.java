package algs.textbook.ch02.s2_4;

import com.wangsg.algs.In;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.Transaction;

import algs.textbook.ch01.s1_3.Stack;

public class TopM {

	public static void main(String[] args) {
		
		
			
			int M = Integer.parseInt(args[0]);
			MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);

			while(StdIn.hasNextLine()){
				
				pq.insert(new Transaction(StdIn.readLine()));
				if(pq.size() > M) pq.delMin();
				
			}
			Stack<Transaction> stack = new Stack<Transaction>();
			while(!pq.isEmpty()) stack.push(pq.delMin());
			for(Transaction t:stack) StdOut.println(t);
			
		}
		
	

}
