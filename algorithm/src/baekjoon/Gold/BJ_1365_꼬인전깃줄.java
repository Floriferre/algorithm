package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 26.
@see https://www.acmicpc.net/problem/1365
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	전봇대가 연결이 되어있음 (왼쪽 전봇대와 오른쪽 전봇대는 하나의 전선으로 연결)
*	전깃줄이 꼬여있는 것을 잘라서 화재 예방
*	최대한 적은 갯수의 전깃줄을 자르려고 할 때 자르는 전깃줄의 개수는?
*
*	<입력>
*	첫째줄 : 전봇대 개수 N (1<=N<=100,000)
*	N보다 작거나 같은 자연수 N개가 주어짐
*	i번째 입력되는 자연수는 길 왼쪽 i번째 전봇대와 연결된 길 오른편의 전봇대가 몇 번 전봇대인지 나타냄
*	
*	<출력>
*	전선이 꼬이지 않으려면 최소 몇 개의 전선을 잘라내야 하는지 출력
*
*
*	<풀이>
*	최장 증가 수열 찾는 문제 (LIS)
*	N - 최장 증가 수열
*	1. DP로 풀기 
*		1-1. 첫 번째 원소부터 N번째 원소까지 dp테이블의 값을 채워나간다 (1로)
*		1-2. 현재 원소의 위치에 대하여, 앞의 원소의 값을 비교하며 값을 갱신한다
*		1-3. 만일 부분 수열이 증가할 가능성이 있다면 dp 테이블에 저장된 lis를 바탕으로 가장 큰 값을 dp[i]의 값으로 갱신한다
*	2. 이분탐색으로 풀기
*		2-1. 원소를 추가할 때마다 현재까지 저장된 dp를 탐색하며 해당 원소보다 큰 값을 갖는 원소가 있는지 탐색
*			2-1-1. 그런 원소를 찾았으면, 그런 값들 중 가장 작은 값과 해당 원소의 위치를 교체
*			2-1-2. 그런 값을 찾지 못했다면, dp 테이블에 채워진 원소의 가장 마지막 부분에 해당 원소를 추가해주는 작업
*		* 이때, dp에 저장된 원소들은 '오름차순'으로 정렬되어 있다는 사실을 알 수 있고, 
*		    이 때문에 앞부분을 탐색하는 과정도 이분탐색을 바탕으로 O(logN)으로 줄일 수 있음
*
*
*
*/



public class BJ_1365_꼬인전깃줄 {

	static int N;
	static int [] lst, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		lst = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// 근데 이 방법으로 풀면 시간초과 난다!
		/*
		// 각 위치에서의 lst를 저장할 1차원 dp테이블
		int [] dp = new int [N];
		// 최대 lst의 값
		int max = 1;
		
		// 첫 번째 원소부터 N번째 원소까지 dp테이블의 값을 채워나간다
		for (int i = 0; i < N; i++) {
			// 우선 해당 위치를 본인의 길이(1)로 초기화
			dp[i] = 1;
			// 현재 원소의 위치에 대하여, 앞의 원소의 값을 비교하며 값을 갱신한다
			for (int j = 0; j < i; j++) {
				// 만일 부분 수열이 증가할 가능성이 있다면
				if(lst[j] < lst[i]) {
					// dp 테이블에 저장된 lis를 바탕으로 가장 큰 값을 dp[i]의 값으로 갱신한다
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				
			}
			// 전체 수열에서 lis 값을 갱신한다
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(N - max);
		*/
		
		// 이분탐색으로 풀어보자 
		// dp에 실질적으로 저장된 원소의 길이 = LIS인 1차원 dp테이블을 만든다
		// 해당 dp에 저장된 원소(0이 아닌 값)들은 이후 조사하는 원소들이 부분 수열을 늘릴 수 있을 지에 대한 정보를 제공한다
		dp = new int[N];
		// 처음에 저장된 원소는 없으므로, LIS=0이다
		int LIS = 0;
		
		// 첫번째 원소부터 N번째 원소까지 dp테이블의 값을 채워나간다
		for (int i = 0; i < N; i++) {
			// 이분 탐색을 활용하여 dp테이블에 저장된 원소를 탐색하며 현재 선택된 숫자가  dp테이블의 어떤 위치에 포함될지 파악한다
			int idx = binarySearch(lst[i], 0, LIS, LIS+1);
			
			// 찾지 못한 경우
			if(idx == -1) {
				// 가장 마지막 위치에 원소를 삽입하고 LIS의 길이를 늘린다
				dp[LIS++] = lst[i];
			}// 찾은 경우
			else {
				// 해당 위치에 현재 값을 삽입하여 갱신한다 
				dp[idx] = lst[i];
			}
		}
		System.out.println(N - LIS);
		
	}
	
	private static int binarySearch(int num, int start, int end, int size) {
		int res = 0;	// 원소 위치 기억
		while(start <= end) {
			// 중앙값을 찾는다
			int mid = (start + end) /2;
			
			// 만일 현재 선택된 원소가 해당 원소보다 작거나 같으면, 앞부분 탐색
			if(num <= dp[mid]) {
				//해당 원소의 위치를 기억해둔다.
				res = mid;
				end = mid - 1;
			}	// 만일 현재 선택된 원소가 해당 원소보다 크다면, 뒷부분 탐색
			else {
				start = mid +1;
			}
			
		}
		// dp 테이블에서 삽입될 위치를 찾지 못한 경우(즉, 모든 수들보다 큰 경우)
		if(start == size) {
			return -1;
		}
		// dp 테이블에서 삽입될 위치를 찾은 경우
		else {
			return res;
		}
		
		
	}

}
