package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 27.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV18P2B6Iu8CFAZN&probBoxId=AYrUJnmKsIwDFAV6&type=PROBLEM&problemBoxTitle=0927%EC%A3%BC&problemBoxCnt=1&&&&&&#none
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	사람 네트워크 中  CC : 한 사용자가 다른 모든 사람에게 얼마나 가까운가
*	CC(i) = sum j dist(i,j)
*
*	<제약사항>
*	자기 자신을 연결하는 간선 X
*	N의 최대는 1000
*	
*	<입력>
*	첫째줄 : 테스트 케이크 개수 T
*	TC1 : 양의 정수 N(사람수) 인접행렬(row by row)
*
*	<출력>
*	#t CC중 최솟값
*
*	<풀이>
*	한 정점에서 다른 정점까지의 최단거리 구해야하므로 플로이드 워셜 알고리즘을 써보자! 
*
*/

public class SWEA_1263_사람네트워크2 {

	static int N;
	static int[][] info;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t < T+1; t++) {
			int result = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			info = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if(i==j) {
						info[i][j] = 0;
					}else {
						if(temp == 0) {
							info[i][j] = 2000;
						}else {
							info[i][j] = temp;
						}						
					}
				}
			}
			
			cc(info);
			
			for(int i = 0; i < N; i++) {
				int sum = 0;
				for(int j = 0; j < N; j++) {
					sum += info[i][j];
				}
				result = Math.min(sum, result);
			}
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
		

	}

	private static void cc(int[][] info) {

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					info[i][j] = Math.min(info[i][k] + info[k][j], info[i][j]);
				}
			}
		}

	}

}
