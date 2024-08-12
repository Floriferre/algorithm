package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10026_적록색약 {

	static char[][] board;
	static boolean[][] visited;
	static int countRGB, countRB, N;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		board = new char[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j);
			}
		}

		visited = new boolean[N][N];

		// 적록 색약 아닌 사람
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					DFS(i, j);
					countRGB++;
				}
			}
		}

		visited = new boolean[N][N];

		// 적록 색약인 사람 -> G를 R로 전부 바꿔버리기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 'G') {
					board[i][j] = 'R';
				}
			}
		}

		// 적록 색약 아닌 사람
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					DFS(i, j);
					countRB++;
				}
			}
		}
		System.out.println(countRGB + " " + countRB);
	}

	private static void DFS(int x, int y) {
		visited[x][y] = true;
		char current_color = board[x][y];

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (!visited[nx][ny]) {
					if (board[nx][ny] == current_color) {
						DFS(nx, ny);
					}
				}
			}
		}
	}

}
