package baekjoon.Gold;

/*
@author 정여민
@since 2024-03-11, 월, 13:3
@see https://www.acmicpc.net/problem/14719
@git
@youtube
@performance 14304KB 136ms
@category #구현 #시뮬레이션
@note
	<문제>
	2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다.
	비가 충분히 많이 온다고 할 때, 고이는 빗물의 총량은 얼마일까?

	<입력>
	첫번째 줄 : 세로길이 H, 가로 길이 W (1 ≤ H, W ≤ 500)
	두번째 줄 : 블록이 쌓인 높이 0 <= 높이 <= H 맨 왼쪽부터 W개

	<출력>
	한 칸의 용량은 1이다. 고이는 빗물의 총량을 출력하여라.

	<풀이>
	물이 고인 곳을 체크하기 위해서는 좌우 높이도 중요하다.
	해당 기둥의 좌우를 비교하자.
	좌우를 비교하는 게 옳을까? 아니면 기둥의 맨 위에서부터 옆으로 가는 게 옳을까

	해당 위치에서 왼쪽으로 갔을 때 가장 최고값 VS 오른쪽으로 갔을 때 가장 최고값
	둘 중 더 작은 것이 해당 위치에서의 빗물 높이
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14719_빗물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 세로 가로
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		// 바닥 높이 입력 받기
		int floor[] = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			floor[i] = Integer.parseInt(st.nextToken());
		}

		// 풀이
		// 빗물
		int result = 0;
		for (int i = 1; i < W - 1; i++) {
			// 죄우에서 가장 큰 높이
			int left = floor[i];
			int right = floor[i];

			for (int j = 0; j < i; j++) {
				left = Math.max(left, floor[j]);
			}

			for (int j = i + 1; j < W; j++) {
				right = Math.max(right, floor[j]);
			}

			result += Math.min(left, right) - floor[i];
		}

		System.out.println(result);

	}
}
