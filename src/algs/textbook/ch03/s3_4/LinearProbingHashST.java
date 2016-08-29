package algs.textbook.ch03.s3_4;

public class LinearProbingHashST<Key,Value> {
	Key[] keys;
	Value[] vals;
	int N;
	int M;
	private static final int INIT_CAPACITY = 4;
	
	public LinearProbingHashST(){
		this(INIT_CAPACITY);
	}
	
	public LinearProbingHashST(int capacity){
		N = capacity;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	private int hash(Key key){
		return (key.hashCode()& 0x7fffffff)%M;
	}
	
	private void resize(int chain){
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key,Value>(chain);
		for(int i = 0; i < M; i++){
			if(keys[i] != null)
				temp.put(keys[i],vals[i]);
		}
		keys = temp.keys;
		vals = temp.vals;
		M = temp.M;
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	public void put(Key key,Value val){
		//���ٱ�֤��ϣ����һ��Ŀ��пռ�
		if(N >= M/2) resize(2*M);
		
		int i = hash(key);
		for(; keys[i] != null; i = (i+1) % M){
			if(keys[i].equals(key)) {vals[i] = val;return;}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
//delete ���� �ҵ��ü�֮������Ϊ�գ����ҽ���֮�������Ԫ�����¼����ϣ��
	public void delete(Key key){
		if(!contains(key)) return;
		int i = hash(key);
		while(!keys[i].equals(key)){
			i = (i+1) % M;
		}
		keys[i] = null;
		vals[i] = null;
		i = (i+1) % M;
		while(keys[i] != null){
			Key keyReToDo = keys[i];
			Value valReToDo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyReToDo,valReToDo);
			i = (i+1) % M;
		}
		N--;
		if(N > 0 && N == M/8) resize(M/2);
	}
	
	public Value get(Key key){
		int i;
		for(i = hash(key); keys[i] != null; i = (i+1)%M){
			if(keys[i].equals(key)) return vals[i];
		}
		return null;
			
	}
}
