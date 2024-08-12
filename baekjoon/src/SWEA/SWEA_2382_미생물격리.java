package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 2.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	정사각형 구역 K개의 미생물 군집
*	N*N 
*	가장자리 부분에 특수 약품 
*	
*	1. 미생물 군집 위치, 군집 내 미생물 수, 이동 방향 
*	2. 군집은 1시간마다 이동방향에 있는 다음 셀로 이동
*	3. 약품이 칠해진 셀에 도착하면 군집 내 미생물 절반 사망, 이동 방향 반대
*		살아남은 미생물 수 : 원래 미생물 수 /2 소수점 이하 버린 값 => 미생물 1마리만 있으면 군집 사라지게됨
*	4. 두 개 이상의 군집이 한 셀에 모이면 군집이 합쳐짐
*		합쳐진 군집의 이동방향은 군집 중 미생물 수가 가장 많은 군집의 이동방향이 됨
*	M시간 후 미생물 수의 총합?
*
*	<제약사항>
*	셀 개수	5<=N<=100
*	군집 개수	5<=K<=1000
*	격리시간	1<=M <= 1000
*	미생물 수 : 1~1000
*	군집 이동방향은 상하좌우 중 하나
*
*
*	<풀이>
*	큐에 미생물들 넣어두고 이동하면 스택을 사용해서 넣어두기 
*	각 칸마다 스택 빌 때까지 pop시켜서 미생물 군집 계산하기? 
*	아니면 3차원 배열 만들어서 탐색 하기
*
*/

public class SWEA_2382_미생물격리 {

	// 상 좌 하 우
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static class microbe {
		int x, y;
		int dir; // 방향 1상 2하 3좌 4우
		int size; // 미생믈 개수

		public microbe(int x, int y, int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public String toString() {
			return "microbe [x=" + x + ", y=" + y + ", dir=" + dir + ", size=" + size + "]";
		}

	}

	static int N, M, K;
	static int[][][] map;
	static Queue<microbe> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 셀 크기
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 군집 수

			map = new int[N][N][6];
			que = new ArrayDeque<>(); // 미생물 저장해둘

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				que.add(new microbe(x, y, dir, size));
			}
			
			int result = 0;
			for(int m = 0; m < M; m++) {
				solve();
			}
			
			while(!que.isEmpty()) {
				microbe temp = que.poll();
				result += temp.size;
			}
			
			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}

	private static void solve() {
		// 1. 큐에서 꺼내서 map에 넣기
		while (!que.isEmpty()) {
			microbe current = que.poll();
			switch (current.dir) {
			case 1: // 상
				map[current.x-1][current.y][1] = current.size;
				break;
			case 2: // 하
				map[current.x+1][current.y][2] = current.size;
				break;
			case 3: // 좌
				map[current.x][current.y-1][3] = current.size;
				break;
			case 4: // 우
				map[current.x][current.y+1][4] = current.size;
				break;
			}
		}

		// 2.map에서 상하좌우 탐색해서 계산 마치고 다시 que에 넣기
		for(int i = 0; i < N; i++) {
			for(int j=0; j<N; j++) {
				
				// 약품에 닿은 거 처리
				if(i == 0 || i == N-1 || j ==0 || j == N-1) {
					int dir = 0;
					for(int k = 1; k < 5; k++) {
						if(map[i][j][k] != 0) {
							if(map[i][j][k]/2 != 0) {
								
								switch (k) {
								case 1: // 상이면 하로 바꾸기
									dir = 2;
									break;
								case 2: // 하이면 상르로 바꾸기
									dir = 1;
									break;
								case 3: // 좌이면 우로 바꾸기
									dir = 4;
									break;
								case 4: // 우이면 좌로 바꾸기
									dir = 3;
									break;
								}
								
								
								que.add(new microbe(i, j, dir, map[i][j][k]/2));
								map[i][j][k] = 0;
							}
							break;
						}
					}
					continue;
				}
				
				
				int dir = 0;
				int max = 0;
				int total = 0;
				for(int k = 1; k < 5; k++) {
					if(max < map[i][j][k]) {	// max 업데이트
						max = map[i][j][k];
						dir = k;
					}
					total += map[i][j][k];
					map[i][j][k] = 0;	// 맵에 있는 정보 지우기
				}
				que.add(new microbe(i, j, dir, total));
			}
		}
		
	}

}
