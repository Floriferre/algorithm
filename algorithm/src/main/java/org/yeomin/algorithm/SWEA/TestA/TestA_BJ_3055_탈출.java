package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 12.
@see https://www.acmicpc.net/problem/3055
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	숲 지도 R*C
*	. : 비어있는 곳
*	* : 물이 차있는 곳
*	X : 돌이 있는 곳
*	D : 비버의 굴
*	S : 고슴도치 위치 
*
*	고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동 (상하좌우)
*	물도 매 초마다 비어있는 칸으로 상하좌우 늘어감
*	물과 고슴도치는 돌을 통과 X
*	고슴도치는 물이 차있는 구역으로 갈 수 없고, 물도 비버의 소굴로 이동 X
*	
*	고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간 구하기
*	
*	고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 다음 시간에 물이 찰 예정인 칸으로 이동 X
*
*	<입력>
*	첫째줄 : R, C <= 50
*	R개의 줄 : 지도, D, S는 하나! 
*
*	<출력>
*	고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간 출력
*	이동 불가하면 KAKTUS 출력
*
*	<풀이>
*	1. 물이 찰 예정인 칸으로 고슴도치가 이동 X => 물을 먼저 이동시키자
*	2. 고슴도치 4방탐색 이동 => 굳이 뒤로 돌아갈 이유는 없을 듯? 
*	3. BFS를 쓰자! (최단거리이므로)
*	4. 고슴도치, 비버굴 위치 미리 기록해두고 비교하기 => point 클래스 만들어 쓰자
*	5. 물을 큐에 넣어두고 계속 확장시키기 
*
*/

public class TestA_BJ_3055_탈출 {

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
	
	
	static int R,C, result;
	static char [][] map;
	static boolean [][] visited;
	
	static Queue<point> water = new ArrayDeque<>();
	static Queue<point> que = new ArrayDeque<>();
	static point cave;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		result = Integer.MAX_VALUE;	// 결과값 초기화
		
		map = new char[R][C];
		for(int i=0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j=0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'D') {
					cave = new point(i, j);
				}else if(map[i][j] == 'S') {
//					hedgehog = new point(i, j);
					que.offer(new point(i, j));
				}else if(map[i][j] == '*') {
					water.offer(new point(i, j));
				}
			}
		}
		visited = new boolean[R][C];
		bfs();
		
		if(result == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}

	}
	
	private static void bfs() {
//		que.add(hedgehog);	 // 고슴도치 처음 위치
		visited[que.peek().x][que.peek().y] = true;
		
		int time = 0;
		while(!que.isEmpty()) {
			// 물을 먼저 이동시킨다
			int waterSize = water.size();
			while(waterSize-->0) {
				point currentWater = water.poll();
				for(int d=0; d< 4; d++) {
					int nx = currentWater.x + dx[d];
					int ny = currentWater.y + dy[d];
					
					if(isIn(nx, ny) && map[nx][ny] == '.') {	// 물이 갈 수 있는 곳이면 
						map[nx][ny] = '*';
						water.offer(new point(nx, ny));
					}
				}
			}
			
			
			// 고슴도치 이동시키기 
			int size = que.size();
			
			while(size-->0) {	// 고슴도치 이동시키기
				point current = que.poll();
				
				if(current.x == cave.x && current.y == cave.y) {
					result = time;
					return;
				}
				
				for(int d=0; d<4; d++) {
					int nx = current.x + dx[d];
					int ny = current.y + dy[d];
					
					if(isIn(nx, ny) && !visited[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
						map[nx][ny] = 'S';
						que.offer(new point(nx, ny));
						map[current.x][current.y] = '.';
						visited[nx][ny] = true; // visited 꼭 체크해주기...!!!!! 
					}
				}
			}
			time++;	
		}
		
		return;
		
		
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx>=0 && nx <R && ny>=0 && ny<C;
	}

}
