package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정여민
 * @since 2023. 8. 10.
 * @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWIeUtVakTMDFAVH&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=7
 * @git
 * @youtube
 * @performance
 * @category #
 * @note 두 명의 손님에게 음식 제공 최대한 비슷한 맛을 만들 것! N개의 식재료(N은 짝수) 절반씩 나누어 제공 식재료별로 시너지
 *       효과(S)가 있음 -> for문 돌려서 얻으면 될 듯 음식의 맛 : 시너지 효과의 총합 i, j를 쓴다면 Sij, Sji 둘 다
 *       구해야함 두 음식의 맛이 최소가 되었을 때의 차이 값! <입력> line 1: 테스트케이스 갯수 TC line1 : 식재료의 수
 *       N TC line ~ N : 시너지 값 -> 2 차원 배열로 받을 것
 * 
 *       그냥.. r이 정해진 경우니까 조합으로 돌리고, 각 케이스 별로 음식별 맛을 구해서 차이 구하면 될 듯? 조합의 시간 복잡도
 *       O(2^N) 충분쓰
 *
 *
 */

public class SWEA_4012_요리사 {
	static int[][] S; // 시너지 저장할 배열

	static int[] base;

	static int N;

	static int SA, SB;
	static int sub = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			// 뭐 선택하고 안 했는지 체크하려고 만든 배열 (나중에 조합에 이용할 것)
			base = new int[N];
			for (int i = 0; i < N; i++) {
				base[i] = i;
			}

			// 시너지 배열 저장
			S = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			makeCombination(0, 0, new int[N / 2]);
			makeCombination2(0, 0, new int [N/2]);
			makeCombination(0, 0, new int [N/2]);
			sb.append("#" + t + " " + sub + "\n");
		}
		System.out.println(sb);

	}

	// 조합 코드
	static void makeCombination(int nth, int startIndex, int[] choosed) {
		if (nth == choosed.length) {
			// 현재 선택된 재료 N/2개
			// A의 식재료는 choosed
			// B의 식재료는 Unchoosed
			int[] unchoosed = new int[N / 2];
			int [] base2 = base;
			
			System.out.println(Arrays.toString(choosed));
			
			// 걍 배열 돌면서 해야겠다
			for(int a : choosed) {
				System.out.println("a는 : " + a);
				base2[a] = -1;
			}
			
			for(int i = 0, k=0; i < N; i++) {
				if(base2[i] != -1) {
					unchoosed[k] = i;
					k++;
				}
			}


			// 두 맛을 저장할 변수
			SA = 0;
			SB = 0;
			sub = 0; // 맛의 차이 저장
			for(int a : choosed) {
				for(int b : choosed) {
					SA += S[a][b];
				}
			}
			
			for(int a : unchoosed) {
				for(int b : unchoosed) {
					SB += S[a][b];
				}
			}
			
			sub = Math.min(Math.abs(SA-SB), sub);

		System.out.println("A : " + Arrays.toString(choosed));
		System.out.println("B : " + Arrays.toString(unchoosed));
		System.out.println();
			System.out.println(sub);
			return;
		}

		for (int i = startIndex; i < base.length; i++) {
			choosed[nth] = base[i];
			makeCombination(nth + 1, i + 1, choosed);
		}
	}
	
	
	static void makeCombination2(final int nth, final int startIndex, int[] choosed) {
		// 결정 요인은 무엇인가? -> 2개 : 몇 번째 선택, 시작 인덱스(몇번째 인덱스부터 고를 것인가?)
		if (nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}

		for (int i = startIndex; i <base.length; i++) {
			choosed[nth] = base[i];
			makeCombination2(nth + 1, i + 1, choosed); // i+1인 것 잊지 말자!

		}

	}

}
