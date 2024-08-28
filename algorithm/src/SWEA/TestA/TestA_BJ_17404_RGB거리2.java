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
*	집이 N개 있음
*	1번 집의 색은 2번, N번 집과 같지 않아야 함
*	N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다
*	=> 양 옆 집의 색과 같지 않아야 한다
*
*	<입력>
*	첫째줄 : 집의 수 N	(2 ≤ N ≤ 1,000)
*	N개의 줄 : 각 집을 R, G, B로 칠하는 비용이 한 줄에 하나씩 (비용<=1000)
*
*	<출력>
*	모든 집을 칠하는 비용의 최솟값 출력
*	
*
*
*/

public class TestA_BJ_17404_RGB거리2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[][] house = new int[N][3];
		int[][] dp = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			house[i][0] = Integer.parseInt(st.nextToken());
			house[i][1] = Integer.parseInt(st.nextToken());
			house[i][2] = Integer.parseInt(st.nextToken());
		}
		for (int[] i : dp) {
			Arrays.fill(i, 1000 * 1000);
		}

		int result = 1000 * 1000; // 최종 결과
		// 스타트 컬러 = R
		dp[0][0] = house[0][0];
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1] + house[i][0], dp[i - 1][2] + house[i][0]);
			dp[i][1] = Math.min(dp[i - 1][0] + house[i][1], dp[i - 1][2] + house[i][1]);
			dp[i][2] = Math.min(dp[i - 1][0] + house[i][2], dp[i - 1][1] + house[i][2]);
		}
		int r = Math.min(dp[N - 1][1], dp[N - 1][2]);

		// 스타트 컬러 = G
		for (int[] i : dp) {
			Arrays.fill(i, 1000 * 1000);
		}
		dp[0][1] = house[0][1];
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1] + house[i][0], dp[i - 1][2] + house[i][0]);
			dp[i][1] = Math.min(dp[i - 1][0] + house[i][1], dp[i - 1][2] + house[i][1]);
			dp[i][2] = Math.min(dp[i - 1][0] + house[i][2], dp[i - 1][1] + house[i][2]);
		}
		int g = Math.min(dp[N - 1][0], dp[N - 1][2]);

		// 스타트 컬러 = B
		for (int[] i : dp) {
			Arrays.fill(i, 1000 * 1000);
		}
		dp[0][2] = house[0][2];
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1] + house[i][0], dp[i - 1][2] + house[i][0]);
			dp[i][1] = Math.min(dp[i - 1][0] + house[i][1], dp[i - 1][2] + house[i][1]);
			dp[i][2] = Math.min(dp[i - 1][0] + house[i][2], dp[i - 1][1] + house[i][2]);
		}
		int b = Math.min(dp[N - 1][0], dp[N - 1][1]);
		
		System.out.println(Math.min(r, Math.min(g, b)));

	}

}
