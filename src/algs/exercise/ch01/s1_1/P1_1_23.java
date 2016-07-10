/*
 * ע�⣬��ݴ�����eclipse���沢�������У���Ҫ�Լ�ͨ��
 * ����������
 */
package algs.exercise.ch01.s1_1;
import java.util.Arrays;

import com.wangsg.algs.*;
public class P1_1_23 {

	public static void main(String[] args) {
		int whitelist[] = new In(args[0]).readAllInts();
		Arrays.sort(whitelist);
		System.out.println(Arrays.toString(whitelist));
		if(args[1].equals("+")){
			while(!StdIn.isEmpty()){
				int key = StdIn.readInt();
				if(rank(key,whitelist) < 0)
					StdOut.println(key);
			}
		}
		else if(args[1].equals("-")){
			while(!StdIn.isEmpty()){
				int key = StdIn.readInt();
				if(rank(key,whitelist) >= 0)
					StdOut.println(key);
			}			
		}

	}
	
	public static int rank(int key, int[] a){
		int lo = 0;
		int hi = a.length - 1;
		while(lo <= hi){
			int mid = (lo + hi) /2;
			if(key > a[mid]) lo = mid + 1;
			else if(key < a[mid]) hi = mid - 1;
			else return mid;
			
		}
		return -1;
	}

	

}
