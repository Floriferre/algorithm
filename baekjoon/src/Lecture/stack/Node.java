package Lecture.stack;

public class Node<T> {

	private T data;
	// 나와 똑같이 생긴 노드에 대한 참조값이 필요하니까 
	private Node<T> link;
	public Node(T data, Node<T> link) {
		super();
		this.data = data;
		this.link = link;
	}
	
	// 이 경우엔 link는 초기화가 이루어져 null값이 들어감 
	public Node(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLink() {
		return link;
	}

	public void setLink(Node<T> link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", link=" + link + "]";
	}
	
	
}
