package algs.exercise.ch01.s1_1;

public class P1_1_15 {

	public static void main(String[] args) {
		int a[] = {1,2,3,3,4,4,5,5,6};
		int []b = histogram(a,a.length);
		for(int i = 0; i < a.length; i++){
			System.out.println(b[i]);
		}

	}
	
	public static int[] histogram(int[] a, int M){
		int [] b = new int[M];
		for(int i = 0; i < M; i++){
			for(int j = 0; j < M; j++){
				if(a[j] == i)
					b[i]++;
			}
		}
		return b;
	}

}
