package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

@author 정여민
@since 2023. 9. 16.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeRZV6kBUDFAVH
@git
@youtube
@performance
@category #
@note 	
*	<문제> 
*	N개의 숫자가 적힌 게임판
*	+ - X / 연산자 카드를 숫자 사이에 넣어 값 구하기
*	연산자는 왼쪽에서 오른쪽으로 차례대로 계산
*	주어진 연산자 카드를 이용하여 계산한 수식 중 결과가 최대, 결과가 최소인 수식 찾고 두 값 사이의 차이 출력
*	
*	<제한사항>
*	숫자의 개수 N 3~12
*	연산자 카드는 N-1개
*	숫자는 1~9
*	숫자 사이에는 연산자 1개
*	나눗셈 소수점 이하 버리기 (round 쓸 것)
*	연산 중의 값은  -100,000,000 이상 100,000,000 이하 => int 형 변수 가능
*
*	<입력> 
*	첫째줄 : 테스트 케이스 개수 T
*	TC1 : 숫자 개수 N
*	TC2 : 연산자 N-1개가 공백
*	TC3 : 수식에 들어가는 N개의 숫자 공백
*	
*	<풀이>
*	순열로 가능한 연산자의 순서를 구하고 이를 적용하여 최댓값 최솟값 찾기
*	
*	<Problem> 
*	1. 숨열 : 순열로 가능한 모든 조합을 만드니까 중복이 가능해져서 (예를 들어 +, +, - , *, / 을 사용하는 경우 첫번째의 +과 두 번째 +가 겹쳐서 중복이 발생)
*	     연산량이 장난이 아니게 됨 -> 시간 초과
*	2. 중복 순열 : +, -, *, / 네 가지값을 N-1개만큼 중복으로 순열을 만들게 하고, 매 회차마다 각 연산자의 개수가 넘치는지 확인하여 가지치기 -> 얘도 시간 초과 
*	3. 연산자 개수만큼 인자로 넣어주고 해당 연산자를 쓰면 -1씩 해주며 돌기 -> 아래 링크 참고
*
*	<참고>
*	https://focus-on-my.tistory.com/222
*
*
*/

public class SWEA_4008_숫자만들기 {

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	static int[] numbers;
	static char[] operators;
	static int[] operatorsN;

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			operators = new char[N - 1];
			numbers = new int[N];
			operatorsN = new int[4];

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			// 연산자 입력 받기
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			for (int i = 0; i < 4; i++) {
				int num = Integer.parseInt(st.nextToken());
				operatorsN[i] = num;
				char current = ' ';
				switch (i) {
				case 0:
					current = '+';
					break;
				case 1:
					current = '-';
					break;
				case 2:
					current = '*';
					break;
				case 3:
					current = '/';
					break;
				}
				for (int j = idx; j < idx + num; j++) {
					operators[j] = current;
				}
				idx += num;
			}

			// 숫자 입력 받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}

			// 자 이제 순열을 돌려서 구하자!
			permutation2(1,  operatorsN[0],  operatorsN[1],  operatorsN[2],  operatorsN[3], numbers[0]);

			sb.append("#" + t + " " + (max - min) + "\n");
		}
		System.out.println(sb);

	}

	private static void permutation2(int nthChoice, int plus, int minus, int multiply, int divisor,
			int result) {

		if (nthChoice == N) {
			// 연산을 하자!

			max = Math.max(result, max);
			min = Math.min(result, min);

			return;
		}

		if (plus > 0) {
			permutation2(nthChoice + 1,  plus - 1, minus, multiply, divisor, result + numbers[nthChoice ]);
		}
		if (minus > 0) {
			permutation2(nthChoice + 1, plus, minus - 1, multiply, divisor, result - numbers[nthChoice ]);
		}
		if (multiply > 0) {
			permutation2(nthChoice + 1,  plus, minus, multiply - 1, divisor, result * numbers[nthChoice ]);
		}
		if (divisor > 0) {
			permutation2(nthChoice + 1,  plus, minus, multiply, divisor - 1, result / numbers[nthChoice ]);
		}

	}

	private static void permutation(int nthChoice, char[] choosed, boolean[] visited) {

		if (nthChoice == choosed.length) {
			// 연산을 하자!
			int result = numbers[0];
			for (int i = 0; i < N - 1; i++) {
				switch (choosed[i]) {
				case '+':
					result = result + numbers[i + 1];
					break;
				case '-':
					result = result - numbers[i + 1];
					break;
				case '*':
					result = result * numbers[i + 1];
					break;
				case '/':
					result = (int) Math.floor((result / numbers[i + 1]));
					break;
				}
			}

			max = Math.max(result, max);
			min = Math.min(result, min);

			return;
		}

		for (int i = 0; i < operators.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = operators[i];
				permutation(nthChoice + 1, choosed, visited);
				visited[i] = false;
			}
		}

	}

}
