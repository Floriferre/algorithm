package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
*	7명의 여학생으로 구성
*	7명의 자리는 가로나 세로로 반드시 인접 (상하좌우 탐색을 돌리자)
*	반드시 이다솜파일 필요는 없음(S 이다솜, Y 임도연)
*	단, 7명 중 4명 이상은 이다솜 파여야함!
*	소문난 칠공주를 결성할 수 있는 모든 경우의 수 구하기
*
*	<입력>
*	S(이다솜파), Y(임도연파)를 값으로 갖는 5*5 행렬이 공백 없이 첫째줄부터 다섯줄게 걸쳐 주어짐
*
*	<출력>
*	소문난 칠공주를 결성할 수 있는 모든 경우의 수 출력
*
*	<풀이>
*	1. bfs를 돌면서 7명을 선택하고 이들 중 이다솜 파가 몇명인지 세기 -> 망함 
*	2. 조합으로 7자리를 구한 후에 이들을 탐색하자...
*
*
*/

public class BJ_1941_소문난칠공주 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int[][] map = new int[5][5]; // 0과 1로 탐색하겠다.

	static boolean[] visited; // 방문 여부 체크

	static int[] choosed = new int[7]; // 7개 자리 선택

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < 5; j++) {
				if (s.charAt(j) == 'S') { // 이다솜파 : 1, 임도연파 : 0
					map[i][j] = 1;
				} else {
					map[i][j] = 0;
				}
			}
		}
		
//		System.out.println(Arrays.deepToString(map));

		combination(0, 0, choosed, 0);

		System.out.println(result);

	}

	private static class point {
		int x, y;


		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + "]";
		}

	}

	// 25자리 중 7자리 구하기
	private static void combination(int nth, int startIndex, int[] choosed, int dasomCnt) {
		// 이다솜파가 열세이면 return
		if(nth - dasomCnt > 3) return;
		
		if (nth == choosed.length) {
//			System.out.println(dasomCnt);
			// bfs 돌리기
			visited = new boolean[7];	// 7개 자리 방문했는지 여부 
			bfs(choosed[0] / 5, choosed[0] % 5, visited);
			return;
		}

		// 인덱스를 결과 배열에 넣기! 가지치기를 위해 이다솜 파인지 아닌지 결과도 넣어주자 
		for (int i = startIndex; i < 25; i++) {
			choosed[nth] = i;
			combination(nth + 1, i + 1, choosed, (map[i/5][i%5] == 1)? dasomCnt +1 : dasomCnt);
		}
	}

	private static void bfs(int x, int y, boolean[] visited) {
		Queue<point> que = new ArrayDeque<>();
		que.offer(new point(x, y));
		visited[0] = true;
		int num = 1;
		while(!que.isEmpty()) {	// 큐가 비어있지 않으면
			point current = que.poll();
			for(int d=0; d<4; d++) {	// 사방 탐색 시작
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				if(isIn(nx, ny)) {	// 범위 안에 있을 때만 탐색
					int nxt = 5*nx + ny;	// choosed 배열에 있는 자리랑 맞는지 검증
					for(int i = 0; i < 7; i++) {	// choosed 배열에 있는 자리 확인
						if(!visited[i] && choosed[i] == nxt) {	// 선택된 7자리를 갈 수 있으면
							visited[i] = true;
							num++;	// 만족하는 자리가 있으면 num + 1 (사람수)
							que.offer(new point(nx, ny));
						}
					}
				}
			}
		}
		if(num == 7) {
			result++;
		}
	}
	
	private static boolean isIn(int nx, int ny) { // 범위 안에 있는지 체크
		return nx >= 0 && nx < 5 && ny >= 0 && ny < 5;
	}

//	private static void bfs(int x, int y, boolean[][] visited) {
//		Queue<point> que = new ArrayDeque<>();
//		que.offer(new point(x, y, 1, map[x][y]));
//		visited[x][y] = true;
//
//		while (!que.isEmpty()) {
//			int size = que.size();
//
//			System.out.println(que);
//
//			while (size-- > 0) {
//
//				point current = que.poll();
//
//				if (current.depth > 7) { // 7명 이상인 경우는 result 리턴해버리기
//					return;
//				}
//
////				if (current.depth == 6) { // 7명 결성했을 때
////
////					System.out.println(current.check);
////					System.out.println(current);
////
////					if (current.check >= 4) { // 이다솜파가 더 많으면
////						result++;
////					}
////				}
//
//				for (int d = 0; d < 4; d++) {
//					int nx = current.x + dx[d];
//					int ny = current.y + dy[d];
//					if (isIn(nx, ny) && !visited[nx][ny]) { // 범위 안에 있고 선택할 수 있으면
//						//record[nx][ny] = Math.max(record[nx][ny], record[current.x][current.y] + map[nx][ny]);
//						
//						que.offer(new point(nx, ny, current.depth + 1, record[nx][ny]));
//						
//						visited[nx][ny] = true;
//					}
//
//				}
//
//			}
//		}
//
//	}



}
