/*
 * ��������ʵ�ֵĹ�ϣ��
 * �����������ͻ
 */

package algs.textbook.ch03.s3_4;
import javax.xml.namespace.QName;

import com.wangsg.algs.Queue;
import com.wangsg.algs.SequentialSearchST;

public class SeparateChainingHashST<Key,Value> {
	private int N;             //��ֵ������
	private int M;              //ɢ�б��С
	private SequentialSearchST<Key, Value> []st;  //���������������
	
	public SeparateChainingHashST() {
		this(997);
	}
	
	public SeparateChainingHashST(int M){
		this.M = M;       //M������
		//��ʼ���������
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
	
	//��ϣ����������ֵ��Ϊ��������
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public Value get(Key key){
		int i = hash(key);
		return st[i].get(key);
	}
	
	public void put(Key key, Value val){
		int i = hash(key);
		//�����������ͬ�ļ�ֵ ����N
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
