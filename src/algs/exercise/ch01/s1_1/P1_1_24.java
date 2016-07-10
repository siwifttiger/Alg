package algs.exercise.ch01.s1_1;

public class P1_1_24 {

	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		Euclid(a,b);

	}
	
	public static int Euclid(int a, int b){
	
		System.out.println((a/b) + " " +(a % b));
		
			if(a % b == 0) return b;
			return Euclid(b, a % b);
		
	}
}
		

