package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 10.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	연구소 NxM
*	일부칸은 바이러스, 바이러스는 상하좌우 인접한 칸으로 퍼져나감
*	벽을 세워야하는데 벽은 3개 세울 수 있고, 
*	벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳 : 안전영역
*	안전영역 크기의 최댓값
*
*	<입력>
*	첫째줄 : 세로크기 N, 가로 크기 M
*	N개의 줄 : 0 빈캄, 1 벽, 2 바이러스
*	2<= 바이러스 <= 10
*	빈 칸의 개수 3개 이상임!
*
*	<출력>
*	안전영역 크기의 최댓값
*
*	<문제풀이>
*	조합 + bfs를 활용해보자! 
*	1. 벽을 세우자
*		조합으로 빈칸 중 3개를 골라 벽을 세워보자
*	2. 벽을 다 세웠으면 bfs로 바이러스를 퍼트리자
*	3. 퍼트리고 난 후의 안전영역 크기를 구해보자
*
*	* 입력 받을 때 바이러스의 위치를 따로 받아서 이걸로 탐색에 사용하자!
*
*
*
*
*/

public class TestA_BJ_14502_연구소 {

	static int N, M;
	static int map[][];
	
	static Queue<point> virus = new ArrayDeque<>();
	static boolean [][] visited;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	static int result = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.offer(new point(i, j)); 	// 바이러스 따로 담아두기
				}
			}
		}
//		for(int [] i : map) {
//			System.out.println(Arrays.toString(i));
//		}
//		System.out.println();
		
		
		
//		System.out.println(virus);
		
		combination(0, 0, new int[3]);
		
		System.out.println(result);
		
	}
	
	private static void combination(int nth, int startIndex, int [] choosed) {
		if(nth == 3) {	// 벽 세 개 다 골랐으면
			
			// 배열 복사해서 벽 위치 더 세운 후에 넘겨주자!
			int [][] temp = copyMap(map);
			
//			System.out.println("복사전");
//			for(int [] i : temp) {
//				System.out.println(Arrays.toString(i));
//			}
//			System.out.println();
			
			for(int i = 0; i < 3; i++) {
				temp[choosed[i]/M][choosed[i]%M] = 1;
			}
			
//			System.out.println("복사후");
//			for(int [] i : temp) {
//				System.out.println(Arrays.toString(i));
//			}
//			System.out.println();
			
			
			visited = new boolean[N][M];
			bfs(temp, visited);
			
			return;
		}
		
		for(int i = startIndex; i < N*M; i++) {
			if(map[i/M][i%M] == 0) {	// 벽을 세울 수 있는 곳만 골라야함! 
				choosed[nth] = i;
				combination(nth+1, i+1, choosed);				
			}
		}
	}
	
	private static void bfs(int [][] temp, boolean [][] visited) {
		
		Queue<point> virusTemp = new ArrayDeque<>();	// 새로운 큐 만들기 
		
		// virus 담아있는 큐 복사해서 사용하기
		int size = virus.size();
		while(size -->0) {
			point ct = virus.poll();
			virusTemp.offer(ct);
			virus.offer(ct);
		}
		
		visited[virusTemp.peek().x][virusTemp.peek().y] = true;
		
		
		// 바이러스 4개 탐색 돌리기
		while(!virusTemp.isEmpty()) {
			point current = virusTemp.poll();
			
			for(int d=0; d< 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				if(isIn(nx, ny) && !visited[nx][ny] && temp[nx][ny] == 0) {	// 바이러스가 퍼질 수 있는 위치면
					temp[nx][ny] = 2;
					visited[nx][ny] = true;
					virusTemp.offer(new point(nx, ny));
				}
			}
			
		}
//		if(countZero(temp) > result) {
//			for(int [] i : temp) {
//				System.out.println(Arrays.toString(i));
//			}
//			System.out.println(countZero(temp));
//			System.out.println();
//			
//		}
		
		// 결과값 업데이트 
		result = Math.max(countZero(temp), result);		
		
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx>=0 && nx<N && ny>=0 && ny<M;
	}
	
	// 안전지역 세는 메서드
	private static int countZero(int [][] map) {
		int cnt=0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	
	private static int[][] copyMap(int [][] map){
		int temp[][] = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j=0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		return temp;
	}
	
	
	private static class point{
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

}
