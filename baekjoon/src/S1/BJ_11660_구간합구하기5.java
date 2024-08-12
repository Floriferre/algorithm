package S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 2.
@see https://www.acmicpc.net/problem/11660
@git
@youtube
@performance 
@category #
@note
	첫째줄 : 표의 크기 N, 합을 구해야 하는 횟수 M
	둘째줄 : N개의 줄에 표에 채워져 있는 수 -> board 배열
	M개의 줄 : 네 개의 정수 x1, y1, x2, y2
	각 행별로 누적합을 구해두고 이를 사용하자!	
*/

public class BJ_11660_구간합구하기5 {
	
	static int N;
	static int M;
	static int [][]board;
	
	// 미리 누적합 저장해둘 변수
	
	static int [][] sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		// 보드 입력
		board = new int [N][N+1];
		
		for(int i = 0; i <N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < N+1; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sum = new int[N][N+1];
		// 행 별로 누적 합을 미리 구해두기! 
		for(int i = 0; i < N; i++) {
			for(int j = 1; j <N+1; j++) {
				sum[i][j] = sum[i][j-1] + board[i][j];
			}
		}
		
		// M개의 줄에 
		for(int i = 0; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int result = 0;
			for(int j = x1-1; j < x2; j++) {
				result += sum[j][y2] - sum[j][y1-1];
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	
	
}
