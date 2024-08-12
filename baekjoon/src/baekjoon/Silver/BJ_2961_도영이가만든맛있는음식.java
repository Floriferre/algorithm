package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.security.auth.Subject;

/*
@author 정여민
@since 2023. 8. 3.
@see https://www.acmicpc.net/problem/2961
@git
@youtube
@performance
@category #
@note 
	재료 N개 - 쓴맛 B & 신맛 S
		- 쓴맛 : 합
		- 신맛 : 곱
	첫째줄 : 재료의 개수 N	1 <= N <= 10
	N개의 줄 : 신맛 & 쓴맛이 공백 -> 각각 배열로 받아올까? 1<= S, B < 1,000,000,000
	쓴맛과 신맛의 차이가 가장 작은 요리의 차이를 출력
	-> 부분집합을 만들어서 이에 해당되는 쓴맛과 신맛을 구한 후 차이 저장 -> 작은 것 업데이트! 
*/

public class BJ_2961_도영이가만든맛있는음식 {
	
	static int [] S;
	static int [] B;
	
	static int N;
	
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = new int [N];
		B = new int [N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			S[i] = s;
			B[i] = b;
		}
		
		SubSet();
		
		System.out.println(result);
	}
	
	// 바이너리 카운팅을 사용하여 해보자~ 
	public static void SubSet() {
		int sour = 1;
		int bitter = 0;
		boolean check = false;
		for(int i = 0; i < 1 << S.length; i++) {
			sour = 1; 
			bitter = 0;
//			System.out.print("[");
			for(int j = 0; j < S.length; j++) {
				if((i&(1<<j)) > 0) {
//					System.out.print(S[j]);
					sour *= S[j];
					bitter += B[j];
					System.out.println(j + " : " + sour + " , " + bitter);
					check = true;
				}
			}
//			System.out.println("]");
			if(check) {
				result = Math.min(result, Math.abs(sour-bitter));				
			}
		}
	}

}
