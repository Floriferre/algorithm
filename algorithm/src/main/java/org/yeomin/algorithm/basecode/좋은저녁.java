package basecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class 좋은저녁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	static int N, M, V;
	static LinkNode[] graph;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new StringReader(src));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		// 1. 그래프 생성( 인접행렬로 주어졌나요? > 노드의 방문 순서를 편집해야하나요?), 방향성(단방향, 양방향)
		graph = new LinkNode[N+1]; 	//1 base 인덱스를 사용하므로
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프
			graph[from] = new LinkNode(to, graph[from]);
			graph[to] = new LinkNode(from, graph[to]);
		}
//		for(LinkNode l: graph) {
//			System.out.println(l);
//		}
		
//		bfs();
		dfs(new LinkNode(V, null), new boolean[N+1]);
		System.out.println(sb);
	}
	
	static void dfs(LinkNode node, boolean [] visited) {
		// 1. 방문 처리
		visited[node.i] = true;
		
		// 2. 사용하기
		sb.append("depth :" + node.depth + ", 부모 :" + node.parent + ", 나는? " + node.i + "\n");
		
		// 3. 다음 자식 찾기
		for(LinkNode child = graph[node.i]; child != null; child = child.next) {
			if(!visited[child.i]) {
				child.parent = node;
				child.depth = node.depth+1;
				dfs(child, visited);
			}
		}
	}
	

	static void bfs() {
		// 1. 준비물
		Queue<LinkNode> que = new ArrayDeque<>();
		boolean [] visited = new boolean[N+1];
		// 2. 초기 설정 : 얘는 그래프와 상관 X
		que.offer(new LinkNode(V, null));
		visited[V] = true;
		
		// 3. 탐색
		int depth = 1;	// 몇 번만에 찾았는지 
		while(!que.isEmpty()) {
//			int size = que.size();
//			sb.append(depth + " : " + size + " >> ");
			
//			while(size-- > 0) {
				// 3-1. 대장 데려오기
				LinkNode head = que.poll();
				sb.append("depth : " + head.depth + ", 부모는: " + head.parent + ", ");
				
				// 3-2. 대장 사용하기
				sb.append(head.i).append(", ");
				
				// 3-3. 미방문인 자식 탐색하기
				for(LinkNode child = graph[head.i] ; child != null; child = child.next) {
					if(!visited[child.i]) {
						visited[child.i] = true;
						child.depth = head.depth+1;
						child.parent = head;	// 부모 노드 누구냐?! 
						que.offer(child);
					}
				}				
//			}
			
			sb.append("\n");
			// 기존 사이즈 소진 완료!
			depth++;
		}
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

	private static String src = "5 5 3\r\n" + "5 4\r\n" + "5 2\r\n" + "1 2\r\n" + "3 4\r\n" + "3 1";

}
