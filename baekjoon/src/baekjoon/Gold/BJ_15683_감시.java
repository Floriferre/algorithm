package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 22.
@see https://www.acmicpc.net/problem/15683
@git
@youtube
@performance 69004KB 388ms
@category #구현, 브루트포스, 시뮬레이션, 백트래킹
@note 
*	사무실의 CCTV 5 종류
*		1) 한쪽만 탐색
*		2) 양쪽 탐색
*		3) 직각인 두 방향 탐색
*		4) 세 방향 탐색
*		5) 네 방향 탐색
*	CCTV는 90도씩 회전할 수 있음
*	지도
*		0 : 빈 칸
*		1~5 : CCTV 종류
*		6 : 벽
*	CCTV는 벽이 아니면 전부 감시 가능
*	사각지대 : CCTV가 감시할 수 없는 영역 -> 사각지대의 최소 크기를 구하는 프로그램
*
*	<입력>
*	첫째줄 : 세로 크기 N, 가로 크기 M
*	둘째줄 ~ N개의 줄 : 사무실 각 칸의 정보 
*
*	cctv를 사용하여 맵을 차례로 탐색하는데 한 번 탐색이 끝나면 다음 cctv로 넘기면서 모든 조합? 순열? 에서 가능한 최소 사각지대를 찾아내자 
*	ㄹㅇ 그냥 DFS 사용한 구현
*
*
*
*/

public class BJ_15683_감시 {

	static int N, M; // 사무실 세로크기 N, 가로 크기 M

	static ArrayList<CCTV> cctvs = new ArrayList<>(); // CCTV 목록 저장할 배열

	static int[][] map; // 맵 정보 저장할 배열
	
	static int min = Integer.MAX_VALUE; // 최소 사각지대 갯수 담을 변수 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if (map[n][m] >= 1 && map[n][m] <= 5) { // cctv 추가하기
					cctvs.add(new CCTV(n, m, map[n][m]));
				}
			}
		}

		dfs(0, map, cctvs);
		System.out.println(min);

	}
	
	
	// 탐색 한다 
	private static void dfs(int cnt, int [][] map, ArrayList<CCTV> cctvs) {	// 현재까지 탐색한 cctv 갯수, 현재까지 바뀐 맵의 정보, 남은 CCTV 정보
		// 모든 cctv 탐색했으면 사각지대 정보업뎃 후 리턴
		if(cnt == cctvs.size()) {
			min = Math.min(min, COUTN0(map));
			return;
		}
		
		// 이번 회차에 탐색할 cctv 정보
		int cctvNum = cctvs.get(cnt).type;
		int cctvX = cctvs.get(cnt).x;
		int cctvY = cctvs.get(cnt).y;
		
		// 맵 정보 저장할 배열
		int [][] tmp;
		
		switch(cctvNum) {
		case 1:	// 한쪽 방향만 탐색
			tmp = copyMap(map);
			checkLeft(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkRight(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkUp(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkDown(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			break;
			
		case 2:	// 양쪽 방향 탐색
			tmp = copyMap(map);
			checkLeft(tmp, cctvX, cctvY);
			checkRight(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkUp(tmp, cctvX, cctvY);
			checkDown(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			break;
		case 3:	// 직각 방향 탐색
			tmp = copyMap(map);
			checkLeft(tmp, cctvX, cctvY);
			checkUp(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkUp(tmp, cctvX, cctvY);
			checkRight(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkRight(tmp, cctvX, cctvY);
			checkDown(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkDown(tmp, cctvX, cctvY);
			checkLeft(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			break;
		case 4:	// 세 방향 탐색
			tmp = copyMap(map);
			checkLeft(tmp, cctvX, cctvY);
			checkUp(tmp, cctvX, cctvY);
			checkRight(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkUp(tmp, cctvX, cctvY);
			checkRight(tmp, cctvX, cctvY);
			checkDown(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkRight(tmp, cctvX, cctvY);
			checkDown(tmp, cctvX, cctvY);
			checkLeft(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			tmp = copyMap(map);
			checkDown(tmp, cctvX, cctvY);
			checkLeft(tmp, cctvX, cctvY);
			checkUp(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			break;
		case 5:	// 상하좌우 모든 방향 탐색
			tmp = copyMap(map);
			checkLeft(tmp, cctvX, cctvY);
			checkRight(tmp, cctvX, cctvY);
			checkUp(tmp, cctvX, cctvY);
			checkDown(tmp, cctvX, cctvY);
			dfs(cnt+1, tmp, cctvs);
			
			break;
		}
		
		
		
	}

	// map 복사해서 사용 (원본에 영향 주면 안 되니까)
	private static int [][] copyMap(int [][] map){
		int [][] temp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		return temp;
	}
	
	private static int COUTN0(int [][]map) {
		int temp = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j]==0) temp++;
			}
		}
		return temp;
	}
	
	
	// 좌측 탐색
	private static void checkLeft(int[][] map, int x, int y) {
		for (int i = y - 1; i >= 0; i--) {
			if (map[x][i] == 6)
				return; // 벽이면 탐색 종료
			if (map[x][i] != 0)
				continue; // cctv나 이미 탐색된 공간이면 넘기고
			map[x][i] = -1; // 빈 공간이면 -1로 바꾸기
		}
	}

	// 우측 탐색
	private static void checkRight(int[][] map, int x, int y) {
		for (int i = y + 1; i < M; i++) {
			if (map[x][i] == 6)
				return; // 벽이면 탐색 종료
			if (map[x][i] != 0)
				continue; // cctv나 이미 탐색된 공간이면 넘기고
			map[x][i] = -1; // 빈 공간이면 -1로 바꾸기
		}
	}

	// 위쪽 탐색
	private static void checkUp(int[][] map, int x, int y) {
		for (int i = x - 1; i >= 0; i--) {
			if (map[i][y] == 6)
				return; // 벽이면 탐색 종료
			if (map[i][y] != 0)
				continue; // cctv나 이미 탐색된 공간이면 넘기고
			map[i][y] = -1; // 빈 공간이면 -1로 바꾸기
		}
	}

	// 아래쪽 탐색
	private static void checkDown(int[][] map, int x, int y) {
		for (int i = x + 1; i < N; i++) {
			if (map[i][y] == 6)
				return; // 벽이면 탐색 종료
			if (map[i][y] != 0)
				continue; // cctv나 이미 탐색된 공간이면 넘기고
			map[i][y] = -1; // 빈 공간이면 -1로 바꾸기
		}
	}

}

class CCTV {
	int x;
	int y;
	int type;

	public CCTV(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public String toString() {
		return "CCTV [x=" + x + ", y=" + y + ", type=" + type + "]";
	}

}
