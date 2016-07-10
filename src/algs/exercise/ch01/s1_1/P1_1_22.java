package algs.exercise.ch01.s1_1;

public class P1_1_22 {
	static int d = 0;
	public static void main(String[] args) {
		int a[] = {1,2,3,5,3,4,6,7,3,4,6,8,4,5,6};
		
		System.out.println(rank(2,a));
		System.out.println(d);

	}
	public static int rank(int key, int[] a){
		return rank(key,a,0,a.length-1);
	}
	public static int rank(int key, int[] a, int lo, int hi){
		for(int i = 0; i < d;i++){
			System.out.print(" ");
		}
		System.out.println(lo + " " + hi);
		++d;
		//System.out.println(deep);
		if(lo > hi) {return -1;}
		int mid = lo + (hi - lo) / 2;
		if(key < a[mid]) return rank(key,a,lo,mid - 1);
		else if(key > a[mid]) return rank(key,a,mid+1,hi);
		else return mid;
	}

}
