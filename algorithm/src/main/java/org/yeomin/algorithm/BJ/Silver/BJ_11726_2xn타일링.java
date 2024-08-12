package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 29.
@see https://www.acmicpc.net/problem/11726
@git
@youtube
@performance 14272KB 128ms
@category #DP
@note 
*	<문제>
*	2xn 크기의 직사각형을 1x2, 2x1 타일로 채우는 방법의 수를 구하는 프로그램
*
*	<입력> 
*	첫째줄 : n
*	
*	<출력>
*	2xn 크기의 직사각형을 채우는 방법의 수 % 10007 출력
*
*
*	<풀이>
*	내가 좋아하는 DP를 이용하여 푸는 문제! 
*	i번째 타일
*	1) i-1번째 타일 + 2x1짜리 타일 1개 붙이기
*	2) i-2번쨰 타일 + 1x2짜리 타일 2개 붙이기 
*
*/



public class BJ_11726_2xn타일링 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		
		int [] dp = new int[1001];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		dp[4] = 5;
		
		
		for(int i = 5; i <= n; i++) {
			dp[i] = dp[i-1]%10007 + dp[i-2]%10007;
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[n]%10007);

	}

}
