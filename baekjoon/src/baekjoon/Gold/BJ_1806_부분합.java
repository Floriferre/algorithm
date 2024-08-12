package baekjoon.Gold;

/*
@author 정여민
@since 2024-04-16, 화, 9:18
@see https://www.acmicpc.net/problem/1806
@git
@youtube
@performance
@category #
@note
	<문제>
	10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다.
	수열에서 연속된 수들의 부분합 중 그 합이 S이상 되는 것 중 가장 짧은 것의 길이

	<입력>
	첫째줄 : N S  (10 ≤ N < 100,000) (0 < S ≤ 100,000,000)
	둘째줄 : 수열 (10,000이하의 자연수)

	<출력>
	구하고자 하는 최소 길이 출력
	만약 그러한 합을 만드는 게 불가능하다면 0을 출력
	
	<풀이>
	투 포인터 쓰면 될 것 같다
	매 회차별로 최소길이 업데이트
	초기화 : 길이 MAX value로


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1806_부분합 {

	public static int N;    // 수열의 길이 N
	public static int S;    // 합 S

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		int result = Integer.MAX_VALUE; // 길이 최솟값

		int[] series = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			series[i] = Integer.parseInt(st.nextToken());
		}

		int l = 0;
		int r = 0;

		int sum = series[l];    // 합 체크
		while (l <= r && r < N) {
			if (sum >= S) {    // 현재까지 합이 S 이상이면
				result = Math.min(result, r - l + 1); //  길이 최솟값 업데이트
				sum -= series[l];
				l++;    // 왼쪽을 증가시켜 길이 줄이기
			} else {    // 현재까지 길이 합이 S보다 작으면
				r += 1;
				if (r < N) {
					sum += series[r];    // 길이 늘리기
				} else {
					break;
				}
			}
		}

		if (result == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(result);
		}
	}

}
