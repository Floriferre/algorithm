package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 25.
@see https://swexpertacademy.com/main/talk/solvingClub/problemBoxDetail.do?solveclubId=AYlH3z4K78kDFAVR&probBoxId=AYoaJoMabgADFAU6&leftPage=1&&&
@git
@youtube
@performance
@category #
@note 
	<문제> 
	가로 N개 x 세로 N개의 cell
	1개의 cell에는 1개의 core 혹은 1개의 전선
	가장자리에는 전원이 흐름
	core와 전원을 연결하는 전선은 직선만 가능
	전선은 교차해서 X
	초기 : 전선 연결 전의 맥시노스 상태
	최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합
	단, 여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값
	
	<제약사항>
	1. 7 <= N <= 12
	2. 1 <= core <= 12
	3. 최대한 많은 Core에 전원을 연결해도, 전원이 연결되지 않는 Core가 존재할 수 있다 
	
	<입력>
	첫번째 : 총 테스트케이스 개수 T
	TC1 : N
	이후 N줄 : 맥시노스 초기 상태 N x N
	0 : 빈 셀 / 1 : core
	
	<출력> 
	#X 정답




*/

public class SWEA_1767_프로세서연결하기 {

	static class Core {
		int x;
		int y;

		/**
		 * @param x
		 * @param y
		 */
		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int N;
	static int[][] maxynos;

	static ArrayList<Core> cores;
	
	static ArrayList<int []> list;	// 각각 코어수, 전선 수 저장해둘 변수 
	
//	static ArrayList<E>
	
	// 최소 전선 길이 잴 변수 
	static int min = Integer.MAX_VALUE;

	static int edgeCore;	// 가장자리에 연결된 코어는 무시
	
	static int lineCore;	// 사용된 코어의 수 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 개수

		for (int t = 1; t < T + 1; t++) { // 테스트 케이스만큼 반복
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 배열의 크기 N

			cores = new ArrayList<>(); // 코어 저장해둘 리스트
			list = new ArrayList<>();
			edgeCore = 0;
			lineCore = 0;

			maxynos = new int[N][N]; // 맥시노스 선언
			for (int i = 0; i < N; i++) { // 맥시노스 정보 받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maxynos[i][j] = Integer.parseInt(st.nextToken());
					if (maxynos[i][j] == 1) { // 코어의 위치 기록
						if(i == 0 || i == N-1 || j == 0 || j == N-1) edgeCore++;
						else {
							cores.add(new Core(j, i));							
						}
					}
				}
			}
			
			
			// dfs 돌리기 
			dfs(0, maxynos, cores, 0);
			
//			list.sort(null);
//			System.out.println(list);
			
			System.out.println(lineCore + " : " + min);
			
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb);
	}
	
	
	private static void dfs(int cnt, int [][] maxynos, ArrayList<Core> cores, int tempCores) {	// 탐색할 dfs, 현재까지 사용된 코어 수 셀 변수, 현재 맵 상태, 코어 정보
		// 모든 코어 탐색 했으면 전선 길이 업데이트
		if(cnt == cores.size()) {
			System.out.println(tempCores);
			if(lineCore <= tempCores) {
				lineCore = tempCores;
				min = Math.min(min, CountLine(maxynos));
			}
//			int [] arr = {tempCores, min};
//			list.add(arr);
			return;
		}
		
		// 이번 회차에 탐색할 코어 정보
		int coreX = cores.get(cnt).x;
		int coreY = cores.get(cnt).y;
		
		// 현재 맵 정보 카피해서 전달할 변수
		int [][] temp;
		
		int tempCore;
		
		tempCore = tempCores;
		temp = copyMap(maxynos);
		tempCore = checkLeft(temp, coreX, coreY, tempCore);
		dfs(cnt+1, temp, cores, tempCore);
		
		tempCore = tempCores;
		temp = copyMap(maxynos);
		tempCore = checkRight(temp, coreX, coreY, tempCore);
		dfs(cnt+1, temp, cores, tempCore);
		
		tempCore = tempCores;
		temp = copyMap(maxynos);
		tempCore = checkUp(temp, coreX, coreY, tempCore);
		dfs(cnt+1, temp, cores, tempCore);
		
		tempCore = tempCores;
		temp = copyMap(maxynos);
		tempCore = checkDown(temp, coreX, coreY, tempCore);
		dfs(cnt+1, temp, cores, tempCore);
		
		for(int [] a : temp) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
	}
	
	
	
	

	// 맵 복사
	private static int[][] copyMap(int[][] map) {
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	// 전선 길이 셀 매서드
	private static int CountLine(int[][] map) {
		int temp = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					temp++;
				}
			}
		}
		return temp;
	}

	// 탐색 방향 총 4개
	private static int checkLeft(int[][] map, int x, int y, int tempCore) { // 현재 맵 정보, 탐색 시작 위치 x, y
		for (int i = y - 1; i >= 0; i--) {
			if (map[x][i] != 0)
				return tempCore; // 다른 코어나 전선을 만나면 탐색 종료
			map[x][i] = 2; // 전선을 설치하면 해당 위치 값 0->2로 바꿔주기
		}
		return tempCore+1;
	}

	private static int checkRight(int[][] map, int x, int y, int tempCore) {
		for (int i = y + 1; i < N; i++) {
			if (map[x][i] != 0)
				return tempCore; // 다른 코어나 전선을 만나면 탐색 종료
			map[x][i] = 2; // 전선을 설치하면 해당 위치 값 0->2로 바꿔주기
		}
		return tempCore+1;
	}

	private static int checkUp(int[][] map, int x, int y, int tempCore) {
		for (int i = x - 1; i >= 0; i--) {
			if (map[i][y] != 0)
				return tempCore; // 다른 코어나 전선을 만나면 탐색 종료
			map[y][y] = 2; // 전선을 설치하면 해당 위치 값 0->2로 바꿔주기
		}
		return tempCore+1;
	}

	private static int checkDown(int[][] map, int x, int y, int tempCore) {
		for (int i = x + 1; i < N; i++) {
			if (map[x][i] != 0)
				return tempCore; // 다른 코어나 전선을 만나면 탐색 종료
			map[x][i] = 2; // 전선을 설치하면 해당 위치 값 0->2로 바꿔주기
		}
		return tempCore+1;
	}

}
