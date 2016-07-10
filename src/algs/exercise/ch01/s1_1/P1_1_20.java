package algs.exercise.ch01.s1_1;

public class P1_1_20 {

	public static void main(String[] args) {
		System.out.println(LnN(4));

	}
	
	public static double LnN(double N){
		if(N == 1) return 0;
		return Math.log(N)+LnN(N-1);
	}

}
