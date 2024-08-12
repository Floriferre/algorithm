package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 1.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV139KOaABgCFAYh&probBoxId=AYlH3z4K78oDFAVR+&type=PROBLEM&problemBoxTitle=0731%EC%A3%BC&problemBoxCnt=++3+
@git
@youtube
@performance 메모리 20,916KB 시간 130ms
@category # 맨하튼거리 기반 탐색 -> 마름모 탐색
@note 
	
*/

public class SWEA_농작물수확하기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T;
		T=Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int [][] field = new int [size][size];
			int score = 0; 
			// 밭 만들기
			for(int i = 0; i < size; i++) {
				String s = st.nextToken();
				for(int j = 0; j < size; j++) {
					field[i][j] = s.charAt(j) - '0';
				}
			}
			
			// 수익 계산하기
			int space = size;
			for(int i = 0; i < size; i++) {
				
				space /= 2;
				// 1번째 탐색
				// 빈 공간 건너뛰기 
				for(int j = 0; j < size; j++) {
					
				}
			}
			
			
		}
	}

}
