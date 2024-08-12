package Lecture.DAY2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class CompleteBinaryTree<T> {
	
	
	private Object [] nodes;
	private int lastIndex = 0;	// 채워진 마지막 노드의 인덱스 
	private final int SIZE;	// 최대 노드의 갯수
	
	public CompleteBinaryTree(int size) {
		this.SIZE = size;
		nodes = new Object[size+1];
	}
	
	public boolean isEmpty() {
		return lastIndex==0;
	}
	public boolean isFull() {
		return lastIndex == SIZE;
	}
	public boolean add(T data) {
		if(isFull()) {
			return false;
		}
		nodes[++lastIndex] = data;
		return true;
	}
	
	public void bfs() {
		
		if(isEmpty()) return;
		
		//탐색 순서를 관리할 대기열 자료구조 생성
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		// 탐색 시작의 대상부터 큐에 넣기 
		queue.offer(1);	// 루트 노드의 인덱스 
		
		while(!queue.isEmpty()) {	// 탐색 대상이 있다면
			int current = queue.poll();	// 탐색 대살 큐에서 꺼내기
			// 탐색 대상 방문처리
			System.out.println(nodes[current]);
			// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
			// 왼쪽 자식
			if(current*2 <= lastIndex) {
				queue.offer(current*2);
			}
			// 오른쪽 자식
			if(current*2+1 <= lastIndex) {
				queue.offer(current*2+1);
			}
		}
	}
	
	public void bfs2() {
		
		if(isEmpty()) return;
		
		//탐색 순서를 관리할 대기열 자료구조 생성
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		// 탐색 시작의 대상부터 큐에 넣기 
		queue.offer(1);	// 루트 노드의 인덱스 
		
		
		int breadth = 0;
		while(!queue.isEmpty()) {	// 탐색 대상이 있다면
			
			int size = queue.size();
			
			// 큐의 사이즈만큼 돌기 
			while(--size >=0) {				
				int current = queue.poll();	// 탐색 대살 큐에서 꺼내기
				// 탐색 대상 방문처리
				System.out.print(nodes[current] + "\t");
				// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
				// 왼쪽 자식
				if(current*2 <= lastIndex) {
					queue.offer(current*2);
				}
				// 오른쪽 자식
				if(current*2+1 <= lastIndex) {
					queue.offer(current*2+1);
				}
			}
			System.out.println();
			System.out.println("============================" + breadth + "너비 탐색 완효");
			breadth++;
		}
	}
	
	
	public void bfs3() {
		
		if(isEmpty()) return;
		
		//탐색 순서를 관리할 대기열 자료구조 생성
		Queue<int []> queue = new ArrayDeque<int []>();	// int[] : {탐색 노드의 인덱스, 너비}
		
		// 탐색 시작의 대상부터 큐에 넣기 
		queue.offer(new int[] {1, 0});	// 루트 노드의 인덱스 
		
		while(!queue.isEmpty()) {	// 탐색 대상이 있다면
			int [] info = queue.poll();
			int current = info[0];	// 탐색 대살 큐에서 꺼내기
			// 탐색 대상 방문처리
			System.out.println(nodes[current] + "//"+ info[1]);
			// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
			// 왼쪽 자식
			if(current*2 <= lastIndex) {
				queue.offer(new int [] {current*2, info[1] + 1});
			}
			// 오른쪽 자식
			if(current*2+1 <= lastIndex) {
				queue.offer(new int [] {current*2+1, info[1] + 1});
			}
		}
	}
	
	public void dfsByPreorder(int current) {	// 현재 노드를 전위 순회로 탐색!! 
		// 탐색 대상 방문처리
		System.out.print(nodes[current]);
		// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색시키기 
		// 왼쪽 자식
		if(current*2 <= lastIndex) {
			dfsByPreorder(current*2);
		}
		// 오른쪽 자식
		if(current*2+1 <= lastIndex) {
			dfsByPreorder(current*2+1);
		}
	}
	
	public void dfsByInorder(int current) {	// 현재 노드를 전위 순회로 탐색!! 
		// 탐색 대상 방문처리
		
		// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색시키기 
		// 왼쪽 자식
		if(current*2 <= lastIndex) {
			dfsByInorder(current*2);
		}
		System.out.print(nodes[current]);
		// 오른쪽 자식
		if(current*2+1 <= lastIndex) {
			dfsByInorder(current*2+1);
		}
	}
	
	public void dfsByPostorder(int current) {	// 현재 노드를 전위 순회로 탐색!! 
		// 탐색 대상 방문처리
		
		// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색시키기 
		// 왼쪽 자식
		if(current*2 <= lastIndex) {
			dfsByPostorder(current*2);
		}
		// 오른쪽 자식
		if(current*2+1 <= lastIndex) {
			dfsByPostorder(current*2+1);
		}
		System.out.print(nodes[current]);
	}
	
	
	// bfs를 큐 대신 스택을 쓰고 넣는 순서를 바꾸면(오른쪽 자식 먼저) 전위 순회와 똑같다 
	public void dfs() {
		
		if(isEmpty()) return;
		
		//탐색 순서를 관리할 대기열 자료구조 생성
		Stack<Integer> queue = new Stack<Integer>();
		
		// 탐색 시작의 대상부터 큐에 넣기 
		queue.push(1);	// 루트 노드의 인덱스 
		
		while(!queue.isEmpty()) {	// 탐색 대상이 있다면
			int current = queue.pop();	// 탐색 대살 큐에서 꺼내기
			// 탐색 대상 방문처리
			System.out.print(nodes[current]);
			// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
			// 오른쪽 자식
			if(current*2+1 <= lastIndex) {
				queue.push(current*2+1);
			}
			
			
			// 왼쪽 자식
			if(current*2 <= lastIndex) {
				queue.push(current*2);
			}

		}
	}
	
	
}
