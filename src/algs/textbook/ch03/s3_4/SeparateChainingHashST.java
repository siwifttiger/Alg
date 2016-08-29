/*
 * 基于链表实现的哈希表
 * 拉链法解决冲突
 */

package algs.textbook.ch03.s3_4;
import javax.xml.namespace.QName;

import com.wangsg.algs.Queue;
import com.wangsg.algs.SequentialSearchST;

public class SeparateChainingHashST<Key,Value> {
	private int N;             //键值对总数
	private int M;              //散列表大小
	private SequentialSearchST<Key, Value> []st;  //存放链表对象的数组
	
	public SeparateChainingHashST() {
		this(997);
	}
	
	public SeparateChainingHashST(int M){
		this.M = M;       //M条链表
		//初始化链表对象
		st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
		for(int i = 0; i < M; i++){
			st[i] = new SequentialSearchST();
		}
		
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	//哈希函数，返回值作为数组索引
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public Value get(Key key){
		int i = hash(key);
		return st[i].get(key);
	}
	
	public void put(Key key, Value val){
		int i = hash(key);
		//如果不包含相同的键值 增加N
		if(!st[i].contains(key)) N++;
		st[i].put(key, val);
	}
	
	public void delete(Key key){
		int i = hash(key);
		if(st[i].contains(key)) N--;
		st[i].delete(key);
	}
	
	public Iterable<Key> keys(){
		Queue<Key> qu = new Queue<Key>();
		for(int i = 0; i < M; i++){
			for(Key key: st[i].keys())
				qu.enqueue(key);
		}
		return qu;
	}
	
	
	
	
}
