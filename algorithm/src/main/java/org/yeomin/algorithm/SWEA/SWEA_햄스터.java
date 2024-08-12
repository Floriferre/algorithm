package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 2.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWxQ310aOlQDFAWL&probBoxId=AYlH3z4K78oDFAVR+&type=PROBLEM&problemBoxTitle=0731%EC%A3%BC&problemBoxCnt=++4+
@git
@youtube
@performance 
@category #
@note 
	N : 햄스터 우리 
	각 우리에 0마리 이상 X마리이하 햄스터
	M개의 기록 -> ㅣ~r 우리의 햄스터 마리수 : s
	
	T : 테스트 케이스 수
	N, X, M 1<=N <=6, 1<=X,M <= 10
	M개의 줄 : l, r, s (1<= 1 <= r <= N, 0<=s<=60)
	=> 중복 조합이네
*/

public class SWEA_햄스터 {

	static int[] cage;

	static int[] index;

	static int [][] Matrix;
	
	static int N, X, M;
	
	static int [] temp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 조합을 전부 구해보자
		int T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			// N 우리 개수, X 우리 당 햄스터 최대 마릿수, M개의 기록
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			index = new int[X + 1];
			for (int i = 0; i < X + 1; i++) {
				index[i] = i;
			}
			
			
			// 햄스터 기록
			Matrix = new int [M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				Matrix[i] = new int[] {l,r, s};
			}
//			DupComb(0, 0, new int[X]);
			DupPerm(0, new int[N]);
			
			System.out.printf("#%d\n", t);
//			System.out.println(Arrays.toString(temp));

		}
	}
	
	
	
	
	
	
	// 중복 순열
	static void DupPerm(int nth, int [] choosed) {
		if (nth == choosed.length) {
			
//			for(int a : choosed) {
//				System.out.print(a);
//				
//			}
			// choosed 한 줄씩(조합 하나씩 배열로 만들기)
			temp = new int [choosed.length];
			for(int i = 0; i < choosed.length; i++) {
				System.out.print(choosed[i]);
				temp[i] = choosed[i];
			}
			System.out.println(" ");
			
			boolean check = true;
			// 입력받은 값이랑 비교
			for(int i = 0; i < Matrix.length; i++) {
				int l = Matrix[i][0];
				int r = Matrix[i][1];
				
				// 구간의 합이 s와 같은지 비교
				int sum = 0;
				for(int j = l-1; j < r; j++) {
					sum += temp[j];
				}
				if(sum == Matrix[i][2]) {
					check = false;
//					return temp;
					System.out.println("정답 " + Arrays.toString(temp));
					break;
				}
			}
			if(check) {
				System.out.println(-1);
			}
			
//			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for (int i = 0; i < index.length; i++) {
			choosed[nth] = index[i];
			DupPerm(nth+1, choosed);
		}
	}

	// 중복 조합
//	static void DupComb(int nth, int startIndex, int[] choosed) {
//		if (nth == choosed.length) {
//			System.out.println(Arrays.toString(choosed));
//			return;
//		}
//
//		for (int i = startIndex; i < index.length; i++) {
//			choosed[nth] = index[i];
//			DupComb(nth + 1, startIndex, choosed);
//		}
//	}


}
