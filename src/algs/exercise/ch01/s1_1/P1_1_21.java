package algs.exercise.ch01.s1_1;
import com.wangsg.algs.*;

public class P1_1_21 {

	public static void main(String[] args) {
		int n = StdIn.readInt(); // ����
		String names[] = new String[n];
		int int1[] = new int[n];
		int int2[] = new int[n];
		double results[] = new double[n];
		for(int i = 0; i < n; i++){
			names[i] = StdIn.readString();
			int1[i] = StdIn.readInt();
			int2[i] = StdIn.readInt();
			results[i] = (double)(int1[i]) / (double)(int2[i]);
			
		}
		for(int i = 0; i < n; i++){
			StdOut.printf("%-14s%6d%6d%14.3f\n", names[i],
					int1[i],int2[i],results[i]);
		}

	}
	


}
