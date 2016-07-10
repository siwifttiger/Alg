

import com.wangsg.algs4.StdIn;

public class P1_1_09 {

	public static void main(String[] args) {
		int N = StdIn.readInt();
		String s = "";
		for(int n = N; n > 0; n /= 2){
			s = n %2 + s;
		}
		System.out.println(s);
	}

}
