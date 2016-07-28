package algs.exercise.ch01.s1_3;
import java.util.Arrays;

import com.wangsg.algs.In;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;

import algs.textbook.ch01.s1_3.*;
public class P1_3_04 {

	public static void main(String[] args) {
		Stack<Character> s = new Stack<Character>();
		String its = StdIn.readString();
		char [] items = its.toCharArray();
		boolean isMatch = false;
		for(int i = 0; i < items.length; i++){
			if(items[i] == '[' || items[i] == '(' || items[i] == '{'){
				s.push(items[i]);
			}else if(items[i] == ']' || items[i] == ')' || items[i] == '}'){
				char item = s.pop();
				isMatch = (item == '{' && items[i] == '}') ||
						  (item == '(' && items[i] == ')') ||
						  (item == '[' && items[i] == ']');
			}
		}
		if(!s.isEmpty() || !isMatch)
			StdOut.println("false");
		else
			StdOut.println("true");

	}

}
