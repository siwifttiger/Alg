

public class P1_1_13 {

	public static void main(String[] args) {
		int [][]arr = {{0,2,1},
				{4,8,9},
				{3,4,5},};
		Trans(arr,3,3);
		
	}
	
	public static void Trans(int [][] a, int M, int N){
		int temp;
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N ; j++){
				
				a[i][j] = a[j][i];
				
				
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}


}
