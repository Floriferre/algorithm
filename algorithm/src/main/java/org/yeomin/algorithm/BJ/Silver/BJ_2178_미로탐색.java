package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 정여민
 * @since 2023. 8. 18.
 * @see https://www.acmicpc.net/problem/2178
 * @git
 * @youtube
 * @performance 15716KB 156ms
 * @category #BFS
 * @note NxM 크기의 미로 1은 이동할 수 있는 칸, 0은 이동할 수 없는 칸 (1,1) -> (N,M) 이동할 때 지나야 하는 최소
 *       칸 수를 구하시오
 * 
 * 
 * 
 */

public class BJ_2178_미로탐색 {

	static int N, M;	// 가로 세로 크기
	static char[][] map;	// 맵 정보를 저장해둘 변수

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	// 상하 좌우 탐색용
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	// 최단 경로 기록할 변수
	static int depth;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 2차원 배열로 맵 정보를 받음
		map = new char[N][M];

		// 1의 개수 저장할 변수 -> 이걸로 그래프 만들 것이기 때문
		int cnt = 0;
		
		// 맵 정보 입력 받음
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		bfs();
		System.out.println(depth);

	}

	private static void bfs() {
		// 1. 준비물
		Queue<Node> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		// 2. 초기 설정 (1,1 -> 0,0부터 탐색으로 변경)
		que.offer(new Node(0, 0));
		visited[0][0] = true;
		
		// 초기 depth는 1
		depth = 1;
		// 3. 탐색
		while (!que.isEmpty()) {
			// 큐의 현재 사이즈만 돌기 : depth별로 돌기!
			int size = que.size();

			while (size-- > 0) {
				// 맨 앞의 것 빼오기 ( x, y 값)
				Node head = que.poll();
				
				// 현재 좌표가 N-1, M-1이면 끝내기
				if(head.x == N-1 && head.y == M-1) return;
				// 사용하기
				sb.append(head.x + ", " + head.y);

				// 조건에 맞으면 큐에 넣기
				for (int d = 0; d < dx.length; d++) {
					int nx = head.x + dx[d];
					int ny = head.y + dy[d];
					// 새로운 좌표가 범위 안에 있고, 방문한 적이 없으며, 갈 수 있는 곳이면
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == '1' ) {
						if (!visited[nx][ny]) {
							visited[nx][ny] = true;	// 방문 처리하고
							que.offer(new Node(nx, ny));	// 큐에 넣기
						}
					}
				}
			}
			depth++;	// 해당 너비 끝나면 +1 하기 

		}

	}

	static class Node {
		int x;
		int y;


		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;

		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
	}

//	static class LinkNode{
//		int i;
//		int depth;
//		LinkNode next;
//		public LinkNode(int i, LinkNode next) {
//			super();
//			this.i = i;
//			this.next = next;
//		}
//		@Override
//		public String toString() {
//			return "LinkNode [i=" + i + ", depth=" + depth + ", next=" + next + "]";
//		}
//		
//	}
}
