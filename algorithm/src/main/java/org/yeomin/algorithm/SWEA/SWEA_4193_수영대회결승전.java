package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;


/*

@author 정여민
@since 2023. 9. 15.
@see https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaG6_6AGQDFARV
@git
@youtube
@performance
@category #
@note 
*	<문제> 
*	수영 대회 맵
*	0 : 빈 공간 / 1 : 장애물 / 2 : 소용돌이
*	소용돌이 > 2초동안 생겼다 1초동안 사라지길 반복하는 것
*	소용돌이가  사라진 곳은 이동 가능. 한 번 간 소용돌이는 위에서 머무르기 가능!
*	가장 빠른 경로로 갔을 때 시간 찾기
*	
*	<입력>
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : 수영장 크기 N
*	N개의 줄 : 수영장 상태 보드
*	다음 줄 : 시작 위치
*	다다음줄 : 종료 위치
*
*	<풀이> 
*	처음에 DFS로 풀려고 했는데 테케가 일부만 맞고 일부는 틀리더라...
*	생각해보니까 이 문제는 최단 경로로 가야하는 것이므로! BFS를 써야함! 
*	1. BFS를 돌릴 때 큐에 어떤 정보를 저장할 것인가?
*		info class를 하나 만들어서 현재 위치(x,y)와 몇초가 지났는지(depth)를 같이 넘겨주자!
*	2. 소용돌이는 어떻게 체크할 것인가?
*		범위 안에 들어온 것 중(보드 내부에 있고, 해당 좌표가 2이면) 현재까지 흐른 초(depth)%3 ==2일 때마다
*		소용돌이가 사라지므로 다음 DFS 탐색을 할 때 depth +3을 해서 넘겨주자!
*	3. 마지막 위치에 도달하면 result에 현재까지의 경로 값이랑 비교해서 최솟값으로 업데이트 
*	
*
*
*/

public class SWEA_4193_수영대회결승전 {

	static int[][] map;

	static boolean[][] visited;

	static int N;

	public static class info {
		int x, y, depth; // 위치

		/**
		 * @param x
		 * @param y
		 */
		public info(int x, int y, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
		}

		public info() {

		}

	}

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

			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작 위치
			info start = new info();
			st = new StringTokenizer(br.readLine());
			start.x = Integer.parseInt(st.nextToken());
			start.y = Integer.parseInt(st.nextToken());

			// 도착 위치
			info end = new info();
			st = new StringTokenizer(br.readLine());
			end.x = Integer.parseInt(st.nextToken());
			end.y = Integer.parseInt(st.nextToken());

//			dfs(map, visited, 0, start, end, 0);
			bfs(map, visited, start, end);

			if (result == Integer.MAX_VALUE) {
				result = -1;
			}
			sb.append("#" + t + " " + result + "\n");
		}

		System.out.println(sb);

	}

	// 최단 경로 찾는 문제 > bfs
	// 언니 화이팅...
	private static void bfs(int[][] arr, boolean[][] visited, info current, info end) {
		Queue<info> que = new ArrayDeque<info>();
		que.add(new info(current.x, current.y, 0));
		visited[current.x][current.y] = true;

		while (!que.isEmpty()) {// 큐가 비어있지 않은 동안 돌기 depth 찾기
			int size = que.size();

//			int i = 0;
			while (size-->0) {
				info temp = que.poll();

				if (temp.x == end.x && temp.y == end.y) {
					result = Math.min(result, temp.depth);
					return;
				}

				for (int d = 0; d < 4; d++) {
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					// 방문한 적이 없고, 장애물이 있는 곳이 아니면 갈 수 있음!
					if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
						if (map[nx][ny] == 2) {
							if (temp.depth % 3 != 2) {// 소용돌이 2초 기다려야함
								visited[temp.x][temp.y] = true;
								que.add(new info(temp.x, temp.y, temp.depth + 1));
							} 
//							else if (temp.depth % 3 == 1) {	// 소용돌이 1초 기다려야함
//								visited[temp.x][temp.y] = true;
//								que.add(new info(x, y, temp.depth + 1));
//							} 
							else {
								visited[nx][ny] = true;
								que.add(new info(nx, ny, temp.depth + 1));
							}
						} else {
							visited[nx][ny] = true;
							que.add(new info(nx, ny, temp.depth + 1));
						}
						
					}
				}
//				i++;
			}
		}
	}

//	private static void dfs(int[][] arr, boolean[][] visited, int depth, info current, info end, int idx) {
//		visited[current.x][current.y] = true;
//
//		if(depth >= result) return;	// 가지치기 
//		
//		if (current.x == end.x && current.y == end.y) {
//			result = Math.min(result, depth);
//			return;
//		}
//
//		for (int d = 0; d < 4; d++) {
//			int nx = current.x + dx[d];
//			int ny = current.y + dy[d];
//			// 방문한 적이 없고, 장애물이 있는 곳이 아니면 갈 수 있음!
//			if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
//				if (map[nx][ny] == 2) {	// 소용돌이가 사라진 상황은 어떻게 해결해야 할까? 
//					if(idx%3 == 0) {	//소용돌이가 있음
//						dfs(arr, visited, depth + 3, new info(nx, ny), end, idx +1 );						
//					}else if (idx%3 == 1) {
//						dfs(arr, visited, depth + 2, new info(nx, ny), end, idx +1 );	
//					}
//					else {	// 소용돌이가 없는 상황
//						dfs(arr, visited, depth + 1, new info(nx, ny), end, idx +1 );
//					}
//				} else {
//					dfs(arr, visited, depth + 1, new info(nx, ny), end, idx + 1);
//					
//				}
//				visited[nx][ny] = false;
//			}
//		}
//
//	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}

}
