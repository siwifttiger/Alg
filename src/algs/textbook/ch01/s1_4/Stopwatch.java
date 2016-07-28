package algs.textbook.ch01.s1_4;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;

public class Stopwatch {
	private final long start;
	public Stopwatch(){
		start = System.currentTimeMillis();
	}
	
	public double elapseTime(){
		long now = System.currentTimeMillis();
		return (now - start) / 1000;
	}
	
	public static void main(String[] args ){
		int N = StdIn.readInt();
		int [] a = new int[N];
		for(int i = 0; i < N; i++){
			a[i] = StdRandom.uniform(-10000, 10000);
		}
		Stopwatch timer = new Stopwatch();
		int cnt = ThreeSum.count(a);
		double time = timer.elapseTime();
		StdOut.println(cnt + "triples " + time + "seconds");
	}
}
