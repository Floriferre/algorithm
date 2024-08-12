package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 10.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWT-lPB6dHUDFAVT&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=7
@git
@youtube
@performance
@category #
@note 
	햄버거의 맛은 최대한 유지, 정해진 칼로리를 넘지 않는 햄버거 
	햄버거 재료에 대한 점수, 가게에게 제공하는 재료에 대한 칼로리 주어짐
	정해진 칼로리 이하의 조합 중에서 가장 선호하는 햄버거를 조합해주는 프로그램 만들기 
	
	햄버거의 선호도 : 조합된 재료들의 맛에 대한 점수 합, 같은 재료 여러번 X
	
	<입력> 
	line 1 : 테스트 케이스의 수 (20개) -> 8초 
	TC line1 : 재료의 수 N, 제한 칼로리 L (1 ≤ N ≤ 20, 1 ≤ L ≤ 104)
	TC line N : 재료에 대한 맛에 대한 점수 Ti, 칼로리 Ki (1 ≤ Ti ≤ 103, 1 ≤ Ki ≤ 103)

	<solution>
	부분집합을 사용해보자! 
*/

public class SWEA_5215_햄버거다이어트 {

	static int [] ingredientsT;
	static int [] ingredientsC;
	static int taste;
	
	static int N, L;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		
		for(int t = 1; t < T +1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			
			ingredientsT = new int [N];
			ingredientsC = new int [N];
			
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredientsT[i] = Integer.parseInt(st.nextToken());
				ingredientsC[i] = Integer.parseInt(st.nextToken());
			}
			taste = 0;
			SubSet2();
			
			sb.append("#" + t + " " + taste + "\n");
		}
		System.out.println(sb);

	}
	
	
	static void SubSet2() {
		for(int i = 0; i < 1<< N; i++) {
			int tempTaste = 0;
			int tempCal = 0;
			for(int j = 0; j < N; j++) {
				if((i & (1<<j))>0) {
					// 이러면 부분집합에 포함 
					tempTaste += ingredientsT[j];
					tempCal += ingredientsC[j];
				}
			}
		// 한 라운드 끝
			if(tempCal <= L) {
				taste = Math.max(taste, tempTaste);
			}
		}
	}

}























