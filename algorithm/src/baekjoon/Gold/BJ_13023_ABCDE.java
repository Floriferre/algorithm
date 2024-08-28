package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 22.
@see https://www.acmicpc.net/problem/13023
@git
@youtube
@performance
@category #
@note 
*	알고리즘 캠프 N명 참가 0~N-1번
*	A는 B와 친구다
*	B는 C와 친구다
*	C는 D와 친구다
*	D는 E와 친구다
*	친구 관계 존재?
*
*	<입력>
*	첫째줄 : 사람수 N, 친구 관계수 M
*	둘째줄 ~ M개의 줄 : a b <- a와 b가 친구
*	존재하면 1, 아니면 0
*	
*	<SOL>
*	친구 관계는 방향 그래프인가 무방향 그래프인가? -> 무방향그래프! 친구 관계가 일방적이면 너무 슬프지 않을까... 
*	A->B->C->D->E 관계를 찾아라! => depth가 4이상인 것을 찾아라! 
*	BFS? DFS? 더 빨리 멀리 가려면 DFS를 사용해보자 
*
*
*/

public class BJ_13023_ABCDE {
	
	static int N, M;
	static LinkNode [] graph;
	
	static int depth_check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 1. 그래프 생성 
		graph = new LinkNode[N];
		
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// 양 방향 그래프
			graph[from] = new LinkNode(to, graph[from]);
			graph[to] = new LinkNode(from, graph[to]);
			
//			System.out.println(graph[from] + " " + graph[to]);
		}
		
//		for (LinkNode node : graph) {	// node : 각 정점의 인접리스트으 헤드
//		System.out.println(node);
//	}
		
		
		
		boolean check = false;
		for(int i = 0; i < N; i++) {	// 가장 큰 거 찾기
			depth_check = 0;
			dfs(new LinkNode(i, null), new boolean[N]);
			if(depth_check ==1) break;
		}
		System.out.println(depth_check);
		
	}
	
	
	static class LinkNode {
		int i;
		int depth;
		LinkNode next;
		LinkNode parent;

		public LinkNode(int i, LinkNode next) {
			super();
			this.i = i;
			this.next = next;
		}

		@Override
		public String toString() {
			return "LinkNode [i=" + i + ", next=" + next + "]";
		}
	}
	
	private static void dfs(LinkNode node, boolean[] visited) {
		// 1. 방문처리
		visited[node.i] = true;
		// 2. 사용하기
		if(node.depth >= 4) {
//		System.out.println(node.depth);
//			depth = node.depth;
			depth_check = 1;
			return;
		}
		
		// 3. 다음 자식 찾기
		for(LinkNode child = graph[node.i]; child != null; child = child.next) {
			if(!visited[child.i]) {
				child.parent = node;
				child.depth = node.depth+1;
				dfs(child, visited);
				visited[child.i] = false;
			}
		}
//		return false;
	}

}




























