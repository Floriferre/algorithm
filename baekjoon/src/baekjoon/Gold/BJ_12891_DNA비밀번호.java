package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

@author 정여민
@since 2023. 8. 3.
@see https://www.acmicpc.net/problem/12891
@git
@youtube
@performance
@category #슬라이딩 윈도우
@note 
	DNA 문자열 -> A C G T 로만 이루어져 있음
	부분 문자열 구하기 -> 이 중 A C G T의 갯수를 만족하는 것만 인정
	S : DNA 문자열의 길이
	P : 부분 문자열의 길이	1<= P <= S <= 1,000,000
	str : 문자열 -> dna char 배열에 저장하자
	A, C, G, T의 최소 갯수
*
*/

public class BJ_12891_DNA비밀번호 {
	static int S, P;
//	static int A, C, G, T;
	static int cA, cC, cG, cT;
	static int result;
	static char[] dna;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();

		dna = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			dna[i] = str.charAt(i);
		}

		// 차례대로 A C G T
		arr = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		slidingwindow();
		System.out.println(result);

	}

	// 슬라이딩 윈도우 사용
	private static void slidingwindow() {
		// 초기값 갱신
		for (int i = 0; i < P; i++) {
			switch (dna[i]) {
			case 'A':
				cA++;
				break;
			case 'C':
				cC++;
				break;
			case 'G':
				cG++;
				break;
			case 'T':
				cT++;
				break;
			}
		}

		// 최소 문자수 체크
		check();

		// 슬라이딩 윈도우 사용
		for (int i = P; i < S; i++) {
			// 초기값 뺴주기
			switch (dna[i - P]) {
			case 'A':
				cA--;
				break;
			case 'C':
				cC--;
				break;
			case 'G':
				cG--;
				break;
			case 'T':
				cT--;
				break;
			}

			// 새로 들어오는 값 더해주기
			switch (dna[i]) {
			case 'A':
				cA++;
				break;
			case 'C':
				cC++;
				break;
			case 'G':
				cG++;
				break;
			case 'T':
				cT++;
				break;
			}

			check();
		}

	}

	// 각 dna의 최소 문자의 수를 만족하는지 확인하는 함수
	private static void check() {
		if (arr[0] <= cA && arr[1] <= cC && arr[2] <= cG && arr[3] <= cT) {
			result += 1;
		}
	}

}
