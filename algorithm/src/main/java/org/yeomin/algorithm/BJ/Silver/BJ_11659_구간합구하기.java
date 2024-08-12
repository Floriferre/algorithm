package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 2.
@see https://www.acmicpc.net/problem/11659
@git
@youtube
@performance 메모리 59604KB 시간 772ms
@category #누적합
@note 
	수 N개 -> i번째 수부터 j번째 수까지 합을 구하는 프로그램
	첫째줄 : 수의 개수 N, 합을 구해야 하는 횟수 M
	둘째줄 : N개의 수
	셋째줄 : 합을 구해야하는 구간 i와 j
	* 제약 조건
	* 	1 <= N <= 100,000
	* 	2 <= M <= 100,000
	* 	1 <= i <= j <= N
	** Solution
		* 누적합 : 미리 현재 인덱스까지의 누적 합을 구해놓고, 구간의 합을 구할 때는 얘네를 잘라서 쓰자!
		* 미리 배열에 합을 저장하는 데 드는 시간은 길어봤자 O(N)일 것이다
*
*/

public class BJ_11659_구간합구하기 {
	static int [] arr;
	
	// 합을 저장해둘 배열
	static int [] sum;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// N개의 수 
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 미리 합 구해놓기
		sum = new int[N+1];
		sum[0] = arr[0];
		sum[1] = arr[1];
		for(int i = 1; i < N+1; i++) {
			sum[i] = sum[i-1] + arr[i];
		}
		
		
//		System.out.println(Arrays.toString(sum));
		
		for(int t = 0; t < M; t++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			int temp = sum[j] - sum[i-1];
			
			sb.append(temp).append("\n");
		}
		
		System.out.println(sb);
		
		
	}

}
