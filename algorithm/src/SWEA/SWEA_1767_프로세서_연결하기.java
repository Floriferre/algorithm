package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서_연결하기 {
	static class Core {
		int x;
		int y;

		/**
		 * @param x
		 * @param y
		 */
		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int N;
	static int[][] maxynos;
	static int minWireLength, maxCore; // 최소 전선의 길이, 코어 수
	static ArrayList<Core> cores = null; // 프로세서 좌표 넣을곳

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 개수

		for (int t = 1; t < T + 1; t++) { // 테스트 케이스만큼 반복
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 배열의 크기 N

			cores = new ArrayList<>(); // 코어 저장해둘 리스트

			maxynos = new int[N][N]; // 맥시노스 선언
			for (int i = 0; i < N; i++) { // 맥시노스 정보 받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maxynos[i][j] = Integer.parseInt(st.nextToken());
					if (maxynos[i][j] == 1) { // 코어의 위치 기록
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1)
							continue; // 가장자리 코어는 기록X
						cores.add(new Core(j, i));	// 가로 세로 입력 주의할 것 
					}
				}
			}
			// 변수 초기화
			minWireLength = Integer.MAX_VALUE;
			maxCore = Integer.MIN_VALUE;

			dfs(0, 0, 0);

			sb.append("#" + t + " " + minWireLength + "\n");
		}
		System.out.println(sb);

	}

	public static void dfs(int idx, int coreCnt, int wireCnt) {
		if (idx == cores.size()) { // 코어 다 썼으면
			if (maxCore < coreCnt) { // 최대한 많은 core에 연결
				maxCore = coreCnt;
				minWireLength = wireCnt;
			} else if (maxCore == coreCnt) { // 전선길이의 합이 최소가 되는 값
				minWireLength = Math.min(wireCnt, minWireLength);
			}
			return;
		}

		// 인덱스 위치의 코어의 좌표
		int x = cores.get(idx).x;
		int y = cores.get(idx).y;

		// 상 하 좌 우 탐색
		for (int dir = 0; dir < 4; dir++) {
			int count = 0, nx = x, ny = y;

			while (true) {
				nx += dx[dir];
				ny += dy[dir];

				// 범위를 벗어났다 is 다른코어나 전선을 만나지 않음
				if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
					break;
				}

				// 가는 길에 다른 코어 혹은 전선 존재 -> 다른 방향으로
				if (maxynos[ny][nx] == 1) {
					count = 0;
					break;
				}

				// 어떠한 방해도 없다면 +1
				count++;
			}

			// count 갯수만큼 1로 채워줌
			int fill_x = x;
			int fill_y = y;

			for (int i = 0; i < count; i++) {
				fill_x += dx[dir];
				fill_y += dy[dir];
				maxynos[fill_y][fill_x] = 1;
			}

			if (count == 0)
				dfs(idx + 1, coreCnt, wireCnt);
			else {
				dfs(idx + 1, coreCnt + 1, wireCnt + count);

				// 원본맵을 다시 0으로 되돌림
				fill_x = x;
				fill_y = y;

				for (int i = 0; i < count; i++) {
					fill_x += dx[dir];
					fill_y += dy[dir];
					maxynos[fill_y][fill_x] = 0;
				}
			}
		}
	}
}
