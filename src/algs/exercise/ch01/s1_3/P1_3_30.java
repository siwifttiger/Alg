package algs.exercise.ch01.s1_3;

public class P1_3_30 {

	private class Node{
		int data;
		Node next;
	}
	
	public Node reverse(Node head){
		Node reverse = null;
		Node first = head;
		while(first != null){
			Node second = first.next;
			first.next = reverse;
			reverse = first;
			first = second;
		}
		return reverse;
	}
	
	public Node reverseRec(Node head){
		if(head == null) return null;
		if(head.next == null) return head;
		Node second = head.next;
		Node rest = reverseRec(second);
		second.next = head;
		head.next = null;
		return rest;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
