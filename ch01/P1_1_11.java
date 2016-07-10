
import com.wangsg.algs4.StdOut;

public class P1_1_11 {

	public static void main(String[] args) {
		boolean [][] arr = {
				{true,false,true},
				{false,false,true},
				{true,false,false},
		};
		
		printArr(arr);

	}
	
	public static void printArr(boolean [][] arr){
		int len = arr.length;
		for(int i = 1; i <= len; i++){
			StdOut.print(" " + (i) + " ");
		}
		StdOut.println();
		for(int i = 0; i < len; i++){
			StdOut.print((i+1)+" ");
			for(int j = 0; j < len; j++){
				if(arr[i][j] == true)
					StdOut.print("*" + " ");
				else
					StdOut.print("  ");
			}
			StdOut.println();
		}
	}

}
