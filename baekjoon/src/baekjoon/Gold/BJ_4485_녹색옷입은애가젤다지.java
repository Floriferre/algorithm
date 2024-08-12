package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 5.
@see
@git
@youtube
@performance
@category #
@note 
*	<문제>
* 	링크의 고군분투 여정
*	0,0 에서 N-1, N-1 까지 이동하는데 잃는 최소 금액 구하기
*	상하좌우 1칸씩 이동 가능
*
*	<입력>
*	첫째줄 : 테스트케이스 크기
*	TC1 : 동굴의 크기를 나타내는 정수 N
*		N = 0인 입력이 주어지면 전체 입력이 종료	(2 ≤ N ≤ 125)
*	N개의 줄 : 도둑 루피의 크기 공백 구분
*		크기가 k이면 k루피를 잃는다 <0<=k<=9)
*	
*	<출력>
*	Problem 1: result 형태
*
*	<풀이>
*	그냥 탐색 돌리면 될 것 같음 최솟값 갱신해주고...
*	오랜만에 dfs를 써볼까 => 시간초과 난다...! ㅋㅋㅋㅋㅋㅋㅋㅋ
*	다익스트라를 써서 풀어야 한다고 한다.... 망했네 
*	최소 비용을 구하는 것이므로...
*
*/

public class BJ_4485_녹색옷입은애가젤다지 {

	private static class point implements Comparable<point> {
		int x, y;
		int rupee; // 현재까지 잃은 루피의 최솟값

		public point(int x, int y, int rupee) {
			super();
			this.x = x;
			this.y = y;
			this.rupee = rupee;
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + ", rupee=" + rupee + "]";
		}

		@Override
		public int compareTo(point o) {
			return this.rupee - o.rupee;
		}

	}
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	static int[][] map;
	static int [][] rupees; 	// 현재 경로까지의 루피의 최솟값 저장해둘 배열
	static int N;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int idx = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			
//			System.out.println("이번 회차: " + N);
			
			if(N == 0) {
				break;
			}
			
			map = new int [N][N];
			rupees = new int [N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					rupees[i][j] = Integer.MAX_VALUE;
				}
			}
			// 맵 입력
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			

			
//			solve(new point(0, 0, map[0][0]), new boolean [N][N]);
			bfs(0, 0, map[0][0], new boolean[N][N], rupees);
			
			sb.append("Problem " + idx + ":" + " " + rupees[N-1][N-1] + "\n");
			idx++;
		}

		System.out.println(sb);
	}
	
	
	private static void bfs(int x, int y, int rupee, boolean [][] visited, int [][] rupees) {
		PriorityQueue<point> que = new PriorityQueue<>();	// 제일 작은 루피를 갖고 있어야하므로
//		Queue<point> que = new ArrayDeque<>();	// 그냥 큐를 쓰면 가장 작은 것부터 돌지 않아서 제대로된 계산이 어렵다!
		que.add(new point(x, y, rupee));
		visited[x][y] = true;
		
		while(!que.isEmpty()) {
			point current = que.poll();
			
			for(int d=0; d< 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				// 범위 안에 있고, 방문한 적 없으며, 루피 최솟닶을 업데이트 할 수 있는 경우
				if(isIn(nx, ny) && !visited[nx][ny] && rupees[nx][ny] > current.rupee + map[nx][ny]) {
					rupees[nx][ny] = current.rupee + map[nx][ny];
					que.add(new point(nx, ny, rupees[nx][ny]));
					visited[nx][ny] = true;
				}
			}
			
			
		}
	}
	
	
	
	
	
	
	// dfs 로 풀기! 
	private static void solve(point current, boolean [][] visited) {
		visited[current.x][current.y] = true;
		
		
		if(current.rupee >= result) return;
		
		if(current.x == N-1 && current.y == N-1) {
			result = Math.min(result, current.rupee);
			return;
		}
		
		
		for(int d = 0; d < 4; d++) {
			int nx = current.x + dx[d];
			int ny = current.y + dy[d];
			
			if(isIn(nx, ny) && !visited[nx][ny]) {
//				visited[nx][ny] = true;
				solve(new point(nx, ny, current.rupee + map[nx][ny]), visited);
				visited[nx][ny] = false;
			}
			
		}
		
		
		
		
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < N && ny >=0 && ny < N;
	}

}
