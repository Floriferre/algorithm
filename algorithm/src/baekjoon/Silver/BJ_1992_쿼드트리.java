package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1992_쿼드트리 {

	static char[][] tree;

	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		tree = new char[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < N; j++) {
				tree[i][j] = s.charAt(j);
			}
		}
		checkBoard(0, 0, N);

		System.out.println(sb);

	}

	private static void checkBoard(int r, int c, int size) {
		char check = tree[r][c];
		boolean flag = true;
		for (int i = r; i < r+size; i++) {
			for (int j = c; j < c+size; j++) {
				if(check != tree[i][j]) {
					int half = size / 2;
					sb.append("(");
					checkBoard(r, c, half);
					checkBoard(r, c + half, half);
					checkBoard(r + half, c, half);
					checkBoard(r + half, c + half, half);
					sb.append(")");		
					return;
				}
			}
		}
		if(check == '0') sb.append(0);
		else sb.append(1);

	}

}
