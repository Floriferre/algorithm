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
@since 2023. 9. 27.
@see https://www.acmicpc.net/problem/3055
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	지도 R*C 
*	비어있는 곳 : .
*	물이 차있는 지역 : *
*	돌 : X
*	비버 굴 : D
*	고슴도치 위치 : S
*	
*	고슴도치는 상하좌우로 이동
*	물도 비어있는 칸으로 확장 (닿은 곳은 전부 물이 참)
*	물과 고슴도치는 돌을 통과 X
*	고슴도치는 물로 차있는 구역 이동 X
*	물도 비버의 소굴로 이동 X
*	
*	지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동히기 위해 필요한 최소 시간 구하기 => BFS!
*
*	<입력>
*	첫째줄 : R, C (r,c <=50)
*	R개의 줄 : 지도 주어짐 => char형 배열로 받자
*
*	<출력>
*	고슴도치가 비버 굴로 이동할 수 있는 가장 빠른 시간 출력
*	만약 이동 불가? => KAKTUS 출력
*
*	<풀이>
*	고슴도치가 비버 굴로 이동할 수 있는 가장 최소 시간을 찾는 거니까  BFS 사용
*	물 <= 큐로 관리하자. 
*		이미 물이 차있는 곳은 다시 고려할 필요 없으니까 매 회차마다 물을 꺼내서 사방탐색 후 가능한 곳(.)은 물로 표시하고 큐에 넣어주기
*	고슴도치의 사방탐색
*
*
*
*
*
*/


public class BJ_3055_탈출 {

	static int R, C;
	static char [][] map;
	
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
	
	static Queue<point> water = new ArrayDeque<>();
	
	static point hedgehog, beaver;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int c = 0; c < C; c++) {
				map[r][c] = temp.charAt(c);
				if(map[r][c] == 'D') {
					beaver = new point(r, c);
				}else if(map[r][c] == 'S') {
					hedgehog = new point(r, c);
				}else if(map[r][c] == '*') {
					water.add(new point(r, c));
				}
				
			}
		}
		
		
		int result = bfs(map, hedgehog.x, hedgehog.y, new boolean[R][C]);
		
		if(result == -1) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
		
		
		
	}
	
	
	private static int bfs(char [][] map, int x, int y, boolean [][] visited) {	// x,y에서 탐색 시작
		
		Queue<point> que = new ArrayDeque<>();
		que.add(new point(x, y));
		visited[x][y] = true;
	
		int depth = 0;
		while(!que.isEmpty()) {
			
			// 물 넓히기
			int watersize = water.size();
			while(watersize -- > 0) {
				point currentwater = water.poll();
				for(int d=0; d<4; d++) {
					int nx = currentwater.x + dx[d];
					int ny = currentwater.y + dy[d];
					if(isIn(nx, ny) && map[nx][ny] == '.') {	// 범위 안에 있고 물이 갈 수 있으면 물 확장ㄹ
						map[nx][ny] = '*';
						water.offer(new point(nx, ny));
					}
				}				
			}
			
			int size = que.size();
			while(size-->0) {
				point current = que.poll();
				
//				System.out.println(current);
				
				// 비버에 도달하면 끝!
				if(current.x == beaver.x && current.y == beaver.y) {
					return depth;
				}				
				
				for(int d=0; d<4; d++) {
					int nx = current.x + dx[d];
					int ny = current.y + dy[d];
					if(isIn(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == 'D') && !visited[nx][ny]) {	// 범위 안에 있고 물이 갈 수 있으면 물 확장ㄹ
						map[x][y] = '.';
						map[nx][ny] = 'S';
						que.offer(new point(nx, ny));
						visited[nx][ny] = true;
					}
				}
				
				
			}
			depth++;	
		}
		
		return -1;
	}

	private static boolean isIn(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C;
	}
	
	
	
}
