package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 5.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWXRQm6qfL0DFAUo&probBoxId=AYr3k03KgA4DFAV6&type=PROBLEM&problemBoxTitle=1004%EC%A3%BC&problemBoxCnt=4
@git
@youtube
@performance
@category #
@note 
*	<문제> 
*	벽돌 깨기
*	W x H 배열, 0은 빈공간, 숫자는 벽돌 
*	1. 구슬은 좌우로만 움직일 수 있고, 맨 위에 있는 벽돌만 깰 수 있음
*	2. 벽돌은 숫자 1~9로 표현, 구슬이 명중한 벽돌은 상하좌우 (벽돌에 적힌 숫자 -1)칸 만큼 같이 제거
*	3. 제거되는 범위 내에 있는 벽돌은 동시에 제거된다 
*	4. 빈 공간이 있을 경우 벽돌은 밑으로 떨어짐
*
*	<제약사항>
*	구슬 갯수 1 ≤ N ≤ 4
*	가로 길이 2 ≤ W ≤ 12
*	세로 길이 2 ≤ H ≤ 15
*
*	<입력> 
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : N, W, H
*	H줄 : W개의 벽돌 정보
*
*	<풀이>
*	1. 벽돌에 구슬 떨어뜨리기 (제일 top에 떨어뜨리기)
*		- top을 구하는 함수 만들어두기
*	2. 벽돌 터뜨리기
*		1) 영향 받은 거 전부 que에 넣고 넣으면서 map의 값 0으로 바꾸기
*		2) que에서 하나씩 꺼내며 범위 탐색
*		3) 열 탐색하며 중간에 빈 곳이 있으면 당기기 
*			- 0이 아닌거 que에 넣고 다시 map에 써주기 
*	3. 벽돌 세기
*		- 0이 아닌 것 세기 
*
*
*/

public class SWEA_5656_벽돌깨기 {

	static class point {
		int x, y;
		int num;

		public point(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + ", num=" + num + "]";
		}

	}

	static int N, C, R;
	static int[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			result = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 구슬 개수
			C = Integer.parseInt(st.nextToken()); // 열 개수
			R = Integer.parseInt(st.nextToken()); // 행 개수

			map = new int[R][C];

			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 맵 카피해서 주기
			int[][] temp = copyMap(map);

			DupPermuataion(0, new int [N]);

	

			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}

	
	// 중복 순열로 구슬 던질 위치 고르기 12PI4
	private static void DupPermuataion(int nthChoice, int [] choosed) {
		if(nthChoice == choosed.length) {
			// 여기에서 사용할 지도 
			int [][] temp = copyMap(map);
			
			for(int i =0; i < choosed.length; i++) {
				bomb(choosed[i], height(choosed[i], temp), new boolean[R][C], temp);
			}
			
			// 구슬 다 던졌으면 0 아닌거 세기
			result = Math.min(countBrick(temp), result);
			return;
		}
		
		for(int i = 0; i < C; i++) {
			choosed[nthChoice] = i;
			DupPermuataion(nthChoice+1, choosed);
		}
	}
	
	
	
	
	// 벽돌 터트리기
	private static void bomb(int x, int y, boolean[][] visited, int[][] map) {
		Queue<point> que = new ArrayDeque<>();
		que.offer(new point(x, y, map[x][y]));
		visited[x][y] = true;
		map[x][y] = 0;

		while (!que.isEmpty()) {
			point current = que.poll();

			for (int d = 0; d < 4; d++) {
				for (int l = 1; l < current.num - 1; l++) {
					int nx = current.x + dx[d] * l;
					int ny = current.y + dy[d] * l;
					if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != 0) { // 방문한 적 없고 범위 안이면
						que.offer(new point(nx, ny, map[nx][ny]));
						map[nx][ny] = 0;
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		// 0인거 땡기는 거 넣어야함! que선언해서 que에 0이 아닌 거 넣고 다시 map에 넣어주기
		for(int j = 0; j < C; j++) {
			Queue<point> queMap = new ArrayDeque<>();	// map 옮길 큐
			Queue<point> queVisited = new ArrayDeque<>();	// visited 옮길 큐
			for(int i = 0; i < R; i++) {
				if(map[i][j] != 0) {
					queMap.offer(new point(i, j, map[i][j]));
//					queVisited.offer(new point(x, y, num));
				}
			}
		}
		
		

	}

	// 각 열별로 가장 위에 있는 거 찾기
	private static int height(int col, int[][] map) { // 열 정보를 입력받아 각 열에서 제일 위에 있는 것의 좌표 반환
		for (int i = 0; i < C; i++) {
			if (map[i][col] != 0) {

//				break;

				return i;
			}
		}

		// 다 터져서 없는 경우 : 터트릴 수 없음
		return -1;
	}

	// map 복사해서 사용
	private static int[][] copyMap(int[][] map) {
		int[][] temp = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	// map에서 벽돌의 개수 세는 메서드
	private static int countBrick(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < R && ny >= 0 && ny < C;
	}

}
