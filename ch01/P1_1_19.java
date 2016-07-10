import com.wangsg.algs4.*;

public class P1_1_19{
	public static void main(String[] args){
		/*for(int i = 0; i <= 100; i++)
			StdOut.println(i + " " + F(i));*/

		for(int i = 0; i <= 100; i++)
			StdOut.println(i + " " + F1(i));
	}
	
	public static long F(int n){
		if(n == 0) return 0;
		if(n == 1) return 1;
		return F(n-1) + F(n-2);
	}

	public static long F1(int n){
		if(n == 0) return 0;
		if(n == 1) return 1;
		long a[] = new long[n+1];
		a[0] = 0;
		a[1] = 1;
		
		for(int i = 2; i <= n; i++)
			a[i] = a[i-1] + a[i-2];
		return a[n];
	}
}
