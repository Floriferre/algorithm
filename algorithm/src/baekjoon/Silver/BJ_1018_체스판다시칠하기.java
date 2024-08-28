package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1018_체스판다시칠하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] board = new char[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		int result = Integer.MAX_VALUE; 
		for(int i = 0; i <= N-8; i++) {
			for (int j = 0; j <= M-8; j++) {
//				char current = board[i][j];	// 현재 보드판의 출발점의 색상 
				int countW = 0;
				int countB = 0;
				for (int k = 0; k < 8; k++) {
					for (int l = 0; l < 8; l++) {
//						System.out.println(1);
						if(board[i+k][j+l] == 'W') countW++;
						else countB++;
					}
				}
				
				System.out.println(countW + " " + countB + " " + Math.abs(countW-countB));
				
				result = Math.min(result, Math.abs(countW-countB));
			}
		}
		
		System.out.println(result/2);
//		System.out.println(result);
		
		
		
		

	}

}
