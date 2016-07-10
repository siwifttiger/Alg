

public class P1_1_10 {
	public static void main(String [] args){
		int [] a = new int[10];     
		for(int i = 0; i < 10; i++){
			a[i] = i * i;   // 没有用new为a分配内存
		}
	}
}
