package algs.exercise.ch01.s1_1;

import java.util.Arrays;

import com.wangsg.algs.In;
import com.wangsg.algs.StdIn;

public class P1_1_30 {

	public static void main(String[] args) {
		int N = StdIn.readInt();
		boolean arr[][] = create(N);
		System.out.println(Arrays.deepToString(arr));

	}
	/**
	 * 
	 * @param N
	 * @return
	 */
	private static boolean[][] create(int N){
		boolean[][] arr = new boolean[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N ; j++){
				if(i == 0 || j == 0){
					arr[i][j] = false;
				}
				else if(Euclid(i,j) == 1){
					arr[i][j] = true;
				}
			}
		}
		return arr;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private static int Euclid(int a, int b){
		if(a % b == 0) return b;
		return Euclid(b,a%b);
	}

}
