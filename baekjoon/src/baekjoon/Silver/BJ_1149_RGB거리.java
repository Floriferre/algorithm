package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 29.
@see https://www.acmicpc.net/problem/1149
@git
@youtube
@performance 14620KB 148ms
@category #DP
@note 
*	<문제> 
*	1~N번 집
*	집은 이웃한 집과 색깔이 달라야 한다
*	R, G, B 컬러별로 비용이 다름
*	모든 집을 칠하는 비용의 최솟값*
*
*	<입력>
*	첫째줄 : 집의 수 N (2<=N<=1000)
*	~N개줄 : 해당 집을 빨강, 초록, 파랑으로 칠하는 비용
*
*	<출력>
*	모든 집을 칠하는 비용의 최솟값
*
*	<풀이>
*	DP로 배열 [N][3] 으로 만들어서 RGB 선택별로 집값 저장해서 맨 마지막에서 min값 출력하기
*
*/

public class BJ_1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int house [][] = new int[n][3];	// R, G, B
		
		st = new StringTokenizer(br.readLine());
		house[0][0] = Integer.parseInt(st.nextToken());
		house[0][1] = Integer.parseInt(st.nextToken());
		house[0][2] = Integer.parseInt(st.nextToken());
		
		
		for(int i = 1; i <n; i++) {
			st = new StringTokenizer(br.readLine());
			house[i][0] = Math.min(house[i-1][1], house[i-1][2]) + Integer.parseInt(st.nextToken());
			house[i][1] = Math.min(house[i-1][0], house[i-1][2]) + Integer.parseInt(st.nextToken());
			house[i][2] = Math.min(house[i-1][0], house[i-1][1]) + Integer.parseInt(st.nextToken());
		}
		
		System.out.println(Math.min(Math.min(house[n-1][0], house[n-1][1]), house[n-1][2]));
		
	}

}
