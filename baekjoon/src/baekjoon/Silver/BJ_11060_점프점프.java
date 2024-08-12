package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 29.
@see https://www.acmicpc.net/problem/11060
@git
@youtube
@performance 14384KB, 136ms
@category #DP, 그래프이론, 그래프탐색, BFS
@note 
*	<문제>
*	1xN 크기의 미로에 갇힌 재환.
*	미로는 1x1 크기의 칸. 
*	각 칸에는 정수 하나. 
*	i번째 칸의 수 Ai.
*	재환이는 Ai이하만큼 오른쪽으로 떨어진 칸으로 한 번에 점프.
*	재환이가 미로 왼쪽 끝에 있고, 오른쪽 끝으로 가려고 함. 최소 몇 번 점프를 해야하는가? 
*	갈 수 없는 경우는 -1 출력
*
*	<입력>
*	첫째줄 : N (1<=N<=1000)
*	둘째줄 : Ai (0<=Ai<=100)
*	
*	<출력> 
*	최소 몇 번 점프를 해야 가장 오른쪽 끝 칸으로 갈 수 있는지 출력
*	갈 수 없으면 -1 출력
*
*	<풀이>
*	DP문제. 
*	각 칸에서 i번째 칸을 가기 위해 필요한 최소 점프 횟수를 저장 
*	0은 절대 밟으면 안 됨! 
*
*
*
*
*
*
*/

public class BJ_11060_점프점프 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int [] A = new int[N];	// 미로 정보
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		
//		if(A[0]==0) {
//			System.out.println(-1);
//		}
//		else {
			
			int [] dp = new int[N];
			Arrays.fill(dp, Integer.MAX_VALUE-1);// dp 초기화 
//		System.out.println(Arrays.toString(dp));
			
			dp[0] = 0;
			
			
			
			for(int i = 0; i < N; i++) {
				if(A[i]==0) {
					continue;
				}
				for (int j = i+1; j < i + A[i] +1; j++) {
					if(j < N) {
						dp[j] = Math.min(dp[j], dp[i] + 1);
					}
				}
			}
			
//		System.out.println(Arrays.toString(dp));
			
			if(dp[N-1] == Integer.MAX_VALUE-1){
				System.out.println(-1);
			}else {
				System.out.println(dp[N-1]);
			}
//		}
	}

}
