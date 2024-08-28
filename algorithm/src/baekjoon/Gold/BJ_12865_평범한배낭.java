package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 24.
@see https://www.acmicpc.net/problem/12865
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	준서의 여행
*	여행에 필요한 N개의 물건, 각자 무게 W 가치 V
*	최대 K만큼 넣을 수 있는 가방
*	가방에 넣을 수 있는 물건들의 최댓값?
*
*	<입력>
*	첫째줄 : 물건의 수 N (1 ≤ N ≤ 100), 배낭 무게 K (1 ≤ K ≤ 100,000)
*	N개의 줄 : 각 물건의 무게 W (1 ≤ W ≤ 100,000), 가치 V (0 ≤ V ≤ 1,000)
*
*	<출력>
*	배낭에 넣을 수 있는 물건들의 가치합의 최댓값
*
*	<풀이>
*	사실 부분집합도 적용은 가능하다 but 시간 초과난다 N이 100까지나 가능해...
*	냅색 문제 (Knapsack Problem)
*	- 물건을 쪼갤 수 없으니 0-1 knapsack
*
*	<알고리즘>
*	dp를 사용 행 i 열 j
*	해당 무게에서 현재 고른 물건을 담을 수 없는 경우 : (j<W)인 경우
*	dp[i][j] = dp[i-1][j] : 그 전까지 담은 가치의 최댓값을 유지
*	해당 무게에서 현재 고른 물건을 담을 수 있는 경우 : (j>=W)인 경우
*	dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W] + V )
*	: 그 전까지 담은 가치의 최댓값과, 현재 물건을 넣었을 때의 가치 중 어떤 것이 더 큰지 비교
*	
*/

public class BJ_12865_평범한배낭 {
	
	static int N, K;
	static int [][] items;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 물건 정보 저장
		items = new int[N+1][2];	// 무게, 가치
		for (int i = 1; i <=N; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		max = 0;
		
		// 2차원 DP 테이블 : i번째 물건이 j번째 무게에서 가질 수 있는 가치의 최댓값 
		int [][] dp = new int[N+1][K+1];
		
		System.out.println(Arrays.deepToString(dp));
		
		// 1번째 물건부터 N번째 물건까지 모두 고려
		for(int i=1; i<=N; i++) {
			// 무게가 1인 경우부터 무게가 K인 경우까지 모두 고려
			for(int j=1; j<=K; j++) {
				// 해당 위치에 물건을 넣을 수 없는 경우 (현재 선택된 물건이 더 무거움)
				if(items[i][0] > j) {
					// i-1번째 물건까지의 무게 j에서의 최댓값
					dp[i][j] = dp[i-1][j];
				}
				else {	// 해당 위치에 물건을 넣을 수 있는 경우 (현재 선택된 물건이 더 가벼움)
					//  i-1번째 물건까지의 무게 j에서의 최댓값 or
					// 	i-1번째 물건까지 고려했을 때 무게 j - 현재무게 의 최대 가지 + 현재 가치 의 최대값
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-items[i][0]] + items[i][1]);
					
				}
			}
		}
		
		// 마지막 무게까지 갔을 때의 무게 K에서의 가치 최댓값 출력
		System.out.println(dp[N][K]);
		
	}

}
