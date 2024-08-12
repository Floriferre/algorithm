package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20922_겹치는건싫어 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 길이 N
		int K = Integer.parseInt(st.nextToken()); // K개 이하로

		int[] list = new int[1000001];
		for (int i = 0; i < 1000001; i++) { // 초기화
			list[i] = 0;
		}

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = 0;
		int max = 0;
		// 투 포인터 사용, 같은 정수를 K개 이하로 포함
		while (right < N) {
			if (list[arr[right]] < K) {
				list[arr[right]] += 1;
				right++;
			} else {
				list[arr[left]] -= 1;
				left++;
			}
			max = Math.max(max, right - left);
//			System.out.println(num);

		}

		System.out.println(max);

	}
}
