package TestA;

import java.io.*;
import java.util.*;

/*
@author 정여민
@since 2023. 10. 12.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	젤다 화폐 루피(rupee) 中 도둑루피 : 소지금 감소
*	링크 (0,0) -> (N-1, N-2)로 가려고 한다 
*	한 번에 상하좌우 이동 가능
*	잃을 수 밖에 없는 최소 금액?
*
*	<입력>
*	TC1 : 동굴 크기 N (N이 0이면 종료)
*	N개의 줄 : 도둑루피의 크기	0 <= 루피 <= 9
*	
*	<출력>
*	Problem 1: 20 형식
*
*	<풀이>
*	최단경로를 찾는 문제와 같다 
*	일반적으로 가중치가 없을 때는 BFS를 쓰지만, 이건 가중치가 있으므로 다익스트라를 쓴다
*	다익스트라 : 해당 장소를 갈 때의 최솟값을 계속 업데이트 하는 방식 
*
*	최솟값을 업데이트 해야하니까... 우선순위 큐를 사용해보는 건 어떨까? 가장 작은 애부터 큐에서 꺼내서 탐색하는 걸로
*	BFS를 써보자!
*
*
*
*
*/

public class TestA_BJ_4485_녹색옷입은애가젤다지 {

	static int [][] map;
	static int [][] rupees;
	static int N;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int idx = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			
			if(N==0) break;
			
			map = new int[N][N];
			rupees = new int[N][N];
			
			for(int [] i : rupees) {
				Arrays.fill(i, 2000);	// 도둑루피들 최솟값 초기화
			}
		
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			BFS();
			
			sb.append("Problem " + idx+": " + rupees[N-1][N-1] + "\n");
			idx++;
		}
		
		System.out.println(sb);
	}
	
	// bfs 돌립시다 
	private static void BFS() {
		PriorityQueue<point> que = new PriorityQueue<>();
		que.offer(new point(0, 0, map[0][0]));	// 가장 처음 좌표 0,0에서 시작
		rupees[0][0] = map[0][0];
		
		// 또 가도 되니까 visited는 없어도 됨! 
		
		while(!que.isEmpty()) {
			point current = que.poll();	// 현재까지 값 중 가장 작은 값 넣기
			
			if(current.x == N-1 && current.y == N-1) return;
			
			for(int d=0; d<4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				if(isIn(nx, ny) && rupees[nx][ny] > rupees[current.x][current.y] + map[nx][ny]) {
					rupees[nx][ny] = rupees[current.x][current.y] + map[nx][ny];	// 루피 값  업데이트
					que.offer(new point(nx, ny, rupees[nx][ny]));
				}
			}
		}
		
		
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx>=0 && nx <N && ny>=0 && ny<N;
	}

	static class point implements Comparable<point> {
		int x, y, rupees;

		public point(int x, int y, int rupees) {
			super();
			this.x = x;
			this.y = y;
			this.rupees = rupees;
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + ", rupees=" + rupees + "]";
		}

		@Override
		public int compareTo(point o) {
			return this.rupees - o.rupees;
		}
	}
}
