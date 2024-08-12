package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 4.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	활주로 건설 : 가로 or 세로
*	활주로 : 높이가 같아야 or 경사로 설치
*	경사로 : 길이 x, 높이 1
*	경사로의 길이 x와 절벽지대의 높이 정보가 주어졌을 때, 활주로를 건설할 수 있는 경우의 수 계산
*
*	<제약사항>
*	지도의 크기 6 <= N <= 20
*	경사로의 높이 항상 1, 길이x 2<=x<=4
*	지형의 높이 1 <= H <= 6
*	동일한 셀에 두 개 이상의 경사로 겹쳐서 사용 X
*	
*	<풀이>
*	1. 높이가 같은거 : cnt +1 
*	2. 높이가 1 작아짐 && cnt >= 0 : cnt = -X +1 
*	3. 높이가 1 높아짐  && cnt >= X : cnt =1
*	4. 경사로 설치가 불가능한 경우(높이 2이상 포함) : flag false로
*	if (!flag || cnt < 0) : 설치 못하는 경우 + 1
*
*	2*N - 설치 못하는 경우 
*
*/

public class SWEA_4014_활주로건설 {

	static int N, X;
	static int[][] map;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int [N][N];
			// 맵 정보 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = 0;
			solve();


			sb.append("#" + t + " " + (2 * N - result) + "\n");
		}
		System.out.println(sb);

	}

	private static void solve() {
		for (int i = 0; i < N; i++) {
			// 한 행씩
			int cnt = 1; // 연속된 문자열의 개수
			int currentN = map[i][0]; // 각 행의 시작점을 기준으로 카운트
			boolean flag = true; // 활주로 구성이 가능한지 아닌지 체크
			for (int j = 1; j < N; j++) {
				if (currentN == map[i][j]) { // 경사가 같으면 cnt +1
					cnt++;
				} else if (currentN - map[i][j] == 1 && cnt >= 0) { // 경사가 낮아지면
					currentN = map[i][j];
					cnt = -X + 1;
				} else if (map[i][j] - currentN == 1 && cnt >= X) { // 경사가 높아지면
						cnt = 1;
						currentN = map[i][j];
				} else { // 높이 차이가 2 이상이거나 경사로 설치가 불가능한 경우
					flag = false;
					break;
				}
			}
			
			if(!flag || cnt < 0) result++;
		}

		for (int j = 0; j < N; j++) {
			// 한 행씩
			int cnt = 1; // 연속된 문자열의 개수
			int currentN = map[0][j]; // 각 행의 시작점을 기준으로 카운트
			boolean flag = true; // 활주로 구성이 가능한지 아닌지 체크
			for (int i = 1; i < N; i++) {
				if (currentN == map[i][j]) { // 경사가 같으면 cnt +1
					cnt++;
				} else if (currentN - map[i][j] == 1 && cnt >= 0) { // 경사가 낮아지면
					currentN = map[i][j];
					cnt = -X + 1;
				} else if (map[i][j] - currentN == 1 && cnt >= X) { // 경사가 높아지면
						cnt = 1;
						currentN = map[i][j];
				} else { // 높이 차이가 2 이상이거나 경사로 설치가 불가능한 경우
					flag = false;
					break;
				}
			}
			
			if(!flag || cnt < 0) result++;
		}

	}

	private static int[][] copyMap(int[][] map) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

}
