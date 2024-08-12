package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 7.
@see https://www.acmicpc.net/problem/17485
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	진우의 달여행
*	지구와 달 사이 NxM
*		각 원소의 값 : 그 공간을 지날 때 소모되는 연료의 양
*	지구 -> 달 경로
*		- 좌측 하단(1), 아래(2), 우측 하단(3) 만 가능!
*		- 전에 움직인 방향으로 움직일 수 없음!(같은 방향 2번 X)
*			=> 1 2 / 1 3 / 2 1 / 2 3 / 3 1 / 3 2 가능
*	연료를 최대한 아끼며 지구의 어느 위치에서든 출발하여 달의 어느 위치든 착륙하는 것
*
*	<입력>
*	첫째줄 : 행렬 크기 N, M (2 ≤ N, M ≤ 1000)
*	N줄 : 각 행렬의 원소값 100이하의 자연수 
*
*	<출력>
*	최소 연료의 값 
*
*
*/

public class BJ_17485_진우의달여행large {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 행렬 크기 
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int max = 100*1000;
		//DP -> 3차원 DP : 좌측, 아래, 오른쪽에서 올 때 저장 
		int [][][] dp = new int[N][M +2][3];	// 양 끝에도 garbage값을 주어 계산 편리하게
		for(int i = 0; i < N; i++) {	// dp 배열 전체 max값으로 초기화! 
			for(int j = 0; j < M+2; j++) {
				for(int k=0; k < 3; k++) {
//					System.out.println(i + " " + j + " " + k);
					dp[i][j][k] = max;
				}
			}
		}
		
		// 연료 사용 처음은 dp에 그대로 저장 
		st = new StringTokenizer(br.readLine());
		// 양 쪽 끝값은 max로 
//		dp[0][0][0] = max;
//		dp[0][0][1] = max;
//		dp[0][0][2] = max;
//		dp[0][M+1][0] = max;
//		dp[0][M+1][1] = max;
//		dp[0][M+1][2] = max;
		for(int i = 1; i < M+1; i++) {
			int temp = Integer.parseInt(st.nextToken());
			dp[0][i][0] = temp;
			dp[0][i][1] = temp;
			dp[0][i][2] = temp;
		}
		
		
		// 연료 저장할 배열
		int [][] fuel = new int[N][M+2];	// 양 끝에도 garbage값을 주어 계산 편리하게
		int fuels = 0;
		for (int i = 1; i < N; i++) {	// 연료 가장 처음은 이미 밭았으니까! 
			st = new StringTokenizer(br.readLine());
			// 다음 연료 사용량 목록
			fuel[i][0] = max;	//양 끝값 최대로 초기화
			fuel[i][M+1] = max;	
			for (int j = 1; j < M+1; j++) {
//				fuel[i][j] = Integer.parseInt(st.nextToken());
				fuels = Integer.parseInt(st.nextToken());
				// 왼쪽으로 내려갈 때의 사용량 = 가운데에서 온 것과 오른쪽에서 온 것 중 최소!
				dp[i][j][0] = Math.min(dp[i-1][j-1][2], dp[i-1][j][1]) + fuels;
				
				// 가운데로 내려갈 때의 사용량 = 왼쪽에서 온 것과 오른쪽에서 온 것 중 최소!
				dp[i][j][1] = Math.min(dp[i-1][j-1][2], dp[i-1][j+1][0]) + fuels;
				
				// 오른쪽으로 내려갈 때의 사용량 = 왼쪽에서 온 것과 가운데에서 온 것 중 최소!
				dp[i][j][2] = Math.min(dp[i-1][j][1], dp[i-1][j+1][0]) + fuels;
//				System.out.println(dp[i-1][j][0] + " " + dp[i][j][1] + " " + dp[i][j][2]);
			}
//			for (int j = 1; j < M+1; j++) {
//				
//			}
			
			
		}
		// 마지막 줄에서 최솟값 뽑아와야함!! dp[N-1][][] 에서! 
		int result = Integer.MAX_VALUE;
		for (int i = 1; i < M+1; i++) {
			result = Math.min(result, Math.min(Math.min(dp[N-1][i][0], dp[N-1][i][1]), dp[N-1][i][2]));
		}
//		System.out.println(Math.min(Math.min(dp[N-1][M][0], dp[N-1][M][1]), dp[N-1][M-1][2]));
		System.out.println(result);
	}

}
