package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;




/*
@author 정여민
@since 2023. 10. 10.
@see https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaG6_6AGQDFARV
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	N*N 바다
*	장애물 1, 소용돌이 2
*	소용돌이는 2초간 유지, 1초간 사라지기를 반복
*	가장 빠른 길을 찾으려고 함! -> BFS
*	몇초만에 골인 가능?
*
*	<입력>
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : 수영장 크기 N 2<=N<=15
*	N개의 줄 : 수영장 모양 	0 : 빈공간 / 1 : 장애물 / 2 : 소용돌이
*	시작위치 A, B 0<=A,B<=N-1
*	도착위치 C, D 1<=C,D<=N-1
*	
*	<출력>
*	각 테스트 케이스마다 테스트 케이스의 번호와 이동시간을 공백을 두고 표시한다 
*	도착 할 수 없다면 -1을 출력한다.
*
*	<풀이>
*	가장 빠른 길 : BFS를 쓰자!
*	소용돌이가 관건 : 큐를 사용해서 소용돌이를 관리하자 < 리스트에 넣어도 괜찮을 것 같긴 한데 
*	소용돌이가 3초를 주기로 사라졌다 생기는데 
*	depth로 관리하자! depth%3 == 2일 때마다 소용돌이 부분 갈 수 있도록 
*	소용돌이에 걸리면 못 움직이는 걸로 -> delta 배열을 제자리에 있는 경우도 만들어야 한다 
*	다음에 갈 수 있는 곳을 큐에 넣을 때, 소용돌이면 큐에 넣기 
*
*
*/
public class TestA_SWEA_4193_수영대회결승전 {
	
	static class point{
		int x, y;

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
	

	static int result, N;
	static int [][] map;
	static boolean [][] visited;
	static point start, end;
	static int depth;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = { 0, 0, -1, 1};
	
	static List<point> falls;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t <= T; t++) {
			result = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 수영장 크기
			map = new int[N][N];
			falls = new ArrayList<>();	// 소용돌이 저장
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) {
						falls.add(new point(i, j));
					}
				}
			}
			st = new StringTokenizer(br.readLine());
			start = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			end = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			visited = new boolean[N][N];
			
			BFS(start, visited);
			
			
			if(result == Integer.MAX_VALUE) {
				sb.append("#"+t+ " " + -1 + "\n");				
			}else {
				sb.append("#"+t+ " " + result + "\n");				
			}
			
		}
		System.out.println(sb);
	}
	
	private static void BFS(point start, boolean [][] visited) {
		Queue<point> que = new ArrayDeque<>();
		que.offer(start);
		visited[start.x][start.y] = true;
		
		depth = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			
			
			while(size-->0) {
				point current = que.poll();
				// 도작첨이면 
				if(current.x == end.x && current.y == end.y) {
					result = Math.min(result, depth);
					return;
				}
				
				for(int d=0; d< 4; d++) {
					int nx = current.x + dx[d];
					int ny = current.y + dy[d];
					if(isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {	// 범위 안에 있고, 방문한 적 없고, 장애물이 아니면 
						if(map[nx][ny] == 2) {
							if(depth%3 == 2) {	// 소용돌이 지날 수 있을 때
								que.offer(new point(nx,ny));
								visited[nx][ny] = true;
							}else {	//  아직 소용돌이면
								que.offer(current);
							}
						}else {	// 소용돌이가 아닌 경우 그냥 큐에 넣기
							que.offer(new point(nx, ny));
							visited[nx][ny] = true;
						}
					}
				}
			}
			depth++;
		}
		
	}

	private static boolean isIn(int nx, int ny) {
		return nx>=0 && nx <N && ny>=0 && ny<N;
	}
	
}
