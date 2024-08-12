package baekjoon.Gold;

import java.io.*;
import java.util.*;

/*
@author 정여민
@since 2023. 10. 16.
@see https://www.acmicpc.net/problem/2234
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	벽이 있음
*	1. 성의 방의 개수
*	2. 가장 넓은 방의 넓이
*	3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
*
*	MxN  (1 ≤ M, N ≤ 50)
*	
*	<입력>
*	첫째줄 : N, M
*	M개의 줄 : N개ㅐ의 정수로 벽에 대한 정보
*	서쪽 1 북쪽 2 동쪽 4 남쪽 8 => 0 ~ 15까지의 값
*
*	<출력>
*	1, 2, 3에 대한 답을 각각 한 줄씩 출력
*
*	<풀이>
*	이게 무슨문제인가 했는데 비트마스킹을 사용하는 문제였다
*	1. 각 벽을 비트 단위로 해당 위치 저장
*	2. bfs로 방 개수 파악 및 방 별 넓이 확인
*		2-1. visited에 현재 위치가 몇번 방인지 저장(방별로 인덱스를 만들어줌)
*		2-2. 해시맵에 방번호 : 방사이즈호 저장
*
*	2. visited(현재위치 방번호)를 4방향을 돌면서 다른 방을 만났을 때
*		현재방 사이즈 + 다른방 사이즈가 최댓값인지 비교하기
*
*	참고 : https://cano721.tistory.com/36
*
*/

public class BJ_2234_성곽 {
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

	static int N, M;
	static int [][] map, visited;
	
	static int result2, result3;
	
	static Map<Integer, Integer> hash = new HashMap<>();	// 방 번호 : 방 크기
	
	static int [] dx = {0, -1, 0, 1};
	static int [] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [M][N]; // 방 정보
		visited = new int [M][N];	// 방의 인덱스 저장 및 방문 관리
		
		for(int i=0; i < M; i++) {	// map 정보 입력 받기
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 방 탐색하면서 번호 매기기 & 방 크기 체크
		bfs();
		
		// visited를 돌면서 다른 번호일 때 + 이웃한 방일 때 그 합 구하기
		sol3();
		
		sb.append(hash.size() + "\n" + result2 + "\n" + result3 + "\n");
		
		System.out.println(sb);

	}
	
	private static void bfs() {
		Queue<point> que = new ArrayDeque<>();
		
		
		int roomNum = 1;	// 방 개수
		int roomSize = 0; // 방 크기
		
		for(int i=0; i < M; i++) {
			for(int j=0; j < N; j++) {
				// 미방문이면 방문
				if(visited[i][j] == 0) {
					que.offer(new point(i, j));
					visited[i][j] = roomNum;	// 방 번호 부여
					
					// 방 크기
					roomSize = 1;
					
					while(!que.isEmpty()) {
						point current = que.poll();
						
						for(int d=0; d< 4; d++) {
							int nx = current.x + dx[d];
							int ny = current.y + dy[d];
							
							if(isIn(nx, ny)) {	// 범위 안에 있고
								// 현재 맵 기준에서 벽이 없으면서 방문하지 않았으면 1 << idx : 비트 연산을 통해 상하좌우 중 벽이 없는 곳을 알아냄 
								if((map[current.x][current.y] & (1<<d)) == 0 && visited[nx][ny] == 0 ) {
									visited[nx][ny] = visited[current.x][current.y];	// 같은 색깔로 업데이트
									que.offer(new point(nx, ny));
									// 방 크기 증가
									roomSize++;							
									
								}
							}
						}
					}
					// 방 저장 : que 다 돌음 = 해당 방 다 돌음
					hash.put(roomNum, roomSize);
//					System.out.println("현재 방 : " + roomNum + " : " + roomSize);
					roomNum++;
					// 방 크기가 최대면 업데이트
					result2 = Math.max(result2, roomSize);
					
				}
			}
		}
	}
	
	private static void sol3() {
		for(int i=0; i < M; i++) {
			for(int j=0; j < N; j++) {
				// 현재 방 번호
				int roomNum = visited[i][j];
				
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					// 범위 안에 있고, 현재와 방 번호가 다를 때
					if(isIn(nx, ny) && visited[nx][ny] != roomNum) {
						// 현재 방 + 다른 방 최댓값 업데이트
//						System.out.println(hash);
						result3 = Math.max(result3, hash.get(roomNum)  + hash.get(visited[nx][ny]));
					}
				}
			}
		}
	}
	
	
	
	private static boolean isIn(int nx, int ny) {
		return nx>=0 && nx <M && ny>=0 && ny<N;
	}
	
	

}
