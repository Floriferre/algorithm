package basecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 좋은저녁_0828 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, V;
	static LinkNode[] graph;
	private static String src = "5 5 3\r\n" + "5 4\r\n" + "5 2\r\n" + "1 2\r\n" + "3 4\r\n" + "3 1";

	
	static class LinkNode{
		int i;
		int depth;
		LinkNode next;
		/**
		 * @param i
		 * @param depth
		 * @param next
		 */
		public LinkNode(int i,LinkNode next) {
			super();
			this.i = i;
			this.next = next;
		}
		@Override
		public String toString() {
			return "LinkNode [i=" + i + ", depth=" + depth + ", next=" + next + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new StringReader(src));
		st = new StringTokenizer(br.readLine());
		
		// N 정점 개수, M 간선 개수, V 시작 정점
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 1. 그래프 생성
		graph = new LinkNode[N+1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프
			graph[from] = new LinkNode(to, graph[from]);
			graph[to] = new LinkNode(from, graph[to]);
		}
		
		dfs(new LinkNode(V, null), new boolean[N+1]);
		
		
		
	}

	private static void dfs(LinkNode node, boolean[] visited) {
		visited[node.i] = true; // 방문체크
		
		// 연결된 노드가 있으면 다시 탐색
		for(LinkNode child = graph[node.i]; child != null; child = child.next) {
			if(!visited[child.i]) {
				child.depth = node.depth+1;
				dfs(child, visited);
			}
		}
	}
	
	private static void bfs() {
		// 1. 준비물 큐!! 
		Queue<LinkNode> que = new ArrayDeque<LinkNode>();
		boolean [] visited = new boolean[N+1];
		
		// 2. 초기 설정
		que.offer(new LinkNode(V, null));
		visited[V] = true;
		
		while(!que.isEmpty()) {
			
			int size = que.size();	// depth 별로 살펴보기 
			
			while(size-- > 0) {
				LinkNode current = que.poll();
				
				for(LinkNode child = graph[current.i]; child != null; child = child.next) {
					if(!visited[child.i]) {
						visited[child.i] = true;
						child.depth = current.depth + 1;
						que.offer(child);
					}
				}				
			}
			
					
		}
		
	}

}
























