package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.security.auth.DestroyFailedException;

/*
@author 정여민
@since 2023. 9. 26.
@see https://www.acmicpc.net/problem/2239
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	스도쿠. 가로, 세로, 3x3 보드가 전부 1~9까지 숫자가 하나만 들어가야하는 게임 
*	하다만 스도쿠 퍼즐이 주어졌을 때, 마저 끝내는 프로그램 작성
*
*	<입력>
*	9개 줄에 9개의 숫자로 보드가 입력
*	아직 채워지지 않은 칸에는 0이 주어짐
*
*	<출력>
*	9개의 줄에 9개의 숫자
*	답이 여러개 있으면 사전식으로 앞서는 것을 출력. 81자리 수가 제일 작은 경우 
*
*
*
*/

public class BJ_2239_스도쿠 {

	static int[][] map = new int[9][9];

	static ArrayList<point> zeros;

	static class point {
		int x, y;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		makeSudoku(map, 0, 0);

	}

	private static void makeSudoku(int[][] map, int x, int y) {

		if (y == 9) {
			makeSudoku(map, x + 1, 0);
			return;
		}

		if (x == 9) { // 재귀 종료

			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(map[i][j] + "");
				}
				sb.append("\n");
			}
			
			System.out.println(sb);
			
			System.exit(0);
		}

		if (map[x][y] == 0) {

			for (int i = 1; i < 10; i++) {
				if(check(x, y, i)) {
					map[x][y] = i;
					makeSudoku(map, x, y+1);
				}
			}
			map[x][y] = 0;
			return;
		} 
		makeSudoku(map, x, y+1);
	}

	public static boolean check(int row, int col, int value) {

		// 같은열에 무슨 숫자가 비었는지
		for (int i = 0; i < 9; i++) {
			if (map[row][i] == value) {
				return false;
			}
		}

		// 같은행에 무슨 숫자가 비었는지
		for (int i = 0; i < 9; i++) {
			if (map[i][col] == value) {
				return false;
			}
		}

		// 같은 네모에 뭐가 있는지
		int ind_row = (row / 3) * 3;
		int ind_col = (col / 3) * 3;

		for (int i = ind_row; i < ind_row + 3; i++) {
			for (int j = ind_col; j < ind_col + 3; j++) {
				if (map[i][j] == value)
					return false;
			}
		}

		return true;
	}

	private static boolean checkSudoku(int[][] map) {
		Set<Integer> set = new HashSet<>();
		// 가로 체크
		for (int i = 0; i < 9; i++) {
			// 해시 셋으로 저장해서 판별
			set = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				set.add(map[i][j]);
			}
			if (set.size() != 9)
				return false;
		}

		System.out.println("가로 완료");

		// 세로 체크
		for (int i = 0; i < 9; i++) {
			// 해시 셋으로 저장해서 판별
			set = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				set.add(map[j][i]);
			}
			if (set.size() != 9)
				return false;
		}
		System.out.println("새로 완료");

		// 9칸씩 체크
		for (int i = 0; i < 9; i = +3) {
			// 해시 셋으로 저장해서 판별
			set = new HashSet<>();
			for (int n = 0; n < 9; n += 3) {
				for (int j = n; j < n + 3; j++) {
					set.add(map[i][j]);
				}
				if (set.size() != 9)
					return false;
			}
		}

		// 9칸씩 체크
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				set = new HashSet<>();
				for (int r = i; r < i + 3; r++) {
					for (int c = j; c < j + 3; c++) {
						set.add(map[r][c]);
					}
				}
				if (set.size() != 9)
					return false;
			}
		}

		return true;
	}

}
