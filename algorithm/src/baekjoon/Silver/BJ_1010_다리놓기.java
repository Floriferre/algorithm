package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 30.
@see https://www.acmicpc.net/problem/1010
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	서쪽 사이트 개수 N, 오른쪽 사이트 개수 M
*	서쪽 사이트 개수만큼 다리 짓기 -> 다리는 겹치면 안 됨!
*
*	<입력>
*	첫째줄 : 테스트케이스개수 T
*	둘째줄 ~ : 각 테스트 케이스에 대해 강의 서쪽 사이트, 동쪽 사이트 N, M
*
*	<출력>
*	경우의 수 출력
*	
*	<풀이>
*	mCn 을 구하자! 
*	DP 사용해보자 
*	n, M이 30이나 되어서 그냥 구하다가 터진다 
*
*/
public class BJ_1010_다리놓기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int B [][] = new int [M+1][N+1];
			
			for (int i = 0; i <= M; i++) {
				for (int j = 0; j <= Math.min(i, N); j++) {
					if(j==0||i==j) B[i][j] = 1;
					else B[i][j] = B[i-1][j-1]+B[i-1][j];
				}
			}
			sb.append(B[M][N] + "\n");
		}
		System.out.println(sb);
		
		

	}

}
