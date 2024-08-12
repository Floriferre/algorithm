package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/*

@author 정여민
@since 2023. 8. 20.
@see https://www.acmicpc.net/problem/1987
@git
@youtube
@performance 15140KB 1160ms
@category #그래프탐색, DFS, 백트래킹
@note 
*	R*C 보드 각 칸에 대문자 알파벳 한 개
*	1,1(좌측 상단)에서 출발 -> 상하좌우 중 하나로 이동
*		단, 이미 지나온 알파벳은 또다시 지나칠 수 없음!
*		최대로 말이 몇 칸을 지날 수 있을까?
*
*	경로를 구하는 것 -> BFS? DFS? 
*	BFS -> 풀이 망했음. 백트래킹 부분이 너무 어려움... 이 경로와 저 경로 중 어떤 게 더 긴 것일까 체크하는 게 어려워...
*	DFS -> 굳 
*		방문처리를 어떻게 할 것인가? 
*		- visited 2차원 배열로?
*			- 근데 이러면 알파벳 처리도 해야하는데?
*			- 그런데 이미 지나온 경로에 있는 알파벳은 또 다시 방문하지 않으니까... visited 2차원 배열이 아니라 알파벳만 체크해도 되지 않나? 
*
*
*/

public class BJ_1987_알파벳 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	// 가로 세로 길이
	static int R, C;

	// 맵 정보 입력
	static char[][] map;
	// 방문정보 기록할 배열
	static boolean[][] visited;

	// 상하좌우 이동
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 알파벳 방문 정보 기록할 배열 : 0번이 A 25번이 Z // 대문자 A는 65 Z는 90
	static boolean[] alp = new boolean[26];

	// 최대 경로 길이 잴 변수
	static int depth;

	public static void main(String[] args) throws IOException {
		// 입력 부분
		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 맵 입력받기
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String S = st.nextToken();
			for (int j = 0; j < C; j++) {
				map[i][j] = S.charAt(j);
			}
		}

		// DFS 돌리자 (0,0)부터 돌리기 
		DFS(0,0,0);
		System.out.println(depth);

	}
	
	private static void DFS(int x, int y, int temp) {	// 현재 위치 x,y 현재까지의 경로 길이 temp
		// 방문한 적 있는 알파벳이면
		if(alp[(int)map[x][y] -65]) {
			// 현재까지의 경로 길이가 기존의 최대 길이보다 크다면 업데이트
			depth = Math.max(depth, temp);
			return;
		}
		else {	// 방문한 적 없는 알파벳이면
			alp[(int)map[x][y] -65] = true;	// 알파벳 방문 처리
			
			// 상하좌우 탐색을 하자
			for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			// 보드 범위 안에 있는 것만 다음으로 진행
			if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
				DFS(nx,ny, temp+1);	// 다음 방문을 하기 위해 현재까지의 경로 +1 을 해서 같이 보냄!
			}
		}
			alp[(int)map[x][y] -65] = false; // 방문 하지 않은 걸로 처리해야 다른 경로들도 파악할 수 있음 
		}
	}

	
	
	
	
	// BFS로 풀어보려 했는데 너무 어렵다...
//	private static void BFS() {
//		// 세팅
//		Queue<Node> que = new ArrayDeque<>();
//		// 방문 체크할 배열
//		visited = new boolean[R][C];
//
//		que.offer(new Node(0, 0, 1));
//		visited[0][0] = true;
//
//
//		while (!que.isEmpty()) {
//			int size = que.size();
//
//			Node head = que.poll();
//			
//			if(head.x == R-1 && head.y == C-1) {
//				depth = head.depth;
//				return;
//			}
//
//			System.out.println(map[head.x][head.y] + " : " + (map[head.x][head.y] - 65));
//
//			for (int d = 0; d < 4; d++) {
//				int nx = head.x + dx[d];
//				int ny = head.y + dy[d];
//
//				if (nx >= 0 && nx < R && ny >= 0 && ny < C && alp[(int) map[nx][ny] - 65] == false) {
//					if (!visited[nx][ny]) {
//						visited[nx][ny] = true;
//						que.offer(new Node(nx, ny, head.depth + 1));
//					}
//
//				}
//
//			}
//
//		}
//
//	}

	public static class Node {
		int x;
		int y;
		int depth;

		public Node(int x, int y, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

}
