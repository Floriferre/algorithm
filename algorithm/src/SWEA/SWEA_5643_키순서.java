package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 4.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	키 정보(A가 B보다 작다)가 주어졌을 때 자신의 키를 정확히 아는 학생들의 명수?
*
*	<입력>
*	첫째줄 : 테스트케이스 개수	(1<=T<=15)
*	TC1 : 학생수 N	(2<=N<=500)
*	TC2 : 두 학생의 키를 비교한 횟수 M  (0 ≤ M ≤ N*(N-1)/2)
*	M개의 줄 : 두 학생 키를 비교한 결과 a b : a < b
*
*	<출력>
*	#1 + 자신의 키가 몇번째인지 알 수 있는 학생이 모두 몇명인지
*	
*
*	<풀이>
*	자신의 키를 알 수 있는 학생은 다른 모든 노드로부터 자기까지의 거리를 구할 수 있는 녀석들
*	플로이드 워셜을 사용해볼까?
*
*
*
*
*/

public class SWEA_5643_키순서 {

	static int[][] info;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t < T + 1; t++) {
			int result = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 학생수
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 두 학생의 키를 비교한 횟수

			info = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						info[i][j] = 0; // 자기 자신은 0으로 초기화
					else
						info[i][j] = 2000; // 다른 곳은 다 2000으로 초기화
				}
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;

				info[a][b] = 1; // 키를 알 수 있는 애들은 1로 초기화
			}

			// 플로이드 워셜 수행
			howTall(info);

//			for(int [] a : info) {
//				System.out.println(Arrays.toString(a));
//			}
//			

			// 수행 결과를 갖고 키를 알 수 있는 애들 다 찾아내기
			for (int j = 0; j < N; j++) {

				boolean flag = true; // 키를 알 수 있는지 없는지 비교
				for (int i = 0; i < N; i++) {
					if (i == j)
						continue;
					if (info[i][j] == 2000) {
						if (info[j][i] != 2000) {
							continue;
						}
						flag = false;
					}
				}
				if (flag)
					result++;
			}

			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);

	}

	private static void howTall(int[][] info) {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					info[i][j] = Math.min(info[i][k] + info[k][j], info[i][j]);
				}
			}
		}
	}

}
