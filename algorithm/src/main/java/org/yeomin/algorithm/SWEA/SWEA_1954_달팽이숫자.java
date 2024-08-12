package SWEA;

import java.util.Arrays;
import java.util.Scanner;


public class SWEA_1954_달팽이숫자 {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int[][] board;

	static boolean[][] check;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			int n = sc.nextInt();

			board = new int[n][n];
			
//			int temp = 1;
//			for(int i = 0; i < n; i++) {
//				for(int j = 0; j < n; j++) {
//					board[i][j] = temp++;
//				}
//			}
//			
			check = new boolean[n][n];

			// 달팽이가 이제 채워나간다~
			for(int d = 0, i = 0, j = -1, l=0;  ;) {
				int nx = i + dx[d];
				int ny = j + dy[d];
				
				// 경계를 벗어나면 방향 전환
				if(nx >= n || nx < 0 || ny >= n || ny <0 || check[nx][ny]) {
					d = (d+1)%4;
					continue;
				}
				// 경계 내부면 진행
				else {
					board[nx][ny] = l+1;
					check[nx][ny] = true;
					i = nx;
					j = ny;
					l++;
					if(l == n*n) {
						break;
					}
				}
			}
			sb.append("#").append(t+1 + "\n");
			for(int i = 0; i < n; i ++) {
				for(int j = 0; j < n; j++) {
					sb.append(board[i][j] + " ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}

}
