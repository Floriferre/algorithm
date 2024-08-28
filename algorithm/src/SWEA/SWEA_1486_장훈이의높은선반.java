package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 19.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	서점 운영하는 장훈이 키 되게 커서 선반 위에 물건 둠
*	직원 N명이 힘을 합쳐 물건 꺼내기로
*	탑의 높이 = 직원의 키의 합
*	높이가 B이상인 탑 중에서 높이가 가장 작은 것 구하기
*
*	<입력>
*	첫째줄 : 테스트 케이스 개수 T
*	TC1 : 두 정수 점원의 수 N, 목표 탑의 높이 B  (1 ≤ N ≤ 20, 1 ≤ B ≤ S)
*	TC2 : 점원 N명의 키
*
*	<출력>
*	만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것을 출력
*
*	<풀이>
*	부분집합으로 풀면 될 것 같은 문제. 조합으로 풀기에는 nC1 ~ nCn까지 다 해봐야하므로
*	현재의 최솟값보다 키가 넘치면 	 
*	백트래킹을 적절하게 활용하자.
*
*
*/


public class SWEA_1486_장훈이의높은선반 {

	static int result, N, B;
	static int [] src;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t < T+1; t++) {
			result = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 직원 명수
			B = Integer.parseInt(st.nextToken());	// 탑의 높이
			
			// 직원의 키 입력 받기
			st = new StringTokenizer(br.readLine());
			src = new int [N];
			for(int i = 0; i < N; i++) {
				src[i] = Integer.parseInt(st.nextToken());
			}
			
			makeSubset(0, new boolean [N]);
			
			
			sb.append("#" + t + " " + (result-B) + "\n");
		}
		System.out.println(sb);
		
	}
	
	// 부분 집합
	private static void makeSubset(int nth, boolean [] status) {
		if(nth == status.length) {
			// result 값 업데이트
			int sum = 0;
			for(int i = 0; i < status.length; i++) {
				if(sum > result) return;
				
				if(status[i]) {
					sum += src[i];
				}
			}
			if(sum >= B) result = Math.min(result, sum);	// 탑 높이가 더 클 때만 result값 업데이트
		
			return;
		}
		
		status[nth] = true;
		makeSubset(nth+1, status);
		status[nth] = false;
		makeSubset(nth+1, status);
		
		
	}

}
