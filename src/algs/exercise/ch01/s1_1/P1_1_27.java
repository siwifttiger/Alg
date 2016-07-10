package algs.exercise.ch01.s1_1;

import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

public class P1_1_27 {
	public static double[][] binomial = new double[1000][500];
	public static int deep = 0;
	public static void main(String[] args) {
		//System.out.println(binomialRec(100, 50, 0.25) + " " + deep); time is too long to be counted
		
		int N = StdIn.readInt();
		int k = StdIn.readInt();
		double p = StdIn.readDouble();
		
		for(int i = 0; i <= N; i++){
			for(int j = 0; j <= k; j++)
				binomial[i][j] = -1.0;
		}
		System.out.println(binomialRec1(N, k, p) + " " + deep);
		
	}
	
	public static double binomialRec(int N, int k, double p){
		++deep;
		if(N == 0 && k == 0) return 1.0;
		if(N < 0 || k < 0) return 0.0;
		return (1.0 - p) * binomialRec(N-1,k,p) + p * binomialRec(N - 1, k - 1, p);
	}
	

	public static double binomialRec1(int N, int k, double p){
		
	
		if(N < 0 || k < 0)
			return 0.0;
		else if(N == 0 && k == 0){
			binomial[N][k] = 1.0;
		}
		else if(binomial[N][k] == -1.0){
			binomial[N][k] = (1.0 - p) * binomialRec1(N-1,k,p)
					+ p * binomialRec1(N - 1, k - 1, p);
		}
		return binomial[N][k];
		
	}
 
	
}
