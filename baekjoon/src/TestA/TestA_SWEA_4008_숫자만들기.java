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
*	연산자 카드
*	연산자 우선순위는 고려하지 않고 왼쪽에서 오른쪽으로 차례대로 계산
*	주어진 연산자 카드를 사용했을 때 그 결과가 최대와 최소가 되는 수식을 찾고, 두 값의 차이를 출력하시오
*
*	<제약사항>
*	숫자의 개수 N 3<=N<=12
*	연산자 카드 : N-1
*	숫자는 1~9
*	모든 연산자 카드 사용해야함
*	나눗셈을 계산할 때 소수점 이하는 버림
*	연산 중의 값은 -100,000,000 이상 100,000,000 이하임	=> int로 처리 가능하다! (int는 21억까지 가능)
*
*	<입력>
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : 숫자의 개수 N
*	TC2 : 연산자 카드의 개수 + - * '/' 순서
*	TC3 : 수식에 들어가는 N개의 숫자 
*
*	<출력>
*	각 테케에 대한 값 : 최댓값과 최솟값의 차이
*
*	<풀이>
*	연산자가 여러개고, 순서가 중요하니까 중복 순열을 생각할 수 있는데 이 경우 N이 12까지 있기 때문에 시간 초과 날 가능성
*	넥스트퍼뮤테이션 혹은 연산자 갯수 조절을 필요로 할 것 같다
*	넥퍼는 내가 못 외웠으니까 연산자 갯수 조절로 한 번 해보자!
*	(가지치기를 빡세게. 연산자 갯수 같이 넘겨주기)
*
*
*/
public class TestA_SWEA_4008_숫자만들기 {

	static int N;
	static int [] operators = new int[4];	// 연산자 갯수 + - * / 순
	static int [] nums;	// 숫자 저장
	static int MaxValue, MinValue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t <= T; t++) {
			MaxValue = Integer.MIN_VALUE;
			MinValue = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 숫자 개수
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {	// 각 연산자 개수
				operators[i] = Integer.parseInt(st.nextToken());
			}
			
			nums = new int[N];	// 숫자 입력 받기
			st = new StringTokenizer(br.readLine());
			for(int i=0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			cal(0, operators[0], operators[1], operators[2], operators[3], nums[0]);
			
			sb.append("#" + t + " " + Math.abs(MaxValue-MinValue) + "\n");
		}
		System.out.println(sb);
	}
	
	// 걍 부분집합? 재귀로 돌려볼까 
	private static void cal(int nth, int plus, int minus, int multiply, int divide, int result) {
		if(nth == N-1) {	// 연산자 다 썼으면 
			// 숫자 계산결과 업데이트
			MaxValue = Math.max(MaxValue, result);
			MinValue = Math.min(MinValue, result);
		}
		
		if(plus > 0) cal(nth+1, plus-1, minus, multiply, divide, result + nums[nth+1]);
		if(minus > 0) cal(nth + 1, plus, minus - 1, multiply, divide, result - nums[nth+1]);
		if(multiply > 0) cal(nth+1, plus, minus, multiply-1, divide, result * nums[nth+1]);
		if(divide > 0) cal(nth+1, plus, minus, multiply, divide-1, result/nums[nth+1]);
	}

}
