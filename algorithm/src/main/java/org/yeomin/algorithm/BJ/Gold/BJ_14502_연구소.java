package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 25.
@see https://www.acmicpc.net/problem/14502
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	바이러스 유출 : 벽을 세워 막자!
*	NxM 크기의 연구소, 벽은 칸 하나
*	일부 칸은 바이러스 존재. 바이러스가 상하좌우 인접한 빈 캄으로 퍼져나감
*	세울 수 있는 벽의 수 :3개 (꼭 세워야함!!!)
*	0 : 빈칸 / 1 : 벽 / 2 : 바이러스
*
*	벽 3개를 세운 후 바이러스가 퍼질 수 없는 곳 : 안전 영역
*	안전 영역의 크기의 최댓값을 구하는 프로그램 작성
*
*	<입력>
*	찻째줄 : 세로 N, 가로 M	(3<=N,M <=8)
*	N개의 줄 : 지도의 모양
*	0 : 빈칸 / 1 : 벽 / 2 : 바이러스 (2<= 바이러스 <=10)
*
*	<풀이>
*	어 이걸 어케 하냐? 고민이네 
*	브루트포스로 가? nC3은 좀 에반데 
*	바이러스가 못 퍼지게 막아야 해 근데 어떻게 해? 
*	ㄹㅇ 브루트포스 가..? 
*	0인 곳의 위치 ArrayList에 넣고 갯수 
*	배열 복사해서 해당 위치만 1로 바꿔주고 
*	탐색 고? 
*	
*
*
*
*/

public class BJ_14502_연구소 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static List<point> virus, space;
	static int result = Integer.MIN_VALUE;

	static class point {
		int x, y;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		/**
		 * @param x
		 * @param y
		 */
		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 세로 가로 길이
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		space = new ArrayList<point>();
		virus = new ArrayList<point>();

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if (map[n][m] == 0) { // 빈 공강 기록
					space.add(new point(n, m));
				} else if (map[n][m] == 2) { // 바이러스 공간 기록
					virus.add(new point(n, m));
				}
			}
		}

		ArrayList<point> choosed = new ArrayList<>();
		choosed.add(new point(0, 0));
		choosed.add(new point(0, 0));
		choosed.add(new point(0, 0));

		Combination(0, 0, choosed);

		System.out.println(result);

	}

	private static void bfs(int[][] map, boolean[][] visited, int x, int y) {
		Queue<point> que = new ArrayDeque<point>();
		que.offer(new point(x, y));
		visited[x][y] = true;

		while (!que.isEmpty()) { // 큐가 비어있지 않는 동안
			point current = que.poll();

			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];

				if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0) { // 탐색 가능한 곳이면
					map[nx][ny] = 2; // 바이러스가 침투한 곳은 2로 만들어버리기
					que.offer(new point(nx, ny));
					visited[nx][ny] = true;
				}
			}

		}

	}

	// 탐색 시작
	private static void dfs(int[][] map, boolean[][] visited, int x, int y) {
		visited[x][y] = true;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0) { // 탐색 가능한 곳이면
						map[nx][ny] = 2; // 바이러스가 침투한 곳은 2로 만들어버리기
						dfs(map, visited, nx, ny);
						visited[nx][ny] = false;
					}
				}
			}
		}
	}

	private static void Combination(int nth, int startIndex, ArrayList<point> choosed) {
		if (nth == 3) { // 벽은 딱 3개만 세운다
//			System.out.println("choosed는 ?" +  choosed);
			int[][] temp = copymap(map);
			for (int i = 0; i < 3; i++) {
				temp[choosed.get(i).x][choosed.get(i).y] = 1;
//				System.out.println(choosed.get(i));
			}

//			System.out.println("탐색전 " + Arrays.deepToString(temp));

			boolean[][] visited = new boolean[N][M];
			for (int i = 0; i < virus.size(); i++) {
//				dfs(temp, visited, space.get(i).x, space.get(i).y);
				bfs(temp, visited, virus.get(i).x, virus.get(i).y);
			}
//			System.out.println("탐색후 " + Arrays.deepToString(temp));
			result = Math.max(result, count0(temp));

//			System.out.println(result);

			return;
		}

		for (int i = startIndex; i < space.size(); i++) {

			// 여기서 무조건 더하지 말고
			choosed.get(nth).x = space.get(i).x;
			choosed.get(nth).y = space.get(i).y;

//			choosed.add(space.get(i));
//			System.out.println("space값:" + space.get(i));
			Combination(nth + 1, i + 1, choosed);
		}
	}

	// 안전지역 세기
	private static int count0(int[][] map) {
		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					result++;
				}
			}
		}

		return result;
	}

	// 배열 복사
	private static int[][] copymap(int[][] map) {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}

		return temp;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
