package TestA;

import java.io.*;
import java.util.*;


/*
@author 정여민
@since 2023. 10. 11.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	서점에 높이 B인 선반
*	서점의 직원 N명. 키가 각각 다름. 
*	탑을 쌓아서 선반 위의 물건 사용
*	탑의 높이가 B이상인 경우 물건 사용, B이상 중 높이가 가장 작은 것!
*
*	<입력>
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : N, B (1 ≤ N ≤ 20, 1 ≤ B ≤ S)
*	TC2 : N개의 정수 공백으로, 각 정수는 각 점원의 키 Hi (1 ≤ Hi ≤ 10,000)
*
*	<출력>
*	만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 탑의 높이와 B의 차이가 가장 작은 것을 출력한다.	
*
*	<풀이>
*	직원 N명 중 1~N명을 택하여 탑을 만들어야하므로 부분집합을 사용하자
*	백트래킹도 적절하게 쓰면 좋을 것 같다! (선택한 사람의 키를 더해서 넘겨줄까?)
*
*
*
*
*
*
*/
public class TestA_SWEA_1486_장훈이의높은선반 {
	
	static int N, B;
	static int [] height;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t < T+1; t++) {
			result = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 직원 명수
			B = Integer.parseInt(st.nextToken());	// 목표 탑의 높이
			
			st = new StringTokenizer(br.readLine());
			
			height = new int[N];	// 직원 키 저장
			for(int i=0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			subset(0, 0);
			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
		
	}
	
	private static void subset(int nth, int sum) {
		if(sum -B >= result) {	// 현재까지의 직원 키의 합이 결과보다 더 크면 
			return;
		}
		if(nth == N) {
			if(sum >= B) {
				result = Math.min(result, sum-B);				
			}
			return;
		}
		
		// 해당 키 선택
		subset(nth+1, sum + height[nth]);
		// 해당 키 선택 X
		subset(nth+1, sum);
	}
}
