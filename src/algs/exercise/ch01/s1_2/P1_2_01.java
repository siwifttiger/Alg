package algs.exercise.ch01.s1_2;

import com.wangsg.algs.StdDraw;
import com.wangsg.algs.StdIn;
import com.wangsg.algs.StdOut;
import com.wangsg.algs.StdRandom;

public class P1_2_01 {
	private double x;
	private double y;
	
	public P1_2_01(){}
	
	public P1_2_01(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double r(){
		return Math.sqrt(x*x + y*y);
	}
	
	public double theta(){
		return Math.atan2(y, x);
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double distTo(P1_2_01 that){
		return Math.sqrt((this.x-that.x)*(this.x - that.x) + (this.y - that.y)*(this.y - that.y));
	}
	
	public void draw(){
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.setPenRadius(0.003);
		StdDraw.point(x, y);
	}
	
	public static void main(String[] args){
		int N = StdIn.readInt();
	
		P1_2_01[] point = new P1_2_01[N];
		for(int i = 0; i < N; i++){
			point[i] = new P1_2_01();
		}
		for(int i = 0; i < N; i++){
			point[i].setX(StdRandom.random());
			point[i].setY(StdRandom.random());
			point[i].draw();
			StdDraw.show(10);
		}
		
		//the pair of points having shortest distance
		double min = Double.MAX_VALUE;
		for(int i = 0; i < N -1; i++){
			for(int j = i+1; j < N ; j++){
				if(min < point[i].distTo(point[j])){
					min = point[i].distTo(point[j]);
				}
			}
		}
		StdOut.println(min);
	}
}
