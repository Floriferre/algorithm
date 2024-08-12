package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 4.
@see https://www.youtube.com/watch?v=X8DtkTyWPO4
@git
@youtube
@performance
@category #
@note 
*	다른건 다 똑같은데 첫 집과 마지막집의 색깔이 달라야 한다는 규정이 추가
*	이걸 어떻게 처리할까? 
*	처음 집을 R로 칠했으면 마지막 집의 색깔은 G, B
*		   B -> R, G
*		   G -> R, B
*	이걸 한 번에 처리하기는 빡세다...! 
*	각 케이스를 나누어 세 번 반복해보자
*	이때, 첫 줄의 최솟값이 영향을 주지 않도록 필요없는 값은 무한대로 설정해주자
*
*
*
*
*/

public class BJ_17404_RGB거리2 {
	static int[] dpMin = new int[3];

	static int[][] arr;

	static int[][] dp;

	static int Red, Blue, Green;
	
	static int maxValue = 10001;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		// RGB 저장해둘 것
		arr = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}

		// Red 칠했을 때
		dp = new int[N][3];
		dp[0][0] = arr[0][0];
		dp[0][1] = maxValue;
		dp[0][2] = maxValue;

		for (int i = 1; i < N; i++) {
			// 최소
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
		}
		Red = Math.min(dp[N - 1][1], dp[N - 1][2]);

		// Blue 칠했을 때
		dp = new int[N][3];
		dp[0][0] = maxValue;
		dp[0][1] = arr[0][1];
		dp[0][2] = maxValue;

		for (int i = 1; i < N; i++) {
			// 최소
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
		}
		Blue = Math.min( dp[N - 1][0], dp[N - 1][2]);

		// Green 칠했을 때
		dp = new int[N][3];
		dp[0][0] = maxValue;
		dp[0][1] = maxValue;
		dp[0][2] = arr[0][2];

		for (int i = 1; i < N; i++) {
			// 최소
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
		}
		Blue = Math.min(dp[N - 1][0], dp[N - 1][1]);

//		for (int i = 0; i < N - 1; i++) {
//			st = new StringTokenizer(br.readLine());
//
//			int first = Integer.parseInt(st.nextToken());
//			int second = Integer.parseInt(st.nextToken());
//			int third = Integer.parseInt(st.nextToken());
//
//			// 최소
//			int minLeft = dpMin[0];
//			int minRight = dpMin[2];
//			dpMin[0] = Math.min(dpMin[0], dpMin[1]) + first;
//			dpMin[2] = Math.min(dpMin[1], dpMin[2]) + third;
//			dpMin[1] = Math.min(Math.min(minLeft, dpMin[1]), minRight) + second;
//		}
		System.out.println(Red + " " + Blue + " " + Green);
		System.out.println(Math.min(Math.min(Red, Blue), Green));
	}

}
