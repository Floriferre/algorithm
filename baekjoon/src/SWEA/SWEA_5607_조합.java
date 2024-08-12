package SWEA;

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
*	자연수 N, R이 주어진다
*	이때 N combination R의 값을 1234567891로 나눈 나머지를 출력하시오
*	예를 들면 N이 4, R이 2라면 4 combination 2는 (4 * 3) / (2 * 1) = 6이 된다.
*
*	<입력>
*	첫째줄 : 테스트케이스 개수 T (1 ≤ T ≤ 20)
*	TC1 : 정수 N, R (1 ≤ N ≤ 1000000, 0 ≤ R ≤ N)
*	
*	<출력>
*	각 테스트케이스마다 한 줄에 걸쳐, 테스트케이스 수 “#(TC) “를 출력하고, N combination R을 1234567891로 나눈 나머지를 출력하시오.
*
*	<풀이>
*	페르마의 소정리를 이용하는 문제
*	
*	> 페르마의 소정리
*		제곱수에서 나머지 연산의 순환 
*		a^p % p = a (p는 소수, a는 정수)
*		a^(p-1) % p = 1
*
*		아주 큰 제곱수에 대한 나머지 계산이 가능해진다!
*		ex) 7^222 %11 을 구해보자
*			7^10%11 = 1
*			7^222 = ((7^10)^22) * 7^2 = 7^2%11 = 49%11 = 5
*
*		이항정리에도 이용 가능
*		nCr%p 	= (n!/((n-r)! * r!))%p
*				= (A/B)%p
*				= (A*B^(p-2))%p
*		A = n!
*		B = (n-r)!*r!
*
*		ex) 7C5%11  = (7!/(2!*5!))%11
*					= (7! * (2!*5!)^(11-2))%11
*					= (7!%11 * (2!*5!)^9%11)%11
*/

public class SWEA_5607_조합 {

	static long mod = 1234567891;
	
	static long [] factorials = new long[1000001]; //미리 팩토리얼 결과를 다 계산해서 넣어두자! (시간적 이점)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		calFac();
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			long result = 0;

			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			// nCr 구하기 1234567891로 나눈 나머지임을 명심할 것!
			// nCr%p = (n!/((n-r)! * r!))%p = (A*B^(p-2))%p
			// A = n!
			// B = (n-r)!*r!

			result = (factorials[N] % mod) * power(factorials[N-R] * factorials[R]%mod, mod - 2) % mod;

			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}

	// 재귀로 짠 팩토리얼 이거 쓰면 재귀 호출 스택 제한 걸린다
//	private static int factorial(int n) {
//		if(n==1) return 1;
//		
//		return n*factorial(n-1);
//	}

	// 반복문을 통해 처리하는 팩토리얼
	private static long factorial(long n) {
		long answer = 1L;
		while (n > 1) {
			answer *= n;
			n--;
		}

		return answer;
	}
	
	private static void calFac() {
		factorials[1] = 1;
		for(int i = 2; i < 1000001; i++) {
			factorials[i] = i*factorials[i-1]%mod;
		}
	}
	

	// pow 계산하는 메서드 밑a 지수b
	private static long power(long a, long b) {
		// 지수가 1이면
		if (b == 1) {
			return a % mod;
		}

		// 지수 계속 나누기 
		long temp = power(a, b / 2)%mod;

		// 지수가 홀수라면
		if (b % 2 == 1) {
			return (((temp * temp) % mod) * a) % mod;
		}
		return (temp * temp) % mod;
	}
}
