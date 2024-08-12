package baekjoon.Silver;

import java.util.Arrays;
import java.util.Scanner;

/*
@author 정여민
@since 2023. 8. 29.
@see https://www.acmicpc.net/problem/1463
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	정수 X에 사용할 수 있는 연산은 3가지
*	1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
*	2. X가 2로 나누어 떨어지면, 2로 나눈다.
*	3. 1을 뺀다.
*	
*	<입력>
*	첫째줄 : 정수 N (1<= N <= 10^6)
*	
*	<출력>
*	연산을 하는 횟수의 최솟값
*
*	<풀이>
*	DP를 사용하는 문제.
*	처음에 일부 값은 1을 만들기 위해 필요한 최소 횟수를 채워넣는다.
*	dp[0] = 0
*	dp[1] = 0
*	dp[2] = 1
*	dp[3] = 1
*	dp[4] = 2 
*	dp[5] = 3 (1빼고 dp[4]값 더하기)
*/

public class BJ_1463_1로만들기 {
	
	static int [] dp = new int[6];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
//		int [] dp = new int[N+1];
		
		if(N<=5) {
			dp[0] = 0;
			dp[1] = 0;
			dp[2] = 1;
			dp[3] = 1;
			dp[4] = 2;
			dp[5] = 3;			
		}else {
			dp = new int[N+1];
			dp[0] = 0;
			dp[1] = 0;
			dp[2] = 1;
			dp[3] = 1;
			dp[4] = 2;
			dp[5] = 3;	
			for (int i = 5; i < N+1; i++) {
				if(i%3==0 && i%2==0) {
					dp[i] = Math.min(Math.min(dp[i/3], dp[i/2]), dp[i-1])+1;
				}
				else if(i%3==0) {				
					dp[i] = Math.min(dp[i/3], dp[i-1])+1;
				}
				else if(i%2==0) {
					dp[i] = Math.min(dp[i/2], dp[i-1])+1;
				}else {
					dp[i] = dp[i-1]+1;
				}
			}
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
		
		
		
		
		
	}

}
