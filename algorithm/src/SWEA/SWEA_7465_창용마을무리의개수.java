package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

import SWEA.SWEA_1251_하나로.Edge;

/*
@author 정여민
@since 2023. 9. 15.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU#
@git
@youtube
@performance
@category #
@note 
*
*	창용마을에는 N명의 사람이 살고 있다 1~N번의 사람
*	두 사람은 서로를 알고 있는 관계일 수도 있고, 아닐 수도 있다.
*	두 사람이 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계 = 무리
*	몇 개의 무리가 존재하는가?
*	
*	<sol>
*	BFS? 유니온 파인드?
*	
*	// 유니온 파인드 해보자! 
*	N, M(1 ≤ N ≤ 100, 0 ≤ M ≤ N(N-1)/2) 
*	N : 사람 명수 
*	M : 서로를 아는 사람의 명수 (두 사람의 번호가 주어짐)
*
*
*
*/

public class SWEA_7465_창용마을무리의개수 {
	
	static class LinkNode{
		int no;
		LinkNode pre;
		public LinkNode(int no, LinkNode pre) {
			super();
			this.no = no;
			this.pre = pre;
		}
		
	}
	static int A;
	
	static int N;
	static Integer parents[];
	
	
	// 부모를 자기 자신으로 초기화
	private static void make() {
		parents = new Integer[N];
		for(int i = 0 ; i < N; i++) {
			parents[i] = i;
		}
	}
	
	// find
	
	private static int find(int a) {
		if(a==parents[a]) {
			return a;
		}
		
		return parents[a] = find(parents[a]);
	}
	
	// union
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;	// 대표자가 같으면 유니온 안 함
		}
		if(aRoot < bRoot) {
			parents[bRoot] = aRoot;			
			
		}else {
			parents[aRoot] = bRoot;
		}
		
		return true;
	}
	
	
	static int[][] graph;
	
	// 그래프 선언
	static LinkNode [] graph2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			int result = 0;
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			graph = new int[N][N];
			
			make();
			
			int M = Integer.parseInt(st.nextToken());
			
			for(int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) -1 ;
				int b = Integer.parseInt(st.nextToken()) -1;
//				union(a,b);
				graph[a][b] = graph[b][a] = 1;
			}
			
			boolean[] visited = new boolean[N];
			
			for(int i = 0; i < N; i++) {
				if(!visited[i]) {
					dfs(graph, visited, i);
					result ++ ;
				}
			}
			
			
//			// 결과를 해시셋으로 저장하기 -> 자식노드는 계속 find를 사용해서 루트 노드를 찾아야 한다!
//			HashSet<Integer> hash = new HashSet<>();
//			for(Integer i : parents) {
//				hash.add(find(i));
//			}
//			result = hash.size();
//			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
		
	}
	
	// 완전 탐색으로도 풀어보자! 
	
	private static void dfs(int [][] arr, boolean [] visited, int current) {
		visited[current] = true;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[current][i] != 0 && !visited[i]) {
				dfs(arr, visited, i);
			}
		}
	}
	
	// 그래프 만들기
//	private static void makeGraph() {
//		graph2 = new LinkNode[N+1];
//		for(Edge edge : edges) {
//			graph2[edge.from] = new LinkNode(edge.to, edge.from);
//			graph2[edge.to] = new LinkNode(edge.from, edge.to);
//		}
//	}
	
	private static void bfs() {
//		makeGraph();
		
		Queue<Integer> que = new ArrayDeque<>();
		boolean [] visited = new boolean[N+1];
	
		// 모든 미방문 정점을 대상으로 bfs가 돌아야 함!
		for(int n = 1; n < N; n++) {
			if(!visited[n]) {
				// 미방문 정점 탑색 - 그룹
				A++;
				que.offer(n);
				visited[n] = true;
				
				while(!que.isEmpty()) {
					// 대장 데려오기
					int head = que.poll();
					// 할일
					
					// 자식 탐색
					for(LinkNode next = graph2[head]; next != null; next= next.pre) {
						if(!visited[next.no]) {
							que.offer(next.no);
							visited[next.no] = true;
						}
					}
				}
			}
		}
		
	}
	
	

}
