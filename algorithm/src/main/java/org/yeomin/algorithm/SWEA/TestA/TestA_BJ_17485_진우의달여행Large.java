package TestA;

import java.io.*;
import java.util.*;

/*
@author 정여민
@since 2023. 10. 12.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	달과 지구의 사이 N*M
*	각 원소의 값 : 우주선이 그 공간을 지날 때 소모되는 연료의 양
*	우주선의 움직임 : 왼쪽 아래, 아래, 오른쪽 아래
*	우주선은 전에 움직인 방향으로 움직일 수 없음! (같은 방향 두 번 연속 X)
*	연료를 최대한 아끼며 지구의 어느 위치에서 출발하든 간에 달의 어느 위치든 착륙
*
*	연료의 최솟값?
*
*	<입력> 
*	첫째줄 : N, M  (2 ≤ N, M ≤ 1000)
*	N줄 : 각 행렬의 원소 값 <=100
*
*	<출력>
*	최소 연료값
*
*	<풀이>
*	DP를 사용하는 문제 
*	다만, 우주선의 방향이 세 방향이므로 dp테이블을 [N][N][5]으로 선언하면 좋을 듯 
*	왼쪽 방향 : dp[i][j][1] = dp[i-1][j-1][3] , dp[i-1][j][2]
*	가운데 방향 : dp[i][j][2] = dp[i-1][j-1][3], dp[i-1][j+1][1]
*	오른쪽 방향 : dp[i][j][3] = dp[i-1][j+1][1], dp[i-1][j][2]*
*	
*
*/

public class TestA_BJ_17485_진우의달여행Large {

	static int N, M;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dp = new int[M + 2][3];

		// dp 배열 초기화
		for (int[] i : dp) {
			Arrays.fill(i, 100 * 1000);
		}

		// 1 ~ M 인덱스를 사용 / 가장 첫 줄 세팅
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < M + 1; i++) {
			int start = Integer.parseInt(st.nextToken());

			for (int j = 0; j < 3; j++) {
				dp[i][j] = start;
			}
		}

		for (int i = 1; i < N; i++) {
//			System.out.println("i는 : " + i);
			st = new StringTokenizer(br.readLine());
			int[][] tempArr = new int[M + 2][3];
			for (int[] t : tempArr) {
				Arrays.fill(t, 100 * 1000);
			}
			for (int j = 1; j < M + 1; j++) {	// M개 원소 입력 받기 
//				System.out.println("j는 : " + j);
				int temp = Integer.parseInt(st.nextToken());

				/*
				 * 
				 * 왼쪽 방향 : dp[i][j][1] = dp[i-1][j-1][3] , dp[i-1][j][2] 
				 * 가운데 방향 : dp[i][j][2] = dp[i-1][j-1][3], dp[i-1][j+1][1] 
				 * 오른쪽 방향 : dp[i][j][3] = dp[i-1][j+1][1],dp[i-1][j][2]
				 * 
				 */
				tempArr[j][0] = Math.min(dp[j - 1][2], dp[j][1]) + temp;
				tempArr[j][1] = Math.min(dp[j - 1][2], dp[j + 1][0]) + temp;
				tempArr[j][2] = Math.min(dp[j][1], dp[j + 1][0]) + temp;
				
				
				
			}
			dp = tempArr;
//			System.out.println(Arrays.deepToString(dp));
		}
		int result = Integer.MAX_VALUE;
		for(int [] i: dp) {
			for(int j : i) {
				result = Math.min(result, j);
			}
		}
		
		System.out.println(result);

	}

}
